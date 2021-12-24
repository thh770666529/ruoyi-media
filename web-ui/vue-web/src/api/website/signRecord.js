import request from '@/utils/request'

// 查询签到日志列表
export function listSignRecord(query) {
  return request({
    url: '/website/signRecord/list',
    method: 'get',
    params: query
  })
}

// 查询签到数据
export function getSignRecord(signRecordId) {
  return request({
    url: '/website/signRecord/getSignRecord',
    method: 'get'
  })
}

// 签到
export function signIn() {
  return request({
    url: '/website/signRecord/signIn',
    method: 'post'
  })
}
