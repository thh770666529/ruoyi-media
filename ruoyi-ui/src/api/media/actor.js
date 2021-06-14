import request from '@/utils/request'

// 查询演员列表
export function listActor(query) {
  return request({
    url: '/media/actor/list',
    method: 'get',
    params: query
  })
}

// 查询没被选中的演员列表
export function notSelectedActorList(query,actorIds) {
  return request({
    url: '/media/actor/notSelectedList/'+ actorIds,
    method: 'get',
    params: query
  })
}

// 查询演员详细
export function getActor(actorId) {
  return request({
    url: '/media/actor/' + actorId,
    method: 'get'
  })
}

// 新增演员
export function addActor(data) {
  return request({
    url: '/media/actor',
    method: 'post',
    data: data
  })
}

// 修改演员
export function updateActor(data) {
  return request({
    url: '/media/actor',
    method: 'put',
    data: data
  })
}

// 删除演员
export function delActor(actorId) {
  return request({
    url: '/media/actor/' + actorId,
    method: 'delete'
  })
}

// 导出演员
export function exportActor(query) {
  return request({
    url: '/media/actor/export',
    method: 'get',
    params: query
  })
}
