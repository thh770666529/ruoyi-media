<template>
  <div class="video-body">
    <el-row type="flex" v-if="!dialogVideoFile.visible">
      <!--左边-->
      <el-col :md="18" :xs="24" :sm="24">
        <div class="mt20">
          <span class="fsize20">{{movie.title}}</span>
        </div>
        <div style="min-height: 529px">
          <iFrame v-show="video.url"
                  v-if="video.storageType === `iframe`"
                  :src="video.url"
          />
          <video-preview v-else ref="player" :videoId="videoId" :autoplay="!dialogVideoFile.visible"></video-preview>
        </div>

        <!-- 评论 -->
          <el-card class="box-card mt20">
            <div class="intro">
              <div class="intro-text">
                <span>電影短評</span>
              </div>
            </div>
            <Comment :targetId="movie.movieId" tableName="wm_movie"></Comment>
          </el-card>
      </el-col>

      <!--右边-->
      <el-col  class="hidden-sm-and-down" :md="6">
        <!--剧情集数-->
        <div class="ml15 mt50">
          <el-menu
            :default-active="`/video/` + video.videoId"
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
                <span class="fsize12">{{ video.title | ellipsis(20) }}</span>
              </el-menu-item>

            </el-submenu>
          </el-menu>
        </div>
      </el-col>
    </el-row>
    <!-- 视频秘钥对话框 -->
    <el-dialog
      title="视频密码"
      :visible.sync="dialogVideoFile.visible"
      :show-close="false"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      width="500px">
      <el-form
        ref="passwordForm"
        :model="dialogVideoFile.passwordForm"
        :rules="dialogVideoFile.passwordFormRules"
        label-width="80px">
        <el-form-item label="密码" prop="password">
          <el-input type="password" show-password placeholder="密码" minlength="6" maxlength="12" @keyup.enter.native="handleSubmitBtnClick('passwordForm')" v-model="dialogVideoFile.passwordForm.password"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button plain type="primary" @click.native.prevent="handleSubmitBtnClick('passwordForm')">提 交</el-button>
        <el-button plain type="primary" @click="handleCloseBtnClick()">关 闭</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import VideoPreview from '@/components/Play/VideoPreview'
import movieApi from '@/api/media/movie'
import {getWebConfig} from '@/api/website/webConfig'
import Comment from "@/components/Comment"
import iFrame from "@/components/iFrame";
import {videoPlayer} from 'vue-video-player'

export default {
  name: 'playVideo',
  components: {
    VideoPreview,
    Comment,
    iFrame
  },
  computed: {},
  data() {
    return {
      activeName: 'description',
      video: {},
      movie: {},
      sourceVideoUrl: '',
      videoPreviewList: [],
      webConfig: {},
      // 视频秘钥对话框数据
      dialogVideoFile: {
        visible: false,
        passwordForm: {
          password: ''
        },
        passwordFormRules: {
          password: [
            {
              required: true,
              message: '请输入密码',
              trigger: 'blur'
            },
            { min: 6,
              max: 12,
              message:
                '密码长度必须介于 6 和 12 之间',
              trigger: 'blur'
            }
          ]
        }
      }
    }
  },
  created() {
    this.videoId = this.$route.params && this.$route.params.videoId;
    getWebConfig().then(response => {
      let data = response.data;
      if (data.showList) {
        let showList = JSON.parse(data.showList);
        let loginTypeList = JSON.parse(data.loginTypeList);
        data.showList = showList;
        data.loginTypeList = loginTypeList;
      } else {
        data.showList = [];
      }
      this.webConfig = data
    });
    this.getVideoById(this.videoId);
  },
  mounted() {
  },
  watch: {
    "$route": {
      async handler(route) {
        const videoId = route.params && route.params.videoId;
        await this.getVideoById(videoId);
      }
    }
  },
  methods: {
    getVideoById(videoId) {
      movieApi.getMovieVideo(videoId).then(response => {
        this.video = response.data.video;
        this.movie = response.data.movie;
        const key = `movie_pwd:${this.movie.movieId + `:` + this.$store.state.user.userId}`
        const passwordFlag = this.getCookies(key);
        if (passwordFlag  === 'true'){
          this.playVideo();
          this.dialogVideoFile.visible = false;
        }else if (this.movie.openPassword == 1){
          this.dialogVideoFile.visible = true;
        }else {
          this.playVideo();
        }
      });
    },
    playVideo() {
      if (this.video.storageType === `iframe`){
        return
      }
      this.videoPreviewList = []
      const openSteamMedia = this.webConfig.openSteamMedia;
      if (openSteamMedia === '1') {
        if (this.video.superDefinitionUrl) {
          this.addVideoPreview('超清', this.fileUploadHost + this.video.superDefinitionUrl, 'application/x-mpegURL');
        }
        if (this.video.highDefinitionUrl) {
          this.addVideoPreview('高清', this.fileUploadHost + this.video.highDefinitionUrl, 'application/x-mpegURL');
        }
        if (this.video.standardDefinitionUrl) {
          this.addVideoPreview('标清', this.fileUploadHost + this.video.standardDefinitionUrl, 'application/x-mpegURL');
        }
      } else {
        this.addVideoPreview('原文件', this.fileUploadHost + this.video.url, 'video/mp4');
      }
      let data = {
        videoPreviewVisible: true,
        videoPreviewList: this.videoPreviewList,
        activeIndex: 0,
        openSteamMedia: openSteamMedia
      };
      if (!this.videoPreviewList.length) {
        this.$modal.msgError("没有转化好的视频文件，请联系管理员！");
        data.videoPreviewVisible = false
      }
      this.$store.commit('setVideoPreviewData', data);
    },
    addVideoPreview(name, url, type) {
      let data = {
        withCredentials: false,
        type: type,
        src: url,
        name: name
      };
      this.videoPreviewList.push(data)
    },
    handleCloseBtnClick() {
      this.dialogVideoFile.visible = false
      this.$router.push({ path: '/movie/' + this.movie.movieId, query: {} })
    },
    handleSubmitBtnClick(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          movieApi.checkPassword({
            password: this.dialogVideoFile.passwordForm.password,
            movieId: this.movie.movieId
          }).then(res => {
            if (res.code === 200){
              this.playVideo();
              this.resetForm(formName);//  清空表单
              this.dialogVideoFile.visible = false
              const key = `movie_pwd:${this.movie.movieId + `:` + this.$store.state.user.userId}`
              this.setCookies(key, true);
            }else {
              this.$modal.msgError(res.msg)
            }
          });
        } else {
          return false
        }
      })
    }
  }
}
</script>

<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  height: 440px;
  overflow: auto;
}

.video-body{
  width: 1200px;
  margin: auto;
}

</style>
