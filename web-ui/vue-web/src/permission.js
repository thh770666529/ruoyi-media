import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import { getToken } from '@/utils/auth'

NProgress.configure({ showSpinner: false })

const backList = ['/ucenter']

router.beforeEach((to, from, next) => {
  NProgress.start()
  if (getToken()) {
    if (!store.getters.userId) {
        // 判断当前用户是否已拉取完user_info信息
        store.dispatch('GetInfo').then(() => {
          next();
        }).catch(err => {
          store.dispatch('LogOut').then(() => {
              Message.error(err)
              next({ path: '/' })
          })
        })

    }else {
      next();
    }
  }else {
    // 没有token
    if (backList.indexOf(to.path) !== -1) {
      // 黑名单做拦截
      next({ path: '/' }) // 否则全部重定向到登录页
      Message.error('未登录无法访问！');
      store.dispatch('showLoginForm');
      NProgress.done()
    } else {
      next();
    }

  }

})

router.afterEach(() => {
  NProgress.done()
})
