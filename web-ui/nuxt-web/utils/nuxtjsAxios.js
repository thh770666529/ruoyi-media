import { Notification, MessageBox, Message } from 'element-ui'
import NProgress from 'nprogress'
import errorCode from '@/utils/errorCode'
import { getToken } from '@/utils/token'

export default function ({$axios,store, redirect, router})  {
  // 数据访问前缀
  $axios.defaults.baseURL = 'http://localhost:3000/api';
  $axios.defaults.timeout = 3000
  $axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
  $axios.defaults.withCredentials = true
  // request拦截器
  $axios.onRequest(config => {
    if(process.client){
      // 客户端下，请求进度条开始
      NProgress.start();
    }
    // 是否需要设置 token
    const isToken = (config.headers || {}).isToken === false
    //判断cookie里面是否有数据
    if (getToken() && !isToken ) {
      config.headers['Authorization'] = 'Bearer ' + getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
    }

    // get请求映射params参数
    if (config.method === 'get' && config.params) {
      let url = config.url + '?';
      for (const propName of Object.keys(config.params)) {
        const value = config.params[propName];
        var part = encodeURIComponent(propName) + "=";
        if (value !== null && typeof(value) !== "undefined") {
          if (typeof value === 'object') {
            for (const key of Object.keys(value)) {
              let params = propName + '[' + key + ']';
              var subPart = encodeURIComponent(params) + "=";
              url += subPart + encodeURIComponent(value[key]) + "&";
            }
          } else {
            url += part + encodeURIComponent(value) + "&";
          }
        }
      }
      url = url.slice(0, -1);
      config.params = {};
      config.url = url;
    }
    return config
  },error => {
    console.log(error);
    return Promise.reject(error);
  });
  // response拦截器，数据返回后，可以先在这里进行一个简单的判断
  $axios.interceptors.response.use(
    response => {
      if(process.client){
        // 客户端下， 请求进度条结束
        NProgress.done();
      }
      const code = response.data.code || 200;
      const msg = errorCode[code] || response.data.msg || errorCode['default'];

      if(code === 401){
        // 返回401，token验证失败，清除客户端cookie
        // 重定向到登录页面， 这里做一个判断，容错：req.url 未定义
        redirect("/login")
      }else if(code === 404){
        redirect("/")
      } else if (code !== 200) {
        Notification.error({
          title: msg
        })
        return Promise.reject('error')
      }else{
        // 请求接口数据正常，返回数据
        return response
      }
    },error => {
      if(process.client){
        NProgress.done();
      }
      console.log('err' + error)
      let { message } = error;
      if (message === "Network Error") {
        message = "后端接口连接异常";
      }
      else if (message.includes("timeout")) {
        message = "系统接口请求超时";
      }
      else if (message.includes("Request failed with status code")) {
        message = "系统接口" + message.substr(message.length - 3) + "异常";
      }
      Message({
        message: message,
        type: 'error',
        duration: 5 * 1000
      })
      return Promise.reject(error)
    })
}
