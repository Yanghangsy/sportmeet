<template>
  <el-card style="width: 500px;">
    <el-form label-width="90px" size="small">
      <el-form-item label="运动员编号">
        <el-input v-model="form.anum" disabled autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="form.aname"disabled autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="年龄">
        <el-input v-model="form.age" disabled autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-input v-model="form.sex===1?'男':'女'" disabled autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="学院名称">
        <el-input  v-model="form.group.gname" disabled autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="紧急联系人">
        <el-input  v-model="form.group.leader" disabled autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
import {serverIp} from "../../../public/config";

export default {
  name: "Person",
  data() {
    return {
      serverIp: serverIp,
      form: {},
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    this.getUser().then(res => {
      console.log(res)
      this.form = res
    })
  },
  methods: {
    async getUser() {
      return (await this.request.get("/athlete/" + this.user.userid)).data
    },
    save() {
      this.request.post("/user", this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功")

          // 触发父级更新User的方法
          this.$emit("refreshUser")

          // 更新浏览器存储的用户信息
          this.getUser().then(res => {
            res.token = JSON.parse(localStorage.getItem("user")).token
            localStorage.setItem("user", JSON.stringify(res))
          })

        } else {
          this.$message.error("保存失败")
        }
      })
    },
    handleAvatarSuccess(res) {
      this.form.avatarUrl = res
    }
  }
}
</script>

<style>
  .el-card{
    margin: 0 auto;
  }
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
</style>
