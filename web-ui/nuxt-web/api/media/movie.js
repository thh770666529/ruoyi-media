import request from '@/utils/request'


export default {
// 查询电影管理列表
  listMovie(query) {
    return request({
      url: '/media/movie/list',
      method: 'get',
      params: query
    })
  },

  // 查询电影管理详细
  getMovie(movieId) {
    return request({
      url: '/media/movie/' + movieId,
      method: 'get'
    })
  },
  getMovieVideo(videoId) {
    return request({
      url: '/media/movie/video/' + videoId,
      method: 'get'
    })
  }



}



