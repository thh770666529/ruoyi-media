import request from '@/utils/request'

// 查询积分记录列表
export function listIntegralRecord(query) {
  return request({
    url: '/website/integralRecord/list',
    method: 'get',
    params: query
  })
}

// 查询积分记录详细
export function getIntegralRecord(integralRecordId) {
  return request({
    url: '/website/integralRecord/' + integralRecordId,
    method: 'get'
  })
}

// 新增积分记录
export function addIntegralRecord(data) {
  return request({
    url: '/website/integralRecord',
    method: 'post',
    data: data
  })
}

// 修改积分记录
export function updateIntegralRecord(data) {
  return request({
    url: '/website/integralRecord',
    method: 'put',
    data: data
  })
}

// 删除积分记录
export function delIntegralRecord(integralRecordId) {
  return request({
    url: '/website/integralRecord/' + integralRecordId,
    method: 'delete'
  })
}
