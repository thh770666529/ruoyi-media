import Vue from 'vue'
import VueAwesomeSwiper from 'vue-awesome-swiper/dist/ssr'
import VueQriously from 'vue-qriously'
import ElementUI from 'element-ui' //element-ui的全部组件
import JSEncrypt from 'jsencrypt/bin/jsencrypt.min'
import 'element-ui/lib/theme-chalk/index.css'//element-ui的css


// 全局方法挂载

import Swiper from 'swiper'
Vue.use(new JSEncrypt())
Vue.use(ElementUI) //使用elementUI
Vue.use(VueQriously)
Vue.use(VueAwesomeSwiper)











