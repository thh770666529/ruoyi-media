import request from '@/utils/request'

// 查询收藏列表
export function listCollect(query) {
  return request({
    url: '/website/collect/list',
    method: 'get',
    params: query
  })
}

// 查询收藏详细
export function getCollect(collectId) {
  return request({
    url: '/website/collect/' + collectId,
    method: 'get'
  })
}

// 新增收藏
export function addCollect(data) {
  return request({
    url: '/website/collect',
    method: 'post',
    data: data
  })
}

// 修改收藏
export function updateCollect(data) {
  return request({
    url: '/website/collect',
    method: 'put',
    data: data
  })
}

// 删除收藏
export function delCollect(collectId) {
  return request({
    url: '/website/collect/' + collectId,
    method: 'delete'
  })
}
