import request from '@/utils/request'

// 查询积分流水列表
export function listCreditsFlow(query) {
  return request({
    url: '/website/creditsFlow/list',
    method: 'get',
    params: query
  })
}

// 查询积分流水详细
export function getCreditsFlow(creditsId) {
  return request({
    url: '/website/creditsFlow/' + creditsId,
    method: 'get'
  })
}

// 新增积分流水
export function addCreditsFlow(data) {
  return request({
    url: '/website/creditsFlow',
    method: 'post',
    data: data
  })
}

// 修改积分流水
export function updateCreditsFlow(data) {
  return request({
    url: '/website/creditsFlow',
    method: 'put',
    data: data
  })
}

// 删除积分流水
export function delCreditsFlow(creditsId) {
  return request({
    url: '/website/creditsFlow/' + creditsId,
    method: 'delete'
  })
}

// 导出积分流水
export function exportCreditsFlow(query) {
  return request({
    url: '/website/creditsFlow/export',
    method: 'get',
    params: query
  })
}