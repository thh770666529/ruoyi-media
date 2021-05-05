import request from '@/utils/request'

export default {
    //查询热门课程和名师
  getHotMovieList() {
    return request({
      url: '/index/getHotMovieList',
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
