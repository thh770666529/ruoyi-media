import request from '@/utils/request'

// 查询文章标签列表
export function listTag(query) {
  return request.get('/blog/tag/list', query)
}

// 查询文章标签详细
export function getTag(tagId) {
  return request.get('/blog/tag/' + tagId)
}