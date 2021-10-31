const host = 'https://unidemo.dcloud.net.cn/';

import Vue from 'vue'
import App from './App'
import config from '@/config'
import { friendlyDate, parseTime, resetForm, selectDictLabel, selectDictLabels } from "@/utils";
// 此处为引用自定义顶部
import cuCustom from './colorui/components/cu-custom.vue'
Vue.component('cu-custom',cuCustom);

// 全局方法挂载
Vue.prototype.$fileUploadHost = config.fileUploadHost
Vue.prototype.$parseTime = parseTime
Vue.prototype.$resetForm = resetForm
Vue.prototype.$selectDictLabel = selectDictLabel
Vue.prototype.$selectDictLabels = selectDictLabels
Vue.prototype.$friendlyDate = friendlyDate

// 引入:uView-UI
import uView from 'uview-ui';
Vue.use(uView);

// 全局过滤器设置
Vue.filter('ellipsis', function (msg, num) {
  const currentNum = num || 5
  if(!msg) {
    return ''
  }
  if(msg.length > currentNum) {
    return msg.slice(0, currentNum) + '...'
  }
  return msg
})

Vue.config.productionTip = false
Vue.prototype.$host = host;
App.mpType = 'app'
const app = new Vue({
  ...App
})
app.$mount()
