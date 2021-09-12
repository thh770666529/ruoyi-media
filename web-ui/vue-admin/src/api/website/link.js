import request from '@/utils/request'

// 查询站点友情链接列表
export function listLink(query) {
  return request({
    url: '/website/link/list',
    method: 'get',
    params: query
  })
}

// 查询站点友情链接详细
export function getLink(linkId) {
  return request({
    url: '/website/link/' + linkId,
    method: 'get'
  })
}

// 新增站点友情链接
export function addLink(data) {
  return request({
    url: '/website/link',
    method: 'post',
    data: data
  })
}

// 修改站点友情链接
export function updateLink(data) {
  return request({
    url: '/website/link',
    method: 'put',
    data: data
  })
}

// 删除站点友情链接
export function delLink(linkId) {
  return request({
    url: '/website/link/' + linkId,
    method: 'delete'
  })
}

// 导出站点友情链接
export function exportLink(query) {
  return request({
    url: '/website/link/export',
    method: 'get',
    params: query
  })
}