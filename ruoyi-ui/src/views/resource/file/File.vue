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

export default {
  name: 'File',
  components: {
    AsideMenu,
    FileList
  },
  computed: {
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
