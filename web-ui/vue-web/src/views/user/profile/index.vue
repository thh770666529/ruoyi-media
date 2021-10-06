<template>
  <div class="app-container">
    <el-row :gutter="24">
      <el-col :span="18" :xs="24">
        <el-card class="box-card">
          <el-tabs v-model="activeTab" tab-position="left">
            <el-tab-pane label="基本资料"   name="userinfo">
              <span slot="label"><i class="el-icon-user-solid"></i> 基本资料</span>
              <userInfo :user="user" />
            </el-tab-pane>
            <el-tab-pane label="我的收藏" name="MyCollect">
              <span slot="label"><i class="el-icon-collection-tag"></i> 我的收藏</span>
              <el-button type="danger" class="mb20" size="mini" @click="close">关闭</el-button>
              <el-tabs type="border-card">
                <el-tab-pane label="電影">
                  <userCollect tableName="wm_movie"></userCollect>
                </el-tab-pane>
                <el-tab-pane label="博客">
                  <userCollect tableName="blog_article"></userCollect>
                </el-tab-pane>
                <el-tab-pane label="演员">
                  <userCollect tableName="wm_actor"></userCollect>
                </el-tab-pane>
                <el-tab-pane label="问答">
                  <userCollect tableName="qa_question"></userCollect>
                </el-tab-pane>
              </el-tabs>
            </el-tab-pane>
            <el-tab-pane label="修改密码" name="resetPwd">
              <span slot="label"><i class="el-icon-s-tools"></i> 修改密码</span>
              <resetPwd :user="user" />
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
      <el-col :span="6" :xs="24">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>个人信息</span>
          </div>
          <div>
            <div class="text-center">
              <userAvatar :user="user" />
            </div>
            <ul class="list-group list-group-striped">
              <li class="list-group-item">
                <svg-icon icon-class="user" />用户名称
                <div class="pull-right">{{ user.userName }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="phone" />手机号码
                <div class="pull-right">{{ user.phonenumber }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="email" />用户邮箱
                <div class="pull-right">{{ user.email }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="tree" />所属部门
                <div class="pull-right" v-if="user.dept">{{ user.dept.deptName }} / {{ postGroup }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="peoples" />所属角色
                <div class="pull-right">{{ roleGroup }}</div>
              </li>
              <li class="list-group-item">
                <svg-icon icon-class="date" />创建日期
                <div class="pull-right">{{ user.createTime }}</div>
              </li>
            </ul>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import userAvatar from "./userAvatar";
import userInfo from "./userInfo";
import resetPwd from "./resetPwd";
import userCollect from "./userCollect";
import { getUserProfile } from "@/api/system/user";

export default {
  name: "Profile",
  components: { userAvatar, userInfo, resetPwd, userCollect },
  data() {
    return {
      user: {},
      roleGroup: {},
      postGroup: {},
      activeTab: "userinfo"
    };
  },
  created() {
    this.getUser();
  },
  methods: {
    close() {
      this.$router.push({ path: "/" });
    },
    getUser() {
      getUserProfile().then(response => {
        this.user = response.data;
        this.roleGroup = response.roleGroup;
        this.postGroup = response.postGroup;
      });
    }
  }
};
</script>
<style scoped>
  .app-container {
    padding: 20px;
    width: 1300px;
    margin: auto;
  }

  .list-group {
    padding-left: 0px;
    list-style: none;
  }

  .list-group-item {
    border-bottom: 1px solid #e7eaec;
    border-top: 1px solid #e7eaec;
    margin-bottom: -1px;
    padding: 11px 0px;
    font-size: 13px;
  }

  .pull-right {
    float: right !important;
  }
</style>
