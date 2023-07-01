
<template>
    <div>
        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search"  v-model="aname" ></el-input>
            <el-button class="ml-5" type="primary" @click="load" >搜索</el-button>
        </div>
        <div style="margin: 10px 0">
            <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-button type="primary">批量删除 <i class="el-icon-remove-outline"></i></el-button>
            <el-button type="primary">导入 <i class="el-icon-bottom"></i></el-button>
            <el-button type="primary">导出 <i class="el-icon-top"></i></el-button>
        </div>
        <span hidden v-model="user_id=user.userid" >{{user.userid}}</span>
        <el-table :data="dataTable" border stripe header-cell-class-name="headerBg">
            <el-table-column prop="pnum" label="项目编号" width="100"></el-table-column>
            <el-table-column prop="pname" label="项目名称" width="100"></el-table-column>
            <el-table-column prop="ptime" label="开始时间" width="100"></el-table-column>
            <el-table-column prop="space" label="比赛地点" width="80"></el-table-column>
            <el-table-column prop="count" label="项目名额" width="80"></el-table-column>
            <el-table-column prop="teacher.tname" label="负责人姓名" width="90"></el-table-column>
            <el-table-column prop="teacher.email" label="邮箱" width="150"></el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button type="success" @click="handleEdit(scope.row)">报名 <i class="el-icon-edit"></i></el-button>
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

        <!--更新-->
        <el-dialog title="项目信息" :visible.sync="updateFrom" width="30%" >
            <el-form label-width="90px" size="small">
                <el-form-item label="项目编号">
                    <el-input readonly v-model="form.pnum" disabled autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="项目名称">
                    <el-input v-model="form.pname" disabled autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="开始时间">
                    <el-input v-model="form.ptime" disabled autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="比赛地点">
                    <el-input v-model="form.space" disabled autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="项目名额">
                    <el-input v-model="form.count" disabled autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="项目负责人">
                    <template>
                        <el-select v-model="form.teacher" disabled value-key="tnum" placeholder="请选择">
                            <el-option
                                    v-for="item in teachers"
                                    :key="item.tnum"
                                    :label="item.tname"
                                    :value="item">
                                <span style="float: left">{{ item.tname }}</span>
                            </el-option>
                        </el-select>
                    </template>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="update">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "Grade.vue",
        data(){
            return{
                dataTable:[],
                teachers:[],
                user_id:'',
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
                teacher:{},
                form:{},
                dialogFormVisible:false,
                updateFrom:false,
                multipleSelection:[],
                headBg:'headBg'
            }
        },
        created() {
            //请求分页查询数据
            this.user_id = this.user.userid
            this.load()
        },
        props:{
          user:Object
        },
        methods:{
            load(){
                this.request.get("/teacher/getProjects",{
                    params:{
                        pageNum:this.pageNum,
                        pageSize:this.pageSize,
                        pname: this.pname,
                    }
                }).then( res => {
                    console.log(this.user_id)
                    this.dataTable = res.data.data
                    this.total = res.data.total
                    this.projects = res.data.projects
                    this.teachers = res.data.teachers
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

            handleAdd(){
                this.dialogFormVisible = true
                this.form = {}
            },
            handleEdit(row) {
                this.form = JSON.parse(JSON.stringify(row))
                this.updateFrom = false
                this.request.get("/athlete/event",
                    {
                        params: {
                            anum:this.user_id,
                            pnum: this.form.pnum
                        }
                    }
                ).then(res => {
                    this.msg = res.msg
                    if (res.code === '200') {
                        this.$message.success("报名成功")
                        this.updateFrom = false
                        this.load()
                    } else {
                        this.$message.error(this.msg)
                    }
                })
            },
            update(){
                this.request.post("/teacher/update", this.form).then(res => {
                    if (res.code === '200') {
                        this.$message.success("保存成功")
                        this.updateFrom = false
                        this.load()
                    } else {
                        this.$message.error("保存失败")
                    }
                })
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
