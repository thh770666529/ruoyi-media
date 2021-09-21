<template>
  <div class="mt60">
    <div class="container">
      <div class="main">
        <div class="mt20">
          <span class="fsize20">{{ movie.title }}</span>
        </div>
        <!-- 播放器 -->
        <div style="min-height: 529px">
          <video-preview ref="player"></video-preview>
        </div>
      </div>

      <!-- 侧边栏 -->
      <div class="aside">
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
              <el-menu-item :index="`/video/`+video.videoId" v-for="video in movie.videoList" :key="video.videoId">
                <span class="fsize12">{{ video.title | ellipsis(20) }}</span>
              </el-menu-item>

            </el-submenu>
          </el-menu>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '@/assets/styles/less/index.less'
import VideoPreview from '@/components/Play/VideoPreview'
import movieApi from '@/api/media/movie'
import {getWebConfig} from '@/api/website/webConfig'

export default {
  name: 'playVideo',
  components: {
    VideoPreview
  },
  computed: {},
  data() {
    return {
      activeName: 'description',
      video: {},
      movie: {},
      sourceVideoUrl: '',
      comments: [],
      commentInfo: {
        targetId: this.$route.params.videoId
      },
      videoPreviewList: [],
      // 评论
      showCancel: false,
      webConfig: {}
    }
  },
  async created() {
    this.videoId = this.$route.params && this.$route.params.videoId;
    await getWebConfig().then(response => {
      let data = response.data
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
    await this.getVideoById(this.videoId);
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
    async getVideoById(videoId) {
      await movieApi.getMovieVideo(videoId).then(response => {
        this.video = response.data.video;
        this.movie = response.data.movie;
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
        }
        if (!this.videoPreviewList.length) {
          this.msgError("没有转化好的视频文件，请联系管理员！")
          data.videoPreviewVisible = false
        }
        this.$store.commit('setVideoPreviewData', data)
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
    }
  }
}
</script>

<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  height: 440px;
  overflow: auto;
}
</style>
