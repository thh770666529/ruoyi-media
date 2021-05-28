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
  }
}
