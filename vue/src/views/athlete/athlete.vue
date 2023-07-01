<template>
    <el-container style="min-height: 100vh; ">
        <el-aside :width="sideWidth+'px'" style="background-color: rgb(238, 241, 246);min-height: 100%;">
            <athleteAside :isCollapse="isCollapse" :logoTextShow="logoTextShow"/>
        </el-aside>

        <el-container>

            <el-header style="border-bottom: 1px solid #ccc">
                <athleteHeader :collapseBtnClass="collapseBtnClass" :collapse="collapse" :pathName="pathName" :user="user"/>
            </el-header>
            <el-main>
                <!--表示当前页面的子路由会在 router-view 展示-->
                <router-view @refreshUser="getUser" :user="user"/>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
   import athleteAside from "../../components/athleteAside";
   import athleteHeader from "../../components/athleteHeader";
    export default {
        name: 'athlete',
        data() {
            return {
                collapseBtnClass:'el-icon-s-fold',
                isCollapse:false,
                sideWidth:200,
                logoTextShow:true,
                pathName:'',
                user:{}
            }
        },
        components: {
            athleteAside,
            athleteHeader,
        },
        created() {
            // 从后台获取最新的User数据
            this.getUser()
        },  methods:{
            //点击收缩按钮收缩
            collapse(){
                this.isCollapse = !this.isCollapse;
                if (this.isCollapse){ //收缩
                    this.sideWidth = 64
                    this.collapseBtnClass = 'el-icon-s-unfold'
                    this.logoTextShow = false
                }else { //展开
                    this.sideWidth=200
                    this.collapseBtnClass = 'el-icon-s-fold'
                    this.logoTextShow = true
                }
            },
            getUser() {
                let userid = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")).userid : ""
                if (userid) {
                    // 从后台获取User数据
                    this.request.get("/admin/" + userid).then(res => {
                        // 重新赋值后台的最新User数据
                        this.user = res.data
                    })
                }
            }
        }
    }
</script>
