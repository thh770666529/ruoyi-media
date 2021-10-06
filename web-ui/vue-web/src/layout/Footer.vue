<template>
  <!-- 页面底部 -->
  <div class="footer" style="visibility: visible;">
    <p class="friendly-links">
      关于辉皇影院 :
      <a href="#" target="_blank">关于我们&nbsp;&nbsp;</a>
      <a href="https://gitee.com/tanhuihuang/ruoyi-media.git" target="_blank">码云&nbsp;&nbsp;</a>
      友情链接 : <a :href="item.linkUrl"  v-for="(item,index) in linkList" :key="index" :title="item.linkName" :target="item.linkTarget">{{item.linkName}}&nbsp;&nbsp;</a>
    </p>
    <p class="friendly-links">
      商务合作邮箱：18776050775@163.com&nbsp;&nbsp;客服电话：xxxxxx 违法和不良信息举报电话：xxxxxxxxxx
    </p>
    <p class="friendly-links  credentials">
      <a href="" target="_blank">京公网安备
        XXXXXXXXXXX号</a>
      <span></span>
      <a href="#/" target="_blank">京ICP备XXXXXXXX号</a>
    </p>
    <p>辉皇影院</p>
    <p>
      ©<span class="my-footer-year">Copyright©2021  </span> 辉皇影院</p>
    <div class="certificate">
      <a href="#" target="_blank">
        <img src="http://p0.meituan.net/moviemachine/e54374ccf134d1f7b2c5b075a74fca525326.png">
      </a>
      <a href="#" target="_blank">
        <img src="http://p1.meituan.net/moviemachine/805f605d5cf1b1a02a4e3a5e29df003b8376.png">
      </a>
      <a href="#" target="_blank">
        <img src="http://p0.meituan.net/scarlett/3cd2a9b7dc179531d20d27a5fd686e783787.png">
      </a>
    </div>
  </div>
</template>

<script>
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

<style scoped>
.footer {
  background-color: #262426;
  padding: 56px 0;
  margin: 0 auto;
  min-width: 1200px;
  margin-top: 82px;
}
.footer p {
  text-align: center;
  color: #ffffff;
}
.footer p a:hover {
  text-decoration: underline;
}
.footer .friendly-links a {
  color: #EF4238;
}
.footer .credentials a {
  color: #ffffff;
}
.footer .certificate {
  padding-left: 40%;
}
</style>
