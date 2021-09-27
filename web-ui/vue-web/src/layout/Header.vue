<template>
  <header>
    <div class="header-body">
      <a href="/" class="logo">辉皇电影</a>
      <div class="city">成都
        <span class="caret"></span>
      </div>
      <div class="city-list">
        <div class="now-city">
          定位城市: 定位失败
        </div>
        <div class="citys">
          <span class="icon"></span> 定位失败
        </div>
      </div>
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
          <el-menu-item index="/hot">热门</el-menu-item>
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
      <div class="userimg">
        <img src="../assets/styles/images/user.png" alt=""/>
        <span class="caret"></span>
      </div>
      <div class="userlogin">
        <a href="login.html" class=" notlogin">登录</a>
        <div class="islogin nologin">
          <a href="personal.html">个人中心</a>
          <a href="index.html">退出</a>
        </div>
      </div>
    </div>
  </header>
</template>

<script>
import  '@/assets/styles/css/clear.css';
export default {
  name: "Header",
  data() {
    return {
      isLogin: false,
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
      return routePath;
    }
  },

  mounted() {
  },

  methods: {
  },

}
</script>

<style scoped>
</style>
