<template>
  <div class="article-container">
    <el-row type="flex" class="mt20">
      <el-col :span="17">
      <el-card class="box-card">
      <div class="newsview">
        <h3 class="news_title" v-if="article.title">{{article.title}}</h3>
        <div class="bloginfo">
          <ul>
            <li class="author">
              <span class="iconfont">&#xe60f; </span>
              <a href="javascript:void(0);" >{{article.author}}</a>
            </li>
            <li class="lmname">
              <span class="iconfont">&#xe603; </span>
              <a href="javascript:void(0);"
              >{{article.categoryData ? article.categoryData.name: ""}}</a>
            </li>
            <li class="createTime">
              <span class="iconfont">&#xe606; </span>
              {{article.createTime}}
            </li>
            <li class="view">
              <span class="iconfont">&#xe8c7; </span>
              {{article.clickCount}}
            </li>
            <li class="like">
              <span class="iconfont">&#xe663; </span>
              {{article.collectCount}}
            </li>
          </ul>
        </div>
        <div class="tags">
          <a
            v-for="item in article.tagList"
            :key="item.tagId"
            href="javascript:void(0);"
            target="_blank"
          >{{item.content}}</a>
        </div>
        <div class="news_about">
          <strong>版权</strong>
          <span v-html="article.copyright">
          </span>
        </div>
        <div
          class="news_con ck-content"
          v-html="article.content"
          v-highlight
        ></div>
      </div>
        <!--付款码和点赞-->
        <PayCode  :articleId="articleId" :praiseCount.sync="article.collectCount"></PayCode>
      </el-card>
        <el-card class="box-card">
          <div class="otherlink" v-if="sameBlogList.length > 0">
            <h2>相关文章</h2>
            <ul>
              <li v-for="item in sameBlogList" :key="item.articleId">
              <a href="javascript:void(0);"
                @click="goToInfo(item)"
                :title="item.title">{{item.title | ellipsis(18)}}</a>
            </li>
          </ul>
        </div>
        </el-card>
      </el-col>
      <el-col  class="hidden-sm-and-down" :offset="1" :span="6">
          <side-catalog
            class="catalog"
            v-bind="catalogProps">
          </side-catalog>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import { getArticle, getSameArticleList } from '@/api/blog/article';
  import SideCatalog from '@/components/VueSideCatalog';
  export default {
    name: "articleDetail",
    data() {
      return {
        catalogProps: {
          // 内容容器selector(必需)
          container: '.ck-content',
          watch: true,
          levelList: ["h2", "h3"],
        },
        articleId: undefined,
        sameBlogList: [],
        article: {},

      }
    },
    computed: {
    },
    components: {
      SideCatalog
    },
    watch: {
      $route(to, from) {
        location.reload()
      }
    },
    mounted () {
    },
    created() {
     this.articleId = this.$route.params && this.$route.params.articleId;
     this.initInfo();
    },
    methods: {
      initInfo() {
         getArticle(this.articleId).then(response => {
          this.article = response.data;
        });
        getSameArticleList(this.articleId).then(response => {
          this.sameBlogList = response.data;
        });
      },

      //跳转到文章详情【或推广链接】
      goToInfo(article) {
        if(article.type == "0") {
          let routeData = this.$router.resolve({
            path: "/article/" + article.articleId
          });
          window.open(routeData.href, '_blank');
        } else if(article.type == "1") {
          const params = new URLSearchParams();
          params.append("articleId", article.articleId);
          window.open(article.outsideLink, '_blank');
        }
      }
    }
  };
</script>

<style scoped>
  .emoji-panel-wrap {
    box-sizing: border-box;
    border: 1px solid #cccccc;
    border-radius: 5px;
    background-color: #ffffff;
    width: 470px;
    height: 190px;
    position: absolute;
    z-index: 999;
    top: 10px;
  }
  .emoji-size-small {
    zoom: 0.3;
    margin: 5px;
    vertical-align: middle;
  }
  .emoji-size-large {
    zoom: 0.5;
    margin: 5px;
  }
  .iconfont {
    font-size: 14px;
    margin-right: 3px;
  }
  .message_infos {
    width: 96%;
    margin-left: 10px;
  }
  .noComment {
    width: 100%;
    text-align: center;
  }
  .catalog {
    position: fixed;
    margin-left: 20px;
    max-height: 400px;
    overflow:auto;
    /*border:1px solid #0f74ff*/
  }


  .line-style {
    display: inline-block;
    height: 20px;
    width: 3px;
    background: transparent;
  }
  .line-style--active {
    background: currentColor;
  }
  .article-container {
    width: 1300px;
    margin: auto;
  }

  .infosbox {
    float: left;
    width: 80%;
    overflow: hidden;
    background: #FFF;
  }

  .newsview {
    padding: 0 30px;
    background-image: -webkit-gradient(linear, left top, right top, color-stop(3%, rgba(50, 0, 0, 0.05)), color-stop(3%, rgba(0, 0, 0, 0))), -webkit-gradient(linear, left bottom, left top, color-stop(3%, rgba(50, 0, 0, 0.05)), color-stop(3%, rgba(0, 0, 0, 0)));
    background-image: linear-gradient(90deg, rgba(50, 0, 0, 0.05) 3%, rgba(0, 0, 0, 0) 3%), linear-gradient(360deg, rgba(50, 0, 0, 0.05) 3%, rgba(0, 0, 0, 0) 3%);
    background-size: 20px 20px;
    background-position: center center;
  }

  .intitle {
    line-height: 40px;
    height: 40px;
    font-size: 14px;;
    border-bottom: #000 2px solid;
  }

  .intitle a {
    font-weight: normal;
  }

  .news_title {
    font-size: 24px;
    font-weight: normal;
    padding: 30px 0 0 0;
    color: #333;
  }

  .news_author {
    width: 100%;
    color: #999;
    line-height: 18px;
  }

  .news_author span {
    margin-right: 10px;
    padding-left: 20px
  }

  .au03 b {
    color: #333;
    padding: 0 5px
  }

  .au04 {
    font-weight: normal;
  }

  .news_about {
    color: #888888;
    border: 1px solid #F3F3F3;
    padding: 10px;
    margin: 20px auto 15px auto;
    line-height: 23px;
    background: none repeat 0 0 #F6F6F6;
  }

  .news_about strong {
    color: #38485A;
    font-weight: 400 !important;
    font-size: 13px;
    padding-right: 8px;
  }

  .news_content {
    line-height: 24px;
    font-size: 14px;
  }

  .news_content p {
    overflow: hidden;
    padding-bottom: 4px;
    padding-top: 6px;
    word-wrap: break-word;
  }

  .tags a {
    background: #F4650E;
    padding: 3px 8px;
    margin: 0 5px 0 0;
    color: #fff;
  }

  .tags {
    margin: 30px 0;
  }

  .news_con {
    line-height: 1.8;
    font-size: 16px;
    text-align: justify;
  }

  .news_con p {
    margin-bottom: 25px
  }

  .news_con img {
    max-width: 650px;
    height: auto;
  }

  .share {
    padding: 20px;
  }

  .nextinfo {
    line-height: 24px;
    width: 100%;
    background: #FFF;
    border-radius: 10px;
    overflow: hidden;
    margin: 20px 0
  }

  .nextinfo p {
    padding: 4px 10px;
    border-radius: 5px;
  }

  .nextinfo a:hover {
    color: #000;
    text-decoration: underline
  }

  .iconfont {
    font-size: 15px;
    margin-right: 2px;
  }

  .bloginfo {
    overflow: hidden;
    margin-top: 30px
  }

  .bloginfo ul li {
    float: left;
    font-size: 13px;
    padding: 0 0 0 20px;
    margin: 0 15px 0 0;
    color: #748594;
    line-height: 1.5;
    display: inline-block;
  }

  .bloginfo ul li a {
    color: #748594;
  }

  .bloginfo ul li a:hover {
    color: #000
  }

  .otherlink, .xzsm, .ffsm {
    width: 100%;
    background: #FFF;
    border-radius: 10px;
    overflow: hidden;
    margin: 20px 0
  }

  .otherlink h2 {
    border-bottom: #000 2px solid;
    line-height: 40px;
    font-size: 14px;
    background: url(../../assets/img/blog/5794.png) left 10px center no-repeat;
    padding-left: 40px;
    color: #000
  }

  .otherlink ul {
    margin: 10px 0
  }

  .otherlink li {
    line-height: 24px;
    height: 24px;
    display: block;
    width: 290px;
    float: left;
    overflow: hidden;
    margin-right: 30px;
    padding-left: 10px;
  }

  .otherlink li a:hover {
    text-decoration: underline;
    color: #000
  }
</style>
