import request from '@/utils/request'

export default {
    //查询热门电影和演员
  getHotMovieList() {
    return request({
      url: '/index/getHotMovieList',
      method: 'get'
    });
  },
  getHotActorList() {
    return request({
      url: '/index/getHotActorList',
      method: 'get'
    });
  },
  getAllBannerList() {
    return request({
      url: '/index/getAllBannerList',
      method: 'get'
    });
  },
  // 查询站点友情链接列表
   getLinkList() {
    return request({
      url: '/index/getLinkList',
      method: 'get'
    })
  },

  // 获取热门的文章列表
  getHotArticleList() {
    return request({
      url: '/index/getHotArticleList',
      method: 'get'
    })
  },

  //获取最新的文章列表
  getNewArticleList(query) {
    return request({
      url: '/index/getNewArticleList',
      method: 'get',
      params: query
    })
  },

  // 获取热门的标签列表
  getHotTagList() {
    return request({
      url: '/index/getHotTagList',
      method: 'get'
    })
  }
}
