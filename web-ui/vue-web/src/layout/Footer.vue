<template>
  <!-- 页面底部 -->
  <div class="footer" style="visibility: visible;">
    <p class="friendly-links">
      关于猫眼 :
      <a href="#" target="_blank">关于我们</a>
      <span></span>
      <a href="#" target="_blank">管理团队</a>
      <span></span>
      <a href="#" target="_blank">投资者关系</a> &nbsp;&nbsp;&nbsp;&nbsp; 友情链接 :
      <a href="#" data-query="utm_source=wwwmaoyan" target="_blank">美团网</a>
      <span></span>
      <a href="#" data-query="utm_source=wwwmaoyan">格瓦拉</a>
      <span></span>
      <a href="#" data-query="utm_source=wwwmaoyan" target="_blank">美团下载</a>
      <span></span>
      <a href="#" data-query="utm_source=maoyan_pc" target="_blank">欢喜首映</a>
    </p>
    <p class="friendly-links">
      商务合作邮箱：v@maoyan.com 客服电话：10105335 违法和不良信息举报电话：4006018900
    </p>
    <p class="friendly-links">
      用户投诉邮箱：tousujubao@meituan.com 舞弊线索举报邮箱：wubijubao@maoyan.com
    </p>
    <p class="friendly-links  credentials">
      <a href="#" target="_blank">中华人民共和国增值电信业务经营许可证 京B2-20190350</a>
      <span></span>
      <a href="#" target="_blank">营业性演出许可证 京演（机构）（2019）4094号</a>
    </p>
    <p class="friendly-links  credentials">
      <a href="#" target="_blank">广播电视节目制作经营许可证 （京）字第08478号</a>
      <span></span>
      <a href="#" target="_blank">网络文化经营许可证 京网文（2019）3837-369号 </a>
    </p>
    <p class="friendly-links  credentials">
      <a href="#" target="_blank">猫眼用户服务协议 </a>
      <span></span>
      <a href="#" target="_blank">猫眼平台交易规则总则 </a>
      <span></span>
      <a href="#" target="_blank">隐私政策 </a>
    </p>
    <p class="friendly-links  credentials">
      <a href="" target="_blank">京公网安备
        11010102003232号</a>
      <span></span>
      <a href="#/" target="_blank">京ICP备16022489号</a>
    </p>
    <p>北京猫眼文化传媒有限公司</p>
    <p>
      ©<span class="my-footer-year">2020</span> 猫眼电影 maoyan.com</p>
    <div class="certificate">
      <a href="#" target="_blank">
        <img src="http://p0.meituan.net/moviemachine/e54374ccf134d1f7b2c5b075a74fca525326.png">
      </a>
      <a href="#" target="_blank">
        <img src="http://p1.meituan.net/moviemachine/805f605d5cf1b1a02a4e3a5e29df003b8376.png">
      </a>
      <a href="h#" target="_blank">
        <img src="http://p0.meituan.net/scarlett/3cd2a9b7dc179531d20d27a5fd686e783787.png">
      </a>
    </div>
  </div>
</template>

<script>
export default {
  name: "Footer"
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
