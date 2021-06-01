<template>

  <div>
    <!-- 左（上下）右-->
    <section class="container">

    <section class="path-wrap txtOf hLh30">
        <router-link to="/" tag="a" active-class="c-999 fsize14" title="首页">
          首页
        </router-link>
    </section>

    <!--左边-->
    <el-row type="flex">
      <el-col :md="18" :xs="24" :sm="24">
        <!--电影封面信息-->
        <div>
          <el-card>
            <article class="c-v-pic-wrap" >
              <section class="p-h-video-box" >
                <img height="380px" :src="`http://localhost:7070`+movie.images" :alt="movie.title" class="dis c-v-pic">
              </section>
            </article>
            <aside class="c-attr-wrap">
              <section class="ml20 mr15">
                <h2 class="hLh30 txtOf mt15">
                  <span class="c-fff fsize24">{{movie.title}}</span>
                </h2>
                <section class="c-attr-mt10 c-attr-undis">
                  <span class="c-fff fsize14">分类：&nbsp;{{selectDictLabel(typeOptions, movie.type)}}</span>&nbsp;&nbsp;
                  <span class="c-fff fsize14">地区：&nbsp;{{selectDictLabel(countryOptions, movie.country)}}</span>&nbsp;&nbsp;
                  <span class="c-fff fsize14">年份：&nbsp;{{ parseTime(movie.publishTime, '{y}') }}</span>
                </section>

                <section class="c-attr-mt10 c-attr-undis">
                  <span class="c-fff fl">评分：</span>
                  <el-rate
                    v-model="movie.rate"
                    disabled
                    style="font-size:20px;"
                    show-score
                    text-color="#ff9900"
                    score-template="{value}">
                  </el-rate>
                </section>
                <section class="c-attr-mt10 c-attr-undis">
                  <span class="c-fff fsize14"> 导演：&nbsp; </span>
                  <span class="c-fff fsize14" v-if="!directorList||directorList.length==0">
                  无
              </span>
                  <span class="c-fff fsize14" v-for="director in directorList" :key="director.actorId">
                  {{director.name}}&nbsp;
              </span>
                </section>
                <section class="c-attr-mt10 c-attr-undis">
                  <span class="c-fff fsize14">主演：&nbsp;</span>
                  <span class="c-fff fsize14" v-if="!actorList||actorList.length==0">
                  无
              </span>
                  <span class="c-fff fsize14" v-for="actor in actorList" :key="actor.actorId">
                 {{actor.name}}&nbsp;
              </span>
                </section>
                <section class="c-attr-mt10 c-attr-undis">
                  <span class="c-fff fsize14">标签：&nbsp;{{labelValue}}</span>
                </section>

                <section class="c-attr-mt10 c-attr-undis">
                  <span class="c-fff fsize14">发布人：&nbsp;{{movie.publishUsername}}</span>
                </section>

                <section class="c-attr-mt of">
                  <el-button icon="el-icon-thumb" size="mini" type="primary" plain >赞</el-button>
                  <el-button type="warning" size="mini" icon="el-icon-star-off" plain />
                </section>

                <section class="c-attr-mt">
                  <a @click="play()" title="立即观看" class="comm-btn c-btn-3">立即观看</a>
                </section>
              </section>
            </aside>
            <div class="clear"></div>

          </el-card>
        </div>

        <!--电影描述-->
        <div class="mt15 mb50">
          <el-card>

            <el-tabs v-model="activeName" >
              <el-tab-pane label="电影详情" name="description">
                <span slot="label"><i class=" el-icon-info"></i> 电影详情</span>
                <section class="course-txt-body">
                  <p v-html="movie.description">{{movie.description}}</p>
                </section>
              </el-tab-pane>
              <el-tab-pane label="用户评论"  name="comment">
                <span slot="label"><i class="el-icon-chat-dot-round"></i> 用户评论</span>
                <div >
                  <ul>
                    <CommentBox
                      :userInfo="userInfo"
                      :commentInfo="commentInfo"
                      @submit-box="submitBox"
                      :showCancel="showCancel"
                    ></CommentBox>
                    <!--<div class="message_infos">
                      <CommentList :comments="comments" :commentInfo="commentInfo"></CommentList>
                      <div class="noComment" v-if="comments.length ==0">还没有评论，快来抢沙发吧！</div>
                    </div>-->
                  </ul>
                </div>


              </el-tab-pane>
            </el-tabs>
          </el-card>
        </div>
      </el-col>

      <!--右边-->
      <el-col  class="hidden-sm-and-down" :md="6">
        <!--剧情集数-->
        <div class="ml15">
          <el-menu
            :default-active="`/video/`+video.videoId"
            class="el-menu-vertical-demo"
            background-color="#f4f4f4"
            text-color="#222"
            active-text-color="#00a1d6"
            router
            >
            <el-submenu index="0">
              <template slot="title">
                <i class="el-icon-film"></i>
                <span>剧集列表</span>
              </template>
              <el-menu-item   :index="`/video/`+video.videoId" v-for="video in videoList" :key="video.videoId" >
               {{video.title}}
                <div class="fr">
                  <i  class="free-icon vam">免费试听</i>
                </div>
              </el-menu-item>

              <el-menu-item    v-if="videoList.length==0"  >
                该视频弹幕为空
              </el-menu-item>

            </el-submenu>
          </el-menu>
        </div>
      </el-col>
    </el-row>
    </section>
  </div>

</template>

<script>
import movieApi from '@/api/movie'
//import CommentList from "@/components/article/CommentList";
import CommentBox from "@/components/article/CommentBox";
export default {
  // 异步调用
  async asyncData({ params, error }) {
     return {movieId: params.movieId}
   },
  components: {
    //注册组件
    //CommentList,
    CommentBox
  },
   data() {
     return {
        movie: {},
        videoList: [],
        video:{},
        actorList:[],
        directorList:[],
        isbuy: false,
       //电影国家字典
       countryOptions:[],
       //状态字典
       statusOptions:[],
       //电影类型
       typeOptions:[],
       //标签字典
       labelOptions:[],
       //标签值
       labelValue:'',
       activeName:'description',
       // 评论
       showCancel: false,
       comments: [],
       commentInfo: {
         // 评论来源： MESSAGE_BOARD，ABOUT，BLOG_INFO 等 代表来自某些页面的评论
         source: "movieDetail",
         objId: this.$route.params.moviedId
       },
       userInfo: {},
       faceList:["https://cdn.jsdelivr.net/gh/volantis-x/cdn-emoji@1.0.0/valine/twemoji/twemoji-124.png",
         "https://cdn.jsdelivr.net/gh/volantis-x/cdn-emoji@1.0.0/valine/twemoji/twemoji-125.png"]
     }
   },
  async created() {//在页面渲染之前执行
    await this.getDicts("movie_country").then(response => {
      this.countryOptions = response.data;
    });
    await this.getDicts("movie_status").then(response => {
      this.statusOptions = response.data;
    });
    await this.getDicts("movie_type").then(response => {
      this.typeOptions = response.data;
    });
    await this.getDicts("movie_label").then(response => {
      this.labelOptions = response.data;
    });

    this.initInfo();
  },
   methods:{
     //查询电影详情信息
     async initInfo() {
        const response = await movieApi.getMovie(this.movieId)
        this.movie=response.data
        this.videoList=this.movie.videoList
        this.actorList=this.movie.actorList
        this.directorList=this.movie.directorList
        this.labelValue =  this.selectDictLabels(this.labelOptions, this.movie.label)
        if (this.videoList.length > 0 ) {
         this.video = this.videoList[0]
        }


     },
     //播放
     play(){
       if (this.videoList.length==0){
         this.msgError("暂时没有播放的视频！请联系管理员进行上传！")
       }else {
         window.open("/video/"+this.videoList[0].videoId);
       }
     },
     // 发表评论
     submitBox(e) {
       this.msgSuccess("发表成功！")
     },
   }
};
</script>
<style>
  .c-v-pic-wrap{width:60%;height:380px}
  .thr-attr-box{width:120px;height:380px}
  .c-attr-wrap{width:40%;height:380px}

  .el-menu-vertical-demo:not(.el-menu--collapse) {
    height: 420px;
    overflow: auto;
  }
  .container{width:1400px}

</style>
