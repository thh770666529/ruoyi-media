<template>
  <div >
    <!-- 电影简介 -->
    <div class="movie-top">
      <div class="movie-banner">
        <div class="movie-img">
          <el-image :src="fileUploadHost + movie.images" :alt="movie.title">
            <div slot="error" class="image-slot">
              <img src="../../assets/styles/images/hotmovie1.png" alt="" style="width: 100%" >
            </div>
          </el-image>
        </div>
        <div class="movie-data">
          <div class="movie-name">
            <h2 class="name" id="moviename">{{movie.title}}</h2>
            <!-- <h2 class="othername" id="englishname">天気の子</h2>-->
            <p class="kind" id="kind">{{selectDictLabel(typeOptions, movie.type)}}</p>
            <p class="kind" id="category">{{ movie.categoryName ? movie.categoryName: '无' }}</p>
            <p class="area" id="adress">{{selectDictLabel(countryOptions, movie.country)}} / {{totalLength}}</p>
            <p class="time" id="time"> {{ parseTime(movie.publishTime, '{y}-{m}-{d}') }}上映</p>
            <template v-for="(item, index) in tagOptions">
              <template v-if="getTagArray(movie.tagId).includes(String(item.tagId))">
              <span
                style="margin: 20px 5px 0 0"
                v-if="item.listClass == 'default' || item.listClass == ''"
                :key="item.tagId"
                :index="index"
                :class="item.cssClass">{{ item.content }}
              </span>
                <el-tag
                  v-else
                  style="margin: 20px 5px 0 0"
                  :key="item.tagId"
                  :index="index"
                  :type="item.listClass === 'primary' ? '' : item.listClass"
                  :class="item.cssClass? item.cssClass: ''">
                  {{ item.content }}
                </el-tag>
              </template>
            </template>


          </div>
          <div class="movie-btn">
           <!-- <a href="#" class="like"><i class="normal"></i> <span>想看</span></a>-->
            <Collect :targetId="movieId" :tableName="tableName" :collectCount="movie.collectionCount"></Collect>
          </div>
          <div class="watching">
            <p class="name">评分</p>
            <el-rate
              v-if="movie.rate"
              v-model="movie.rate"
              disabled
              style="font-size:24px;"
              show-score
              text-color="#ff9900"
              score-template="{value}">
            </el-rate>
            <span style="color: #ffb400;" v-else>暂无评分</span>
            <p class="money mt10"><span class="iconfont">&#xe8c7; </span>点击数</p>
            <p class="num">{{movie.clickCount}}</p>
          </div>
        </div>
      </div>
    </div>
    <!-- 页面主体 -->
    <div class="container">
      <!-- 左部主题内容 -->
      <div class="main">
        <div class="nav">
          <div class="intro">
            <span>剧集列表</span>
          </div>
          <el-row :gutter="24">
            <el-col :xs="12" :sm="8" :md="6" :lg="4"  style="padding: 8px;" v-for="video in videoList" :key="video.videoId" >
              <el-tooltip  :content="video.title" placement="top">
                <el-button type="danger" v-if="video.title && video.title.length < 6" @click="play(video.videoId)"  style="margin-bottom: 20px" plain>{{ video.title | ellipsis(6)}}</el-button>
                <el-button type="danger" v-else @click="play(video.videoId)"  style="margin-bottom: 20px" plain>{{ video.title | ellipsis(6)}}</el-button>
              </el-tooltip>
            </el-col>
          </el-row>
          <p v-if="videoList.length === 0" style="color:#ef4238">  暂时没有播放的视频！请联系管理员进行上传！</p>
        </div>

        <!-- 标签页切换控制 -->
        <el-tabs v-model="activeName">
          <el-tab-pane label="介绍" name="description">
            <el-card class="box-card">
            <div class="referral">
              <div class="intro">
                剧情简介
              </div>
              <span v-html="movie.description">{{movie.description}}</span>
            </div>
            </el-card>
          </el-tab-pane>
          <el-tab-pane label="演职人员" name="actor">
            <!-- 演职人员 -->
            <div class="actor">
              <div class="director">
                <p>导演</p>
                <div class="director-img" :key="index" v-for="(item,index) in directorList">
                  <router-link :to="'/actor/' + item.actorId" tag="a" :title="item.name" target="_blank" active-class="fsize20 #333" exact>
                    <img  class="default-img" :src="fileUploadHost + item.avatar" alt="">
                    <p>{{item.name | ellipsis(10) }}</p>
                  </router-link>
                </div>
                <span  style="color:#ef4238"  v-if="directorList.length === 0">
                  导演信息未录入
                </span>
              </div>
              <div class="starts">
                <p>演员</p>
                <div class="start" :key="index" v-for="(item,index) in actorList">
                  <router-link :to="'/actor/' + item.actorId" tag="a" :title="item.name" target="_blank" active-class="fsize20 #333" exact>
                    <img class="default-img" :alt="item.name" :src="fileUploadHost + item.avatar">
                    <p>{{item.name | ellipsis(10) }}</p>
                  </router-link>
                  <!--<p>饰:森鸟帆高</p>-->
                </div>
                <span  style="color:#ef4238"  v-if="actorList.length === 0">
                  演员信息未录入
                </span>
              </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="图集" name="images">
            <!-- 图集 -->
            <div class="images">
              <div class="intro">
                <div class="intro-text">
                  <span>图集</span>
                  <span>
                            <a href="#">
                                全部>
                            </a>
                        </span>
                </div>
              </div>
              <div class="images-main">
                <a href="#">
                  <img class="default-img" alt="天气之子剧照图集" src="https://p0.meituan.net/movie/95300ef2d49506fd68a2a56897bc5043315152.jpg@465w_258h_1e_1c">
                </a>
              </div>
              <div class="images-aside">
                <div class="aside-img1">
                  <img class="default-img" alt="天气之子剧照图集" src="https://p0.meituan.net/movie/11e1b4c016551a5f8be80612f6062a7d131097.jpg@126w_126h_1e_1c">
                </div>
                <div class="aside-img2">
                  <img class="default-img" alt="天气之子剧照图集" src="https://p1.meituan.net/movie/7172e7db970129838ebd068372a2bfb1152767.jpg@126w_126h_1e_1c">
                </div>
                <div class="aside-img3">
                  <img class="default-img" alt="天气之子剧照图集" src="https://p0.meituan.net/movie/55e6794ea722a7a58b410e38ae4b7cd7156727.jpg@126w_126h_1e_1c">
                </div>
                <div class="aside-img4">
                  <img class="default-img" alt="天气之子剧照图集" src="https://p0.meituan.net/movie/2b38aeed774ed7fefd72b47ff191cc9c116946.jpg@126w_126h_1e_1c">
                </div>
              </div>
            </div>
          </el-tab-pane>
        </el-tabs>


        <!-- 评论 -->
        <div class="comment mt20">
          <el-card class="box-card">
          <div class="intro">
            <div class="intro-text">
              <span>電影短評</span>
            </div>
          </div>
            <Comment :targetId="movieId" :tableName="tableName"></Comment>
          </el-card>
        </div>

      </div>
      <!-- 侧边栏 -->
      <div class="aside">
        <!-- 相关电影 -->
        <div class="other-movie">
          <div class="referral">
            <div class="intro">
              相关电影
            </div>
          </div>
          <div class="other-movie-body">
            <ul>
              <li class="othermovie-model" :key="index" v-for="(item,index) in sameTypeMovieList">
                <a :href="`/movie/` + item.movieId">
                  <div class="movie-poster">
                    <img v-if="item.images" :alt="item.title" :src="fileUploadHost + item.images">
                  </div>
                  <p>{{item.title}}</p>
                </a>
                <i>{{item.rate ? item.rate: `暂无评分`}}</i>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import '@/assets/styles/less/moviedetail.less'
import movieApi from '@/api/media/movie';
import { listTag } from "@/api/media/tag";
import { listCategory } from "@/api/media/category";
import { getDictsByTypeList } from '@/api/system/dict/data';
import { checkCollectFlag, addCollect, cancelCollectByTargetId } from "@/api/website/collect";
import { selectDictLabels } from '@/utils/ruoyi';
export default {
  components: {
  },
  data() {
    return {
      tableName: 'wm_movie',
      movieId: '',
      countryOptions: [],
      statusOptions: [],
      typeOptions: [],
      tagOptions: [],
      movie: {},
      videoList: [],
      actorList: [],
      directorList: [],
      video: {},
      activeName: 'description',
      sameTypeMovieList: []
    }
  },
  created() {
    this.movieId = this.$route.params && this.$route.params.movieId;
    listTag({status: '1'}).then(response => {
      this.tagOptions =  response.rows;
    });
    this.getDictList();
    this.getDetail();
  },

  computed:{
    totalLength() {
      if (this.videoList.length === 0){
        return '无'
      } else {
        return this.videoList[0].length;
      }
    }
  },
  methods:{
    getDictList() {
      // 获取字典
      const dictTypeList =  ['movie_country', 'movie_status', 'movie_type'];
      getDictsByTypeList(dictTypeList).then(response => {
        this.countryOptions = response.data.movie_country;
        this.statusOptions= response.data.movie_status
        this.typeOptions= response.data.movie_type
      });
    },
    //查询电影详情信息
    getDetail() {
      movieApi.getMovie(this.movieId).then(response => {
        this.movie = response.data;
        this.videoList = this.movie.videoList;
        this.actorList = this.movie.actorList;
        this.directorList=this.movie.directorList;
        if (this.videoList.length > 0 ) {
          this.video = this.videoList[0]
        }
      });
      movieApi.getSameTypeMovieList(this.movieId).then(response => {
        this.sameTypeMovieList = response.data;
      });
    },
    //播放
    play(videoId){
      if (this.videoList.length === 0){
        this.msgError("暂时没有播放的视频！请联系管理员进行上传！")
      }else {
        const routeData = this.$router.resolve({
          path: "/video/" + videoId,
          query: {}
        });
        window.open(routeData.href, '_blank');
      }
    },
    //标签翻译
    getTagArray(tagId) {
      if (!tagId) {
        return []
      }
      return tagId.split(',');
    }
  }
};
</script>
<style scoped>
.cc-mask{background:rgba(0,0,0,.4);height:100%;left:0;opacity:0;filter:alpha(opacity=0);position:absolute;right:0;top:0;transition:all .3s ease 0s;width:100%;transition:.3s;-webkit-transition:.3s}
.movies-body ul li:hover .img-responsive{transform:scale(1.1);-webkit-transform:scale(1.1)}
.movies-body ul li:hover .cc-mask{opacity:1;filter:alpha(opacity=100)}

>>> .el-tabs__nav .el-tabs__item {
  font-size: 22px;
  color: #ef4238;
}
>>> .el-tabs__item.is-active {
  color: #ef4238;
}
>>> .el-tabs__active-bar{
  background-color: #ef4238;
}

.default-img {
  background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEQAAAA+CAYAAACSqr0VAAAAAXNSR0IArs4c6QAAAAlwSFlzAAALEwAACxMBAJqcGAAABBdpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IlhNUCBDb3JlIDUuNC4wIj4KICAgPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4KICAgICAgPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIKICAgICAgICAgICAgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iCiAgICAgICAgICAgIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIgogICAgICAgICAgICB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iCiAgICAgICAgICAgIHhtbG5zOnRpZmY9Imh0dHA6Ly9ucy5hZG9iZS5jb20vdGlmZi8xLjAvIj4KICAgICAgICAgPHhtcE1NOkRlcml2ZWRGcm9tIHJkZjpwYXJzZVR5cGU9IlJlc291cmNlIj4KICAgICAgICAgICAgPHN0UmVmOmluc3RhbmNlSUQ+eG1wLmlpZDpFODIyRENGNzM5NzMxMUU2OENCMjlCOTg0QUUzQjg3Qzwvc3RSZWY6aW5zdGFuY2VJRD4KICAgICAgICAgICAgPHN0UmVmOmRvY3VtZW50SUQ+eG1wLmRpZDpFODIyRENGODM5NzMxMUU2OENCMjlCOTg0QUUzQjg3Qzwvc3RSZWY6ZG9jdW1lbnRJRD4KICAgICAgICAgPC94bXBNTTpEZXJpdmVkRnJvbT4KICAgICAgICAgPHhtcE1NOkRvY3VtZW50SUQ+eG1wLmRpZDpFODIyRENGQTM5NzMxMUU2OENCMjlCOTg0QUUzQjg3QzwveG1wTU06RG9jdW1lbnRJRD4KICAgICAgICAgPHhtcE1NOkluc3RhbmNlSUQ+eG1wLmlpZDpFODIyRENGOTM5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);
  background-position: 50%;
  background-size: 68px 62px;
  background-repeat: no-repeat;
  width: 128px;
  height: 170px;
}


</style>
