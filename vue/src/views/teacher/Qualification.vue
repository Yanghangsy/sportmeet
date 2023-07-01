<template>
    <div>

        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search"  v-model="aname" ></el-input>
            <el-button class="ml-5" type="primary" @click="load" >搜索</el-button>
        </div>
        <div style="margin: 10px 0">
<!--            <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>-->
            <el-button type="primary">批量删除 <i class="el-icon-remove-outline"></i></el-button>
            <el-button type="primary">导入 <i class="el-icon-bottom"></i></el-button>
            <el-button type="primary">导出 <i class="el-icon-top"></i></el-button>
        </div>

        <el-table :data="dataTable" border stripe header-cell-class-name="headerBg">
            <el-table-column prop="anum" label="运动员编号" width="100"></el-table-column>
            <el-table-column prop="aname" label="姓名" width="80"></el-table-column>
            <el-table-column prop="pnum" label="项目编号" width="80"></el-table-column>
            <el-table-column prop="pname" label="项目名称" width="80"></el-table-column>
            <el-table-column prop="ptime" label="开始时间" width="100"></el-table-column>
            <el-table-column prop="space" label="参赛地点" width="80"></el-table-column>
            <el-table-column prop="status" label="审核状态" width="80">
                <el-tag type="warning">未审核</el-tag>
            </el-table-column>
            <el-table-column prop="teacher.tname" label="负责人姓名" width="100"></el-table-column>
            <el-table-column prop="teacher.email" label="负责人邮箱"></el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button type="success" @click="handleEdit(scope.row)">审核<i class="el-icon-s-claim"></i></el-button>
                </template>
            </el-table-column>
        </el-table>
        <div style="padding: 10px 0">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page=pageNum
                    :page-sizes="[2,5, 10, 15, 20]"
                    :page-size=pageSize
                    layout="total, sizes, prev, pager, next, jumper"
                    :total=total>
            </el-pagination>
        </div>
    </div>
</template>

<script>
    export default {
        name:"Qualification",
        data(){
            return{
                dataTable:[],
                projects:{},
                teachers:'',
                teacher: {},
                group:[],
                groups:[],
                total:0,
                pageNum:1,
                pageSize:5,
                anum:'',
                gnum:'',
                leader:'',
                gname:'',
                sex:'',
                aname:'',
                age:'',
                form:{},
                dialogFormVisible:false,
                updateFrom:false,
                multipleSelection:[],
                headBg:'headBg'
            }
        },
        created() {
            //请求分页查询数据
            this.load()
        },
        methods:{
            load(){
                this.request.get("/teacher/getSign",{
                    params:{
                        pageNum:this.pageNum,
                        pageSize:this.pageSize,
                    }
                }).then( res => {
                    this.dataTable = res.data.data
                    this.total = res.data.total
                })
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
            handleEdit(row) {
                this.form = JSON.parse(JSON.stringify(row))
                this.request.post("/teacher/audit", this.form).then(res => {
                    if (res.code === '200') {
                        this.$message.success("审核成功")
                        this.updateFrom = false
                        this.load()
                    } else {
                        this.$message.error("审核失败")
                    }
                })
            },


        },

    }
</script>

<style>
    .headerBg{
        background-color: #ccc!important; ;
    }
    .el-table td,.el-table th{
        text-align: center !important;
    }
</style>
