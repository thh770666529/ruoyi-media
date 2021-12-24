import request from '@/utils/request'

// 查询用户账户列表
export function listAccount(query) {
  return request({
    url: '/website/account/list',
    method: 'get',
    params: query
  })
}

// 查询用户账户详细
export function getSignRecord(accountId) {
  return request({
    url: '/website/account/' + accountId,
    method: 'get'
  })
}

// 新增用户账户
export function addAccount(data) {
  return request({
    url: '/website/account',
    method: 'post',
    data: data
  })
}

// 修改用户账户
export function updateAccount(data) {
  return request({
    url: '/website/account',
    method: 'put',
    data: data
  })
}

// 删除用户账户
export function delAccount(accountId) {
  return request({
    url: '/website/account/' + accountId,
    method: 'delete'
  })
}

// 导出用户账户
export function exportAccount(query) {
  return request({
    url: '/website/account/export',
    method: 'get',
    params: query
  })
}
