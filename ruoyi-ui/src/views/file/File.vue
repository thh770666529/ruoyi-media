<template>
  <div class="app-container">
  <div class="file-wrapper">
    <el-container class="el-container">
      <el-aside width="auto">
        <AsideMenu></AsideMenu>
      </el-aside>
      <el-container>
        <el-main class="el-main">
          <FileList></FileList>
        </el-main>
      </el-container>
    </el-container>
  </div>
    <!-- 将上传组件全局注册 -->
    <global-uploader ></global-uploader>
    <!-- 图片预览 -->
    <img-preview ></img-preview>
    <!-- 视频预览 -->
    <video-preview></video-preview>

  </div>
</template>

<script>
import AsideMenu from './components/AsideMenu/AsideMenu'
import FileList from './components/FileList'
import '@/assets/styles/file/css/base.css'
import '@/assets/styles/file/css/border.css'
import '@/assets/styles/file/css/element-cover.css'
import '@/assets/styles/file/iconfont/iconfont.css'
import '@/assets/styles/file/css/mediaScreen.styl'
import globalUploader from '@/components/common/GlobalUploader'
import ImgPreview from '@/components/common/ImgPreview'
import VideoPreview from '@/components/common/VideoPreview'
export default {
  name: 'File',
  components: {
    AsideMenu,
    FileList,
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

<style lang="stylus" scoped>
@import '~@/assets/styles/varibles.styl';
@import '~@/assets/styles/mixins.styl';

.file-wrapper {
  width: 100% !important;
}

.el-container {
  .el-aside {
    height: calc(100vh - 61px);
    overflow: hidden;
  }
}

.el-main {
  padding: 0px 16px !important;
  overflow: hidden;
}
</style>
