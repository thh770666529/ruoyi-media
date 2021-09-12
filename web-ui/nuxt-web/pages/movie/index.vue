<template>
  <div id="aMovieList" class="container bg-fa of mt40">
    <!-- /电影列表 开始 -->
      <el-form :model="queryParams" label-width="70px" ref="queryForm">
      <el-collapse v-model="activeNames">
        <el-collapse-item name="1">
          <template slot="title">
              <span class="ml20 mb10 fsize24">最新电影-电影在线观看</span><span class="ml0 mb10 c-999 fsize14">筛选（辉皇影视提供服务）</span>
          </template>
            <el-form-item label="类别" prop="categoryId">
              <el-col :span="24">
                <el-radio-group size="small" fill="#68cb9b" @change="getList" v-model="queryParams.categoryId" style="margin-bottom: 8px;">
                  <el-radio-button  label="">全部</el-radio-button>
                  <el-radio-button  v-for="(item,index) in categoryOptions" :key="index" :label="item.categoryId">{{item.name}}</el-radio-button>
                </el-radio-group>
              </el-col>
            </el-form-item>
            <el-form-item label="地区" prop="categoryId">
              <el-col :span="24">
                <el-radio-group size="small" fill="#68cb9b" @change="getList" v-model="queryParams.country" style="margin-bottom: 30px;">
                  <el-radio-button  label="">全部</el-radio-button>
                  <el-radio-button  v-for="(dict,index) in countryOptions" :key="index" :label="dict.dictValue">{{dict.dictLabel}}</el-radio-button>
                </el-radio-group>
              </el-col>
            </el-form-item>
          <el-form-item label="排序">
            <el-radio-group size="small" fill="#68cb9b" @change="getList" v-model="queryParams.orderByColumn" style="margin-bottom: 30px;">
              <el-radio-button  label="createTime">时间</el-radio-button>
              <el-radio-button  label="clickCount">人气</el-radio-button>
              <el-radio-button  label="rate">评分</el-radio-button>
            </el-radio-group>
          </el-form-item>
        </el-collapse-item>
      </el-collapse>
      </el-form>

      <section class="c-sort-box">
        <div class="mt40">
          <!-- /无数据提示 开始-->
          <section v-if="total === 0" class="no-data-wrap" >
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">没有相关视频，小编正在努力整理中...</span>
          </section>


          <article v-if="total>0" class="comm-movie-list">
            <ul id="bna" class="of">
              <li v-for="movie in movieList" :key="movie.movieId">
                <div class="cc-l-wrap">
                  <section class="movie-img">
                    <img :src="fileUploadHost + movie.images" class="img-responsive" :alt="movie.title">
                    <div class="cc-mask">
                      <a :href="'/movie/' + movie.movieId" title="开始观看" class="comm-btn c-btn-1">开始观看</a>
                    </div>
                  </section>
                  <h4 class="title">
                    <a :href="'/movie/' + movie.movieId" :title="movie.title">{{movie.title}}</a>
                  </h4>
                  <p class="text text-overflow text-muted hidden-xs">
                    主演：{{actorFormatter(movie.actorList) | ellipsis(10)}}
                    <br>标签 :{{ tagFormat(tagOptions , movie.tagId) | ellipsis(10)}}
                  </p>
                </div>
              </li>
            </ul>
            <div class="clear"/>
          </article>
        </div>
        <!-- 公共分页 开始 -->
        <div>
          <div class="block">
            <pagination
              v-show="total>0"
              :total="total"
              :page.sync="queryParams.pageNum"
              :limit.sync="queryParams.pageSize"
              @pagination="getList"
            />
          </div>
        </div>
      </section>
    <!-- /电影列表 结束 -->
  </div>
</template>
<script>
import movieApi from '@/api/media/movie';
import { listTag } from "@/api/media/tag";
import { listCategory } from "@/api/media/category";
import { getDictsByTypeList } from "@/api/system/dict/data";
export default {
  // 异步调用
  async asyncData({ params, error }) {
    const tagOptions = await listTag({status: '1'});
    const categoryOptions = await listCategory({status: '1'});
    const dictTypeList =  ['movie_country', 'movie_status', 'movie_type'];
    const dictDataList = await getDictsByTypeList(dictTypeList);
    return {
      countryOptions: dictDataList.data.movie_country,
      tagOptions: tagOptions.rows,
      categoryOptions: categoryOptions.rows
    }
  },
  data() {
    return {
      activeNames: ['1'], //折叠面板默认打开
      movieList: [],  //电影列表
      total: 0,
      searchObj: {}, // 查询表单对象
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
        isAsc: 'desc'
      }
    }
  },
  created() {
    this.getList();
  },
  methods:{
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
            actions.push(tagOptions[j].content + currentSeparator);
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
    }
  }
};
</script>
<style scoped>
  .active {
    background: #bdbdbd;
  }
  .hide {
    display: none;
  }
  .show {
    display: block;
  }
  .container{width:1300px}
</style>
