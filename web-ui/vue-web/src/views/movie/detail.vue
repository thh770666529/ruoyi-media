<template>
  <div >
    <!-- 电影简介 -->
    <div class="movie-top">
      <div class="movie-banner">
        <div class="movie-img">
          <el-image :src="fileUploadHost + movie.images" :alt="movie.title">
            <div slot="error" class="image-slot">
              <img src="../../assets/styles/images/hotmovie1.png" alt="" style="width: 100%" >
            </div>
          </el-image>
        </div>
        <div class="movie-data">
          <div class="movie-name">
            <h2 class="name" id="moviename">{{movie.title}}</h2>
            <!-- <h2 class="othername" id="englishname">天気の子</h2>-->
            <p class="kind" id="kind">{{selectDictLabel(typeOptions, movie.type)}}</p>
            <p class="kind" id="category">{{ movie.categoryName ? movie.categoryName: '无' }}</p>
            <p class="area" id="adress">{{selectDictLabel(countryOptions, movie.country)}} / 113分钟</p>
            <p class="time" id="time"> {{ parseTime(movie.publishTime, '{y}-{m}-{d}') }}上映</p>
            <p class="time">
              <el-tag
                size="mini"
                style="margin-left: 3px"
                :key="index"
                v-for="(item, index) in tagFormat(tagOptions , movie.tagId)"
              >{{ item }}
              </el-tag>
            </p>
          </div>
          <div class="movie-btn">
            <a href="#"><i></i> <span>想看</span></a>
            <a href="#"><i></i> <span>评分</span></a>
          </div>
          <div class="watching">
            <p class="name">评分</p>
            <el-rate
              v-model="movie.rate"
              disabled
              style="font-size:24px;"
              show-score
              text-color="#ff9900"
              score-template="{value}">
            </el-rate>
<!--            <p class="wantnum">
              111
            </p>-->
            <p class="money">累计票房</p>
            <p class="num">3000 <span>万</span></p>
          </div>
        </div>
      </div>
    </div>
    <!-- 页面主体 -->
    <div class="container">
      <!-- 左部主题内容 -->
      <div class="main">
        <!-- 标签导航 -->
        <div class="nav">
          <a href="index.html">
            猫眼电影
          </a> >
          <a href="#">
            电影
          </a> >
          <a href="#">
            天气之子
          </a>
        </div>

        <div class="nav">
          <div class="intro">
            <span>剧集列表</span>
          </div>
          <el-row :gutter="24">
            <el-col :xs="3" :sm="4" :md="6" :lg="8"  style="padding: 8px;" v-for="video in videoList" :key="video.videoId" >
              <el-button v-if="video.title && video.title.length < 6" @click="play(video.videoId)"  style="margin-bottom: 20px" plain>{{ video.title | ellipsis(6)}}</el-button>
              <el-tooltip v-else :content="video.title" placement="top">
                <el-button @click="play(video.videoId)"  style="margin-bottom: 20px" plain>{{ video.title | ellipsis(6)}}</el-button>
              </el-tooltip>

            </el-col>
          </el-row>
        </div>

        <!-- 标签页切换控制 -->
        <el-tabs v-model="activeName">
          <el-tab-pane label="介绍" name="description">
<!--            <div class="border"></div>-->
            <!-- 剧情简介 -->
            <div class="referral">
              <div class="intro">
                剧情简介
              </div>
              <span v-html="movie.description">{{movie.description}}</span>
            </div>
            <!-- 演职人员 -->
            <div class="actor">
              <div class="intro">
                <div class="intro-text">
                  <span>演职人员</span>
                  <span>
                            <a href="#">
                                全部>
                            </a>
                        </span>
                </div>
              </div>
              <div class="director">
                <p>导演</p>
                <div class="director-img">
                  <img src="@/assets/styles/images/新海诚.jpg" alt="">
                  <p>新海诚</p>
                </div>
              </div>
              <div class="starts">
                <p>演员</p>
                <div class="start">
                  <img class="default-img" alt="天气之子 醍醐虎汰朗" src="https://p0.meituan.net/moviemachine/74cf5577c9cf7c2bf60f3c023c37135f158637.jpg@128w_170h_1e_1c">
                  <p>醍醐虎汰朗</p>
                  <p>饰:森鸟帆高</p>
                </div>
                <div class="start">
                  <img class="default-img" alt="天气之子 森七菜" src="https://p0.meituan.net/moviemachine/9a34f136c4edc05c42c4a4ccd0bb2585157478.jpg@128w_170h_1e_1c">
                  <p>森七菜</p>
                  <p>饰:天野阳菜</p>
                </div>
                <div class="start">
                  <img class="default-img" alt="天气之子 本田翼" src="https://p0.meituan.net/moviemachine/be397d21a7f15567ae5ec36063dc5610166398.jpg@128w_170h_1e_1c">
                  <p>本田翼</p>
                  <p>饰:夏美</p>
                </div>
                <div class="start">
                  <img class="default-img" alt="天气之子 小栗旬" src="https://p0.meituan.net/movie/9326c1fe5d87325da3a504d7d806bbcd58300.jpg@128w_170h_1e_1c">
                  <p>小栗旬</p>
                  <p>饰:须贺圭介</p>
                </div>
              </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="演职人员" name="actor">
            <!-- 演职人员 -->
            <div class="actor">
              <div class="director">
                <p>导演</p>
                <div class="director-img">
                  <img src="@/assets/styles/images/新海诚.jpg" alt="">
                  <p>新海诚</p>
                </div>
              </div>
              <div class="starts">
                <p>演员</p>
                <div class="start">
                  <img class="default-img" alt="天气之子 醍醐虎汰朗" src="https://p0.meituan.net/moviemachine/74cf5577c9cf7c2bf60f3c023c37135f158637.jpg@128w_170h_1e_1c">
                  <p>醍醐虎汰朗</p>
                  <p>饰:森鸟帆高</p>
                </div>
                <div class="start">
                  <img class="default-img" alt="天气之子 森七菜" src="https://p0.meituan.net/moviemachine/9a34f136c4edc05c42c4a4ccd0bb2585157478.jpg@128w_170h_1e_1c">
                  <p>森七菜</p>
                  <p>饰:天野阳菜</p>
                </div>
                <div class="start">
                  <img class="default-img" alt="天气之子 本田翼" src="https://p0.meituan.net/moviemachine/be397d21a7f15567ae5ec36063dc5610166398.jpg@128w_170h_1e_1c">
                  <p>本田翼</p>
                  <p>饰:夏美</p>
                </div>
                <div class="start">
                  <img class="default-img" alt="天气之子 小栗旬" src="https://p0.meituan.net/movie/9326c1fe5d87325da3a504d7d806bbcd58300.jpg@128w_170h_1e_1c">
                  <p>小栗旬</p>
                  <p>饰:须贺圭介</p>
                </div>
              </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="奖项" name="awards">
            <!-- 奖项 -->
            <div class="awards">
              <div class="intro">
                <div class="intro-text">
                  <span>奖项</span>
                  <span>
                            <a href="#">
                                全部>
                            </a>
                        </span>
                </div>
              </div>
              <div class="awards-body">
                <ul>
                  <li class="awards-model">
                    <div class="awards-img">
                      <img src="https://p0.meituan.net/movie/04d8bf2467f29ea0b72491587f8b34f95687.jpg@50w_50h_1e_1c" alt="">
                    </div>
                    第43届日本电影学院奖
                    <div class="awards-text">
                      获奖：最佳动画片 / 最佳配乐
                    </div>
                  </li>
                  <li class="awards-model">
                    <div class="awards-img">
                      <img src="https://p0.meituan.net/movie/a9f2bda1bf18b199972d69d1512840ed6444.jpg@50w_50h_1e_1c" alt=""></div>
                    第47届安妮奖
                    <div class="awards-text">
                      提名：最佳动画视觉效果 / 最佳独立动画长片 / 最佳动画导演 / 最佳动画剧本 </div>
                  </li>
                </ul>
              </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="图集" name="images">
            <!-- 图集 -->
            <div class="images">
              <div class="intro">
                <div class="intro-text">
                  <span>图集</span>
                  <span>
                            <a href="#">
                                全部>
                            </a>
                        </span>
                </div>
              </div>
              <div class="images-main">
                <a href="#">
                  <img class="default-img" alt="天气之子剧照图集" src="https://p0.meituan.net/movie/95300ef2d49506fd68a2a56897bc5043315152.jpg@465w_258h_1e_1c">
                </a>
              </div>
              <div class="images-aside">
                <div class="aside-img1">
                  <img class="default-img" alt="天气之子剧照图集" src="https://p0.meituan.net/movie/11e1b4c016551a5f8be80612f6062a7d131097.jpg@126w_126h_1e_1c">
                </div>
                <div class="aside-img2">
                  <img class="default-img" alt="天气之子剧照图集" src="https://p1.meituan.net/movie/7172e7db970129838ebd068372a2bfb1152767.jpg@126w_126h_1e_1c">
                </div>
                <div class="aside-img3">
                  <img class="default-img" alt="天气之子剧照图集" src="https://p0.meituan.net/movie/55e6794ea722a7a58b410e38ae4b7cd7156727.jpg@126w_126h_1e_1c">
                </div>
                <div class="aside-img4">
                  <img class="default-img" alt="天气之子剧照图集" src="https://p0.meituan.net/movie/2b38aeed774ed7fefd72b47ff191cc9c116946.jpg@126w_126h_1e_1c">
                </div>
              </div>
            </div>
          </el-tab-pane>
        </el-tabs>
        <!-- 评论 -->
        <div class="comment">
          <div class="intro">
            <div class="intro-text">
              <span>热门短评</span>
              <span>
                            <a href="#">
                                写短评
                            </a>
                        </span>
            </div>
          </div>
          <div class="comment-body">
            <ul>
              <li class="comment-model">
                <div class="comment-img">
                  <img src="https://p0.meituan.net/movie/04d8bf2467f29ea0b72491587f8b34f95687.jpg@50w_50h_1e_1c" alt="">
                </div>
                <div class="comment-text">
                  <div class="comment-main">
                    <span class="username"> redfth47 <span class="tag">购</span></span>
                    <div>
                                        <span>
                                            <span class="comment-time">2019-11-02</span>
                                        <span class="comment-level"></span>
                                        </span>
                      <a href="#">
                        <i class="zan"></i>
                        <span>8585</span>
                      </a>
                    </div>
                  </div>
                  <p> 快30岁的人了独自跑去看，进了影院发现全是小年轻，还是比较尴尬的。08年高考前一年我和她还在为艺考奔波，她用U盘给我看了秒速五厘米，后来陆续接触了遥远世界，星之声，云之彼端，她和她的猫，被极其干净漂亮的场设吸引，从小被日漫熏陶下大学选择了动画制作专业，至此和她相隔两地。虽然那么多年过去，动画制作没有成为我的职业，倒是没想到我居然会和秒5里的剧情遭遇如此一样，我心里的她，那个放学等我坐在画室窗边金黄阳光下的马尾辫女孩早已是别人的妻子和一位母亲了，我依旧还单身，长大了明白了太多，以前热血纯洁的时光也走了。只能偶尔偷偷回忆一下。onemoretimeonemorechance也是我唯一会唱的日语歌。
                  </p>
                </div>
              </li>

            </ul>
          </div>
        </div>
      </div>
      <!-- 侧边栏 -->
      <div class="aside">
        <!-- 预告片 -->
        <div class="trailer">
          <div class="tab-contral">
            <div class="tab active" id="trailer">预告片</div>
            <div class="tab" id="other">相关资讯</div>
          </div>
          <div class="border"></div>

          <div class="trailer-video">
            <div class="referral">
              <div class="intro">
                预告片
              </div>
            </div>
            <video :src="fileUploadHost +'/movie/video/2021/09/19/f4579fd863664c47912c76096cb5e573/f4579fd863664c47912c76096cb5e573.mp4'" controls autoplay></video>
          </div>
        </div>
        <!-- 相关电影 -->
        <div class="other-movie">
          <div class="referral">
            <div class="intro">
              相关电影
            </div>
          </div>
          <div class="other-movie-body">
            <ul>
              <li class="othermovie-model">
                <a href="#">
                  <img alt="你的名字。海报封面" src="https://p1.meituan.net/movie/04de313fdd7f3835563d3c4bdfb980735556062.jpg@106w_145h_1e_1c">
                  <p>你的名字</p>
                </a>
                <i>9.2</i>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import '@/assets/styles/less/moviedetail.less'
import movieApi from '@/api/media/movie';
import { listTag } from "@/api/media/tag";
import { listCategory } from "@/api/media/category";
import { getDictsByTypeList } from '@/api/system/dict/data';
import { selectDictLabels } from '@/utils/ruoyi';

export default {
  components: {
  },
  computed:{
  },
  data() {
    return {
      movieId: undefined,
      countryOptions: [],
      statusOptions: [],
      typeOptions: [],
      tagOptions: [],
      movie: undefined,
      videoList: [],
      actorList: [],
      directorList: [],
      video: undefined,
      activeName: 'description',
      // 评论
      showCancel: false,
      comments: [],
      commentInfo: {
        targetId: this.$route.params.movieId
      }
    }
  },
  created() {
    listTag({status: '1'}).then(response => {
      this.tagOptions =  response.rows;
    });
    this.getDictList();
    this.getDetail();
  },

  methods:{
    getDictList() {
      // 获取字典
      const dictTypeList =  ['movie_country', 'movie_status', 'movie_type'];
      getDictsByTypeList(dictTypeList).then(response => {
        this.countryOptions = response.data.movie_country;
        this.statusOptions= response.data.movie_status
        this.typeOptions= response.data.movie_type
      });
    },
    //查询电影详情信息
    getDetail() {
      this.movieId = this.$route.params.movieId;
      movieApi.getMovie(this.movieId).then(response => {
        this.movie = response.data;
        this.videoList = this.movie.videoList;
        this.actorList = this.movie.actorList;
        this.directorList=this.movie.directorList;
        if (this.videoList.length > 0 ) {
          this.video = this.videoList[0]
        }
      });
    },
    //播放
    play(videoId){
      if (this.videoList.length === 0){
        this.msgError("暂时没有播放的视频！请联系管理员进行上传！")
      }else {
        this.$router.push({
          path: "/video/" + videoId,
          query: {}
        });
      }
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
            actions.push(tagOptions[j].content);
            break;
          }
        }
      }
      return actions;
    }
  }
};
</script>
<style scoped>
.cc-mask{background:rgba(0,0,0,.4);height:100%;left:0;opacity:0;filter:alpha(opacity=0);position:absolute;right:0;top:0;transition:all .3s ease 0s;width:100%;transition:.3s;-webkit-transition:.3s}
.movies-body ul li:hover .img-responsive{transform:scale(1.1);-webkit-transform:scale(1.1)}
.movies-body ul li:hover .cc-mask{opacity:1;filter:alpha(opacity=100)}

>>> .el-tabs__nav .el-tabs__item {
  font-size: 22px;
  color: #ef4238;
}
>>> .el-tabs__item.is-active {
  color: #ef4238;
}
>>> .el-tabs__active-bar{
  background-color: #ef4238;
}
</style>
