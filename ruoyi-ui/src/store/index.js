import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app'
import user from './modules/user'
import tagsView from './modules/tagsView'
import permission from './modules/permission'
import settings from './modules/settings'
import getters from './getters'
import fileList from './modules/fileList'  //  文件列表模块
import imgPreview from './modules/imgPreview'  //  图片预览模块
import videoPreview from './modules/videoPreview'  //  视频预览模块
import sideMenu from './modules/sideMenu'  //  视频预览模块
Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    user,
    tagsView,
    permission,
    settings,
    fileList,
    imgPreview,
    videoPreview,
    sideMenu
  },
  getters
})

export default store
