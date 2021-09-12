import request from '@/utils/request'

// 查询问答分类列表
export function listCategory(query) {
  return request({
    url: '/question/category/list',
    method: 'get',
    params: query
  })
}

// 查询问答分类详细
export function getCategory(categoryId) {
  return request({
    url: '/question/category/' + categoryId,
    method: 'get'
  })
}

// 新增问答分类
export function addCategory(data) {
  return request({
    url: '/question/category',
    method: 'post',
    data: data
  })
}

// 修改问答分类
export function updateCategory(data) {
  return request({
    url: '/question/category',
    method: 'put',
    data: data
  })
}

// 删除问答分类
export function delCategory(categoryId) {
  return request({
    url: '/question/category/' + categoryId,
    method: 'delete'
  })
}

// 导出问答分类
export function exportCategory(query) {
  return request({
    url: '/question/category/export',
    method: 'get',
    params: query
  })
}