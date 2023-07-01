<template>
    <div>
        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search"  v-model="aname" ></el-input>
            <el-button class="ml-5" type="primary" @click="load" >搜索</el-button>
        </div>
        <div style="margin: 10px 0">
            <el-button type="primary" >新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-button type="primary">批量删除 <i class="el-icon-remove-outline"></i></el-button>
            <el-button type="primary">导入 <i class="el-icon-bottom"></i></el-button>
            <el-button type="primary">导出 <i class="el-icon-top"></i></el-button>
        </div>
<!--        <span hidden v-model="anum=user.userid" >{{user.userid}}</span>-->
        <el-table :data="dataTable" border stripe header-cell-class-name="headerBg">
            <el-table-column prop="anum" label="运动员编号" width="180"></el-table-column>
            <el-table-column prop="aname" label="运动员姓名" width="120"></el-table-column>
            <el-table-column prop="pnum" label="项目编号" width="100"></el-table-column>
            <el-table-column prop="pname" label="项目名称" width="160"></el-table-column>
            <el-table-column prop="ptime" label="开始时间" width="160"></el-table-column>
            <el-table-column prop="space" label="比赛地点" width="110"></el-table-column>
            <el-table-column  prop="statu" label="审核状态" width="105">
                <template slot-scope="scope">
                    <el-tag v-if="scope.row.statu==='1'" type="warning">审核中</el-tag>
                    <el-tag v-else type="success">已审核</el-tag>
             </template>
            </el-table-column>
            <el-table-column prop="grade" label="比赛成绩" width="130">
                <template slot-scope="scope">
                    <el-tag v-if="scope.row.statu==='1'" type="info">无</el-tag>
                    <el-tag v-else type="info">{{scope.row.grade}}</el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="teacher.tname" label="负责人姓名" width="220"></el-table-column>
            <el-table-column prop="teacher.email" label="邮箱" width="220"></el-table-column>
        </el-table>
<!--        <div style="padding: 10px 0">-->
<!--            <el-pagination-->
<!--                    @size-change="handleSizeChange"-->
<!--                    @current-change="handleCurrentChange"-->
<!--                    :current-page=pageNum-->
<!--                    :page-sizes="[2,5, 10, 15, 20]"-->
<!--                    :page-size=pageSize-->
<!--                    layout="total, sizes, prev, pager, next, jumper"-->
<!--                    :total=total>-->
<!--            </el-pagination>-->
<!--        </div>-->
    </div>
</template>

<script>
    export default {
        name: "Grade.vue",
        data(){
            return{
                dataTable:[],
                grade:'',
                user_id:'',
                statu:'',
                teachers:[],
                teacher:[],
                count:'',
                select:{},
                groups:'',
                total:0,
                pageNum:1,
                pageSize:5,
                projects: {},
                aname:'',
                tnum:'',
                pnum:'',
                pname:'',
                ptime:'',
                space:'',
                anum:'',
                teacher:{},
                form:{},
                dialogFormVisible:false,
                updateFrom:false,
                multipleSelection:[],
                headBg:'headBg',
                user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
            }
        },
        props:{
            user:Object
        },
        created() {
            //请求分页查询数据
            this.load()
        },

        methods:{
            load(){
                this.request.get("/athlete/grade?anum="+this.user.userid,{
                }).then( res => {
                    this.dataTable = res.data.data
                    this.total = res.data.total
                    this.projects = res.data.projects
                    this.teachers = res.data.teachers
                })

            },
            getUserId(){
                let anum = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")).userid : ""
               return{
                    anum : anum
               }
            },
            handleSizeChange(pageSize){
                // console.log(pageSize)
                this.pageSize = pageSize;
                this.load()
            },
            handleCurrentChange(pageNum){
                // console.log(pageNum)
                this.pageNum = pageNum;
                this.load()
            },

        },

    }
</script>

<style>
    .el-select .el-input {
        width: 100px;
    }
    .headerBg{
        background-color: #ccc!important; ;
    }
    .el-table td,.el-table th{
        text-align: center !important;
    }
</style>
