import request from '@/utils/request'

// 查询博客分类列表
export function listCategory(query) {
  return request.get('/blog/category/list', query)
}

// 查询博客分类详细
export function getCategory(categoryId) {
  return request.get('/blog/category/' + categoryId)
}
