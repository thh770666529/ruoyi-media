<template>
  <div class="mt20">
    <!-- 轮播图 -->
    <el-carousel :interval="4000" type="card">
      <el-carousel-item v-for="banner in bannerList" :key="banner.bannerId">
        <a target="_blank" :href="banner.linkUrl">
          <el-image :src="fileUploadHost + banner.imageUrl" :alt="banner.title">
            <div slot="error" class="image-slot">
              <img src="../assets/styles/images/2.jpg" alt="" style="width: 1700px;height: 520px">
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
                  <img :src="fileUploadHost + item.images"/>
                  <div class="movie-overlay movie-overlay-bg">
                    <div class="movie-info">
                      <div class="movie-score">
                        <span class="fsize12" v-if="!item.rate">暂无评分</span>
                        <i class="integer">{{rateFormatter(item.rate, 0)}}</i><i class="fraction">{{rateFormatter(item.rate,
                        1)}}</i>
                      </div>
                      <div class="movie-title" :title="item.title">{{ item.title }}</div>
                    </div>
                  </div>
                </div>
                <button @click="toMovieDetail(item.movieId)">观看</button>
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
                  <img :alt="item.title" :src="fileUploadHost + item.images"/>
                  <div class="movie-overlay movie-overlay-bg">
                    <div class="movie-info">
                      <div class="movie-title" :title="item.title">{{ item.title }}</div>
                    </div>
                  </div>
                </div>
              </a>
              <p>111111111想看</p>
              <button @click="toMovieDetail(item.movieId)">观看</button>
              <button @click="toMovieDetail(item.movieId)">预告片</button>
              <p>{{item.publishTime?parseTime(item.publishTime, '{m}月{d}日'):`即将` }}上映</p>
            </div>

          </div>
        </div>
      </div>
      <!-- 侧边栏 -->
      <div class="aside">

        <el-card class="box-card mb20">
            <h2 class="hometitle">
              签到
              <el-tooltip content="每隔10分钟自动刷新" placement="left" effect="light">
                <i class="el-icon-refresh" @click="getSignData" style="cursor: pointer; float: right;"></i>
              </el-tooltip>
            </h2>
            <el-row style="line-height: 40px; text-align: center;">
              <el-col :lg="4">
                <el-tooltip content="通过发布文章、问答和评论可获取积分" placement="left" effect="light">
                  <i class="el-icon-coin"></i>
                </el-tooltip>
              </el-col>
              <el-col :lg="8">
                <span style="font-weight: 600;">{{signRecord.seriesDays}}</span> 积分
              </el-col>
              <el-col :lg="12">

                <el-button type="primary" v-if="signRecord.signinTodayFlag === 0" @click="toSignIn"
                           icon="el-icon-star-off" round>立刻签到
                </el-button>
                <el-button type="primary" v-else icon="el-icon-star-off" round disabled>
                  今日已签到
                </el-button>
              </el-col>
            </el-row>
            <el-row v-if="signRecord.continuityDays > 0"
                    style="margin-top: 10px; text-align: center; background-color: rgb(236, 248, 255); line-height: 30px;">
              <div>
                <i class="el-icon-ice-tea"></i>
                你已经连续签到
                <span style="color: dodgerblue; font-weight: 600;">
                  {{signRecord.continuityDays}}
                 </span>天啦！
              </div>
            </el-row>
        </el-card>


        <el-card class="mb20">
          <h2 class="hometitle">
            用户排行
            <i class="el-tooltip el-icon-refresh item" aria-describedby="el-tooltip-795" tabindex="0"
               style="cursor: pointer; float: right;"></i></h2>
            <ul>
              <li style="padding-right: 6px;">
                <div class="el-row">
                  <div class="el-col el-col-6" style="text-align: center;"><span class="el-avatar el-avatar--circle"
                                                                                 style="height: 40px; width: 40px; line-height: 40px; cursor: pointer;"><img
                    src="http://picture.moguit.cn//blog/admin/jpg/2020/1/31/1580434623431.jpg"
                    style="object-fit: fill;"></span></div>
                  <div class="el-col el-col-12">
                    <div><span
                      style="cursor: pointer; font-size: 13px; font-weight: 600; color: rgb(0, 0, 0); white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
              陌溪
            </span></div>
                    <div
                      style="font-size: 12px; color: rgb(114, 119, 123); white-space: nowrap; overflow: hidden; text-overflow: ellipsis;"><span><span
                      style="color: rgb(0, 167, 235);">494</span> 文章
            </span> <span><span style="color: rgb(0, 167, 235);">3</span> 问答
            </span> <span><span style="color: rgb(0, 167, 235);">263</span> 评论
            </span></div>
                  </div>
                  <div class="el-col el-col-6" style="line-height: 50px; text-align: center;"><i
                    class="el-icon-coin"><span style="font-weight: 550;"> &nbsp;1802</span></i></div>
                </div>
              </li>
            </ul>
            <ul>
              <li style="padding-right: 6px;">
                <div class="el-row">
                  <div class="el-col el-col-6" style="text-align: center;"><span class="el-avatar el-avatar--circle"
                                                                                 style="height: 40px; width: 40px; line-height: 40px; cursor: pointer;"><img
                    src="http://picture.moguit.cn//blog/admin/jpg/2021/11/29/1638175279455.jpg"
                    style="object-fit: fill;"></span></div>
                  <div class="el-col el-col-12">
                    <div><span
                      style="cursor: pointer; font-size: 13px; font-weight: 600; color: rgb(0, 0, 0); white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
              张建强
            </span></div>
                    <div
                      style="font-size: 12px; color: rgb(114, 119, 123); white-space: nowrap; overflow: hidden; text-overflow: ellipsis;"><span><span
                      style="color: rgb(0, 167, 235);">11</span> 文章
            </span> <span><span style="color: rgb(0, 167, 235);">1</span> 问答
            </span> <span><span style="color: rgb(0, 167, 235);">3</span> 评论
            </span></div>
                  </div>
                  <div class="el-col el-col-6" style="line-height: 50px; text-align: center;"><i
                    class="el-icon-coin"><span style="font-weight: 550;"> &nbsp;66</span></i></div>
                </div>
              </li>
            </ul>
            <ul>
              <li style="padding-right: 6px;">
                <div class="el-row">
                  <div class="el-col el-col-6" style="text-align: center;"><span class="el-avatar el-avatar--circle"
                                                                                 style="height: 40px; width: 40px; line-height: 40px; cursor: pointer;"><img
                    src="http://picture.moguit.cn//blog/admin/jpg/2021/12/2/1638454511732.jpg"
                    style="object-fit: fill;"></span></div>
                  <div class="el-col el-col-12">
                    <div><span
                      style="cursor: pointer; font-size: 13px; font-weight: 600; color: rgb(0, 0, 0); white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
              腐草为莹
            </span></div>
                    <div
                      style="font-size: 12px; color: rgb(114, 119, 123); white-space: nowrap; overflow: hidden; text-overflow: ellipsis;"><span><span
                      style="color: rgb(0, 167, 235);">5</span> 文章
            </span> <span><span style="color: rgb(0, 167, 235);">3</span> 问答
            </span> <span><span style="color: rgb(0, 167, 235);">10</span> 评论
            </span></div>
                  </div>
                  <div class="el-col el-col-6" style="line-height: 50px; text-align: center;"><i
                    class="el-icon-coin"><span style="font-weight: 550;"> &nbsp;60</span></i></div>
                </div>
              </li>
            </ul>
            <ul>
              <li style="padding-right: 6px;">
                <div class="el-row">
                  <div class="el-col el-col-6" style="text-align: center;"><span class="el-avatar el-avatar--circle"
                                                                                 style="height: 40px; width: 40px; line-height: 40px; cursor: pointer;"><img
                    src="http://picture.moguit.cn//blog/admin/jpg/2021/10/25/1635128619445.jpg"
                    style="object-fit: fill;"></span></div>
                  <div class="el-col el-col-12">
                    <div><span
                      style="cursor: pointer; font-size: 13px; font-weight: 600; color: rgb(0, 0, 0); white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
              Null
            </span></div>
                    <div
                      style="font-size: 12px; color: rgb(114, 119, 123); white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
                      <!----> <!----> <span><span style="color: rgb(0, 167, 235);">13</span> 评论
            </span></div>
                  </div>
                  <div class="el-col el-col-6" style="line-height: 50px; text-align: center;"><i
                    class="el-icon-coin"><span style="font-weight: 550;"> &nbsp;59</span></i></div>
                </div>
              </li>
            </ul>
            <ul>
              <li style="padding-right: 6px;">
                <div class="el-row">
                  <div class="el-col el-col-6" style="text-align: center;"><span class="el-avatar el-avatar--circle"
                                                                                 style="height: 40px; width: 40px; line-height: 40px; cursor: pointer;"><img
                    src="http://picture.moguit.cn//blog/admin/jpg/2021/12/3/1638522810665.jpg"
                    style="object-fit: fill;"></span></div>
                  <div class="el-col el-col-12">
                    <div><span
                      style="cursor: pointer; font-size: 13px; font-weight: 600; color: rgb(0, 0, 0); white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
              W
            </span></div>
                    <div
                      style="font-size: 12px; color: rgb(114, 119, 123); white-space: nowrap; overflow: hidden; text-overflow: ellipsis;"><span><span
                      style="color: rgb(0, 167, 235);">2</span> 文章
            </span> <!----> <span><span style="color: rgb(0, 167, 235);">6</span> 评论
            </span></div>
                  </div>
                  <div class="el-col el-col-6" style="line-height: 50px; text-align: center;"><i
                    class="el-icon-coin"><span style="font-weight: 550;"> &nbsp;54</span></i></div>
                </div>
              </li>
            </ul>
            <ul>
              <li style="padding-right: 6px;">
                <div class="el-row">
                  <div class="el-col el-col-6" style="text-align: center;"><span class="el-avatar el-avatar--circle"
                                                                                 style="height: 40px; width: 40px; line-height: 40px; cursor: pointer;"><img
                    src="http://picture.moguit.cn//blog/admin/jpg/2021/12/10/1639099941309.jpg"
                    style="object-fit: fill;"></span></div>
                  <div class="el-col el-col-12">
                    <div><span
                      style="cursor: pointer; font-size: 13px; font-weight: 600; color: rgb(0, 0, 0); white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
              天使抱抱我
            </span></div>
                    <div
                      style="font-size: 12px; color: rgb(114, 119, 123); white-space: nowrap; overflow: hidden; text-overflow: ellipsis;"><span><span
                      style="color: rgb(0, 167, 235);">2</span> 文章
            </span> <!----> <span><span style="color: rgb(0, 167, 235);">4</span> 评论
            </span></div>
                  </div>
                  <div class="el-col el-col-6" style="line-height: 50px; text-align: center;"><i
                    class="el-icon-coin"><span style="font-weight: 550;"> &nbsp;53</span></i></div>
                </div>
              </li>
            </ul>
            <ul>
              <li style="padding-right: 6px;">
                <div class="el-row">
                  <div class="el-col el-col-6" style="text-align: center;"><span class="el-avatar el-avatar--circle"
                                                                                 style="height: 40px; width: 40px; line-height: 40px; cursor: pointer;"><img
                    src="http://picture.moguit.cn//blog/admin/jpg/2021/8/7/1628305452929.jpg" style="object-fit: fill;"></span>
                  </div>
                  <div class="el-col el-col-12">
                    <div><span
                      style="cursor: pointer; font-size: 13px; font-weight: 600; color: rgb(0, 0, 0); white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
              touch fish
            </span></div>
                    <div
                      style="font-size: 12px; color: rgb(114, 119, 123); white-space: nowrap; overflow: hidden; text-overflow: ellipsis;"><span><span
                      style="color: rgb(0, 167, 235);">2</span> 文章
            </span> <!----> <span><span style="color: rgb(0, 167, 235);">12</span> 评论
            </span></div>
                  </div>
                  <div class="el-col el-col-6" style="line-height: 50px; text-align: center;"><i
                    class="el-icon-coin"><span style="font-weight: 550;"> &nbsp;50</span></i></div>
                </div>
              </li>
            </ul>
            <ul>
              <li style="padding-right: 6px;">
                <div class="el-row">
                  <div class="el-col el-col-6" style="text-align: center;"><span class="el-avatar el-avatar--circle"
                                                                                 style="height: 40px; width: 40px; line-height: 40px; cursor: pointer;"><img
                    src="http://picture.moguit.cn//blog/admin/jpg/2021/12/3/1638516754487.jpg"
                    style="object-fit: fill;"></span></div>
                  <div class="el-col el-col-12">
                    <div><span
                      style="cursor: pointer; font-size: 13px; font-weight: 600; color: rgb(0, 0, 0); white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
              🍄祖传摸骨🍄
            </span></div>
                    <div
                      style="font-size: 12px; color: rgb(114, 119, 123); white-space: nowrap; overflow: hidden; text-overflow: ellipsis;"><span><span
                      style="color: rgb(0, 167, 235);">4</span> 文章
            </span> <!----> <span><span style="color: rgb(0, 167, 235);">20</span> 评论
            </span></div>
                  </div>
                  <div class="el-col el-col-6" style="line-height: 50px; text-align: center;"><i
                    class="el-icon-coin"><span style="font-weight: 550;"> &nbsp;47</span></i></div>
                </div>
              </li>
            </ul>
            <ul>
              <li style="padding-right: 6px;">
                <div class="el-row">
                  <div class="el-col el-col-6" style="text-align: center;"><span class="el-avatar el-avatar--circle"
                                                                                 style="height: 40px; width: 40px; line-height: 40px; cursor: pointer;"><img
                    src="http://picture.moguit.cn//blog/admin/jpg/2021/12/2/1638436533912.jpg"
                    style="object-fit: fill;"></span></div>
                  <div class="el-col el-col-12">
                    <div><span
                      style="cursor: pointer; font-size: 13px; font-weight: 600; color: rgb(0, 0, 0); white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
              小强编码
            </span></div>
                    <div
                      style="font-size: 12px; color: rgb(114, 119, 123); white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
                      <!----> <!----> <span><span style="color: rgb(0, 167, 235);">6</span> 评论
            </span></div>
                  </div>
                  <div class="el-col el-col-6" style="line-height: 50px; text-align: center;"><i
                    class="el-icon-coin"><span style="font-weight: 550;"> &nbsp;41</span></i></div>
                </div>
              </li>
            </ul>
            <ul>
              <li style="padding-right: 6px;">
                <div class="el-row">
                  <div class="el-col el-col-6" style="text-align: center;"><span class="el-avatar el-avatar--circle"
                                                                                 style="height: 40px; width: 40px; line-height: 40px; cursor: pointer;"><img
                    src="http://picture.moguit.cn//blog/admin/jpg/2021/12/3/1638515163917.jpg"
                    style="object-fit: fill;"></span></div>
                  <div class="el-col el-col-12">
                    <div><span
                      style="cursor: pointer; font-size: 13px; font-weight: 600; color: rgb(0, 0, 0); white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
              hey
            </span></div>
                    <div
                      style="font-size: 12px; color: rgb(114, 119, 123); white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
                      <!----> <!----> <!----></div>
                  </div>
                  <div class="el-col el-col-6" style="line-height: 50px; text-align: center;"><i
                    class="el-icon-coin"><span style="font-weight: 550;"> &nbsp;40</span></i></div>
                </div>
              </li>
            </ul>
        </el-card>

        <!--关注我们-->
        <About></About>

        <!-- top100 -->

        <div class="top">
          <el-card class="box-card">
            <h2>TOP 100 <span>查看完整榜单></span></h2>
            <div class="toplist">
              <ul class="topone">
                <li class=" top-item-first clearfix" v-if="hotMovieList.length > 0">
                  <a :href="`/movie/` + hotMovieList[0].movieId">
                    <div class="first-item clearfix">
                      <el-image lazy :src="fileUploadHost + hotMovieList[0].images" style="width: 120px;height: 78px"
                                :alt="hotMovieList[0].title">
                        <div slot="error" class="image-slot">
                          <img src="../assets/styles/images/2.jpg" alt="" style="width: 120px;height: 78px">
                        </div>
                      </el-image>
                      <div class="movie-overlay">
                        <div class="movie-rank">
                          <i class="index index-hot">1</i>
                        </div>
                      </div>
                    </div>
                    <span class="name"> {{hotMovieList[0].title}}</span>
                    <span class="rate">{{hotMovieList[0].rate}}分</span>
                  </a>
                </li>
                <li class="top-item" :key="index" v-for="(item,index) in hotMovieList">
                  <a :href="`/movie/` + item.movieId" v-if="index > 0">
                    <div class="index-box">
                      <i v-if="index < 3" class="index index-hot">{{index + 1}}</i>
                      <i v-else class="index">{{index + 1}}</i>
                      <span> {{item.title}}</span>
                    </div>
                    <span class="index-rate">{{item.rate}}分</span>
                  </a>
                </li>
              </ul>
            </div>
          </el-card>
        </div>

        <!-- 热门演员 -->
        <div class="people">
          <el-card class="box-card">
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
                    <span class="name">{{hotActorList[0].name}}</span>
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
          </el-card>
        </div>

        <!-- 热门文章 -->
        <div class="hotspot">
          <el-card class="box-card">
            <h2>热门文章</h2>
            <div class="hotspotlist">
              <ul class="hotspots">
                <li class=" hotspot-item-first clearfix" v-if="hotArticleList.length > 0">
                  <a :href="`/article/` + hotArticleList[0].articleId">
                    <div class="first-item clearfix">
                      <el-image lazy :src="fileUploadHost + hotArticleList[0].images" style="width: 100%;height: 100%"
                                :alt="hotArticleList[0].title">
                        <div slot="error" class="image-slot">
                          <img src="../assets/styles/images/login-body-bg.png" alt="" style="width: 100%;height: 100%"/>
                        </div>
                      </el-image>
                      <div class="article-overlay">
                        <div class="article-rank">
                          <i class="index index-hot">1</i>
                        </div>
                      </div>
                    </div>
                    <span class="name">{{hotArticleList[0].title | ellipsis(18)}}</span>
                  </a>
                </li>
                <li class="hotspot-item" :key="article.articleId" v-for="(article,index) in hotArticleList">
                  <a :href="`/article/` + article.articleId" v-if="index > 0">
                    <div class="index-box">
                      <i v-if="index < 3" class="index index-hot">{{index + 1}}</i>
                      <i v-else class="index">{{index + 1}}</i>
                      <span>{{article.title ? article.title : `未知`}}</span>
                    </div>
                  </a>
                </li>
              </ul>
            </div>
          </el-card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>

  import index from '@/api/index';
  import movieApi from "@/api/media/movie";
  import {getDicts} from '@/api/system/dict/data';
  import {listTag} from "@/api/media/tag";
  import {listCategory} from "@/api/media/category";
  import { mapGetters } from 'vuex'

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
        this.tagOptions = response.rows;
      });
      index.getAllBannerList().then(response => {
        this.bannerList = response.data;
      });
      index.getHotMovieList().then(response => {
        this.hotMovieList = response.data;
      });
      index.getHotActorList().then(response => {
        this.hotActorList = response.data;
      });

      index.getHotArticleList().then(response => {
        this.hotArticleList = response.data;
      });
      const hotPlayMovieQueryParams = {pageNum: 1, pageSize: 8, orderByColumn: 'clickCount', isAsc: 'desc'};
      movieApi.listMovie(hotPlayMovieQueryParams).then(response => {
        this.hotPlayMovieList = response.rows;
        this.hotPlayMovieTotle = response.total;
      })
      // 获取签到数据
      this.getSignData()
    },
    computed: {
      ...mapGetters([
        'signRecord',
        'isLogin'
      ])
    },
    methods: {
      vailLogin() {
        if (!this.isLogin) {
          this.$notify.error({
            title: '警告',
            message: '登录后才可以签到哦~',
            offset: 100
          });
          this.$confirm('登录后才可以签到哦，是否进行登录', '提示', {
            confirmButtonText: '登录',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            //如果没有登录 则转到登录页面
            return this.$store.dispatch('showLoginForm')
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消登录'
            });
          });
          return false;
        } else {
          return true;
        }
      },
      getSignData() {
        if (!this.isLogin) {
          return;
        }
        this.$store.dispatch('GetSignData').then(() => { }).catch(err => {});
      },
      toSignIn() {
        if (!this.vailLogin()) {
          return;
        }
        this.$store.dispatch('SignIn').then(() => {this.msgInfo("签到成功！")}).catch(err => {});
      },
      toMovieDetail(movieId) {
        window.open('/movie/' + movieId, '_blank')
      },
      rateFormatter(rate, index) {
        if (!rate) {
          if (index === 1) {
            return '';
          }
          return '';
        }
        let number = String(rate);
        const numberArr = number.split('.');
        number = numberArr[index];
        if (!number) {
          return '';
        }
        return index === 0 ? number : '.' + number;
      },
      actorFormatter(actorList) {
        if (actorList.length === 0) {
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
      tagFormat(tagOptions, tagId) {
        if (!tagId || !tagOptions) {
          return ''
        }
        const currentSeparator = ",";
        let actions = [];
        let tempArr = tagId.split(currentSeparator);
        for (let i = 0; i < tempArr.length; i++) {
          for (let j = 0; j < tagOptions.length; j++) {
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
  .hometitle { font-size: 18px; color: #282828; font-weight: 600; margin: 0; text-transform: uppercase; padding-bottom: 15px; margin-bottom: 25px; position: relative; }
  .hometitle:after { content: ""; background-color: #282828; left: 0; width: 50px; height: 2px; bottom: 0; position: absolute; -webkit-transition: 0.5s; -moz-transition: 0.5s; -ms-transition: 0.5s; -o-transition: 0.5s; transition: 0.5s; }

</style>
