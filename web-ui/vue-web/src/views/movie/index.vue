<template>
  <div>
    <!-- 导航 -->
    <div class="movies-nav">
      <div class="nav-body">
        <div class="hotshowing">
          <a href="#" :class="defaulActive == `1`?`active`:`` " @click="defaulActive = `1`">
            正在热映
          </a>
        </div>
        <div class="willshow">
          <a href="#" :class="defaulActive == `2`?`active`:`` " @click="defaulActive = `2`">
            即将上映
          </a>
        </div>
        <div class="oldmovie">
          <a href="#" :class="defaulActive == `3`?`active`:`` " @click="defaulActive = `3`">
            经典影片
          </a>
        </div>
      </div>
    </div>
    <!-- 电影分类列表 -->
    <div class="movie-query">
      <div class="list-body">
        <div class="list-model">
          <div class="name">
            类型:
          </div>
          <ul class="tags">
            <el-radio-group size="small" fill="#f34d41" @change="getList" style="margin-left: 10px" v-model="queryParams.categoryId">
              <el-radio-button  label="">全部</el-radio-button>
              <el-radio-button  v-for="(item,index) in categoryOptions"  :key="index" :label="item.categoryId">{{item.name}}</el-radio-button>
            </el-radio-group>
          </ul>
        </div>
        <div class="list-model">
          <div class="name">
            区域:
          </div>
          <ul class="tags">
            <el-radio-group size="small" fill="#f34d41" @change="getList" v-model="queryParams.country" style="margin-left: 10px">
              <el-radio-button  label="">全部</el-radio-button>
              <el-radio-button  v-for="(dict,index) in countryOptions" :key="index" :label="dict.dictValue">{{dict.dictLabel}}</el-radio-button>
            </el-radio-group>
          </ul>
        </div>
        <div class="list-model">
          <div class="name">
            年代:
          </div>
          <ul class="tags">
            <el-radio-group size="small" fill="#f34d41" @change="getList" v-model="queryParams.publishYear" style="margin-left: 10px">
              <el-radio-button  label="">全部</el-radio-button>
              <el-radio-button  v-for="(item,index) in timeOptions" :key="index" :label="item">{{item}}</el-radio-button>
            </el-radio-group>
          </ul>
        </div>
        <div class="list-model">
          <div class="name">
            排序:
          </div>
          <ul class="tags">
            <el-radio-group size="small" fill="#f34d41" @change="getList" style="margin-left: 10px" v-model="queryParams.orderByColumn">
              <el-radio-button label="createTime">时间</el-radio-button>
              <el-radio-button label="clickCount">热门</el-radio-button>
              <el-radio-button label="rate">评分</el-radio-button>
            </el-radio-group>
          </ul>
        </div>
      </div>
    </div>
    <!-- 电影列表 -->
    <div class="movies-body">
      <ul>
        <li class="othermovie-model" v-for="movie in movieList" :key="movie.movieId">
          <a :href="`/movie/` + movie.movieId">
            <div class="movie-poster">
              <img class="img-responsive" :src="fileUploadHost + movie.images" />
<!--              <div class="cc-mask">
                <a :href="`/movie/` + movie.movieId">开始观看</a>
                <el-button type="primary" style="margin-top: 100px" @click="toDetail(movie.movieId)" plain>开始观看</el-button>
              </div>-->
              <div class="movie-overlay movie-overlay-bg">
                <div class="movie-info">
                  <div class="movie-score"><i class="integer">{{ movie.rate }}</i></div>
                  <div class="movie-title"  style="float: left" :title="movie.title">{{ movie.title }}</div>
                </div>
              </div>
            </div>
          </a>
          <span style="float: left">主演：{{actorFormatter(movie.actorList) | ellipsis(8)}}</span><br>
          <span style="float: left">标签：{{ tagFormat(tagOptions , movie.tagId) | ellipsis(8)}}</span>
         <!--<i class="mix2d"></i>-->
        </li>

      </ul>
    </div>
    <!-- 公共分页 开始 -->
    <div style="display: block;width: 1200px;text-align: center;margin: 80px 0 40px 0;">
      <pagination
      v-show="total>0"
      :total="total"
      :pageSizes="[12, 24, 36, 48]"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"/>
    </div>
  </div>
</template>
<script>
  import '@/assets/styles/less/movielist.less'
  import index from '@/api/index'
  import { getDicts } from '@/api/system/dict/data'
  import { listTag } from "@/api/media/tag";
  import { listCategory } from "@/api/media/category";
  import {getDictsByTypeList} from "@/api/system/dict/data";
  import movieApi from "@/api/media/movie";
  export default {
    data() {
      return {
        defaulActive: '1',
        countryOptions: [],
        tagOptions: [],
        categoryOptions: [],
        timeOptions: [
          '2021',
          '2020',
          '2019',
          '2018',
          '2017',
          '2016',
          '2015',
          '2014',
          '2013',
          '2012',
          '2011',
          '2010'
        ],
        activeNames: ['1'], //折叠面板默认打开
        movieList: [],  //电影列表
        total: 0,
        queryParams: {
          pageNum: 1,
          pageSize: 12,
          images: null,
          title: null,
          movieType: null,
          country: '',
          tagId: null,
          description: null,
          publishBy: null,
          publishTime: null,
          status: null,
          categoryId: '',
          orderByColumn: 'createTime',
          isAsc: 'desc',
          publishYear: ''
        }
      }
    },
    created() {
      this.init();
    },
    methods:{
      init() {
        const dictTypeList =  ['movie_country', 'movie_status', 'movie_type'];
        getDictsByTypeList(dictTypeList).then(response => {
          this.countryOptions = response.data.movie_country;
        });
        listCategory({status: '1'}).then(response => {
          this.categoryOptions =  response.rows;
        });
        listTag({status: '1'}).then(response => {
          this.tagOptions =  response.rows;
        });
        this.getList();
      },
      actorFormatter(actorList) {
        if (!actorList||actorList.length === 0){
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
      tagFormat(tagOptions,tagId) {
        if(!tagId || !tagOptions) {
          return ''
        }
        const currentSeparator = ",";
        let actions = [];
        let tempArr = tagId.split(currentSeparator);
        for (let i = 0; i < tempArr.length; i++) {
          for (let j = 0; j < tagOptions.length ; j++) {
            if (tagOptions[j].tagId == ('' + tempArr[i])) {
              actions.push(tagOptions[j].content + '/');
              break;
            }
          }
        }
        return actions.join('').substring(0, actions.join('').length - 1);
      },
      //1 查询第一页数据
      getList() {
        movieApi.listMovie(this.queryParams).then(response => {
          this.movieList = response.rows;
          this.total = response.total;
        })
      },
      toDetail(movieId) {
        window.location.href = 'movie' + movieId;
      }
    }
  }
</script>
<style scoped>
.cc-mask{background:rgba(0,0,0,.4);height:100%;left:0;opacity:0;filter:alpha(opacity=0);position:absolute;right:0;top:0;transition:all .3s ease 0s;width:100%;transition:.3s;-webkit-transition:.3s}
.movies-body ul li:hover .img-responsive{transform:scale(1.1);-webkit-transform:scale(1.1)}
.movies-body ul li:hover .cc-mask{opacity:1;filter:alpha(opacity=100)}
</style>
