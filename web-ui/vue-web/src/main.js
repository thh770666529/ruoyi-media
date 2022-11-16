import Vue from 'vue'
import './element-ui' // @PC.element-ui
import router from './router'
import store from './store'
import App from './App.vue'
import element from '@/utils/element'
import './assets/icons' // icon
import './permission' // permission control
import '../src/assets/styles/css/global.css'
import '@/assets/styles/less/index.less'
import "@/assets/iconfont/iconfont.css";
import { parseTime, resetForm, addDateRange, selectDictLabel, selectDictLabels, download, handleTree } from "@/utils/ruoyi";
import Pagination from "@/components/Pagination";
import PayCode from "@/components/PayCode";
import About from "@/components/About";
import FourthRecommend from "@/components/FourthRecommend";
import HotArticle from "@/components/HotArticle";
import Comment from "@/components/Comment";
import Collect from "@/components/Collect";
import hljs from 'highlight.js';
import 'highlight.js/styles/atom-one-dark.css' //样式
import globalFunction from '@/utils/globalFunction'
import plugins from './plugins' // plugins
// 图片预览组件
import ImagePreview from "@/components/ImagePreview"

Vue.config.devtools = process.env.VUE_APP_ENV === 'dev' || process.env.VUE_APP_ENV === 'stage'
Vue.config.silent = process.env.VUE_APP_ENV === 'prod'
Vue.config.productionTip = false

// 全局方法挂载
Vue.prototype.fileUploadHost = process.env.VUE_APP_FILE_UPLOAD_HOST
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm
Vue.prototype.selectDictLabel = selectDictLabel
Vue.prototype.selectDictLabels = selectDictLabels

Vue.prototype.toArticleDetail = function (article) {
  if(article.type == '0') {
    let routeData = this.$router.resolve({
      path: "/article/" + article.articleId
    });
    window.open(routeData.href, '_blank');
  } else if(article.type == '1') {
    const params = new URLSearchParams();
    params.append("articleId", article.articleId);
    window.open(article.outsideLink, '_blank');
  }
}


// 全局组件挂载
Vue.component('Pagination', Pagination)
Vue.component('PayCode', PayCode);
Vue.component('About', About);
Vue.component('FourthRecommend', FourthRecommend);
Vue.component('HotArticle', HotArticle);
Vue.component('Comment', Comment);
Vue.component('Collect', Collect);
Vue.component('ImagePreview', ImagePreview);

for(let key in globalFunction) {
  Vue.prototype[key] = globalFunction[key]
}

Vue.use(plugins)

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


Vue.directive('highlight',function(el){
  let blocks = el.querySelectorAll('pre code');
  blocks.forEach((block)=>{
    hljs.highlightBlock(block)
  })
})


Vue.use(element)
new Vue({
  router,
  render: h => h(App),
  store
}).$mount('#vue_web');

