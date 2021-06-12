import Vue from 'vue'
import globalFunction from '@/utils/globalFunction'
import './assets/styles/element-variables.scss'
import element from '@/utils/element'
import '@/assets/styles/index.scss' // global css
import '@/assets/styles/ruoyi.scss' // ruoyi css
import App from './App'
import store from './store'
import router from './router'
import permission from './directive/permission'
import * as filters from '@/filters/index'
import './assets/icons' // icon
import './permission' // permission control
import { getDicts } from "@/api/system/dict/data";
import { getConfigKey } from "@/api/system/config";
import { parseTime, resetForm, addDateRange, selectDictLabel, selectDictLabels, download, handleTree } from "@/utils/ruoyi";
import Pagination from "@/components/Pagination";


// 自定义表格工具扩展
import RightToolbar from "@/components/RightToolbar"
// collapse 展开折叠
import CollapseTransition from 'element-ui/lib/transitions/collapse-transition';

Vue.component(CollapseTransition.name, CollapseTransition)
Vue.use(element);
/**
 * vue-simple-uploader 中文官方文档 https://github.com/simple-uploader/vue-uploader/blob/master/README_zh-CN.md
 * simple-uploader.js 中文官方文档 https://github.com/simple-uploader/Uploader/blob/develop/README_zh-CN.md
 */
import uploader from 'vue-simple-uploader'

// 全局方法挂载
Vue.prototype.getDicts = getDicts
Vue.prototype.getConfigKey = getConfigKey
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm
Vue.prototype.addDateRange = addDateRange
Vue.prototype.selectDictLabel = selectDictLabel
Vue.prototype.selectDictLabels = selectDictLabels
Vue.prototype.download = download
Vue.prototype.handleTree = handleTree
Vue.prototype.$EventBus = new Vue()
// 全局组件挂载
Vue.component('Pagination', Pagination)
Vue.component('RightToolbar', RightToolbar)
Vue.use(permission)

for(let key in globalFunction) {
  Vue.prototype[key] = globalFunction[key]
}

// 过滤器
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

Vue.config.productionTip = false
Vue.use(uploader)
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
