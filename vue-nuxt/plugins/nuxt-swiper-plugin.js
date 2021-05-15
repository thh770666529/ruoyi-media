import Vue from 'vue'
import VueAwesomeSwiper from 'vue-awesome-swiper/dist/ssr'
import VueQriously from 'vue-qriously'
import ElementUI from 'element-ui' //element-ui的全部组件
import 'element-ui/lib/theme-chalk/index.css'//element-ui的css

// 全局方法挂载
// Vue.prototype.getDicts = getDicts
// Vue.prototype.parseTime = parseTime
// Vue.prototype.addDateRange = addDateRange
// Vue.prototype.selectDictLabel = selectDictLabel
// Vue.prototype.selectDictLabels = selectDictLabels







Vue.use(ElementUI); //使用elementUI
Vue.use(VueQriously);
Vue.use(VueAwesomeSwiper);











