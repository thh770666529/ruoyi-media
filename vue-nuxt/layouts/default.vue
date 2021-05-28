<template>
  <div class="in-wrap">
    <!-- 公共头引入 -->
    <Header />
    <!-- /中间部分引入 -->
    <div class="default-main">
      <nuxt/>
    </div>
    <!-- 公共底引入 -->
    <Footer />
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

  import Header from '@/components/layouts/Header'
  import Footer from '@/components/layouts/Footer'
  export default {
    components: {
      Header,
      Footer
    },
    data() {
      return {

      }
    },
    mounted () {

    },
    created() {
    },

    methods: {
    }
  };
</script>
<style>
  .default-main{
      padding-top: 50px;
  }

</style>
