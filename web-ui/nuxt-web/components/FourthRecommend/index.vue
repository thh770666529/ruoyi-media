<template>
    <div class="tuijian" v-if="fourthData.length > 0">
      <el-card class="box-card">
        <div slot="header" class="d-flex align-items-center">
          <span>推荐文章</span>
        </div>
      <ul class="tjpic" v-if="fourthData[0]">
        <i><img v-if="fourthData[0].images" style="cursor:pointer"  @click="goToInfo(fourthData[0])" :src="fileUploadHost + fourthData[0].images"></i>
        <p><a href="javascript:void(0);" @click="goToInfo(fourthData[0])">{{fourthData[0].title}}</a></p>
      </ul>

      <ul class="sidenews">
        <li v-for="item in sideNews" :key="item.articleId">
          <i><img style="cursor:pointer" v-if="item.images" @click="goToInfo(fourthData[0])" :src="fileUploadHost + item.images"></i>
          <p><a href="javascript:void(0);" @click="goToInfo(item)">{{item.title}}</a></p>
          <span>{{item.createTime}}</span>
        </li>
      </ul>
      </el-card>
    </div>
</template>

<script>
import { getArticleList } from "@/api/blog/article";
export default {
  name: 'FourthRecommend',
    data() {
    	return {
	      fourthData: [], //；四级推荐数据
        queryParams: { pageNum: 1, pageSize: 4, level: 4}
    	}
    },
    created() {
      getArticleList(this.queryParams).then(response => {
        this.fourthData = response.rows;
      });
    },
    computed: {
      //添加一个计算属性用于简单过滤掉数组中第一个数据
      sideNews() {
        return this.fourthData.filter(blog =>
          this.fourthData.indexOf(blog) != 0
        )
      }
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
      },
    }
}
</script>

<style scoped>
  .box-card span{
    font-weight: bold;
  }

  /*tjpic */
  .tjpic {
    width: 100%;
    height: 170px;
    background: #000;
    margin-bottom: 20px;
    overflow: hidden;
    display: block;
    clear: both;
    position: relative;
  }

  .tjpic img {
    width: 100%;
    min-height: 170px;
    -moz-transition: all .5s ease;
    -webkit-transition: all .5s ease;
    -ms-transition: all .5s ease;
    -o-transition: all .5s ease;
    transition: all .5s ease;
  }

  .tjpic p {
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    padding: 10px 20px;
    font-size: 15px;
    text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;;
    background: rgba(0, 0, 0, 0.7);
    color: #fff;
  }

  .tjpic p a {
    color: #fff;
  }

  .tjpic:hover img {
    transform: scale(1.1)
  }

  /*sidenews*/
  .sidenews {
  }

  .sidenews li {
    margin: 0 0 20px 0;
    overflow: hidden
  }

  .sidenews li p {
    line-height: 24px;
    color: #888;
    font-size: 15px;
    overflow: hidden;
    text-overflow: ellipsis;
    -webkit-box-orient: vertical;
    display: -webkit-box;
    -webkit-line-clamp: 2;
  }

  .sidenews li a {
    color: #48494d;
  }

  .sidenews li a:hover {
    color: #00A7EB;
  }

  .sidenews i {
    width: 100px;
    height: 75px;
    overflow: hidden;
    display: block;
    border: #efefef 1px solid;
    float: left;
    margin-right: 10px
  }

  .sidenews img {
    height: 100%;
    max-height: 75px;
    margin: auto;
    -moz-transition: all .5s ease;
    -webkit-transition: all .5s ease;
    -ms-transition: all .5s ease;
    -o-transition: all .5s ease;
    transition: all .5s ease;
    transition: all 0.5s;
  }

  .sidenews li:hover i img {
    transform: scale(1.1)
  }

  .sidenews span {
    font-size: 12px;
    color: #9A9A9A;
    margin-top: 10px;
    display: block
  }
</style>
