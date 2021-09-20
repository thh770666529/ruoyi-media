import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '../layout/Layout'
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
