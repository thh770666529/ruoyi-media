<template>
    <div class="guanzhu" id="follow-us" ref="follow" v-if="isShow">
      <el-card class="box-card">
        <div slot="header" class="d-flex align-items-center">
          <img class="card-icon" src="../../assets/img/article/lianjie.png"/>
          <span>关注我们 么么哒！</span>
        </div>
      <ul>
        <li class="qqGroup" v-if="contact.qqGroup"><a href="javascript:void(0);"><span>QQ群</span>{{contact.qqGroup}}</a></li>
        <li class="qq" v-if="contact.qqNumber"><a :href="'tencent://AddContact/?fromId=50&fromSubId=1&subcmd=all&uin=' + contact.qqNumber" target="_blank"><span>QQ号</span>{{contact.qqNumber}}</a></li>
        <li class="email" v-if="contact.email"><a href="javascript:void(0);"><span>邮箱帐号</span>{{contact.email}}</a></li>
        <li class="wxgzh" v-if="contact.weChat"><a href="javascript:void(0);"><span>微信号</span>{{contact.weChat}}</a></li>
        <li class="github" v-if="contact.github"><a :href="contact.github" target="_blank"><span>Github</span>{{contact.github}}</a></li>
        <li class="gitee" v-if="contact.gitee"><a :href="contact.gitee" target="_blank"><span>Gitee</span>{{contact.gitee}}</a></li></ul>
      </el-card>
    </div>
</template>

<script>
import { getWebConfig } from '@/api/website/webConfig'
export default {
  name: "About",
  data() {
    return {
      contact: {},
      isShow: false
    };
  },
  created() {
    this.getContactData()
  },
  methods: {
    getContactData: function() {
        getWebConfig().then(response => {
          if (response.code === 200) {
            this.contact = response.data;
            let showList = response.data.showList
            if(showList.length > 2) {
              this.isShow = true;
            }
            this.mailto = "mailto:" + this.contact.email;
          }
        });
    },
  }
};
</script>

<style scoped>
  .box-card span{
    font-weight: bold;
  }
  
  .card-icon{
    width: 20px;
    height: 20px;
    margin-right: 10px;
  }
  /*guanzhu*/
  .guanzhu ul li { font-size: 12px; margin-bottom: 10px; background: #fff; color: #525252; line-height: 40px; padding: 0 0 0 34px; border: 1px solid #DDD; border-radius: 2px; position: relative; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; }
  .guanzhu .sina { border: #ec3d51 1px solid; background: url(../../assets/img/blog/gzbg.jpg) no-repeat 0 10px }
  .guanzhu .tencent { border: #68a6d6 1px solid; background: url(../../assets/img/blog/gzbg.jpg) no-repeat 0 -43px }
  .guanzhu .qq { border: #2ab39a 1px solid; background: url(../../assets/img/blog/gzbg.jpg) no-repeat 0 -98px }
  .guanzhu .qqGroup { border: #EB6841 1px solid; background: url(../../assets/img/blog/qqGroup.png) no-repeat; background-size:22px; background-position-y:47%;}
  .guanzhu .email { border: #12aae8 1px solid; background: url(../../assets/img/blog/gzbg.jpg) no-repeat 0 -150px }
  .guanzhu .wxgzh { border: #199872 1px solid; background: url(../../assets/img/blog/gzbg.jpg) no-repeat 0 -200px }
  .guanzhu .github { border: #000000 1px solid; background: url(../../assets/img/blog/github.jpg) no-repeat; background-size:25px; background-position-y:47%; }
  .guanzhu .gitee { border: #E93B3C 1px solid; background: url(../../assets/img/blog/gitee.jpg) no-repeat; background-size:25px; background-position-y:47%; }
  .guanzhu .wx { overflow: hidden; padding: 0 }
  .guanzhu .wx img { width: 100%; }
  .guanzhu ul li span { float: right; text-align: center; width: 85px; -moz-transition: all .5s ease; -webkit-transition: all .5s ease; -ms-transition: all .5s ease; -o-transition: all .5s ease; transition: all .5s ease; transition: all 0.5s; }
  .guanzhu .sina span { background: #ec3d51; }
  .guanzhu .tencent span { background: #68a6d6; }
  .guanzhu .qq span { background: #2ab39a; }
  .guanzhu .qqGroup span { background: #EB6841; }
  .guanzhu .email span { background: #12aae8; }
  .guanzhu .wxgzh span { background: #199872; }
  .guanzhu .github span { background: #000000; }
  .guanzhu .gitee span { background: #E93B3C; }
  .guanzhu a span { color: #FFF }
  .guanzhu ul li:hover span { width: 100px; }
</style>
