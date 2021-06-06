<template>

  <div id="header" >
    <h1 id="logo" >
      <a href="#" title="辉皇影院">
        <img src="~/assets/img/logo2.png" width="100%" alt="辉皇影院">
      </a>
    </h1>
    <section class="container">
    <div class="h-r-nsl">
      <ul class="nav">
        <router-link to="/" tag="li" active-class="current" exact>
          <a>首页</a>
        </router-link>
        <router-link to="/movie" tag="li" active-class="current">
          <a>电影</a>
        </router-link>
        <router-link to="/actor" tag="li" active-class="current">
          <a>演员</a>
        </router-link>
        <router-link to="/article" tag="li" active-class="current">
          <a>文章</a>
        </router-link>
        <router-link to="/qa" tag="li" active-class="current">
          <a>问答</a>
        </router-link>
      </ul>
      <!-- / nav -->
      <ul class="h-r-login">
        <li v-if="!userInfo" id="no-login">


          <a @click="$store.dispatch('LoginPage')" tag="a" title="登录">
            <em class="icon18 login-icon">&nbsp;</em>
            <span class="vam ml5">登录</span>
          </a>
          |
          <router-link to="/register" tag="a" title="注册">
            <span class="vam ml5">注册</span>
          </router-link>
        </li>
        <li v-if="userInfo" id="is-login-two" class="h-r-user">

          <el-dropdown trigger="click" @command="handleCommand" >
           <span class="el-dropdown-link">

            <img
              width="30"
              height="30"
              class="avatar"
              :src="`http://localhost:7070`+userInfo.avatar"
            >
           {{ userInfo.nickName }}
         </span>
            <el-dropdown-menu  slot="dropdown">
              <el-dropdown-item command="ucenter" icon="el-icon-user-solid">个人中心</el-dropdown-item>
              <el-dropdown-item command="logout" icon="el-icon-remove-outline">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>

          <a id="headerMsgCountId" href="#" title="消息">
            <em class="icon18 news-icon">&nbsp;</em>
          </a>
          <q class="red-point" style="display: none">&nbsp;</q>
        </li>



        <!-- /未登录显示第1 li；登录后显示第2，3 li -->
      </ul>
      <aside class="h-r-search">
        <form action="#" method="post">
          <label class="h-r-s-box">
            <input type="text" placeholder="输入你想观看的电影" name="queryCourse.courseName" value>
            <button type="submit" class="s-btn">
              <em class="icon18">&nbsp;</em>
            </button>
          </label>
        </form>
      </aside>
    </div>
    <aside class="mw-nav-btn">
      <div class="mw-nav-icon"></div>
    </aside>
    <div class="clear"></div>
    </section>
  </div>
</template>
<script>
  import { mapActions,mapState, mapMutations} from 'vuex'
  export default {
    data() {
      return {

      }
    },
    computed:{
      ...mapState(['userInfo'])
    },
    mounted () {

    },
    created() {

    },

    methods: {
      handleCommand(command){
        if (!this.userInfo){
          //如果没有登录 则转到登录页面
          return this.$store.dispatch('LoginPage')
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
            //routeData = this.$route.resolve("/movie")
            //window.open(routeData.href, '_blank')
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
    }
  };

</script>

<style  scoped>


  #header{
    width: 100%;
    background-color: #FAFAFA;
    box-shadow:0 2px 3px rgba(7,0,2,.04);
    padding:5px 0;
    position:fixed;
    z-index: 1501;
    height: 60px;
  }

  #logo{
    float:left;
    width:130px;
    height:58px;
    overflow:hidden;
    margin:0 auto;
    padding-left: 70px;
  }
  #logo a{
    display:table-cell;
    vertical-align:middle;
    height:40px
  }
  #logo a img{
    display:block;
    max-width:100%;
    height:auto;
    vertical-align:middle
  }

  .avatar{
    margin: 0 auto;
    border-radius: 50%;
  }
</style>
