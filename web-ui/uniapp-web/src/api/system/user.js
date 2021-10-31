import request from '@/utils/request'
import { praseStrEmpty } from "@/utils";


// 查询用户详细
export function getUser(userId) {
  return request.get('/system/user/' + praseStrEmpty(userId))
}

// 新增用户
export function addUser(data) {
  return request.post('/system/user', data);
}

// 修改用户
export function updateUser(data) {
  return request.put('/system/user', put)
}

// 用户密码重置
export function resetUserPwd(userId, password) {
  const data = {
    userId,
    password
  }
  return request.put('/system/user/resetPwd', data)
}

// 用户状态修改
export function changeUserStatus(userId, status) {
  const data = {
    userId,
    status
  }
  return request.put('/system/user/changeStatus', data)
}

// 查询用户个人信息
export function getUserProfile() {
  return request.get('/system/user/profile')
}

// 修改用户个人信息
export function updateUserProfile(data) {
  return request.put('/system/user/profile', data)
}

// 用户密码重置
export function updateUserPwd(oldPassword, newPassword) {
  const data = {
    oldPassword,
    newPassword
  }
  return request.put('/system/user/profile/updatePwd', data)
}

// 用户头像上传
export function uploadAvatar(data) {
  return request.post('/system/user/profile/avatar', data)
}
