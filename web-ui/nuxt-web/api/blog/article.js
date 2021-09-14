import request from '@/utils/request'

// 查询博客文章详细
export function getArticle(articleId) {
  return request({
    url: '/blog/article/' + articleId,
    method: 'get'
  })
}

// 支持文章
export function supportArticle(articleId) {
  return request({
    url: '/blog/article/supportArticle/' + articleId,
    method: 'get'
  })
}

//搜索文章列表
export function searchArticleList(queryParam) {
  return request({
    url: '/blog/article/searchArticleList',
    method: 'get',
    params: queryParam
  })
}

//搜索文章列表
export function getArticleList(queryParam) {
  return request({
    url: '/blog/article/list',
    method: 'get',
    params: queryParam
  })
}

// 查询相关文章列表
export function getSameArticleList(articleId) {
  return request({
    url: '/blog/article/getSameArticleList/' + articleId,
    method: 'get'
  })
}



