<template>
  <div  id="footer">
  <section class="container">
    <div class>
      <h4 class="hLh30">
        <span class="fsize18 f-fM c-999">友情链接</span>
      </h4>
      <ul class="of flink-list">
        <li v-for="link in linkList" :key="link.linkId">
          <a :href="link.linkUrl" title="辉皇影院" :target="link.linkTarget">{{link.linkName}}</a>
        </li>
      </ul>
      <div class="clear"></div>
    </div>
    <div class="b-foot">
      <section class="fl col-7">
        <section class="mr20">
          <section class="b-f-link">
            <a href="#" title="关于我们" target="_blank">关于我们</a>|
            <a href="#" title="联系我们" target="_blank">联系我们</a>|
            <a href="#" title="帮助中心" target="_blank">帮助中心</a>|
            <a href="#" title="资源下载" target="_blank">资源下载</a>|
            <span>服务热线：010-56253825(北京) 0755-85293825(深圳)</span>
            <span>Email：info@ithhit.com</span>
          </section>
          <section class="b-f-link mt10">
            <span>©2021电影版权均归辉皇影院所有 京ICP备17055252号</span>
          </section>
        </section>
      </section>
      <aside class="fl col-3 actor mt15">
        <section class="gf-tx">
              <span>
                <img src="~/assets/img/wx-icon.png" alt>
              </span>
        </section>
        <section class="gf-tx">
              <span>
                <img src="~/assets/img/wb-icon.png" alt>
              </span>
        </section>
      </aside>
      <div class="clear"></div>
    </div>
  </section>
  </div>
</template>
<script>
  import '~/assets/css/reset.css'
  import '~/assets/css/theme.css'
  import '~/assets/css/global.css'
  import '~/assets/css/web.css'
  import '~/assets/css/base.css'
  import '~/assets/css/activity_tab.css'
  import '~/assets/css/bottom_rec.css'
  import '~/assets/css/nice_select.css'
  import '~/assets/css/order.css'
  import '~/assets/css/swiper-3.3.1.min.css'
  import "~/assets/css/pages-weixinpay.css"
  import index from '@/api/index'
  export default {
    name: 'Footer',
    data () {
      return {
        linkList: []
      }
    },
    mounted () {
      index.getLinkList().then(response => {
        this.linkList = response.data;
      })
    }
  }
</script>
<style scoped >

</style>
