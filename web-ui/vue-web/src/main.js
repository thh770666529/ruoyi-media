import Vue from 'vue'
import './element-ui' // @PC.element-ui
import router from './router'
import store from './store'
import App from './App.vue'
import element from '@/utils/element'
import '../src/assets/styles/css/global.css'
import { parseTime, resetForm, addDateRange, selectDictLabel, selectDictLabels, download, handleTree } from "@/utils/ruoyi";

Vue.config.devtools = process.env.VUE_APP_ENV === 'dev' || process.env.VUE_APP_ENV === 'stage'
Vue.config.silent = process.env.VUE_APP_ENV === 'prod'
Vue.config.productionTip = false

// 全局方法挂载
Vue.prototype.fileUploadHost = process.env.VUE_APP_FILE_UPLOAD_HOST
Vue.prototype.parseTime = parseTime

Vue.use(element)
new Vue({
  router,
  render: h => h(App),
  store
}).$mount('#vue_web');

