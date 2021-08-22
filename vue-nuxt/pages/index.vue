<template xmlns:v-swiper="http://www.w3.org/1999/xhtml">

  <div>
    <!-- 幻灯片 开始 -->
    <div v-swiper:mySwiper="swiperOption">
      <div class="swiper-wrapper">

        <div v-for="banner in bannerList" :key="banner.bannerId" class="swiper-slide" style="background: #040B1B;">
          <a target="_blank" :href="banner.linkUrl">
            <img :src="fileUploadHost+banner.imageUrl" :alt="banner.title">
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
                        <a :href="'/movie/'+movie.movieId" title="开始观看" class="comm-btn c-btn-1">开始观看</a>
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
                        <i class="c-999 f-fA">9634人观看</i>
                        |
                        <i class="c-999 f-fA">456评论</i>
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



      <!-- 热门演员 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333">热门演员</span>
            </h2>
          </header>
          <div>
            <article class="i-teacher-list">
              <ul class="of">
                <li v-for="actor in actorList" :key="actor.actorId">
                  <section class="i-teach-wrap">
                    <div class="i-teach-pic">
                      <router-link :to="'/actor/'+actor.actorId" tag="a" target="_blank" exact>
                        <img :src="fileUploadHost+actor.avatar" :alt="actor.name">
                      </router-link>
                    </div>
                    <div class="mt10 hLh30 txtOf tac">
                      <router-link :to="'/actor/'+actor.actorId" tag="a" title="actor.name" target="_blank" active-class="fsize18 c-666" exact>
                        {{actor.name}}
                      </router-link>
                    </div>
                    <div class="hLh30 txtOf tac">
                      <span class="fsize14 c-999" v-html="actor.description"></span>
                    </div>
                    <div class="mt15 i-q-txt">
                      <span class="c-999 f-fA" v-html="actor.awards"></span>
                    </div>
                  </section>
                </li>

              </ul>
              <div class="clear"></div>
            </article>
            <section class="tac pt20">
              <a href="/actor" title="全部演员"  class="comm-btn c-btn-2">全部演员</a>
            </section>
          </div>
        </section>
      </div>
      <!-- /热门演员 结束 -->

    </div>
  </div>
</template>

<script>
  import index from '@/api/index'

  export default {
    async asyncData({$axios}) {
      //const movieList = await $axios.$get('/index/getHotMovieList')
      //const bannerList = await $axios.$get('/index/getAllBannerList')
      const movieList = await index.getHotMovieList();
      const actorList = await index.getHotActorList();
      const bannerList = await index.getAllBannerList();
      return {movieList: movieList.data, bannerList: bannerList.data, actorList: actorList.data}
    },
    data() {
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
        }
      }
    },
    created() {

    },
    methods: {}
  }
</script>
