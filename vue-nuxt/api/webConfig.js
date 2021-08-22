import request from '@/utils/request'

// 查询站点配置详细
export function getWebConfig() {
  return request({
    url: '/website/webConfig/getWebConfig' ,
    method: 'post'
  })
}
