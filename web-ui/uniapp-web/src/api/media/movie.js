import request from '@/utils/request'


export default {
// 查询电影管理列表
  listMovie(query) {
    return request.get('/media/movie/list', query)
  },

  // 查询电影管理详细
  getMovie(movieId) {
    return request.get('/media/movie/' + movieId)
  },
  getMovieVideo(videoId) {
    return request.get('/media/movie/video/' + videoId)
  },

  // 查询演员相关电影
  getListByActorId(movieId) {
    return request.get('/media/movie/getListByActorId/' + movieId)
  },

  // 查询相关电影列表
  getSameTypeMovieList(movieId) {
    return request.get('/media/movie/getSameTypeMovieList/' + movieId)
  },
  checkPassword(param) {
    return request.get('/media/movie/checkPassword', param)
  }
}



