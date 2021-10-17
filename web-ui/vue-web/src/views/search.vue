<template>
  <div class="movie-container">
    <el-row type="flex">
      <!--左边-->
      <el-col :span="16" class="mt20">
        <span>
          <el-input v-model="keyword" maxlength="256" >
             <el-button type="primary" @click="search"  slot="append">搜索</el-button>
          </el-input>
        </span>
        <div class="keyword">与<span style='color:red'>{{queryParams.keyword}}</span>相关的搜索结果</div>

        <el-empty v-if="movieList.length === 0" description="没有搜索到任何数据！"></el-empty>

        <div
          v-for="(item,index) in movieList"
          :key="index"
          class="movie-item"
          data-scroll-reveal="enter bottom over 1s">

      <span class="movie-image">
          <a href="javascript:void(0);" @click="toMovieDetail(item.movieId)">
            <el-image lazy class="movie-banner" :src="fileUploadHost + item.images" :alt="item.title">
              <div slot="error" class="image-slot">
                 <img class="movie-banner" src="../assets/styles/images/hotmovie1.png">
              </div>
            </el-image>
          </a>
        </span>
          <h3 class="movie-title">
            <a href="javascript:void(0);" @click="toMovieDetail(item.movieId)" v-html="item.title">{{item.title}}</a>
          </h3>
          <p class="movie-text" v-html="item.description"></p>

          <div class="movie-info">
            <ul>
              <li class="author">
                <span class="iconfont">&#xe60f; </span>
                <a href="javascript:void(0);">{{item.publishUsername ? item.publishUsername : `未知`}}</a>
              </li>
              <li class="lmname" v-if="item.categoryId">
                <span class="iconfont">&#xe603; </span>
                <a
                  href="javascript:void(0);"
                  v-html="item.categoryName"
                ></a>
              </li>
              <li class="view">
                <span class="iconfont">&#xe8c7; </span>
                <span>{{item.clickCount}}</span>
              </li>
              <li class="like">
                <span class="iconfont">&#xe663; </span>
                {{item.collectionCount}}
              </li>
              <li class="createTime">
                <span class="iconfont">&#xe606; </span>
                {{item.publishTime}}
              </li>
            </ul>
          </div>
        </div>


        <div class="block pagination">
          <pagination
            v-show="total>0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="search"
          />
        </div>
      </el-col>
      <el-col :span="6" class="hidden-sm-and-down mt20" id="side" :offset="1">
        <!--关注我们-->
        <div class="item">
          <About></About>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import indexApi from '@/api/index'
  import {getDicts} from '@/api/system/dict/data'
  import {listTag} from "@/api/media/tag";
  import {listCategory} from "@/api/media/category";
  import {getDictsByTypeList} from "@/api/system/dict/data";

  export default {
    name: 'ArticleList',
    components: {},
    data() {
      return {
        movieList: [],
        tagOptions: [],
        categoryOptions: [],
        total: 0,
        keyword: '',
        queryParams: {
          pageNum: 1,
          pageSize: 12,
          keyword: ''
        },
        loading: false
      }
    },
    computed: {
    },
    watch: {
      $route(route) {
        this.keyword =  route.query.keyword;
        this.search();
      }
    },
    created() {
      this.keyword =  this.$route.query.keyword;
      this.init();
    },
    methods: {
      init() {
        const dictTypeList = ['movie_country', 'movie_status', 'movie_type'];
        getDictsByTypeList(dictTypeList).then(response => {
          this.countryOptions = response.data.movie_country;
        });
        listCategory({status: '1'}).then(response => {
          this.categoryOptions = response.rows;
        });
        listTag({status: '1'}).then(response => {
          this.tagOptions = response.rows;
        });
        this.search();
      },
      actorFormatter(actorList) {
        if (!actorList || actorList.length === 0) {
          return '无';
        }
        const currentSeparator = ",";
        let actions = [];
        for (let index = 0; index < actorList.length; index++) {
          actions.push(actorList[index].name + currentSeparator);
        }
        return actions.join('').substring(0, actions.join('').length - 1);
      },
      /** 标签翻译 */
      tagFormat(tagOptions, tagId) {
        if (!tagId || !tagOptions) {
          return ''
        }
        const currentSeparator = ",";
        let actions = [];
        let tempArr = tagId.split(currentSeparator);
        for (let i = 0; i < tempArr.length; i++) {
          for (let j = 0; j < tagOptions.length; j++) {
            if (tagOptions[j].tagId == ('' + tempArr[i])) {
              actions.push(tagOptions[j].content + '/');
              break;
            }
          }
        }
        return actions.join('').substring(0, actions.join('').length - 1);
      },
      search() {
        if (!this.keyword) {
          this.msgError('搜索关键字不得为空！')
          return;
        }
        this.queryParams.keyword = this.keyword
        indexApi.search(this.queryParams).then(response => {
          this.movieList = response.rows;
          this.total = response.total;
        })
      },
      toMovieDetail(movieId) {
        window.open('/movie/' + movieId, '_blank')
      }
    }
  }
</script>

<style scoped>
  .movie-container {
    width: 1200px;
    margin: auto;
  }

  .keyword {
    font-size: 24px;
    margin-bottom: 15px;
  }

  #side .item {
    margin-bottom: 30px;
    margin-top: 8px;
  }

  h5 {
    font-size: 18px;
  }

  .pagination {
    background-color: #F9F9F9;
  }

  html, body {
    margin: 0;
    padding: 0;
  }

  [v-cloak] {
    display: none;
  }

  .movie-item {
    position: relative;
    overflow: hidden;
    margin-bottom: 20px;
    padding: 20px;
    background: #FFF;
    -webkit-box-shadow: 0 2px 5px 0 rgba(146, 146, 146, .1);
    -moz-box-shadow: 0 2px 5px 0 rgba(146, 146, 146, .1);
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);

    -webkit-transition: all 0.6s ease;
    -moz-transition: all 0.6s ease;
    -o-transition: all 0.6s ease;
    transition: all 0.6s ease;
  }

  .movie-item .star {
    width: 50px;
    height: 50px;
    position: absolute;
    top: 0;
    right: 0;
  }

  .movie-item .movie-image {
    width: 160px;
    height: 220px;
    float: left;
    margin-right: 20px;
    display: block;
    overflow: hidden;
  }

  .movie-item .movie-image img {
    width: 100%;
    height: auto;
    -webkit-transition: all 0.6s ease;
    -moz-transition: all 0.6s ease;
    -o-transition: all 0.6s ease;
    transition: all 0.6s ease;
    margin-bottom: 10px
  }

  .movie-item .movie-image :hover img {
    transform: scale(1.4)
  }

  .movie-banner {
    width: 100%;
    height: 100%;
    transition: all 0.6s;
  }

  .movie-banner:hover {
    transform: scale(1.4);
  }


  .movie-item .movie-title {
    margin: 0 0 10px 0;
    font-size: 20px;
    overflow: hidden;
  }

  .movie-item .movie-title a:hover {
    color: #337ab7;
  }

  .movie-item .movie-text {
    font-size: 14px;
    color: #566573;
    overflow: hidden;
    text-overflow: ellipsis;
    -webkit-box-orient: vertical;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    margin-top: 20px
  }

  .movie-info {
    overflow: hidden;
    bottom: 20px;
    left: 200px;
    position: absolute;
  }

  .movie-info ul li {
    float: left;
    font-size: 12px;
    padding: 0 20px 0 0;
    margin: 0 15px 0 0;
    color: #748594;
    line-height: 1.5;
    display: inline-block;
  }

  .movie-info ul li a {
    color: #748594;
  }

  .movie-info ul li a:hover {
    color: #000
  }

   .s_ipt_wr {
    width: 590px;
    height: 36px;
    border: 2px solid #c4c7ce;
    border-radius: 10px 0 0 10px;
    border-right: 0;
    overflow: visible;
    background-image: none;
  }

</style>
