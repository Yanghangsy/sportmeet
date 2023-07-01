<template>
    <div style="font-size: 12px; border-bottom: 1px solid #ccc;line-height:60px; display: flex ">
        <div style="flex: 1;font-size: 18px">
            <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>

            <el-breadcrumb separator="/" style="display: inline-block;margin-left: 10px">
                <el-breadcrumb-item :to="'/home'" >首页</el-breadcrumb-item>
                <el-breadcrumb-item >{{currentPathName}}</el-breadcrumb-item>
            </el-breadcrumb>


        </div>
        <el-dropdown style="width: 150px;cursor: pointer; text-align: right">
            <span>{{user.userid}}</span><i class="el-icon-arrow-down" style="margin-right: 5px"></i>
            <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
                <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                    <span style="text-decoration: none" @click="logout">退出</span>
                </el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
    </div>
</template>

<script>
    export default {
        name: "athleteHeader",
        props:{
            collapseBtnClass:String,
            collapse:'',
            user:Object
        },
        computed:{
            currentPathName(){
                return this.$store.state.currentPathName;
            }
        },
        //监听路由变化
        watch:{
            currentPathName:function(newVal,oldVal){
                console.log(newVal)
            }
        },
        methods:{
            logout() {
                this.$store.commit("logout")
                this.$message.success("退出成功")
            }
        }

    }
</script>

<style scoped>

</style>