<template xmlns:v-swiper="http://www.w3.org/1999/xhtml">

  <div>
    <section class="container">
    <el-carousel :interval="4000" type="card" height="400px">
      <el-carousel-item v-for="banner in bannerList" :key="banner.bannerId">
        <a target="_blank" :href="banner.linkUrl">
          <el-image :src="fileUploadHost + banner.imageUrl" :alt="banner.title">
          </el-image>
        </a>
      </el-carousel-item>
    </el-carousel>
    </section>

    <!-- 幻灯片 开始 -->
    <!--<div v-swiper:mySwiper="swiperOption">
      <div class="swiper-wrapper">

        <div v-for="banner in bannerList" :key="banner.bannerId" class="swiper-slide" style="background: #040B1B;">
          <a target="_blank" :href="banner.linkUrl">
            <img :src="fileUploadHost + banner.imageUrl" :alt="banner.title">
          </a>
        </div>
      </div>
      <div class="swiper-pagination swiper-pagination-white"></div>
      <div class="swiper-button-prev swiper-button-white" slot="button-prev"></div>
      <div class="swiper-button-next swiper-button-white" slot="button-next"></div>
    </div>-->
    <!-- 幻灯片 结束 -->

    <div id="aMovieList">
      <!-- 热门电影 开始 -->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="actor">
              <span class="c-333">热门电影</span>
            </h2>
          </header>
          <div>
            <article class="comm-movie-list">
              <ul class="of" id="bna">
                <li v-for="movie in movieList" :key="movie.movieId">
                  <div class="cc-l-wrap">
                    <section class="movie-img">
                      <img :src="fileUploadHost+movie.images" class="img-responsive" :alt="movie.title">
                      <div class="cc-mask">
                        <a :href="'/movie/'+movie.movieId" title="开始观看" class="comm-btn c-btn-1">开始观看</a>
                      </div>
                    </section>
                    <h4 class="title">
                      <a :href="'/movie/'+movie.movieId" :title="movie.title">{{movie.title}}</a>
                    </h4>
                    <div class="text text-overflow text-muted hidden-xs">
                      主演：{{actorFormatter(movie.actorList) | ellipsis(10)}}
                      <br>标签 :{{ tagFormat(tagOptions , movie.tagId) | ellipsis(10)}}
                    </div>
                  </div>
                </li>

              </ul>
              <div class="clear"></div>
            </article>
            <section class="actor pt20">
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
            <h2 class="actor">
              <span class="c-333">热门演员</span>
            </h2>
          </header>
          <div>
            <article class="i-actor-list">
              <ul class="of">
                <li v-for="actor in actorList" :key="actor.actorId">
                  <section class="i-actor-wrap">
                    <div class="i-actor-pic">
                      <router-link :to="'/actor/'+actor.actorId" tag="a" target="_blank" exact>
                        <img :src="fileUploadHost + actor.avatar" :alt="actor.name">
                      </router-link>
                    </div>
                    <div class="mt10 hLh30 txtOf actor">
                      <router-link :to="'/actor/' + actor.actorId" tag="a" :title="actor.name" target="_blank" active-class="fsize20 #333" exact>
                        <b>{{actor.name}}</b>
                      </router-link>
                    </div>
                    <div class="hLh30 txtOf actor">
                      <span class="fsize12 c-999" v-html="actor.description"></span>
                    </div>
                  </section>
                </li>
              </ul>
              <div class="clear"></div>
            </article>
            <section class="actor pt20">
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
  import { getDicts } from '@/api/system/dict/data'
  import { listTag } from "@/api/media/tag";
  import { listCategory } from "@/api/media/category";
  export default {
    async asyncData({$axios}) {
      //const movieList = await $axios.$get('/index/getHotMovieList')
      //const bannerList = await $axios.$get('/index/getAllBannerList')
      const tagOptions = await listTag({status: '1'});
      const movieList = await index.getHotMovieList();
      const actorList = await index.getHotActorList();
      const bannerList = await index.getAllBannerList();
      return {tagOptions: tagOptions.rows,movieList: movieList.data, bannerList: bannerList.data, actorList: actorList.data}
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
    methods: {
      actorFormatter(actorList) {
        if (actorList.length === 0){
          return '无';
        }
        const currentSeparator = ",";
        let actions = [];
        for (let index = 0; index < actorList.length; index++) {
          actions.push(actorList[index].name + currentSeparator);
        }
        return actions.join('').substring(0, actions.join('').length - 1);
      },
      /** 标签翻译 */
      tagFormat(tagOptions,tagId) {
        if(!tagId || !tagOptions) {
          return ''
        }
        const currentSeparator = ",";
        let actions = [];
        let tempArr = tagId.split(currentSeparator);
        for (let i = 0; i < tempArr.length; i++) {
          for (let j = 0; j < tagOptions.length ; j++) {
            if (tagOptions[j].tagId == ('' + tempArr[i])) {
              actions.push(tagOptions[j].content + currentSeparator);
              break;
            }
          }
        }
        return actions.join('').substring(0, actions.join('').length - 1);
      }
    }
  }
</script>
<style>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
