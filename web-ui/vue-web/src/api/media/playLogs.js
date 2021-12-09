import request from '@/utils/request'

// 查询播放记录列表
export function listPlayLogs(query) {
  return request({
    url: '/media/playLogs/list',
    method: 'get',
    params: query
  })
}

// 查询播放记录详细
export function getPlayLogs(query) {
  return request({
    url: '/media/playLogs/getPlayLogs',
    method: 'post',
    data: query
  })
}

// 新增或者更新播放记录
export function insertOrUpdatePlayLogs(data) {
  return request({
    url: '/media/playLogs/insertOrUpdatePalyLogs',
    method: 'post',
    data: data
  })
}

