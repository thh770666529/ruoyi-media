<template>
  <div id="aCoursesList"  class="bg-fa of">
    <!-- /电影详情 开始 -->
    <section class="container">
      <section class="path-wrap txtOf hLh30">
        <a href="#" title class="c-999 fsize14">首页</a>
      </section>
      <div>
        <article class="c-v-pic-wrap" style="height: 380px;">
          <section class="p-h-video-box" id="videoPlay">
            <img height="380px" :src="`http://localhost:7070`+movieVo.images" :alt="movieVo.title" class="dis c-v-pic">
          </section>
        </article>
        <aside class="c-attr-wrap">
          <section class="ml20 mr15">
            <h2 class="hLh30 txtOf mt15">
              <span class="c-fff fsize24">{{movieVo.title}}</span>
            </h2>
            <section class="c-attr-mt10 c-attr-undis">
              <span class="c-fff fsize14">分类：&nbsp;{{selectDictLabel(typeOptions, movieVo.type)}}</span>&nbsp;&nbsp;
              <span class="c-fff fsize14">地区：&nbsp;{{selectDictLabel(countryOptions, movieVo.country)}}</span>&nbsp;&nbsp;
              <span class="c-fff fsize14">年份：&nbsp;{{ parseTime(movieVo.publishTime, '{y}') }}</span>
            </section>

            <section class="c-attr-mt10 c-attr-undis">
              <span class="c-fff fl">评分：</span>
              <el-rate
                v-model="movieVo.rate"
                disabled
                style="font-size:20px;"
                show-score
                text-color="#ff9900"
                score-template="{value}">
              </el-rate>
            </section>
            <section class="c-attr-mt10 c-attr-undis">
              <span class="c-fff fsize14">导演：&nbsp;妹妹</span>
            </section>
            <section class="c-attr-mt10 c-attr-undis">
              <span class="c-fff fsize14">主演：&nbsp;小美&nbsp;小明&nbsp;小张</span>
            </section>
            <section class="c-attr-mt10 c-attr-undis">
              <span class="c-fff fsize14">标签：&nbsp;{{labelValue}}</span>
            </section>

            <section class="c-attr-mt10 c-attr-undis">
              <span class="c-fff fsize14">发布人：&nbsp;{{movieVo.publishUsername}}</span>
            </section>

            <section class="c-attr-mt of">
              <span class="ml10 vam">
                <em class="icon18 scIcon"></em>
                <a class="c-fff vam" title="收藏" href="#" >收藏</a>
              </span>
            </section>

            <section   class="c-attr-mt">
              <a @click="play()" title="立即观看" class="comm-btn c-btn-3">立即观看</a>
            </section>
            <!--<section  class="c-attr-mt">
              <a  href="#" title="立即购买" class="comm-btn c-btn-3">立即购买</a>
            </section>-->
          </section>
        </aside>
       <!-- <aside class="thr-attr-box">
          <ol class="thr-attr-ol">
            <li>
              <p>&nbsp;</p>
              <aside>
                <span class="c-fff f-fM">购买数</span>
                <br>
                <h6 class="c-fff f-fM mt10">0&lt;!&ndash;{{movieVo.buyCount}}&ndash;&gt;</h6>
              </aside>
            </li>
            <li>
              <p>&nbsp;</p>
              <aside>
                <span class="c-fff f-fM">课时数</span>
                <br>
                <h6 class="c-fff f-fM mt10">20</h6>
              </aside>
            </li>
            <li>
              <p>&nbsp;</p>
              <aside>
                <span class="c-fff f-fM">浏览数</span>
                <br>
                <h6 class="c-fff f-fM mt10">501</h6>
              </aside>
            </li>
          </ol>
        </aside>-->
        <div class="clear"></div>
      </div>
      <!-- /电影封面介绍 -->
      <div class="mt20 c-infor-box">
        <article class="fl col-12" style="width: 100%">
          <section class="mr5">
            <div class="i-box">
              <div>
                <section id="c-i-tabTitle" class="c-infor-tabTitle c-tab-title">
                  <a name="c-i" class="current" title="电影详情">电影详情</a>
                </section>
              </div>
              <article class="ml10 mr10 pt20">
                <div>
                  <h6 class="c-i-content c-infor-title">
                    <span>电影介绍</span>
                  </h6>
                  <div class="course-txt-body-wrap">
                    <section class="course-txt-body">
                      <p v-html="movieVo.description">{{movieVo.description}}</p>
                    </section>
                  </div>
                </div>
                <!-- /电影介绍 -->
                <div class="mt50">
                  <h6 class="c-g-content c-infor-title">
                    <span>剧集列表</span>
                  </h6>
                  <section class="mt20" >
                    <div class="lh-menu-wrap">
                      <menu id="lh-menu" class="lh-menu mt10 mr10">
                        <ul>
                          <!-- 文件目录 -->
                          <li class="lh-menu-stair" v-for="video in videoList" :key="video.videoId">

                            <ol class="lh-menu-ol" style="display: block;">
                              <li class="lh-menu-second ml30">
                                <a :href="'/video/'+video.videoId" target="_blank">
                                  <span class="fr">
                                    <i class="free-icon vam mr10">免费试听</i>
                                  </span>
                                  <em class="lh-menu-i-2 icon16 mr5">&nbsp;</em>{{video.title}}
                                </a>
                              </li>
                            </ol>

                          </li>
                        </ul>
                      </menu>
                    </div>
                  </section>
                </div>
                <!-- /电影大纲 -->
              </article>
            </div>
          </section>
        </article>
       <!-- <aside class="fl col-3">
          <div class="i-box">
            <div>
              <section class="c-infor-tabTitle c-tab-title">
                <a title href="javascript:void(0)">演员</a>
              </section>
              <section class="stud-act-list">
                <ul style="height: auto;">
                  <li>
                    <div class="u-face">
                      <a href="#">
                        <img :src="movieVo.avatar" width="50" height="50" alt>
                      </a>
                    </div>
                    <section class="hLh30 txtOf">
                      <a class="c-333 fsize16 fl" href="#">{{movieVo.teacherName}}</a>
                    </section>
                    <section class="hLh20 txtOf">
                      <span class="c-999">{{movieVo.intro}}</span>
                    </section>
                  </li>
                </ul>
              </section>
            </div>
          </div>
        </aside>-->
        <div class="clear"></div>
      </div>
    </section>
    <!-- /电影详情 结束 -->
  </div>
</template>

<script>
import movieApi from '@/api/movie'
import ordersApi from '@/api/orders'
import { MessageBox, Message } from 'element-ui'
export default {
  // 异步调用
   asyncData({ params, error }) {
     return {movieId: params.movieId}
   },
   data() {
     return {
        movieVo: {},
        videoList: [],
        isbuy: false,
       //电影国家字典
       countryOptions:[],
       //状态字典
       statusOptions:[],
       //电影类型
       typeOptions:[],
       //标签字典
       labelOptions:[],
       //标签值
       labelValue:''
     }
   },
  created() {//在页面渲染之前执行
    this.getDicts("movie_country").then(response => {
      this.countryOptions = response.data.data;
    });
    this.getDicts("movie_status").then(response => {
      this.statusOptions = response.data.data;
    });
    this.getDicts("movie_type").then(response => {
      this.typeOptions = response.data.data;
    });
    this.getDicts("movie_label").then(response => {
      this.labelOptions = response.data.data;
    });

    this.initInfo();
  },
   methods:{
     //查询电影详情信息
     initInfo() {
       movieApi.getMovie(this.movieId)
          .then(response => {
            this.movieVo=response.data.data;
            this.videoList=this.movieVo.videoList;
            //this.isbuy=response.data.data.isBuy
            this.labelValue =  this.selectDictLabels(this.labelOptions, this.movieVo.label);
        })
     },
     //生成订单
     createOrders() {
      /* ordersApi.createOrders(this.courseId)
        .then(response => {
          //获取返回订单号
          //生成订单之后，跳转订单显示页面
          this.$router.push({path:'/orders/'+response.data.data.orderId})
        })*/
     },
     //播放
     play(){
       if (this.videoList.length==0){
         Message({
           message: '暂时没有播放的视频！请联系管理员进行上传',
           type: 'error'
         })
       }else {
         window.open("/video/"+this.videoList[0].videoId);
       }
     }
   }
};
</script>
