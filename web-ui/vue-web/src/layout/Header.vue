<template>
  <header>
    <div class="header-body">
      <a href="/" class="logo">辉皇影院</a>
      <!--<div class="city">成都
        <span class="caret"></span>
      </div>
      <div class="city-list">
        <div class="now-city">
          定位城市: 定位失败
        </div>
        <div class="citys">
          <span class="icon"></span> 定位失败
        </div>
      </div>-->
      <div class="nav">
        <el-menu :default-active="defaultActive"
                 mode="horizontal"
                 text-color="black"
                 router
                 background-color="#fafafa"
                 active-text-color="#ef4238">
          <el-menu-item index="/">首页</el-menu-item>
          <el-menu-item index="/movie">电影</el-menu-item>
          <el-menu-item index="/actor">演员</el-menu-item>
          <el-menu-item index="/article">文章</el-menu-item>
          <el-menu-item index="/question">问答</el-menu-item>
          <el-menu-item><a href="https://gitee.com/tanhuihuang/ruoyi-media.git" target="_blank">源码</a></el-menu-item>
        </el-menu>
       <!--<ul class="nav-list">
          <li><a href="/" class="nav-model active">首页</a></li>
          <li><a href="/movie" class="nav-model">电影</a></li>
          <li><a href="/actor" class="nav-model">演员</a></li>
          <li class="nav-model">文章</li>
          <li class="nav-model">问答</li>
          <li class="nav-model">热门</li>
        </ul>-->



      </div>
      <div class="app-download">
        <a href="#">
          <span class="phone"></span> APP下载
          <span class="caret"></span>

          <div class="appcode">
            <img src="../assets/styles/images/phonecode.png" class="app-code"/>
            <p class="down-tip">扫码下载APP</p>
            <p class="down-text">选座更优惠</p>
          </div>
        </a>
      </div>
      <div class="search">
        <div type="text" class="searchinput">
          <input type="text" class="input" placeholder="找影视剧、影人、影院">
          <input class="submit" type="submit" value="">
        </div>
      </div>
      <!--<div  class="userimg">
        <img :src="fileUploadHost + avatar" alt=""/>
        <span class="caret"></span>
      </div>
      <div class="userlogin">
        <a v-if="!isLogin" href="/login" class=" notlogin">登录</a>
        <div v-if="isLogin" class="islogin nologin">
          <a href="personal.html">个人中心</a>
          <a href="/logout">退出</a>
        </div>
      </div>-->

      <ul class="h-r-login">
        <li v-if="!isLogin">
          <a href="#" @click="$store.dispatch('showLoginForm')" title="登录">
            <em class="icon18 login-icon">&nbsp;</em>
            <span class="vam ml5">登录</span>
          </a>
          |
          <router-link to="/register" tag="a" title="注册">
            <span class="vam ml5">注册</span>
          </router-link>
        </li>
        <li v-if="isLogin" class="h-r-user">
          <el-dropdown trigger="click" @command="handleCommand" >
            <span class="el-dropdown-link">

              <img style="width: 30px;height: 30px"
                class="avatar"
                :src="avatar"/>
              {{ name }}
            </span>
            <el-dropdown-menu  slot="dropdown">
              <el-dropdown-item command="ucenter" icon="el-icon-user-solid">个人中心</el-dropdown-item>
              <el-dropdown-item command="logout" icon="el-icon-remove-outline">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </li>
      </ul>

    </div>
  </header>
</template>

<script>
import  '@/assets/styles/css/clear.css';
import { mapGetters } from 'vuex'
export default {
  name: "Header",
  data() {
    return {
      searchList: [],
      state: '',
      timeout: null,
      user: {},
    }
  },
  computed: {
    defaultActive() {
      let routePath = this.$route.matched[1].path || '/';
      // 如果是动态路由，则只取前一级路由 /movie/:id
      if (routePath.indexOf('/', 1) !== -1){
        // 截取一级路由 /movie
        routePath = routePath.substring(0, routePath.indexOf('/', 1));
      }
      if (routePath === '/video'){
        return '/movie';
      }
      return routePath;
    },
    ...mapGetters([
      'name',
      'avatar',
      'isLogin'
    ])
  },

  mounted() {
  },

  methods: {
    handleCommand(command){
      if (!this.isLogin){
        this.$store.dispatch('showLoginForm');
      }
      switch (command) {
        case 'ucenter':
          window.open('/ucenter', '_blank')
          break
        case 'editPassword':
          window.open("/ucenter/editPassword", '_blank')
          break
        case 'movie':
          // 以新窗口方式 打开编辑文章窗口
          window.open('/movie', '_blank')
          break
        case 'actor':
          window.open('/actor', '_blank')
          break
        case 'logout':
          this.$store.dispatch('LogOut').then(() => {
            this.msgInfo("退出成功！")
          }).catch(err => {
          })
          break
        default:
          break
      }
    }
  },

}
</script>

<style scoped>
  .avatar{
    margin: 0 auto;
    border-radius: 50%;
  }
  .h-r-login li a:hover,.nav li a:hover,.nav li.current a{text-decoration:none}
  .h-r-login,.h-r-search{float:right;padding-top:22px}
  .h-r-login li{float:left;margin-left:10px;position:relative}
  .h-r-login li a{cursor:pointer;line-height:50px;color:#666;font-size:16px;transition:.3s;-webkit-transition:.3s}
  .h-r-login li.h-r-user a img{border-radius:50%;width:30px;height:30px}
  .h-r-login li.h-r-user span{max-width:60px;height:17px;font-size:16px;line-height:16px;overflow:hidden}
  .red-point{position:absolute;right:-4px;top:8px;display:block;width:8px;height:8px;border-radius:50%;background:#cb2020;text-indent:-9999px}

</style>
