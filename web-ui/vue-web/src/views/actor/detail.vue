<template>
  <div>
    <section class="actor-body">
      <div class="comm-title">
        <h2 class="fl actor">
          <span class="c-333">演员介绍</span>
        </h2>
      </div>

      <el-card>
        <div class="mt20 ml20">
          <el-row>
            <el-col :span="8">
              <el-image class="actorImages" :src="fileUploadHost + actor.avatar" :alt="actor.name"/>
            </el-col>
            <el-col :span="14">
              <span class="actorTitle">{{actor.name}}</span>
              <span class="actorDescription" v-html="actor.description"></span>
              <p class="mt20" v-html="actor.awards"></p>
            </el-col>
          </el-row>
        </div>
      </el-card>


      <section class="mt30">
        <div class="comm-title">
          <h2 class="fl actor">
            <span class="c-333">主演电影</span>
          </h2>
          <section class="c-tab-title">
            <a href="javascript: void(0)">&nbsp;</a>
          </section>
        </div>

        <!-- 电影列表 -->
        <div class="movies-body">
          <ul>
            <li class="othermovie-model" v-for="movie in movieList" :key="movie.movieId">
              <a :href="`/movie/` + movie.movieId">
                <div class="movie-poster">
                  <img class="img-responsive" :src="fileUploadHost + movie.images"/>
                  <!--              <div class="cc-mask">
                                  <a :href="`/movie/` + movie.movieId">开始观看</a>
                                  <el-button type="primary" style="margin-top: 100px" @click="toDetail(movie.movieId)" plain>开始观看</el-button>
                                </div>-->
                  <div class="movie-overlay movie-overlay-bg">
                    <div class="movie-info">
                      <div class="movie-score"><i class="integer">{{ movie.rate }}</i></div>
                      <div class="movie-title" style="float: left" :title="movie.title">{{ movie.title }}</div>
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
      </section>
    </section>


  </div>
</template>
<script>
  import {listActor, getActor} from '@/api/media/actor';
  import movieApi from '@/api/media/movie';
  import {listTag} from "@/api/media/tag";
  import {listCategory} from "@/api/media/category";
  import {getDictsByTypeList} from "@/api/system/dict/data";

  export default {
    data() {
      return {
        tagOptions: [],
        categoryOptions: [],
        countryOptions: [],
        movieList: [],
        actor: {}
      }
    },
    created() {
      this.init();
    },
    methods: {
      init() {
        listTag({status: '1'}).then(response => {
          this.tagOptions = response.rows
        });
        listCategory({status: '1'}).then(response => {
          this.categoryOptions = response.rows
        });
        const dictTypeList = ['movie_country', 'movie_status', 'movie_type'];
        getDictsByTypeList(dictTypeList).then(response => {
          this.countryOptions = response.data.movie_country
        });
        const actorId = this.$route.params && this.$route.params.actorId;
        this.msgSuccess(actorId)
        getActor(actorId).then(response => {
          this.actor = response.data
        });
        movieApi.getListByActorId(actorId).then(response => {
          this.movieList = response.rows
        });

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
              actions.push(tagOptions[j].content + currentSeparator);
              break;
            }
          }
        }
        return actions.join('').substring(0, actions.join('').length - 1);
      },
    }
  };
</script>
<style lang="less" scoped>
  .actor-body {
    width: 1200px;
    margin: auto;
  }

  .actorImages {
    width: 250px;
    height: 360px;
    display: block;
  }

  .actorTitle {
    font-size: 22px;
    font-weight: bold;
  }

  .img-responsive {
    max-width: 100%;
    height: 270px;
    display: block;
    transition: .8s;
    -webkit-transition: .8s
  }

  .actorDescription {
    display: inline-block;
    padding: 10px 8px;
    font-size: 15px;
    color: #888;
  }

  .comm-title {
    overflow: hidden;
    clear: both;
    margin: 40px 0 30px
  }

  .comm-title h2 span {
    font-size: 26px;
    font-family: simhei
  }

  .actor-body ul li:hover .img-responsive {
    transform: scale(1.1);
    -webkit-transform: scale(1.1)
  }

  .actor-body ul li:hover .cc-mask {
    opacity: 1;
    filter: alpha(opacity=100)
  }


  .movies-body {
    width: 1200px;
    margin: auto;

    ul {
      width: 1120px;
      margin: 0 40px 40px 10px;
      padding: 0 10px;
      display: flex;
      flex-wrap: wrap;

      .othermovie-model {
        width: 166px;
        text-align: center;
        margin: 20px 10px 0 0;
        position: relative;

        img {
          width: 100%;
        }

        .movie-poster {
          background-color: #fcfcfc;
          width: 160px;
          height: 220px;
          overflow: hidden;
          position: relative;


          .movie-overlay-bg {
            background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAKAAAABQCAMAAACpg44GAAAAbFBMVEUiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiK2HsfSAAAAJHRSTlPMxr63q6OahzclHRULBgHCsZOOf3p1cGtmYVxXUk1IQz4wLBAszz8hAAAAhklEQVRo3u3OxY0DAAADsBxDmZn337FTVPIjnsAZ4/zgCOcHhzg/+MBlgPOD/zg/+Ifzg3dcbrj84vzgFZcLLmdcTrgccTngssdlh8sWlw0ua1xWuCxxWeDyg/ODc1xmuHzj/OAXzg9+4vzgFJcPnB98x/nBCS5vOD9YVVVVVVVVVVVVr/IEUdzLcLhtP2AAAAAASUVORK5CYII=) repeat-x bottom;
          }

          .movie-overlay {
            width: 100%;
            height: 100%;
            position: absolute;
            top: 0;
            left: 0;

            .movie-info {
              color: #fff;
              position: absolute;
              bottom: 7px;
              width: 100%;

              .movie-title {
                font-size: 16px;
                line-height: 22px;
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
                margin: 0 10px
              }

              .movie-score {
                color: #ffb400;
                float: right;
                margin-right: 10px;

                .integer {
                  font-size: 18px
                }
              }
            }
          }
        }

        i {
          color: #ffb400;
        }

        .mix2d {
          display: block;
          width: 69px;
          height: 25px;
          background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEUAAAAZCAMAAABQHxOXAAAANlBMVEUAAAAAAAAAhP////9Epf93vf+73v8RjP/d7//u9/8ilP8znf/M5v9mtf+Zzv+q1v+Ixv9Vrf8Ox5ywAAAAAnRSTlMzAIL4qAgAAAEtSURBVDjL7ZPNjsMwCIRbsI3/4iTv/7I7g9NDrb1ttOqhSEUMxR84JA+5wZ63UB5fyv9QQqh/psSiqkMkKawMQwZBpO4yBcsOL5Kmuotl7Qsl5SOrNsmaCUp+MJ/QPG4FAlVVJ1Q6sruWus4icqpWYX/LjIwhNNlxgmVomTNtGEb1/OXpJhQG1QAkfZgOtnEUIgM8oSJXZqV4H29qTqkewuWu8WDjzftUBrDdr7ZSrBMih1+/0x/TpaEZJzlCwDXiHAEaTVZKy9rtupWMV2O6GLk8OMJpTLeiab2RZ0uMyOJf7mp4t1ShN0xQbGhHBiJgZt8xfmWhRHWTqrQMnK+4Qjebr82OTJql1q+9be+UGtzE6JunXpqRSQv1erkrpNcY9Gd/jV/KG+X5uMF+APHWE1liaIDvAAAAAElFTkSuQmCC);
          position: absolute;
          top: 4px;
          left: -2px;
          font-size: 12px;
        }

        .mix3d {
          display: block;
          width: 69px;
          height: 25px;
          position: absolute;
          top: 4px;
          left: -2px;
          font-size: 12px;
          background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEUAAAAZCAMAAABQHxOXAAAANlBMVEUAAAAAAAAAhP////9Epf93vf8RjP+73v/d7//u9/9mtf8ilP/M5v8znf+q1v+Zzv+Ixv9Vrf8pmp8MAAAAAnRSTlMzAIL4qAgAAAExSURBVDjL7VPLcsMgDEwQCMTD4P//2e6K9FD72Eynh2jGghVotZLtR3iDPd/C8viw/AmLxqi/ZkkCqyFkrjZ1RxJxfx3z2hKZWIbIEbRIv7AcOZlYCEUKibInlhOY6WoAuNVEdmZH9BBr97lk5rK+IrUFxRYYNsjIwzDFtqYKMSLntaOYJqNRJEIGfdwOVimFlBGepCG8IleWhejyfHXffAtXuqTFwtXVNm5gCPT7dMfpCpe33+nXdnlKQSYlRLSRtgRgr3ebC6vs2czvwnSJM5lwJKcxPEzyvSO1dHa2gdNVkOXVMnGFAtMpHRGA2MX8HeOxC4sP0Sr00AqLFAEr8ND92RyI5LAn11/vrV46iilF5QcMGzsCI+ZOw4gNa2PjgH5Hgf/13/hh+cnyfLzBvgAoTBNzukOVAAAAAABJRU5ErkJggg==);
        }
      }
    }

  }
</style>
