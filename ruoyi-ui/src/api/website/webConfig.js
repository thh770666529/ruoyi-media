import request from '@/utils/request'

// 查询站点配置详细
export function getWebConfig() {
  return request({
    url: '/website/webConfig/getWebConfig' ,
    method: 'post'
  })
}

// 修改站点配置
export function updateWebConfig(data) {
  return request({
    url: '/website/webConfig',
    method: 'put',
    data: data
  })
}
