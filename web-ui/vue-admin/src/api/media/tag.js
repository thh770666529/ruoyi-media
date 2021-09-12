import request from '@/utils/request'

// 查询电影标签列表
export function listTag(query) {
  return request({
    url: '/media/tag/list',
    method: 'get',
    params: query
  })
}

// 查询电影标签详细
export function getTag(tagId) {
  return request({
    url: '/media/tag/' + tagId,
    method: 'get'
  })
}

// 新增电影标签
export function addTag(data) {
  return request({
    url: '/media/tag',
    method: 'post',
    data: data
  })
}

// 修改电影标签
export function updateTag(data) {
  return request({
    url: '/media/tag',
    method: 'put',
    data: data
  })
}

// 删除电影标签
export function delTag(tagId) {
  return request({
    url: '/media/tag/' + tagId,
    method: 'delete'
  })
}

// 导出电影标签
export function exportTag(query) {
  return request({
    url: '/media/tag/export',
    method: 'get',
    params: query
  })
}