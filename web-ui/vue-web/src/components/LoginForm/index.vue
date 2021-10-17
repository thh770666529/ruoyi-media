<template>
  <div>
    <el-dialog
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :before-close="handleCloseLoginForm"
      :visible.sync="loginFormVisible"
      width="500px">
    <div class="logFormMain">
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
        <el-col :span="5">
          <div style="margin: auto;">其它登录方式</div>
        </el-col>
        <el-col :span="18">
          <div class="icons-list">
            <svg-icon slot="prefix" v-if="loginType.wechat" class="WeChat" @click="thirdAuthRender('wechat')" icon-class="WeChat" />
            <svg-icon slot="prefix" v-if="loginType.qq" class="Alipay"  @click="thirdAuthRender('qq')" icon-class="qq"  />
            <svg-icon slot="prefix" v-if="loginType.gitee" @click="thirdAuthRender('gitee')" icon-class="gitee" />
            <svg-icon slot="prefix"  v-if="loginType.github" class="github" @click="thirdAuthRender('github')" icon-class="github" />
            <!--<svg-icon slot="prefix" class="dingtalk" icon-class="dingtalk"  />
            <svg-icon slot="prefix" class="github" @click="thirdAuthRender('gitee')" icon-class="github" />
            <svg-icon slot="prefix" class="Alipay" icon-class="Alipay"  />
            <svg-icon slot="prefix" class="Sina" icon-class="Sina" />-->
          </div>
        </el-col>
      </el-row>
     </el-form>
    </div>
    </el-dialog>
  </div>
</template>
<script>
  import { getCodeImg } from "@/api/login";
  import { authRender } from "@/api/auth";
  import Cookies from "js-cookie";
  import { encrypt, decrypt } from '@/utils/jsencrypt'
  import {getWebConfig} from '@/api/website/webConfig'

  export default {
    name: "Login",
    data() {
      return {
        codeUrl: "",
        cookiePassword: "",
        webConfig: undefined,
        // 登录类别
        loginType: {
          password: false,
          gitee: false,
          github: false,
          qq: false,
          wechat: false
        },
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
        redirect: undefined,
        authorizeUrl: undefined
      };
    },
    watch: {
      $route: {
        handler: function(route) {
          this.redirect = route.query && route.query.redirect;
        },
        immediate: true
      },
      authorizeUrl(url){
        window.open(url, '第三方授权', 'height=600, width=1000, top=200, left=400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no')
      }
    },
    computed: {
      loginFormVisible(){
        return this.$store.state.user.loginFormVisible
      }
    },
    created() {
      this.getLoginTypeList();
      this.getCode();
      this.getCookie();
    },
    methods: {
      // 第三方登录认证
      thirdAuthRender(source) {
        this.handleCloseLoginForm();
        authRender(source).then(res => {
          this.authorizeUrl = res.data.authorizeUrl;
        })
      },
      // 关闭登录弹出框
      handleCloseLoginForm() {
        this.$store.commit('SET_LOGINFORMVISIBLE', false)
      },
      getCode() {
        getCodeImg().then(res => {
          this.captchaOnOff = res.captchaOnOff === undefined ? true : res.captchaOnOff;
          if (this.captchaOnOff) {
            this.codeUrl = "data:image/gif;base64," + res.img;
            this.loginForm.uuid = res.uuid;
          }
        });
      },
      getLoginTypeList() {
        getWebConfig().then(response => {
          let data = response.data;
          if (data.showList) {
            let showList = JSON.parse(data.showList);
            let loginTypeList = JSON.parse(data.loginTypeList);
            data.showList = showList;
            data.loginTypeList = loginTypeList;
          } else {
            data.showList = [];
          }
          this.webConfig = data
          if(this.webConfig && this.webConfig.loginTypeList) {
            let loginTypeList = this.webConfig.loginTypeList
            for(let index=0; index <loginTypeList.length; index++) {
              console.log(loginTypeList[index])
              switch (loginTypeList[index]) {
                case "1": {
                  this.loginType.password = true
                } break;
                case "2": {
                  this.loginType.gitee = true
                } break;
                case "3": {
                  this.loginType.github = true
                } break;
                case "4": {
                  this.loginType.qq = true
                } break;
                case "5": {
                  this.loginType.wechat = true
                } break;
                default: {
                  this.msgError('登录方式设置有误')
                }
              }
            }
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
              this.$store.dispatch('GetInfo').then(() => {
                this.loading = false;
              }).catch(err => {
                this.$store.store.dispatch('LogOut').then(() => {
                  this.msgError(err)
                })
              })
              //this.$router.push({ path: this.redirect || "/" }).catch(()=>{});
              //window.location.href =  this.redirect || "/";
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

  .logFormMain {
    position: relative;
  }
  .QRcodeIcon{
    position: absolute;
    right:0;
    font-size: 32px;
    color: #1890ff;
    bottom: -15px;
    margin-right:-15px;
    transform:rotate(90deg);
    -ms-transform:rotate(90deg); 	/* IE 9 */
    -moz-transform:rotate(90deg); 	/* Firefox */
    -webkit-transform:rotate(90deg); /* Safari 和 Chrome */
    -o-transform:rotate(90deg); 	/* Opera */
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
    .svg-icon {
      font-size: 27px;
      width: 36px;
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
