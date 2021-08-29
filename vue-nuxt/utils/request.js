import axios from 'axios'
import { Notification, MessageBox, Message } from 'element-ui'
import { getToken } from '@/utils/token'
import errorCode from '@/utils/errorCode'

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
axios.defaults.withCredentials = true // 跨域处理 允许携带cookie
// 创建axios实例
const service = axios.create({
  baseURL: 'http://localhost:3000/api', // api的base_url
  timeout: 3000 // 请求超时时间
})

//第三步 创建拦截器  http request 拦截器
service.interceptors.request.use(config => {
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
            if (value[key] !== null && typeof (value[key]) !== 'undefined') {
              let params = propName + '[' + key + ']'
              let subPart = encodeURIComponent(params) + '='
              url += subPart + encodeURIComponent(value[key]) + '&'
            }
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
})

// http response 拦截器
service.interceptors.response.use(response => {

      const code = response.data.code || 200;
      const msg = errorCode[code] || response.data.msg || errorCode['default'];

      if (code === 401) {
        // 返回 错误代码-1 清除ticket信息并跳转到登录页面
        window.location.href = "/login"
      } else if (code === 500) {
        Message({
          message: msg,
          type: 'error'
        })
        return Promise.reject(new Error(msg))
      } else if (code !== 200) {
        Notification.error({
          title: msg
        })
        return Promise.reject('error')
      } else {
        return response.data;
      }

  }, error => {
  console.log('err' + error)
  let { message } = error;
  if (message == "Network Error") {
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
});

export default service
