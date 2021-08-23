<template>
  <div class="container">
      <!--左边-->
      <el-row type="flex">
        <el-col :md="18" :xs="24" :sm="24">
          <div class="mt20">
          <span class="fsize20">{{movie.title}}</span>
          </div>
          <div style="min-height: 529px">
           <video-preview ref="player"></video-preview>
          </div>
          <!--电影描述-->
          <div class="mt15 mb20">
            <el-card>
              <el-tabs v-model="activeName">
                <el-tab-pane label="电影详情" name="description">
                  <section class="course-txt-body">
                    <p v-html="movie.description">{{movie.description}}</p>
                  </section>
                </el-tab-pane>
                <el-tab-pane label="用户评论" name="comment">
                  <span slot="label"><i class="el-icon-chat-dot-round"></i> 用户评论</span>
                  <div >
                    <ul>
                      <CommentBox
                        :userInfo="userInfo"
                        :commentInfo="commentInfo"
                        @submit-box="submitBox"
                        :showCancel="showCancel"
                      ></CommentBox>
                      <div class="message_infos">
                        <CommentList :comments="comments" :commentInfo="commentInfo"></CommentList>
                        <div class="noComment" v-if="comments.length === 0">还没有评论，快来抢沙发吧！</div>
                      </div>
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
          <div class="ml15 mt50">
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
                <el-menu-item  :index="`/video/`+video.videoId" v-for="video in movie.videoList" :key="video.videoId" >
                  {{video.title}}
                  <!--<div class="fr">
                    <i  class="free-icon vam">免费观看</i>
                  </div>-->
                </el-menu-item>

              </el-submenu>
            </el-menu>
          </div>
        </el-col>
      </el-row>
  </div>
</template>

<script>
  import VideoPreview from '@/components/video/VideoPreview'
  import movieApi from '@/api/movie'
  import { mapActions,mapState, mapMutations} from 'vuex'
  import CommentList from "@/components/website/CommentList"
  import CommentBox from "@/components/website/CommentBox"
  import {replyComment,treeListComment} from '@/api/comment'
  import { getWebConfig } from '@/api/webConfig'
  import { getToken, setToken, removeToken, getCookies,setCookies,removeCookies } from '@/utils/token'
  export default {
    name: 'ckplayerPlugin',
    components: {
      CommentList,
      CommentBox,
      VideoPreview
    },
    computed:{
      ...mapState(['userInfo'])
    },
    data () {
      return {
        activeName: 'description',
        video: {},
        movie: {},
        sourceVideoUrl: '',
        comments: [],
        commentInfo: {
          sid: this.$route.params.videoId
        },
        videoPreviewList: [],
        // 评论
        showCancel: false
      }
    },
    created(){
      this.getVideoById();
      this.getCommentList();
    },
    mounted(){
    },
    watch:{
      "$route":{
        handler(route){
            this.getVideoById();
        }
      }
    },
    methods:{
      async getVideoById(){
        const videoId = this.$route.params && this.$route.params.videoId;
        let webConfig = getCookies('webConfig')
        if(!webConfig) {
          await getWebConfig().then(response => {
            let data = response.data
            if (data.showList) {
              let showList = JSON.parse(data.showList)
              let loginTypeList = JSON.parse(data.loginTypeList)
              data.showList = showList;
              data.loginTypeList = loginTypeList;
            } else {
              data.showList = []
            }
            setCookies('webConfig', data)
            webConfig = data
          });
        } else {
          webConfig = JSON.parse(webConfig)
        }
          await movieApi.getMovieVideo(videoId).then(response => {
            this.video=response.data.video;
            this.movie=response.data.movie;
            const openSteamMedia = webConfig.openSteamMedia
            console.log(webConfig)
            if(openSteamMedia === '1'){
              if(this.video.superDefinitionUrl){
                this.addVideoPreview('超清', this.fileUploadHost + this.video.superDefinitionUrl, 'application/x-mpegURL')
              }
              if(this.video.highDefinitionUrl){
                this.addVideoPreview('高清', this.fileUploadHost + this.video.highDefinitionUrl, 'application/x-mpegURL')
              }
              if(this.video.standardDefinitionUrl){
                this.addVideoPreview('标清', this.fileUploadHost + this.video.standardDefinitionUrl, 'application/x-mpegURL')
              }
            }else {
              this.addVideoPreview('原文件', this.fileUploadHost + this.video.url, 'video/mp4')
            }
            console.log("this.videoPreviewList", this.videoPreviewList)
            let data = {
              videoPreviewVisible: true,
              videoPreviewList: this.videoPreviewList,
              activeIndex: 0,
              openSteamMedia: webConfig.openSteamMedia
            }
            if(!this.videoPreviewList.length) {
              this.msgError("没有转化好的视频文件，请联系管理员！")
              data.videoPreviewVisible = false
            }
            this.$store.commit('videoPreview/setVideoPreviewData', data)
            //this.videoUrl = 'http://220.161.87.62:8800/hls/0/index.m3u8';
          });
      },
      addVideoPreview(name, url, type) {
        let data = {
          withCredentials: false,
          type: type,
          src: url,
          name: name
        }
        this.videoPreviewList.push(data)
      },
      // 发表评论
      submitBox(e) {
        let params = {};
        params.sid = e.sid;
        params.content = e.content;
        params.commentId = e.commentId;
        params.createTime = e.createTime;
        params.updateTime = e.createTime;
        params.tableName = 'wm_video';
        params.url = this.$route.path;
        params.support = 0;
        params.oppose = 0;
        replyComment(params).then(response => {
          if (response.code === 200){
            this.msgSuccess("发表成功！")
            this.getCommentList()
          }else{
            this.msgError("发表失败！")
          }
        });
      },
      getCommentList: function () {
        let params = {};
        params.sid = this.commentInfo.sid
        treeListComment(params).then(response => {
          if (response.code === 200) {
            this.comments = response.rows;
          }
        });
      }

    }
  }
</script>

<style scoped>

  .el-menu-vertical-demo:not(.el-menu--collapse) {
    height: 420px;
    overflow: auto;
  }

  .container{width:1400px;}

</style>
