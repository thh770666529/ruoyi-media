import request from '@/utils/request'

export function list (params) {
  return request.get('/news', params)
}

// 获取网站配置
export function detail (id) {
  return request.get('/news/36kr/' + id)
}


