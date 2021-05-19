<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- /电影列表 开始 -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
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
            <!-- <li :class="{'current bg-orange':buyCountSort!=''}">
                <a title="销量" href="javascript:void(0);" @click="searchBuyCount()">销量
                <span :class="{hide:buyCountSort==''}">↓</span>
                </a>
              </li>
              <li :class="{'current bg-orange':gmtCreateSort!=''}">
                <a title="最新" href="javascript:void(0);" @click="searchGmtCreate()">最新
                <span :class="{hide:gmtCreateSort==''}">↓</span>
                </a>
              </li>
              <li :class="{'current bg-orange':priceSort!=''}">
                <a title="价格" href="javascript:void(0);" @click="searchPrice()">价格&nbsp;
                  <span :class="{hide:priceSort==''}">↓</span>
                </a>
              </li>-->

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
          <section v-if="total==0" class="no-data-wrap" >
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
          </section>


          <article v-if="total>0" class="comm-course-list">
            <ul id="bna" class="of">
              <li v-for="movie in movieList" :key="movie.movieId">
                <div class="cc-l-wrap">
                  <section class="course-img">
                    <img :src="fileUploadHost+movie.images" :alt="movie.title" class="img-responsive">
                    <div class="cc-mask">
                      <a :href="'/movie/'+movie.movieId" title="开始观看" class="comm-btn c-btn-1">开始学习</a>
                    </div>
                  </section>
                  <h3 class="hLh30 txtOf mt10">
                    <a :href="'/movie/'+movie.movieId" :title="movie.title" class="course-title fsize18 c-333">{{ movie.title }}</a>
                  </h3>
                  <section class="mt10 hLh20 of">
                    <span  class="fr jgTag bg-green">
                      <i class="c-fff fsize12 f-fA">免费</i>
                    </span>
                    <!--<span v-else class="fr jgTag ">
                      <i class="c-orange fsize12 f-fA"> ￥0</i>
                    </span>-->
                    <span class="fl jgAttr c-ccc f-fA">
                      <i class="c-999 f-fA">0人学习</i>
                      |
                      <i class="c-999 f-fA">0人购买</i>
                    </span>
                  </section>
                </div>
              </li>
            </ul>
            <div class="clear"/>
          </article>



        </div>
        <!-- 公共分页 开始 -->
        <div>
      <div class="paging">
       <a
          :class="{undisable: true}"
          href="#"
          title="首页"
          @click.prevent="gotoPage(1)">首</a>
        <a
          :class="{undisable: true}"
          href="#"
          title="前一页"
          @click.prevent="gotoPage(queryParams.pageNum-1)">&lt;</a>


        <a
          v-for="page in totalPage"
          :key="page"
          :class="{current: queryParams.pageNum == page, undisable: queryParams.pageNum == page}"
          :title="'第'+page+'页'"
          href="#"
          @click.prevent="gotoPage(page)">{{ page }}</a>
        <a
          href="#"
          title="后一页"
          @click.prevent="queryParams.pageNum<pages?gotoPage(queryParams.pageNum+1):gotoPage(queryParams.pageNum)">&gt;</a>
        <a
          href="#"
          title="末页"
          @click.prevent="gotoPage(pages)">末</a>

        <div class="clear"/>
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
      fileUploadHost:'http://localhost:7070',
      page:1, //当前页
      movieList:[],  //电影列表
      subjectNestedList: [], // 一级分类列表
      subSubjectList: [], // 二级分类列表
      total: 0,
      searchObj: {}, // 查询表单对象
      totalPage:[],
      pages:0,
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
      oneIndex:-1,
      twoIndex:-1,
      buyCountSort:"",
      gmtCreateSort:"",
      priceSort:""
    }
  },
  created() {
    this.getList()
  },
  methods:{
    //1 查询第一页数据
    getList() {

      movieApi.listMovie(this.queryParams).then(response => {
        this.movieList = response.data.rows;
        this.total = response.data.total;
        this.getTotalPage();
      })
    },

    getTotalPage(){
      this.pages = 0;
      this.pages = this.total / this.queryParams.pageSize;
      if (this.total % this.queryParams.pageSize != 0) {
        this.pages++;
      }
      if (this.pages>0){
        this.totalPage = [];
        for (let i = 1; i < this.pages; i++) {
          this.totalPage.push(i);
        }
      }
    },

    //2 查询所有一级分类
    initSubject() {
      /*courseApi.getAllSubject()
        .then(response => {
          this.subjectNestedList = response.data.data.list
        })*/
    },

    gotoPage(page) {
      this.queryParams.pageNum = page;
      this.getList();
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
      this.gotoPage(1)

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
      this.gotoPage(1)
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
      this.gotoPage(1)
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
      this.gotoPage(1)
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
      this.gotoPage(1)
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
