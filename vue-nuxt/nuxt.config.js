module.exports = {
  mode: 'universal',

  router: {
    middleware:'checkuser'
  },
  server:{
    port: 3000,
    host: '0.0.0.0' //默认是localhost
  },
  env: {
    // 访问api接口 process.env.baseApiURL
    authURL: process.env.NODE_ENV ==='development'?'//localhost:3000/login': '//api.ithhit.cn',
    baseApiURL: '//localhost:7070',
    baseURL: '//localhost:3000'
  },
  plugins: [
    { src: '~/plugins/nuxt-swiper-plugin.js', ssr: false },
    '@/plugins/prototype',
    {src:'~/utils/nuxtjsAxios',ssr: true},
    { src: '~/plugins/highlight.js', ssr: false }
  ],
  modules: [
    '@nuxtjs/axios',
    '@gauseen/nuxt-proxy',
    'cookie-universal-nuxt'
  ],
  axios: {
    proxy: true, // 开启axios跨域
   // prefix: '/api',
    credentials: true
  },
  proxy: {
    '/api': {
      target: 'http://localhost:7070', //代理地址
      changeOrigin: true,
      pathRewrite: {
        '^/api' : ''
      }
    }
  },
  css: [
    'swiper/dist/css/swiper.css',
    'element-ui/lib/theme-chalk/index.css'
  ],
  /*
  ** Headers of the page
  */
  head: {
    title: '辉皇影院',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'keywords', name: 'keywords', content: '辉皇影院' },
      { hid: 'description', name: 'description', content: '辉皇影院！' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
    ]
  },
  /*
  ** Customize the progress bar color
  */
  loading: { color: '#3B8070' },
  /*
  ** Build configuration
  */
  build: {
    /*
    ** Run ESLint on save
    */
    extend (config, { isDev, isClient }) {
      if (isDev && isClient) {
        config.module.rules.push({
          enforce: 'pre',
          test: /\.(js|vue)$/,
          loader: 'eslint-loader',
          exclude: /(node_modules)/
        })
      }
    }
  }
}

