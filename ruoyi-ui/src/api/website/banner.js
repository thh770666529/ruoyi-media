import request from '@/utils/request'

// 查询首页banner列表
export function listBanner(query) {
  return request({
    url: '/website/banner/list',
    method: 'get',
    params: query
  })
}

// 查询首页banner详细
export function getBanner(bannerId) {
  return request({
    url: '/website/banner/' + bannerId,
    method: 'get'
  })
}

// 新增首页banner
export function addBanner(data) {
  return request({
    url: '/website/banner',
    method: 'post',
    data: data
  })
}

// 修改首页banner
export function updateBanner(data) {
  return request({
    url: '/website/banner',
    method: 'put',
    data: data
  })
}

// 删除首页banner
export function delBanner(bannerId) {
  return request({
    url: '/website/banner/' + bannerId,
    method: 'delete'
  })
}

// 导出首页banner
export function exportBanner(query) {
  return request({
    url: '/website/banner/export',
    method: 'get',
    params: query
  })
}