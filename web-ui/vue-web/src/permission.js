import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import { getToken } from '@/utils/auth'

NProgress.configure({ showSpinner: false })

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
    next();
  }

})

router.afterEach(() => {
  NProgress.done()
})
