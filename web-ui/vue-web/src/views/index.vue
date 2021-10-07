<template>
  <div class="mt20">
    <!-- 轮播图 -->
    <el-carousel :interval="4000" type="card">
      <el-carousel-item v-for="banner in bannerList" :key="banner.bannerId">
        <a target="_blank" :href="banner.linkUrl">
          <el-image :src="fileUploadHost + banner.imageUrl" :alt="banner.title">
            <div slot="error" class="image-slot">
              <img src="../assets/styles/images/2.jpg" alt="" style="width: 1700px;height: 520px" >
            </div>
          </el-image>
        </a>
      </el-carousel-item>
    </el-carousel>

    <!-- 页面主体 -->
    <div class="container">
      <div class="main">

        <!-- 正在热映 -->
        <div class="hot-movie">
          <div class="movie-header">
            <h2>正在热映（{{hotPlayMovieTotle}}部）</h2>
            <a href="/movie">
              <span>全部></span>
            </a>
          </div>
          <div class="movie-list">
            <div class="movie-item" v-for="(item,index) in hotPlayMovieList" :key="index">
              <a :href="`/movie/` + item.movieId">
                <div class="movie-poster">
                  <img :src="fileUploadHost + item.images" />
                  <div class="movie-overlay movie-overlay-bg">
                    <div class="movie-info">
                      <div class="movie-score">
                        <span class="fsize12" v-if="!item.rate">暂无评分</span>
                        <i class="integer">{{rateFormatter(item.rate, 0)}}</i><i class="fraction">{{rateFormatter(item.rate, 1)}}</i>
                      </div>
                      <div class="movie-title" :title="item.title">{{ item.title }}</div>
                    </div>
                  </div>
                </div>
                <button onclick="alert('123')">抢购</button>
              </a>
            </div>
          </div>
        </div>

        <!-- 即将上映 -->
        <div class="movie-box">
          <div class="movie-header">
            <h2>即将上映（{{hotPlayMovieTotle}}部）</h2>
            <a href="/movie">
              <span>全部></span>
            </a>
          </div>
          <div class="movies">

            <div class="movies-model" v-for="(item,index) in hotPlayMovieList" :key="index">
              <a :href="`/movie/` + item.movieId">
                <div class="movie-poster">
                  <img :alt="item.title" :src="fileUploadHost + item.images" />
                  <div class="movie-overlay movie-overlay-bg">
                    <div class="movie-info">
                      <div class="movie-title" :title="item.title">{{ item.title }}</div>
                    </div>
                  </div>
                </div>
              </a>
              <p>46461661人想看</p>
              <button onclick="alert('123')">预告片</button>
              <button onclick="alert('123')">预售</button>
              <p>{{parseTime(item.publishTime, '{m}月{d}日')}}上映</p>
            </div>

          </div>
        </div>
      </div>
      <!-- 侧边栏 -->
      <div class="aside">

        <!--关注我们-->
        <About></About>

        <!-- top100 -->
        <div class="top">
          <h2>TOP 100 <span>查看完整榜单></span></h2>
          <div class="toplist">
            <ul class="topone">
              <li class=" top-item-first clearfix" v-if="hotMovieList.length > 0" >
                <a :href="`/movie/` + hotMovieList[0].movieId">
                  <div class="first-item clearfix">
                    <el-image lazy :src="fileUploadHost + hotMovieList[0].images" style="width: 120px;height: 78px" :alt="hotMovieList[0].title">
                      <div slot="error" class="image-slot">
                        <img src="../assets/styles/images/2.jpg" alt="" style="width: 120px;height: 78px" >
                      </div>
                    </el-image>
                  </div>
                  <p class="name">{{hotMovieList[0].title}}</p>
                  <p class="money">{{hotMovieList[0].rate}}分</p>
                </a>
              </li>
              <li class="top-item" :key="index" v-for="(item,index) in hotMovieList">
                <a :href="`/movie/` + item.movieId" v-if="index > 0">
                  <div class="index-box">
                    <i v-if="index < 3" class="index index-hot">{{index + 1}}</i>
                    <i v-else class="index">{{index + 1}}</i>
                    <span> {{item.title}}</span>
                  </div>
                  <span class="index-money">{{item.rate}}分</span>
                </a>
              </li>
            </ul>
          </div>
        </div>

        <!-- 热门演员 -->
        <div class="people">
          <h2>热门演员</h2>
          <div class="peoplelist">
            <ul class="peoples">
              <li class=" people-item-first clearfix" v-if="hotActorList.length > 0">
                <a :href="`/actor/` + hotActorList[0].actorId">
                  <div class="first-item clearfix">
                    <img :src="fileUploadHost + hotActorList[0].avatar" alt="" srcset="">
                    <div class="people-overlay">
                      <div class="people-rank">
                        <i class="index index-hot">1</i>
                      </div>
                    </div>
                  </div>
                  <p class="name">{{hotActorList[0].name}}</p>
                </a>
              </li>
              <li class="people-item" :key="index" v-for="(actor,index) in hotActorList">
                <a :href="`/actor/` + actor.actorId" v-if="index > 0">
                  <div class="index-box">
                    <i v-if="index < 3" class="index index-hot">{{index + 1}}</i>
                    <i v-else class="index">{{index + 1}}</i>
                    <span>{{actor.name}}</span>
                  </div>
                </a>
              </li>
            </ul>
          </div>
        </div>

        <!-- 热门文章 -->
        <div class="hotspot">
          <h2>热门文章</h2>
          <div class="hotspotlist">
            <ul class="hotspots">
              <li class=" hotspot-item-first clearfix" v-if="hotArticleList.length > 0">
                <a :href="`/article/` + hotArticleList[0].articleId">
                  <div class="first-item clearfix">
                    <el-image lazy :src="fileUploadHost + hotArticleList[0].images" style="width: 120px;height: 68px" :alt="hotArticleList[0].title">
                      <div slot="error" class="image-slot">
                        <img src="../assets/styles/images/login-body-bg.png" alt="" style="width: 120px;height: 68px" >
                      </div>
                    </el-image>
                  </div>
                  <p class="name">{{hotArticleList[0].title | ellipsis(23)}}</p>
                </a>
              </li>
              <li class="hotspot-item" :key="article.articleId" v-for="(article,index) in hotArticleList" >
                <a :href="`/article/` + article.articleId" v-if="index > 0">
                  <div class="index-box">
                    <i v-if="index < 3" class="index index-hot">{{index + 1}}</i>
                    <i v-else class="index">{{index + 1}}</i>
                    <span>{{article.title}}</span>
                  </div>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>

  import index from '@/api/index';
  import movieApi from "@/api/media/movie";
  import { getDicts } from '@/api/system/dict/data';
  import { listTag } from "@/api/media/tag";
  import { listCategory } from "@/api/media/category";
  export default {
    data() {
      return {
        tagOptions: [],
        hotMovieList: [],
        hotPlayMovieList: [],
        hotPlayMovieTotle: 0,
        bannerList: [],
        hotActorList: [],
        hotArticleList: []
      }
    },
    created() {
      listTag({status: '1'}).then(response => {
         this.tagOptions =  response.rows;
      });
      index.getAllBannerList().then(response => {
        this.bannerList =  response.data;
      });
      index.getHotMovieList().then(response => {
        this.hotMovieList =  response.data;
      });
      index.getHotActorList().then(response => {
        this.hotActorList =  response.data;
      });

      index.getHotArticleList().then(response => {
        this.hotArticleList =  response.data;
      });
      const hotPlayMovieQueryParams = { pageNum: 1,pageSize: 8,orderByColumn: 'clickCount',isAsc: 'desc'};
      movieApi.listMovie(hotPlayMovieQueryParams).then(response => {
        this.hotPlayMovieList = response.rows;
        this.hotPlayMovieTotle = response.total;
      })
    },
    methods: {
      rateFormatter(rate, index) {
        if (!rate){
          if (index === 1){
            return '';
          }
          return '';
        }
        let number = String(rate);
        const numberArr = number.split('.');
        number = numberArr[index];
        if (!number){
          return '';
        }
        return index===0 ?number:'.' + number;
      },
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

</style>
