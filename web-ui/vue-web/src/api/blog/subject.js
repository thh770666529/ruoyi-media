import request from '@/utils/request'

// 查询文章专题列表
export function listSubject(query) {
  return request({
    url: '/blog/subject/list',
    method: 'get',
    params: query
  })
}

// 查询文章专题详细
export function getSubject(subjectId) {
  return request({
    url: '/blog/subject/' + subjectId,
    method: 'get'
  })
}

// 新增文章专题
export function addSubject(data) {
  return request({
    url: '/blog/subject',
    method: 'post',
    data: data
  })
}

// 修改文章专题
export function updateSubject(data) {
  return request({
    url: '/blog/subject',
    method: 'put',
    data: data
  })
}

// 删除文章专题
export function delSubject(subjectId) {
  return request({
    url: '/blog/subject/' + subjectId,
    method: 'delete'
  })
}

// 导出文章专题
export function exportSubject(query) {
  return request({
    url: '/blog/subject/export',
    method: 'get',
    params: query
  })
}