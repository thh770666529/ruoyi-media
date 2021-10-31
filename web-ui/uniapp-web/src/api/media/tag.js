import request from '@/utils/request'

// 查询电影标签列表
export function listTag(query) {
  return request.get('/media/tag/list', query);
}

// 查询电影标签详细
export function getTag(tagId) {
  return request.get('/media/tag/' + tagId)
}