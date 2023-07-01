<template>
    <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
             background-color="rgb(130,57,53)"
             text-color="#000"
             active-text-color="#363328"
             :collapse-transition="false"
             :collapse="isCollapse"
             router


    >
        <div style="height: 60px;line-height: 60px;text-align: center ">
            <img src="../assets/logo.png" style="width: 40px;position: relative;top: 5px;margin-right: 5px;margin-top: 5px">
            <b style="color: white" v-show="logoTextShow">大学生运动会</b>
        </div>
        <el-menu-item index="/qualification">
            <i class="el-icon-s-check"></i>
            <span slot="title">运动员资质审核</span>
        </el-menu-item>
        <el-menu-item index="/record">
            <i class="el-icon-s-data"></i>
            <span slot="title">成绩登记</span>
        </el-menu-item>
        <el-menu-item index="/project">
            <i class="el-icon-s-management"></i>
            <span slot="title">项目信息</span>
        </el-menu-item>
        <el-menu-item index="/tperson">
            <i class="el-icon-message"></i>
            <span slot="title">个人信息</span>
        </el-menu-item>
        <el-menu-item index="/tpassword">
            <i class="el-icon-unlock"></i>
            <span slot="title">修改密码</span>
        </el-menu-item>
    </el-menu>
</template>

<script>
    export default {
        name: "teacherAside.vue",
        props:{
            isCollapse:Boolean,
            logoTextShow:Boolean
        },
    }
</script>

<style scoped>

</style>