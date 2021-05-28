import { login, logout, getInfo } from '@/api/login'
import { getToken, setToken, removeToken, getCookies } from '@/utils/token'
import { setStore, getStore, removeStore, setSession, getSession } from '@/utils/storage'

// nuxt 声明状态 一定是function
const state = () => ({
    userInfo: null,
    accessToken: null,
    refreshToken: null
})

//改变状态值
const mutations = {
  SET_USERINFO: (state, userInfo) => {
    state.userInfo = userInfo
  },
  SET_TOKEN: (state, token) => {
    state.accessToken = token
  },

  // 状态置于空
  RESET_USER_STATE(state) {
      state.userInfo = null
      state.accessToken = null
      state.refreshToken = null
  },
  INIT_WEB: (state) => {
    let token = getToken();
    if (token) {
      const userInfoJSON =  getStore('storeCache');
      if(userInfoJSON){
        state =  JSON.parse(userInfoJSON)
      }
    }else {
      state.userInfo = '';
      removeStore('storeCache')
    }
  },
}


// 定义行为
const actions = {
  // 客户端初始化
  async nuxtClientInit({ commit }, {req}) {
   /* const storeCache = getStore("storeCache")
    if(storeCache){
      console.log( "客户端初始化",JSON.parse(storeCache))
      const state =  JSON.parse(storeCache)
      commit('SET_TOKEN', state.accessToken)
      commit('SET_USERINFO', state.userInfo)
    }*/
  },
  // 服务端初始化
  async nuxtServerInit({ dispatch }, context) {


  },
    //跳转到登录页面
  LoginPage({commit}){
       //重置用户状态
       commit('RESET_USER_STATE')
       //跳转到认证用户端（登录页） redirecURL 作为登录参数 参数值是引发跳转的地址
      //window 是属于浏览器对象 所以触发时 只能在客户端触发
      window.location.href = `http://localhost:3000/login?redirectURL=${window.location.href}`
    },
  Login({ commit }, loginUser) {
    const username = loginUser.username.trim()
    const password = loginUser.password
    const code = loginUser.code
    const uuid = loginUser.uuid
    return new Promise((resolve, reject) => {
      login(username, password, code, uuid).then(res => {

        setToken(res.token)
        commit('SET_TOKEN', res.token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 获取用户信息
  GetUserInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo().then(res => {
          const userInfo = res.user
          commit('SET_USERINFO', userInfo)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 退出系统
  LogOut({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        commit('RESET_USER_STATE')
        removeToken()
        removeStore('storeCache')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 前端 登出
  FedLogOut({ commit }) {
    return new Promise(resolve => {
      commit('RESET_USER_STATE')
      removeToken()
      removeStore('storeCache')
      resolve()
    })
  }
}

export  default {
     state,
     mutations,
     actions
}
