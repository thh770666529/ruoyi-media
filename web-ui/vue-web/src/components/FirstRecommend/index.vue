<template>
  <div class="article-banner">
    <el-carousel class="bannerBox" indicator-position="outside" >
      <el-carousel-item  v-for="(article,index) in slideList" :key="index">
            <img
              style="width:100%; height:100%; display:block;cursor:pointer;"
              v-if="article.images"
              :src="fileUploadHost + article.images"
              :alt="article.title"
              @click="goToInfo(article)">
            <div class="carousel-title" @click="goToInfo(article)">
              <span>{{article.title}}</span>
            </div>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
import { getArticleList } from "@/api/blog/article";
export default {
  name: "FirstRecommend",
  data() {
    return {
      slideList: [],
      queryParams: { pageNum: 1, pageSize: 5, level: 1}
    };
  },
  created() {
    getArticleList(this.queryParams).then(response => {
      this.slideList = response.rows;
    });
  },
  methods: {
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

  .article-banner >>> .el-carousel__container {
    height: 450px !important;;
  }
  .article-banner >>> .carousel-title span {
  color: white;
  font-size: 22px;
  display: inline-block;
}

@media only screen and (max-width: 1200px) {
  .el-carousel__container {
    height: 360px !important;;
  }
}
  @media only screen and (max-width: 1000px) {
    .el-carousel__container {
      height: 340px !important;;
    }
  }

  @media only screen and (max-width: 960px) {
    .el-carousel__container {
      height: 280px !important;;
    }
  }

  @media only screen and (max-width: 500px) {
    .el-carousel__container {
      height: 200px !important;;
    }
  }

.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

  .carousel-title {
    cursor: pointer;
    position: absolute;
    z-index: 10;
    bottom: 40px;
    height: 40px;
    width: 100%;
    line-height: 40px;
    text-align: center;
    background: rgba(0, 0, 0, 0.3);
  }
</style>
