import request from '@/utils/request'

// 查询文章收藏列表
export function listCollect(query) {
  return request({
    url: '/blog/collect/list',
    method: 'get',
    params: query
  })
}

// 查询文章收藏详细
export function getCollect(articleCollectId) {
  return request({
    url: '/blog/collect/' + articleCollectId,
    method: 'get'
  })
}

// 新增文章收藏
export function addCollect(data) {
  return request({
    url: '/blog/collect',
    method: 'post',
    data: data
  })
}

// 修改文章收藏
export function updateCollect(data) {
  return request({
    url: '/blog/collect',
    method: 'put',
    data: data
  })
}

// 删除文章收藏
export function delCollect(articleCollectId) {
  return request({
    url: '/blog/collect/' + articleCollectId,
    method: 'delete'
  })
}

// 导出文章收藏
export function exportCollect(query) {
  return request({
    url: '/blog/collect/export',
    method: 'get',
    params: query
  })
}