import request from '@/utils/request'

// 查询收藏夹列表
export function listCollectFolder(query) {
  return request({
    url: '/website/collectFolder/list',
    method: 'get',
    params: query
  })
}

// 查询收藏夹详细
export function getCollectFolder(collectFolderId) {
  return request({
    url: '/website/collectFolder/' + collectFolderId,
    method: 'get'
  })
}

// 新增收藏夹
export function addCollectFolder(data) {
  return request({
    url: '/website/collectFolder',
    method: 'post',
    data: data
  })
}

// 修改收藏夹
export function updateCollectFolder(data) {
  return request({
    url: '/website/collectFolder',
    method: 'put',
    data: data
  })
}

// 删除收藏夹
export function delCollectFolder(collectFolderId) {
  return request({
    url: '/website/collectFolder/' + collectFolderId,
    method: 'delete'
  })
}
