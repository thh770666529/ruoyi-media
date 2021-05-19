<template xmlns:v-swiper="http://www.w3.org/1999/xhtml">

  <div>
    <!-- 幻灯片 开始 -->
  <div v-swiper:mySwiper="swiperOption">
      <div class="swiper-wrapper">

          <div v-for="banner in bannerList" :key="banner.bannerId" class="swiper-slide" style="background: #040B1B;">
              <a target="_blank" :href="banner.linkUrl">
                  <img :src="banner.imageUrl" :alt="banner.title">
              </a>
          </div>
      </div>
      <div class="swiper-pagination swiper-pagination-white"></div>
      <div class="swiper-button-prev swiper-button-white" slot="button-prev"></div>
      <div class="swiper-button-next swiper-button-white" slot="button-next"></div>
  </div>
  <!-- 幻灯片 结束 -->

     <div id="aCoursesList">
      <!-- 热门电影 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">热门电影</span>
            </h2>
          </header>
          <div>
            <article class="comm-course-list">
              <ul class="of" id="bna">
                <li v-for="movie in movieList" :key="movie.movieId">
                  <div class="cc-l-wrap">
                    <section class="course-img">
                      <img
                        :src="fileUploadHost+movie.images"
                        class="img-responsive"
                        :alt="movie.title"
                      >
                      <div class="cc-mask">
                        <a  :href="'/movie/'+movie.movieId" title="开始观看" class="comm-btn c-btn-1">开始观看</a>
                      </div>
                    </section>
                    <h3 class="hLh30 txtOf mt10">
                      <a :href="'/movie/'+movie.movieId" :title="movie.title" class="course-title fsize18 c-333">{{movie.title}}</a>
                    </h3>
                    <section class="mt10 hLh20 of">
                     <span class="fr jgTag bg-green" v-if="Number(movie.price) === 0">
                        <i class="c-fff fsize12 f-fA">免费</i>
                      </span>
                      <span class="fl jgAttr c-ccc f-fA">
                        <i class="c-999 f-fA">9634人学习</i>
                        |
                        <i class="c-999 f-fA">9634评论</i>
                      </span>
                    </section>
                  </div>
                </li>

              </ul>
              <div class="clear"></div>
            </article>
            <section class="tac pt20">
              <a href="/movie" title="全部电影" class="comm-btn c-btn-2">全部电影</a>
            </section>
          </div>
        </section>
      </div>

      <!-- /热门电影 结束 -->





    </div>
  </div>
</template>

<script>
import index from '@/api/index'
export default {
  data () {
    return {

      swiperOption: {
        //配置分页
        pagination: {
          el: '.swiper-pagination'//分页的dom节点
        },
        //配置导航
        navigation: {
          nextEl: '.swiper-button-next',//下一页dom节点
          prevEl: '.swiper-button-prev'//前一页dom节点
        }
      },
      //banner数组
      bannerList:[],
      movieList:[],
      teacherList:[],
      fileUploadHost:'http://localhost:7070'
    }
  },
  created() {
    //查看所有轮播图
    this.getAllBannerList()
    //查看热门电影
    this.getHotMovieList()
  },
  methods:{
    //查询热门电影和名师
    getHotMovieList() {
     index.getHotMovieList()
        .then(response => {
        this.movieList = response.data.data;
        })

    },
    //查询banner数据
    getAllBannerList() {
      index.getAllBannerList()
        .then(response => {
          this.bannerList = response.data.data
        });
    }
  }
}
</script>
