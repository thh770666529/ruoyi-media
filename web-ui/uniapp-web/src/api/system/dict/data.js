import request from '@/utils/request'

export function getDictsByTypeList(dictTypeList) {
  return request.post('/system/dict/data/listByDictTypeList', dictTypeList);
}

// 查询字典数据列表
export function listData(query) {
  return request.get('/system/dict/data/list', query)
}

// 查询字典数据详细
export function getData(dictCode) {
  return request.get('/system/dict/data/' + dictCode)
}

// 根据字典类型查询字典数据信息
export function getDicts(dictType) {
  return request.get('/system/dict/data/type/' + dictType)
}