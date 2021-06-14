import request from '@/utils/request'

// 查询评论列表
export function listComment(query) {
  return request({
    url: '/website/comment/list',
    method: 'get',
    params: query
  })
}

//获取树结构评论列表
export function treeListComment(query) {
  return request({
    url: '/website/comment/treeList',
    method: 'get',
    params: query
  })
}


// 新增评论
export function replyComment(data) {
  return request({
    url: '/website/comment/reply',
    method: 'post',
    data: data
  })
}


// 查询评论详细
export function getComment(commentId) {
  return request({
    url: '/website/comment/' + commentId,
    method: 'get'
  })
}

// 修改评论
export function updateComment(data) {
  return request({
    url: '/website/comment',
    method: 'put',
    data: data
  })
}

// 删除评论
export function delComment(commentId) {
  return request({
    url: '/website/comment/' + commentId,
    method: 'delete'
  })
}


// 删除我的评论
export function delMyComment(data) {
  return request({
    url: '/website/comment/delete' ,
    method: 'post',
    data: data
  })
}
