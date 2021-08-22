import Vue from 'vue'
import VueAwesomeSwiper from 'vue-awesome-swiper/dist/ssr'
import VueQriously from 'vue-qriously'
import VueCropper from 'vue-cropper';
import ElementUI from 'element-ui' //element-ui的全部组件
import JSEncrypt from 'jsencrypt/bin/jsencrypt.min'
import 'element-ui/lib/theme-chalk/index.css'//element-ui的css
import '@/assets/styles/ruoyi.scss' // ruoyi css
const VueVideoPlayer = require('vue-video-player/dist/ssr')
Vue.use(VueVideoPlayer)
// 全局方法挂载
Vue.use(new JSEncrypt())
Vue.use(VueCropper)
Vue.use(ElementUI) //使用elementUI
Vue.use(VueQriously)
Vue.use(VueAwesomeSwiper)











