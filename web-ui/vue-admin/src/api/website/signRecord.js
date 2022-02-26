import request from '@/utils/request'

// 查询签到日志列表
export function listSignRecord(query) {
  return request({
    url: '/website/signRecord/list',
    method: 'get',
    params: query
  })
}

// 查询签到日志详细
export function getSignRecord(signRecordId) {
  return request({
    url: '/website/signRecord/' + signRecordId,
    method: 'get'
  })
}

// 新增签到日志
export function addSignRecord(data) {
  return request({
    url: '/website/signRecord',
    method: 'post',
    data: data
  })
}

// 修改签到日志
export function updateSignRecord(data) {
  return request({
    url: '/website/signRecord',
    method: 'put',
    data: data
  })
}

// 删除签到日志
export function delSignRecord(signRecordId) {
  return request({
    url: '/website/signRecord/' + signRecordId,
    method: 'delete'
  })
}
