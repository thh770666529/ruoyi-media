import request from '@/utils/request'
// 查询博客文章详细
export function getArticle(articleId) {
  return request.get('/blog/article/' + articleId)
}


//搜索文章列表
export function getArticleList(queryParam) {
  return request.get('/blog/article/list', queryParam)
}



