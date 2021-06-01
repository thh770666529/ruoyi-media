export default ({store, route, redirect,app:{$cookies}}) =>{
  const  userInfo =  $cookies.get('userInfo')?$cookies.get('userInfo') : ''
  const  token =  $cookies.get('token')?$cookies.get('token') : ''
  store.commit('SET_USERINFO',userInfo)
  store.commit('SET_TOKEN',token)
}
