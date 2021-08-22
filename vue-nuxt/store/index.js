import { login, logout, getInfo } from '@/api/login'
import { getWebConfig } from '@/api/webConfig'
import { getToken, setToken, removeToken, getCookies,setCookies,removeCookies } from '@/utils/token'

// nuxt 声明状态 一定是function
const state = () => ({
    userInfo: '',
    token: '',
    webConfig: ''
})

//改变状态值
const mutations = {
  SET_WEBCONFIG: (state, webConfig) => {
    state.webConfig = webConfig
  },
  SET_USERINFO: (state, userInfo) => {
    state.userInfo = userInfo
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
// 服务端获取token
  GET_TOKEN_SERVER: (state,  req) => {
    let token = getToken();
    let userInfo = getCookies('userInfo');
    state.token = token;
    state.userInfo = userInfo;

  },
  // 状态置于空
  RESET_USER_STATE(state) {
      state.userInfo = null
      state.token = null
  },
  INIT_WEB: (state) => {
    let token = getToken();
    const userInfoJSON =  getCookies('userInfo');
    if (token && userInfoJSON) {
      const userInfo =  JSON.parse(userInfoJSON)
      store.commit('SET_TOKEN', token)
      store.commit('SET_USERINFO',userInfo )
    }else {
      state.userInfo = '';
      state.token = '';
      removeCookies('userInfo')
      removeToken();
    }
  },
}


// 定义行为
const actions = {
  // 服务端初始化
  async nuxtServerInit(store,{app:{$cookies}}) {
    const  userInfo =  $cookies.get('userInfo') ? $cookies.get('userInfo') : ''
    const  token =  $cookies.get('token') ? $cookies.get('token') : ''
    store.commit('SET_USERINFO', userInfo)
    store.commit('SET_TOKEN', token)
  },
  getWebConfig: ({ commit, state }) => {
    let webConfig = getCookies('webConfig');
    if(!webConfig){
      getWebConfig().then(response => {
        const data = response.data
        if (data.showList) {
          let showList = JSON.parse(data.showList)
          let loginTypeList = JSON.parse(data.loginTypeList)
          data.showList = showList;
          data.loginTypeList = loginTypeList;
        } else {
          data.showList = []
        }
        setCookies('webConfig', data)
        commit('SET_WEBCONFIG', JSON.parse(data))
      });
    }
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
          setCookies('userInfo',userInfo,1800)
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
        removeCookies('userInfo')
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
      removeCookies('userInfo')
      resolve()
    })
  }
}

export  default {
     state,
     mutations,
     actions
}
