import request from '@/utils/request'

// 查询电影分类列表
export function listCategory(query) {
  return request({
    url: '/media/category/list',
    method: 'get',
    params: query
  })
}

// 查询电影分类详细
export function getCategory(categoryId) {
  return request({
    url: '/media/category/' + categoryId,
    method: 'get'
  })
}

// 新增电影分类
export function addCategory(data) {
  return request({
    url: '/media/category',
    method: 'post',
    data: data
  })
}

// 修改电影分类
export function updateCategory(data) {
  return request({
    url: '/media/category',
    method: 'put',
    data: data
  })
}

// 删除电影分类
export function delCategory(categoryId) {
  return request({
    url: '/media/category/' + categoryId,
    method: 'delete'
  })
}
