import request from '@/utils/request'

// 查询收藏列表
export function listMyCollect(query) {
  return request({
    url: '/website/collect/myCollectList',
    method: 'get',
    params: query
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

// 删除收藏
export function cancelCollectByTargetId(data) {
  return request({
    url: '/website/collect/cancelCollectByTargetId',
    method: 'post',
    data: data
  })
}

// 检查收藏
export function checkCollectFlag(data) {
  return request({
    url: '/website/collect/checkCollectFlag/',
    method: 'post',
    data: data
  })
}
