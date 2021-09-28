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
          <h2>正在热映（{{hotPlayMovieTotle}}部）</h2>
          <div class="movie-list">
            <div class="movie-item" v-for="movie in hotPlayMovieList" :key="movie.movieId">
              <a :href="`/movie/` + movie.movieId">
                <div class="movie-poster">
                  <img :src="fileUploadHost + movie.images" />
                  <div class="movie-overlay movie-overlay-bg">
                    <div class="movie-info">
                      <div class="movie-score"><i class="integer">8.</i><i class="fraction">7</i></div>
                      <div class="movie-title" :title="movie.title">{{ movie.title }}</div>
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
            <h2>即将上映（{{hotPlayMovieTotle.length}}部）</h2>
            <a href="#">
              <span>全部></span>
            </a>
          </div>
          <div class="movies">

            <div class="movies-model" v-for="movie in hotPlayMovieTotle" :key="movie.movieId">
              <a href="">
                <div class="movie-poster">
                  <img :src="fileUploadHost + movie.images" />
                  <div class="movie-overlay movie-overlay-bg">
                    <div class="movie-info">
                      <div class="movie-title" :title="movie.title">{{ movie.title }}</div>
                    </div>
                  </div>
                </div>
              </a>
              <p>46461661人想看</p>
              <button onclick="alert('123')">预告片</button>
              <button onclick="alert('123')">预售</button>
              <p>{{parseTime(movie.publishTime, '{m}月{d}日')}}上映</p>
            </div>

          </div>
        </div>

        <!-- 热播电影 -->
        <div class="movie-box">
          <div class="movie-header c hotmovie ">
            <h2>热播电影（115部）</h2>
            <a href="#">
              <span>全部></span>
            </a>
          </div>
          <div class="movies movies-hot">
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>
            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie2.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie3.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie4.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
          </div>
        </div>
        <!-- 喜剧电影 -->
        <div class="movie-box">
          <div class="movie-header">
            <h2>喜剧电影（115部）</h2>
            <a href="#">
              <span>全部></span>
            </a>
          </div>
          <div class="movies">
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie2.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie3.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie4.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
          </div>
        </div>
        <!-- 动作电影 -->
        <div class="movie-box">
          <div class="movie-header c hotmovie ">
            <h2>动作电影（125部）</h2>
            <a href="#">
              <span>全部></span>
            </a>
          </div>
          <div class="movies movies-hot">
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>
            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie2.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie3.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie4.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
          </div>
        </div>
        <!-- 恐怖片 -->
        <div class="movie-box">
          <div class="movie-header">
            <h2>恐怖电影（115部）</h2>
            <a href="#">
              <span>全部></span>
            </a>
          </div>
          <div class="movies">
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie2.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie3.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie4.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
            <div class="movies-model">
              <a href="">
                <img src="../assets/styles/images/hotmovie1.png" alt="" srcset="">
              </a>
              <p>9.0分</p>
              <button class="buynow">立即购票</button>

            </div>
          </div>
        </div>
      </div>
      <!-- 侧边栏 -->
      <div class="aside">
        <!-- 今日大盘 -->
        <div class="aside-total">
          <h2 class="total-nav">今日大盘</h2>
          <div class="total-body">
            <h2>0.6 <span>万</span></h2>
            <span class="more">查看更多></span>
            <p class="time"><span>北京时间14:48:31</span> <span>猫眼专业版实时票房数据</span></p>
          </div>
        </div>

        <!-- top100 -->
        <div class="top">
          <h2>TOP 100 <span>查看完整榜单></span></h2>
          <div class="toplist">
            <ul class="topone">
              <li class=" top-item-first clearfix"  >
                <a href="#">
                  <div class="first-item clearfix">
                    <el-image :src="fileUploadHost + hotMovieList[0].images" style="width: 120px;height: 78px" :alt="hotMovieList[0].title">
                      <div slot="error" class="image-slot">
                        <img src="../assets/styles/images/2.jpg" alt="" style="width: 120px;height: 78px" >
                      </div>
                    </el-image>
                  </div>
                  <p class="name">{{hotMovieList[0].title}}</p>
                  <p class="money">{{hotMovieList[0].rate}}分</p>
                </a>
              </li>
              <li class="top-item" :key="movie.movieId" v-for="(movie,index) in hotMovieList">
                <a href="#">
                  <div class="index-box">
                    <i v-if="index < 2" class="index index-hot">{{index + 2}}</i>
                    <i v-else class="index">{{index + 2}}</i>
                    <span> {{movie.title}}</span>
                  </div>
                  <span class="index-money">{{movie.rate}}分</span>
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
                <a href="#">
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
              <li class="people-item" :key="actor.actorId" v-for="(actor,index) in hotActorList">
                <a href="#">
                  <div class="index-box">
                    <i v-if="index < 2" class="index index-hot">{{index + 2}}</i>
                    <i v-else class="index">{{index + 2}}</i>
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
                <a href="#">
                  <div class="first-item clearfix">
                    <el-image :src="fileUploadHost + hotArticleList[0].images" style="width: 120px;height: 68px" :alt="hotArticleList[0].title">
                      <div slot="error" class="image-slot">
                        <img src="../assets/styles/images/login-body-bg.png" alt="" style="width: 120px;height: 68px" >
                      </div>
                    </el-image>
                  </div>
                  <p class="name">{{hotArticleList[0].title | ellipsis(23)}}</p>
                </a>
              </li>
              <li class="hotspot-item" :key="article.articleId" v-for="(article,index) in hotArticleList" >
                <a href="#">
                  <div class="index-box">
                    <i v-if="index < 2" class="index index-hot">{{index + 2}}</i>
                    <i v-else class="index">{{index + 2}}</i>
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
