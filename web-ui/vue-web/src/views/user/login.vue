<template>
  <div class="main">
    <svg-icon slot="prefix" class="QRcodeIcon" icon-class="QRcode"  />
    <el-form id="formLogin" ref="loginForm" class="user-layout-login" :model="loginForm" :rules="loginRules">
      <h2 class="mt10 mb15">用户登录</h2>
      <el-form-item prop="username">
        <el-input v-model="loginForm.username" size="large" placeholder="账户: 123" >
          <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model="loginForm.password" show-password type="password" auto-complete="off" placeholder="密码" @keyup.enter.native="handleLogin">
          <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
        </el-input>
      </el-form-item>
      <el-row :gutter="16">
        <el-col class="gutter-row" :span="16">
          <el-form-item prop="code" v-if="captchaOnOff">
            <el-input v-model="loginForm.code" size="large" @keyup.enter.native="handleLogin"  type="text" autocomplete="off" placeholder="验证码">
              <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon" />
            </el-input>
          </el-form-item>
        </el-col>
        <el-col class="gutter-row" :span="8">
          <img class="getCaptcha"  :src="codeUrl" @click="getCode">
        </el-col>
      </el-row>
      <el-form-item>
        <el-checkbox v-model="loginForm.rememberMe">记住密码</el-checkbox>
        <div style="float: right;line-height: 30px;">
          还没有账号？
          <el-link > 立即注册&nbsp;</el-link>
          <el-link > 忘记密码</el-link>
        </div>
      </el-form-item>
      <el-form-item style="margin-top:24px">
        <el-button
          :loading="loading"
          size="medium"
          type="primary"
          style="width:100%;"
          @click.native.prevent="handleLogin"
        >
          <span v-if="!loading">登 录</span>
          <span v-else>登 录 中...</span>
        </el-button>
      </el-form-item>
      <el-row>
        <div style="margin-top: -10px;">其它登录方式</div>
        <div class="icons-list">
          <svg-icon slot="prefix" class="dingtalk" icon-class="dingtalk"  />
          <svg-icon slot="prefix" class="WeChat" icon-class="WeChat" />
          <svg-icon slot="prefix" class="Alipay" icon-class="Alipay"  />
          <svg-icon slot="prefix" class="Sina" icon-class="Sina" />
        </div>
      </el-row>
    </el-form>
  </div>
</template>
<script>
  import { getCodeImg } from "@/api/login";
  import Cookies from "js-cookie";
  import { encrypt, decrypt } from '@/utils/jsencrypt'

  export default {
    name: "Login",
    data() {
      return {
        codeUrl: "",
        cookiePassword: "",
        loginForm: {
          username: "admin",
          password: "admin123",
          rememberMe: false,
          code: "",
          uuid: ""
        },
        loginRules: {
          username: [
            { required: true, trigger: "blur", message: "请输入您的账号" }
          ],
          password: [
            { required: true, trigger: "blur", message: "请输入您的密码" }
          ],
          code: [{ required: true, trigger: "change", message: "请输入验证码" }]
        },
        loading: false,
        // 验证码开关
        captchaOnOff: true,
        // 注册开关
        register: false,
        redirect: undefined
      };
    },
    watch: {
      $route: {
        handler: function(route) {
          this.redirect = route.query && route.query.redirect;
        },
        immediate: true
      }
    },
    created() {
      this.getCode();
      this.getCookie();
    },
    methods: {
      getCode() {
        getCodeImg().then(res => {
          this.captchaOnOff = res.captchaOnOff === undefined ? true : res.captchaOnOff;
          if (this.captchaOnOff) {
            this.codeUrl = "data:image/gif;base64," + res.img;
            this.loginForm.uuid = res.uuid;
          }
        });
      },
      getCookie() {
        const username = Cookies.get("username");
        const password = Cookies.get("password");
        const rememberMe = Cookies.get('rememberMe')
        this.loginForm = {
          username: username === undefined ? this.loginForm.username : username,
          password: password === undefined ? this.loginForm.password : decrypt(password),
          rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
        };
      },
      handleLogin() {
        this.$refs.loginForm.validate(valid => {
          if (valid) {
            this.loading = true;
            if (this.loginForm.rememberMe) {
              Cookies.set("username", this.loginForm.username, { expires: 30 });
              Cookies.set("password", encrypt(this.loginForm.password), { expires: 30 });
              Cookies.set('rememberMe', this.loginForm.rememberMe, { expires: 30 });
            } else {
              Cookies.remove("username");
              Cookies.remove("password");
              Cookies.remove('rememberMe');
            }
            this.$store.dispatch("Login", this.loginForm).then(() => {
              this.$router.push({ path: this.redirect || "/" }).catch(()=>{});
            }).catch(() => {
              this.loading = false;
              if (this.captchaOnOff) {
                this.getCode();
              }
            });
          }
        });
      }
    }
  };
</script>

<style lang="less" scoped>
  .QRcodeIcon{
    position: absolute;
    right:0;
    font-size: 32px;
    color: #1890ff;
    margin-top:5px;
    margin-right:5px;
  }
  .ant-space-align-center{
    color:#8f959e;
    line-height: 30px;
    height: 30px;
  }
  .user-layout-login label{
    font-size: 12px!important;
  }
.user-layout-login {
  label {
    font-size: 14px;
  }
  .ant-page-header{
    padding:60px 0 45px 0;
  }
  .getCaptcha {
    display: block;
    width: 100%;
    height: 40px;
  }

  button.login-button {
    padding: 0 15px;
    font-size: 16px;
    height: 40px;
    width: 100%;
    border-radius: 4px;
  }
  .icons-list{
    .anticon {
      font-size: 30px;
      width: 40px;
    }
    .dingtalk{
      color: #0089FF;
    }
    .WeChat{
      color: #51C332;
    }
    .Alipay{
      color: #06B4FD;
    }
    .Sina{
      color: #D81E06;
    }
  }
}

</style>
