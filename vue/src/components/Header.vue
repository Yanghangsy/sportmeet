<template>
   <div style="font-size: 12px; border-bottom: 1px solid #ccc;line-height:60px; display: flex ">
       <div style="flex: 1;font-size: 18px">
           <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>

               <el-breadcrumb separator="/" style="display: inline-block;margin-left: 10px">
                   <el-breadcrumb-item :to="'/'" >首页</el-breadcrumb-item>
                   <el-breadcrumb-item >{{currentPathName}}</el-breadcrumb-item>
               </el-breadcrumb>


       </div>
       <el-dropdown style="width: 70px;cursor: pointer">
           <span>{{user.userid}}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
           <el-dropdown-menu slot="dropdown">
<!--               <el-dropdown-item>个人信息</el-dropdown-item>-->
               <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                   <span style="text-decoration: none" @click="logout">退出</span>
               </el-dropdown-item>
           </el-dropdown-menu>
       </el-dropdown>
   </div>
</template>

<script>
    export default {
        name: "Header.vue",
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