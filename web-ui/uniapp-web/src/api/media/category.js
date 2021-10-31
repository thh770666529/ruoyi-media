import request from '@/utils/request'

// 查询电影分类列表
export function listCategory(query) {
  return request.get('/media/category/list',query);
}

// 查询电影分类详细
export function getCategory(categoryId) {
  return request.get('/media/category/' + categoryId)
}