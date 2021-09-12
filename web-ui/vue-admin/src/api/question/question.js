import request from '@/utils/request'

// 查询问答列表
export function listQuestion(query) {
  return request({
    url: '/question/question/list',
    method: 'get',
    params: query
  })
}

// 查询问答详细
export function getQuestion(questionId) {
  return request({
    url: '/question/question/' + questionId,
    method: 'get'
  })
}

// 新增问答
export function addQuestion(data) {
  return request({
    url: '/question/question',
    method: 'post',
    data: data
  })
}

// 修改问答
export function updateQuestion(data) {
  return request({
    url: '/question/question',
    method: 'put',
    data: data
  })
}

// 删除问答
export function delQuestion(questionId) {
  return request({
    url: '/question/question/' + questionId,
    method: 'delete'
  })
}

// 导出问答
export function exportQuestion(query) {
  return request({
    url: '/question/question/export',
    method: 'get',
    params: query
  })
}