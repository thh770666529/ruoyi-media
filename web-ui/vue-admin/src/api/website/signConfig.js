import request from '@/utils/request'

// 查询签到配置列表
export function listSignConfig(query) {
  return request({
    url: '/website/signConfig/list',
    method: 'get',
    params: query
  })
}

// 查询签到配置详细
export function getSignConfig(signConfigId) {
  return request({
    url: '/website/signConfig/' + signConfigId,
    method: 'get'
  })
}

// 新增签到配置
export function addSignConfig(data) {
  return request({
    url: '/website/signConfig',
    method: 'post',
    data: data
  })
}

// 修改签到配置
export function updateSignConfig(data) {
  return request({
    url: '/website/signConfig',
    method: 'put',
    data: data
  })
}

// 删除签到配置
export function delSignConfig(signConfigId) {
  return request({
    url: '/website/signConfig/' + signConfigId,
    method: 'delete'
  })
}
