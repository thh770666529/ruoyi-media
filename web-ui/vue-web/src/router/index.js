import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '../layout/Layout'
import UserLayout from '../layout/UserLayout'
Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        component: Layout,
        children: [
            {
                path: '',
                component: () => import("@/views/index")
            },
            {
              path: '/movie',
              component: () => import("@/views/movie/index")
            },
            {
              path: '/movie/:movieId?',
              component: () => import("@/views/movie/detail")
            },
            {
              path: '/video/:videoId?',
              component: () => import("@/views/video/index")
            },
            {
              path: '/actor',
              component: () => import("@/views/actor/index")
            },
            {
              path: '/actor/:actorId?',
              component: () => import("@/views/actor/detail")
            },
            {
              path: '/article',
              component: () => import("@/views/article/index")
            },
            {
              path: '/article/:articleId?',
              component: () => import("@/views/article/detail")
            }
        ],
    },
    {
      path: '/',
      component: UserLayout,
      hidden: true,
      children: [
        {
          path: 'login',
          name: 'login',
          component: () => import(/* webpackChunkName: "user" */ '@/views/user/login')
        }
      ]
    }
];

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
});

export default router
