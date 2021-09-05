<template>
  <div class="share">
    <p class="diggit" @click="praiseBlog(articleId)">
      <span>很赞哦！</span>
      <span v-if="praiseCount!= 0">
        (<b id="diggnum">{{praiseCount}}</b>)
      </span>
    </p>
    <p class="dasbox">
      <a href="javascript:void(0)" @click="dashangToggle()" class="dashang" title="打赏，支持一下">打赏本站</a>
    </p>
    <div class="hide_box" v-if="showPay"></div>
    <div class="shang_box" v-if="showPay">
      <a class="shang_close" href="javascript:void(0)" @click="dashangToggle()" title="关闭">关闭</a>
      <div class="shang_tit">
        <p>感谢您的支持，我会继续努力的!</p>
      </div>
      <div class="shang_payimg">
        <img :src="fileUploadHost + payCode" alt="扫码支持" title="扫一扫">
      </div>
      <div class="pay_explain">扫码打赏，你说多少就多少</div>
      <div class="shang_payselect">
        <div
          class="pay_item"
          :class="[payMethod == 1?'checked':'']"
          data-id="alipay"
          @click="choosePay(1)"
        >
          <span class="radiobox"></span>
          <span class="pay_logo">
            <img src="~/assets/img/blog/alipay.jpg" alt="支付宝">
          </span>
        </div>
        <div
          class="pay_item"
          :class="[payMethod == 2?'checked':'']"
          data-id="weipay"
          @click="choosePay(2)"
        >
          <span class="radiobox"></span>
          <span class="pay_logo">
            <img src="~/assets/img/blog/wechat.jpg" alt="微信">
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getWebConfig } from '@/api/webConfig'
import { praiseArticle } from "@/api/article";
export default {
  name: "PayCode",
  props: {
    praiseCount: {
      type: Number,
      default: 0
    },
    articleId: {
      type: String
    }
  },
  data() {
    return {
      webConfig: undefined,
      showPay: false, //是否显示支付
      payMethod: 1, // 1: 支付宝  2：微信
      payCode: "", //支付码图片
    };
  },
  async created() {
      if(!this.webConfig) {
        await getWebConfig().then(response => {
          this.webConfig = response.data
          this.payCode = this.webConfig.aliPay;
        });
      }
  },
  methods: {
    //拿到vuex中的写的方法
    dashangToggle: function() {
      this.showPay = !this.showPay;
    },
    // 支付方式
    choosePay: function(type) {
      this.payMethod = type;
      if (type === 1) {
        this.payCode = this.webConfig.aliPay;
      } else {
        this.payCode = this.webConfig.weixinPay;
      }
    },
    //博客点赞
    praiseBlog: function(articleId) {
      // 判断用户是否登录
      let userInfo = this.$store.state.userInfo
      if (!userInfo){
        this.$notify.error({
          title: '警告',
          message: '登录后才可以点赞哦~',
          offset: 100
        });
        this.$confirm('登录后才可以评论，是否进行登录', '提示', {
          confirmButtonText: '登录',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          //如果没有登录 则转到登录页面
          return this.$store.dispatch('LoginPage')
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消登录'
          });
        });
        return
      }
      praiseArticle(articleId).then(response => {
        if (response.code === 200) {
          this.$notify({
            title: '成功',
            message: "点赞成功",
            type: 'success',
            offset: 100
          });
          this.$emit('update:praiseCount',response.data);
        } else {
          this.$notify.error({
            title: '错误',
            message: response.msg,
            offset: 100
          });
        }
      });
    }
  }
};
</script>

<style scoped>
  .diggit {
    cursor: pointer;
    float: left;
    width: 140px;
    margin: auto;
    background: #E2523A url( ../../assets/img/blog/dzbg.jpg) no-repeat center left 10px;
    color: #fff !important;
    box-shadow: 1px 2px 6px 0px rgba(0, 0, 0, .2);
    border-radius: 3px;
    line-height: 40px;
    text-align: center;
    padding-left: 20px;
    margin-left: 20%;
  }

  /*share*/
  .share {
    overflow: hidden;
    padding-top: 10px
  }

  .share ul li {
    width: 25%;
    float: left;
    overflow: hidden;
    position: relative;
    margin-bottom: 30px;
    -webkit-transition: all .3s ease;
    -moz-transition: all .3s ease;
    -ms-transition: all .3s ease;
    -o-transition: all .3s ease;
    transition: all .3s ease;
  }

  .share ul li i {
    height: 200px;
    display: block;
    overflow: hidden
  }

  .share ul li i img {
    height: 200px;
    width: auto;
    margin: auto
  }

  .share ul li h2 {
    height: 38px;
    font-size: 14px;
    line-height: 1.4;
    overflow: hidden;
    background: #fff;
    padding: 15px 13px;
    font-weight: normal;
    -webkit-box-shadow: 0 5px 10px 0 rgba(146, 146, 146, .1);
    -moz-box-shadow: 0 5px 10px 0 rgba(146, 146, 146, .1);
    box-shadow: 0 5px 10px 0 rgba(146, 146, 146, .1);
  }

  .share ul li h2 b {
    height: 38px;
    overflow: hidden;
    text-overflow: ellipsis;
    -webkit-box-orient: vertical;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    font-weight: normal
  }

  .share ul li:hover {
    transform: translateY(-6px);
    -webkit-transform: translateY(-6px);
    -moz-transform: translateY(-6px);
  }

  .share ul li span {
    position: absolute;
    right: 30px;
    top: 15px;
    background: rgba(232, 40, 74, .8);
    color: #FFF;
    padding: 3px 8px;
    font-size: 12px;
    border-radius: 3px;
  }


  /*money*/
  .dasbox {
    color: #FFF ;
    width: 130px;
    float: left;
    margin-left: 40px;
    background: url( ../../assets/img/blog/dsbz.jpg) no-repeat left 20px center #E2523A;
    box-shadow: 1px 2px 6px 0px rgba(0, 0, 0, .2);
    border-radius: 3px;
    line-height: 40px;
    padding-left: 10px;
    text-align: center;
  }


  .hide_box {
    z-index: 999;
    filter: alpha(opacity=50);
    background: #666;
    opacity: 0.5;
    -moz-opacity: 0.5;
    left: 0;
    top: 0;
    height: 99%;
    width: 100%;
    position: fixed;
    display: none;
  }

  .shang_box {
    width: 430px;
    padding: 40px 10px;
    background: #fff url( ../../assets/img/blog/tbg.jpg) no-repeat left top 30px;;
    border-radius: 10px;
    position: fixed;
    z-index: 1000;
    left: 50%;
    top: 50%;
    margin-left: -280px;
    margin-top: -280px;
    border: 1px dotted #dedede;
  }

  .shang_box img {
    border: none;
    border-width: 0;
  }

  .dasbox a {
    color: #fff;
  }

  .shang_close {
    float: right;
    display: inline-block;
  }

  .shang_tit {
    width: 100%;
    height: 70px;
    text-align: center;
    line-height: 70px;
  }

  .shang_tit p {
    color: #a3a3a3;
    text-align: center;
    font-size: 16px;
  }

  .shang_payimg {
    width: 140px;
    padding: 10px;
    border: 6px solid #EA5F00;
    margin: 0 auto;
    border-radius: 3px;
    height: 140px;
  }

  .shang_payimg img {
    display: block;
    text-align: center;
    width: 140px;
    height: 140px;
  }

  .pay_explain {
    text-align: center;
    margin: 10px auto;
    font-size: 12px;
    color: #545454;
  }

  .radiobox {
    width: 16px;
    height: 16px;
    background: url(../../assets/img/blog/radio2.jpg);
    display: block;
    float: left;
    margin-top: 5px;
    margin-right: 14px;
  }

  .checked .radiobox {
    background: url(../../assets/img/blog/radio1.jpg);
  }

  .shang_payselect {
    text-align: center;
    margin: 0 auto;
    margin-top: 40px;
    cursor: pointer;
    height: 60px;
    width: 280px;
  }

  .shang_payselect .pay_item {
    display: inline-block;
    margin-right: 10px;
    float: left;
  }

  .shang_payselect img {
    float: left
  }

</style>
