<template>
  <div id="aMovieList" class="bg-fa of">
    <!-- /电影列表 开始 -->

    <section class="container">
      <header class="comm-title">
        <h2 class="fl actor">
          <span class="c-333">全部电影</span>
        </h2>
      </header>
      <section class="c-sort-box">
        <section class="c-s-dl">
          <dl>
            <dt>
              <span class="c-999 fsize14">电影类别</span>
            </dt>
            <dd class="c-s-dl-li">
              <ul class="clearfix">
                <li>
                  <a title="全部" href="#">全部</a>
                </li>
                <!--<li v-for="(item,index) in subjectNestedList" :key="index" :class="{active:oneIndex==index}">
                  <a :title="item.title" href="#" @click="searchOne(item.id,index)">{{item.title}}</a>
                </li>-->

              </ul>
            </dd>
          </dl>
          <dl>
            <dt>
              <span class="c-999 fsize14"></span>
            </dt>
            <dd class="c-s-dl-li">
              <ul class="clearfix">
                <!--<li v-for="(item,index) in subSubjectList" :key="index" :class="{active:twoIndex==index}">
                  <a :title="item.title" href="#" @click="searchTwo(item.id,index)">{{item.title}}</a>
                </li>-->

              </ul>
            </dd>
          </dl>
          <div class="clear"></div>
        </section>
        <div class="js-wrap">
          <section class="fr">
            <span class="c-ccc">
              <i class="c-master f-fM">1</i>/
              <i class="c-666 f-fM">1</i>
            </span>
          </section>
          <section class="fl">
            <ol class="js-tap clearfix">
              <li class="current bg-orange">
                <a title="销量" href="javascript:void(0);" >销量
                  <span >↓</span>
                </a>
              </li>
              <li class="current bg-orange">
                <a title="最新" href="javascript:void(0);" >最新
                  <span >↓</span>
                </a>
              </li>
              <li class="current bg-orange">
                <a title="价格" href="javascript:void(0);" >价格&nbsp;
                  <span >↓</span>
                </a>
              </li>
            </ol>
          </section>
        </div>
        <div class="mt40">
          <!-- /无数据提示 开始-->
          <section v-if="total === 0" class="no-data-wrap" >
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
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
                  <p class="text text-overflow text-muted hidden-xs">主演：.........
                    <br>标签 :  {{selectDictLabels(labelOptions, movie.label) | ellipsis(10)}}
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
    </section>
    <!-- /电影列表 结束 -->
  </div>
</template>
<script>
import movieApi from '@/api/movie'
export default {
  data() {
    return {
      page: 1, //当前页
      movieList: [],  //电影列表
      subjectNestedList: [], // 一级分类列表
      subSubjectList: [], // 二级分类列表
      total: 0,
      searchObj: {}, // 查询表单对象
      queryParams: {
        pageNum: 1,
        pageSize: 12,
        images: null,
        title: null,
        movieType: null,
        country: null,
        label: null,
        description: null,
        publishBy: null,
        publishTime: null,
        status: null
      },
      oneIndex: -1,
      twoIndex: -1,
      buyCountSort: "",
      gmtCreateSort: "",
      priceSort: "",
      labelOptions: []
    }
  },
  created() {
    this.getDicts("movie_label").then(response => {
      this.labelOptions= response.data;
    })
    this.getList()
  },
  methods:{
    //1 查询第一页数据
    getList() {
      movieApi.listMovie(this.queryParams).then(response => {
        this.movieList = response.rows;
        this.total = response.total;
      })
    },


    //4 点击某个一级分类，查询对应二级分类
    searchOne(subjectParentId,index) {
      //把传递index值赋值给oneIndex,为了active样式生效
      /*this.oneIndex = index

      this.twoIndex = -1
      this.searchObj.subjectId = ""
      this.subSubjectList = []

      //把一级分类点击id值，赋值给searchObj
      this.searchObj.subjectParentId = subjectParentId
      //点击某个一级分类进行条件查询

      //拿着点击一级分类id 和 所有一级分类id进行比较，
      //如果id相同，从一级分类里面获取对应的二级分类
      for(let i=0;i<this.subjectNestedList.length;i++) {
        //获取每个一级分类
        var oneSubject = this.subjectNestedList[i]
        //比较id是否相同
        if(subjectParentId == oneSubject.id) {
          //从一级分类里面获取对应的二级分类
          this.subSubjectList = oneSubject.children
        }
      }*/
    },

    //5 点击某个二级分类实现查询
    searchTwo(subjectId,index) {
      //把index赋值,为了样式生效
      this.twoIndex = index
      //把二级分类点击id值，赋值给searchObj
      this.searchObj.subjectId = subjectId
      //点击某个二级分类进行条件查询
    },

    //6 根据销量排序
    searchBuyCount() {
      /*//设置对应变量值，为了样式生效
      this.buyCountSort = "1"
      this.gmtCreateSort = ""
      this.priceSort = ""

      //把值赋值到searchObj
      this.searchObj.buyCountSort = this.buyCountSort
      this.searchObj.gmtCreateSort = this.gmtCreateSort;
      this.searchObj.priceSort = this.priceSort;*/

      //调用方法查询
    },

    //7 最新排序
    searchGmtCreate() {
      //设置对应变量值，为了样式生效
    /*  this.buyCountSort = ""
      this.gmtCreateSort = "1"
      this.priceSort = ""

      //把值赋值到searchObj
      this.searchObj.buyCountSort = this.buyCountSort
      this.searchObj.gmtCreateSort = this.gmtCreateSort;
      this.searchObj.priceSort = this.priceSort;*/
      //调用方法查询
    },

    //8 价格排序
    searchPrice() {
      //设置对应变量值，为了样式生效
    /*  this.buyCountSort = ""
      this.gmtCreateSort = ""
      this.priceSort = "1"

      //把值赋值到searchObj
      this.searchObj.buyCountSort = this.buyCountSort
      this.searchObj.gmtCreateSort = this.gmtCreateSort;
      this.searchObj.priceSort = this.priceSort;*/

      //调用方法查询
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
</style>
