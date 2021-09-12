// 定义权限判断中间件 中间件的第1个参数是 context
export default ({store, route, redirect}) =>{
  if (!store.state.token) {
    //如果没有认证 就重定向到登录页面
     //const redirectURL =  process.env.baseURL + route.path
     const redirectURL =  route.path
     redirect(`${process.env.authURL}`+'?redirect='+`${redirectURL}`)
  }
}
