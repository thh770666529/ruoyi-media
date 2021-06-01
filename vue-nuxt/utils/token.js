import Cookies from 'js-cookie'
const TokenKey = 'token'

// 半个小时过期
let seconds = 1800
let expires = new Date(new Date() * 1 + seconds * 1000);


export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token, { expires: expires })
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

/*
* 设置cookies
* */
export function getCookies (key) {
  return Cookies.get(key)
}
/*
* 设置Cookies
* */
export function setCookies (key, value, expiresTime) {
  let seconds = expiresTime
  let expires = new Date(new Date() * 1 + seconds * 1000)
  return Cookies.set(key, value, { expires: expires })
}
/*
* 移除Cookies
* */
export function removeCookies (key) {
  return Cookies.remove(key)
}
