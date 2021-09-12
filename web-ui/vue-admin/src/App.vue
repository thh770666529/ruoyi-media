<template>
  <div id="app">
    <router-view />
    <!-- 将上传组件全局注册 -->
    <global-uploader ></global-uploader>
    <!-- 图片预览 -->
    <img-preview ></img-preview>
    <!-- 视频预览 -->
    <video-preview></video-preview>
  </div>
</template>

<script>
import globalUploader from '@/components/common/GlobalUploader'
import ImgPreview from '@/components/common/ImgPreview'
import VideoPreview from '@/components/common/VideoPreview'
export default  {
  name:  'App',
  metaInfo() {
    return {
      title: this.$store.state.settings.dynamicTitle && this.$store.state.settings.title,
      titleTemplate: title => {
        return title ? `${title} - ${process.env.VUE_APP_TITLE}` : process.env.VUE_APP_TITLE
      }
    }
  },
  components: {
    globalUploader,
    ImgPreview,
    VideoPreview
  },
  computed: {
    // 网盘页面文件上传/预览相关组件是否显示
    isFileAboutShow() {
      let routerNameList = ['Login', 'Register']
      return routerNameList.includes(this.$route.name) ? false : true
    }
  }
}
</script>
