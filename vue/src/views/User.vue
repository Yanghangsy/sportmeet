<template>
    <div>

        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search"  v-model="tname" ></el-input>
            <el-button class="ml-5" type="primary" @click="load" >搜索</el-button>
        </div>
        <div style="margin: 10px 0">
            <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-button type="primary">批量删除 <i class="el-icon-remove-outline"></i></el-button>
            <el-button type="primary">导入 <i class="el-icon-bottom"></i></el-button>
            <el-button type="primary">导出 <i class="el-icon-top"></i></el-button>
        </div>

        <el-table :data="dataTable" border stripe header-cell-class-name="headerBg">
            <el-table-column prop="tnum" label="教师编号" width="80"></el-table-column>
            <el-table-column prop="tname" label="姓名" width="80"></el-table-column>
            <el-table-column prop="tsex" label="性别" width="80">
                <template slot-scope="scope">{{ scope.row.tsex === 1 ? '男' : '女' }}</template>
            </el-table-column>
            <el-table-column prop="email" label="邮箱"></el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                    <el-popconfirm
                            class="ml-5"
                            confirm-button-text='确定'
                            cancel-button-text='我再想想'
                            icon="el-icon-info"
                            icon-color="red"
                            title="您确定删除吗？"
                            @confirm="del(scope.row.tnum)"
                    >
                        <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
                    </el-popconfirm>
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
      <!--   增加     -->
        <el-dialog title="教师信息" :visible.sync="dialogFormVisible" width="30%" >
            <el-form label-width="80px" size="small">
                <el-form-item label="教师编号">
                    <el-input v-model="form.tnum" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="教师姓名">
                    <el-input v-model="form.tname" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="教师性别">
                    <el-input v-model="form.tsex"  autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input v-model="form.email" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </div>
        </el-dialog>
      <!--更新-->
        <el-dialog title="教师信息" :visible.sync="updateFrom" width="30%" >
            <el-form label-width="80px" size="small">
                <el-form-item label="教师编号">
                    <el-input readonly v-model="form.tnum" disabled autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="教师姓名">
                    <el-input v-model="form.tname" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="教师性别">
                    <el-input v-model="form.tsex===1?'男':'女'" disabled  autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input v-model="form.email" autocomplete="off"></el-input>
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
        name: "User.vue",
        data(){
            return{
                dataTable:[],
                total:0,
                pageNum:1,
                pageSize:5,
                tnum:'',
                tsex:'',
                tname:'',
                email:'',
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
                this.request.get("/admin/teacher/getAll",{
                    params:{
                        pageNum:this.pageNum,
                        pageSize:this.pageSize,
                        tname: this.tname,
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
           handleAdd(){
               this.dialogFormVisible = true
               this.form = {}
           },
            handleEdit(row) {
                this.form = JSON.parse(JSON.stringify(row))
                this.updateFrom = true
            },
            update(){
                this.form.tsex = this.form.tsex==='男'?1:0
                this.request.post("/admin/teacher/update", this.form).then(res => {
                    if (res.code === '200') {
                        this.$message.success("保存成功")
                        this.updateFrom = false
                        this.load()
                    } else {
                        this.$message.error("保存失败")
                    }
                })
            },
            save() {
                console.log(this.form)
                this.form.tsex = this.form.tsex==='男'?1:0
                this.request.post("/admin/teacher/add", this.form).then(res => {
                    if (res.code === '200') {
                        this.$message.success("保存成功")
                        this.dialogFormVisible = false
                        this.load()
                    } else {
                        this.$message.error("保存失败")
                    }
                })
            },
            del(tnum) {
                this.request.delete("/admin/teacher/" + tnum).then(res => {
                    if (res.code === '200') {
                        this.$message.success("删除成功")
                        this.load()
                    } else {
                        this.$message.error("删除失败")
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
