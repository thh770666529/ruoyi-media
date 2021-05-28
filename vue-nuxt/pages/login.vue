<template>
  <div class="main">
    <div class="title">
      <a class="active" href="/login">登录</a>
      <span>·</span>
      <a href="/register">注册</a>
    </div>

    <div class="sign-up-container">
      <el-form ref="loginForm" :model="loginForm">
        <el-form-item class="input-prepend restyle" prop="username" :rules="loginRules">
          <div >
            <el-input type="text" placeholder="请输入手机号 或 数字编号登录" v-model="loginForm.username"/>
            <i class="iconfont icon-phone" />
          </div>
        </el-form-item>

        <el-form-item class="input-prepend" prop="password" :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]">
          <div>
            <el-input type="password" placeholder="密码" v-model="loginForm.password"/>
            <i class="iconfont icon-password"/>
          </div>
        </el-form-item>

        <el-form-item class="input-prepend" prop="code" :rules="[{ required: true, message: '请输入验证码', trigger: 'blur' }]">
          <div>
            <el-input type="password" style="width: 63%" placeholder="验证码" v-model="loginForm.code"/>
            <div class="login-code">
              <img :src="codeUrl" @click="getCode" class="login-code-img"/>
            </div>
            <i class="iconfont icon-password"/>
          </div>
        </el-form-item>

        <div class="btn">
          <input type="button" class="sign-in-button" value="登录" @click="submitLogin()">
        </div>
      </el-form>
      <!-- 更多登录方式 -->
      <div class="more-sign">
        <h6>社交帐号登录</h6>
        <ul>
          <li><a id="weixin" class="weixin" target="_blank" href="http://qy.free.idcfengye.com/api/ucenter/weixinLogin/login"><i class="iconfont icon-weixin"/></a></li>
          <li><a id="qq" class="qq" target="_blank" href="#"><i class="iconfont icon-qq"/></a></li>
        </ul>
      </div>
    </div>


  </div>
</template>

<script>
  import '~/assets/css/sign.css'
  import '~/assets/css/iconfont.css'

  import { getCodeImg } from "@/api/login";
  import Cookies from "js-cookie";
  import { encrypt, decrypt } from '@/utils/jsencrypt'
  export default {
    layout: 'sign',

    data () {
      return {
        //封装登录手机号和密码对象
        codeUrl: "",
        loginForm: {
          username: "admin",
          password: "admin123",
          rememberMe: false,
          code: "",
          uuid: ""
        },
        loginRules: {
          username: [
            { required: true, trigger: "blur", message: "用户名不能为空" }
          ],
          password: [
            { required: true, trigger: "blur", message: "密码不能为空" }
          ],
          code: [{ required: true, trigger: "change", message: "验证码不能为空" }]
        },
        loading: false,
        redirectURL: undefined,
        //用户信息
        userInfo:{}
      }
    },
    watch: {
      $route: {
        handler: function(route) {
          this.redirectURL = route.query && route.query.redirectURL;
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
          this.codeUrl = "data:image/gif;base64," + res.img;
          this.loginForm.uuid = res.uuid;
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
      //登录的方法
      submitLogin() {
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
              this.$store.dispatch("GetUserInfo").then(() => {
                this.msgSuccess("登录成功")
                this.loading = false
                window.location.href =  this.redirectURL || "/"
              }).catch(() => {});
            }).catch(() => {
              this.loading = false;
              this.getCode();
            });
          }
        });
      }
    }
  }
</script>
<style >
   .el-form-item__error{
    z-index: 9999999;
  }

.login-code {
     width: 33%;
     height: 38px;
     float: right;
}

.login-code img {
     height: 90%;
     cursor: pointer;
     vertical-align: middle;
}
</style>
