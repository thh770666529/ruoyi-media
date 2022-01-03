import request from '@/utils/request'

// 查询博客分类列表
export function listCategory(query) {
  return request({
    url: '/blog/category/list',
    method: 'get',
    params: query
  })
}

// 查询博客分类详细
export function getCategory(categoryId) {
  return request({
    url: '/blog/category/' + categoryId,
    method: 'get'
  })
}

// 新增博客分类
export function addCategory(data) {
  return request({
    url: '/blog/category',
    method: 'post',
    data: data
  })
}

// 修改博客分类
export function updateCategory(data) {
  return request({
    url: '/blog/category',
    method: 'put',
    data: data
  })
}

// 删除博客分类
export function delCategory(categoryId) {
  return request({
    url: '/blog/category/' + categoryId,
    method: 'delete'
  })
}
