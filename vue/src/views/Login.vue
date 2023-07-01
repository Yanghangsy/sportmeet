<template>
  <div class="wrapper">
      <div class="title">
        大学生运动会管理系统
      </div>
    <div
        style="margin: 140px auto;  width: 350px; height: 300px; padding: 20px;
         border-radius: 10px; border: black 1px solid">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>登 录</b></div>
      <el-form :model="user" :rules="rules" ref="userForm">
        <el-form-item prop="userid">
          <el-input size="medium" style=" margin: 10px 0" prefix-icon="el-icon-user" v-model="user.userid"></el-input>
        </el-form-item>
        <el-form-item prop="pwd">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password
                    v-model="user.pwd"></el-input>
        </el-form-item>

        <el-form-item style="margin-left: 120px">
          <el-button type="primary" size="small" autocomplete="off" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>

  export default {
  name: "Login",
  data() {
    return {
      user: {},
      userid:'',
      pwd:'',
      prem:'',
      code:'',
      rules: {
        userid: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
        pwd: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
        ],

      }
    }
  },
    methods: {
    login() {
      this.$refs['userForm'].validate((valid) => {

        if (valid) {  // 表单校验合法
          this.request.post("/login", this.user).then(res => {
            if (res.code === '200') {
              localStorage.setItem("user", JSON.stringify(res.data))  // 存储用户信息到浏览器
              localStorage.setItem("menus", JSON.stringify(res.data.menus))  // 存储用户信息到浏览器
              // 动态设置当前用户的路由
              this.$message.success("登录成功")

              if (res.data.perm === 'perm:ath') {
                console.log('ath'+res.data.perm)
                this.$router.push("/athlete")
              } else if (res.data.perm === 'perm:tea') {
                this.$router.push("/teacher")
              }else {
                this.$router.push("/admin")
                console.log('admin'+res.data.perm)
              }
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      });
    },

  }
}
</script>

<style>
.wrapper {
  height: 100vh;
  /*background-image: linear-gradient(to bottom right, #FC466B, #3F5EFB);*/
  overflow: hidden;
  background-image: url("../../public/1.png");
  background-repeat: no-repeat;
  background-position: center;
}
.title{
  text-align: center;
  margin: 40px auto;
  border: 6px black double ;
  font-size:50px ;
  width: 1000px;
  color: brown;
  }
.el-form-item__content{
  display: flex;
  align-content: center;
}
</style>
