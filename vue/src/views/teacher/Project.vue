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
                    <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                    <el-popconfirm
                            class="ml-5"
                            confirm-button-text='确定'
                            cancel-button-text='我再想想'
                            icon="el-icon-info"
                            icon-color="red"
                            title="您确定删除吗？"
                            @confirm="del(scope.row.pnum)"
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
        <el-dialog title="运动员信息" :visible.sync="dialogFormVisible" width="30%" >
            <el-form label-width="90px" size="small">
                <el-form-item label="项目名称">
                    <div class="block">
                        <el-cascader
                                v-model="form.pname"
                                :options="options">
                        </el-cascader>
                    </div>
                </el-form-item>
                <el-form-item label="开始时间">
                    <div class="block">
                        <el-date-picker
                                v-model="form.ptime"
                                type="date"
                                value-format="yyyy-MM-dd"
                                placeholder="选择日期">
                        </el-date-picker>
                    </div>
                </el-form-item>
                <el-form-item label="比赛地点">
                    <template>
                        <el-select v-model="form.space"  placeholder="请选择">
                            <el-option
                                    v-for="item in spaces"
                                    :label="item.value"
                                    :value="item.value">
                                <span style="float: left">{{ item.value }}</span>
                            </el-option>
                        </el-select>
                    </template>
                </el-form-item>
                <el-form-item label="比赛名额">
                    <el-input-number  v-model="form.count"  :min="1" :max="30" :step="5" label="描述文字"></el-input-number>
                </el-form-item>
                <el-form-item label="负责人姓名">
                    <template>
                        <el-select v-model="form.teacher" value-key="tnum" placeholder="请选择">
                            <el-option
                                    v-for="item in teachers"
                                    :key="item.tnum"
                                    :label="item.tname"
                                    :value="item">
                                <span style="float: left">{{ item.tname }}</span>
                                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.email }}</span>
                            </el-option>
                        </el-select>
                    </template>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </div>
        </el-dialog>
        <!--更新-->
        <el-dialog title="项目信息" :visible.sync="updateFrom" width="30%" >
            <el-form label-width="90px" size="small">
                <el-form-item label="项目编号">
                    <el-input readonly v-model="form.pnum" disabled autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="项目名称">
                    <el-input v-model="form.pname" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="开始时间">
                    <el-input v-model="form.ptime" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="比赛地点">
                    <el-input v-model="form.space" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="项目名额">
                    <el-input v-model="form.count" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="项目负责人">
                    <template>
                        <el-select v-model="form.teacher" value-key="tnum" placeholder="请选择">
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
        name: "Project.vue",
        data(){
            return{
                dataTable:[],
                teachers:[],
                options:[
                    {
                        value:'男子',
                        label:'男子',
                        children:[
                            {
                                value:'100米',
                                label:'100米',
                            },
                            {
                                value:'200米',
                                label:'200米',
                            },
                            {
                                value:'400米',
                                label:'400米',
                            },
                            {
                                value:'800米',
                                label:'800米',
                            },
                            {
                                value:'1000米',
                                label:'1000米',
                            },
                            {
                                value:'1600米',
                                label:'1600米',
                            },
                            {
                                value:'3000米',
                                label:'3000米',
                            },
                            {
                                value:'跳高',
                                label:'跳高',
                            },
                            {
                                value:'跳远',
                                label:'跳远',
                            },
                            {
                                value:'三级跳',
                                label:'三级跳',
                            },
                            {
                                value:'铅球',
                                label:'铅球',
                            },
                            {
                                value:'举重',
                                label:'举重',
                            },
                            {
                                value:'体操',
                                label:'体操',
                            },
                        ]
                    },
                    {
                        value:'女子',
                        label:'女子',
                        children:[
                            {
                                value:'100米',
                                label:'100米',
                            },
                            {
                                value:'200米',
                                label:'200米',
                            },
                            {
                                value:'400米',
                                label:'400米',
                            },
                            {
                                value:'800米',
                                label:'800米',
                            },
                            {
                                value:'1000米',
                                label:'1000米',
                            },
                            {
                                value:'1600米',
                                label:'1600米',
                            },
                            {
                                value:'3000米',
                                label:'3000米',
                            },
                            {
                                value:'跳高',
                                label:'跳高',
                            },
                            {
                                value:'跳远',
                                label:'跳远',
                            },
                            {
                                value:'三级跳',
                                label:'三级跳',
                            },
                            {
                                value:'铅球',
                                label:'铅球',
                            },
                            {
                                value:'举重',
                                label:'举重',
                            },
                            {
                                value:'体操',
                                label:'体操',
                            },
                        ]
                    },
                ],
                spaces:[
                    {
                        value:'新田径场'
                    },
                    {
                        value:'体育馆'
                    },
                    {
                        value:'篮球场'
                    },
                    {
                        value:'旧田径场'
                    },
                ],
                teacher:[],
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
            this.load()
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
                this.updateFrom = true
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
            save() {
                console.log(this.form)
                this.form.pname = this.form.pname[0]+this.form.pname[1]
                this.request.post("/teacher/add", this.form).then(res => {
                    if (res.code === '200') {
                        this.$message.success("保存成功")
                        this.dialogFormVisible = false
                        this.load()
                    } else {
                        this.$message.error("保存失败")
                    }
                })
            },
            del(pnum) {
                this.request.delete("/teacher/" + pnum).then(res => {
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
