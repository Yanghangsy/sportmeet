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
        <el-menu-item index="/event">
            <i class="el-icon-user-solid"></i>
            <span slot="title">赛事报名</span>
        </el-menu-item>
        <el-menu-item index="/grade">
            <i class="el-icon-s-custom"></i>
            <span slot="title">成绩管理</span>
        </el-menu-item>
        <el-menu-item index="/person">
            <i class="el-icon-message"></i>
            <span slot="title">个人信息</span>
        </el-menu-item>
        <el-menu-item index="/password">
            <i class="el-icon-unlock"></i>
            <span slot="title">修改密码</span>
        </el-menu-item>
    </el-menu>
</template>

<script>
    export default {
        name: "athleteAside.vue",
        props:{
            isCollapse:Boolean,
            logoTextShow:Boolean
        },
    }
</script>

<style scoped>

</style>