import request from '@/utils/request'

// 查询字典类型列表
export function listType(query) {
  return request.get('/system/dict/type/list', query)
}

// 查询字典类型详细
export function getType(dictId) {
  return request.get('/system/dict/type/' + dictId)
}


// 获取字典选择框列表
export function optionselect() {
  return request.get('/system/dict/type/optionselect')
}