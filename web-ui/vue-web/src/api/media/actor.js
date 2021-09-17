import request from '@/utils/request'

// 查询演员列表
export function listActor(query) {
  return request({
    url: '/media/actor/list',
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
