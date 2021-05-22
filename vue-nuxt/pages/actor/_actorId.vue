<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- 演员介绍 开始 -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">演员介绍</span>
        </h2>
      </header>
      <div class="t-infor-wrap">
        <!-- 演员基本信息 -->
        <section class="fl t-infor-box c-desc-content">
          <div class="mt20 ml20">
            <section class="t-infor-pic">
              <img :src="fileUploadHost+actor.avatar" :alt="actor.name" >
            </section>
            <h3 class="hLh30">
              <span class="fsize24 c-333">{{actor.name}}&nbsp;
               高级演员
              </span>
            </h3>
            <section class="mt10">
              <span class="t-tag-bg" v-html="actor.description">{{actor.description}}</span>
            </section>
            <section class="t-infor-txt">
              <p class="mt20" v-html="actor.awards" >{{actor.awards}}</p>
            </section>
            <div class="clear"></div>
          </div>
        </section>
        <div class="clear"></div>
      </div>
      <section class="mt30">
        <div>
          <header class="comm-title all-teacher-title c-course-content">
            <h2 class="fl tac">
              <span class="c-333">主讲电影</span>
            </h2>
            <section class="c-tab-title">
              <a href="javascript: void(0)">&nbsp;</a>
            </section>
          </header>
          <!-- /无数据提示 开始-->
          <section class="no-data-wrap" v-if="movieList.length==0">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
          </section>
          <!-- /无数据提示 结束-->
          <article class="comm-course-list">
            <ul class="of">
              <li v-for="movie in movieList" :key="movie.movieId">
                <div class="cc-l-wrap">
                  <section class="course-img">
                    <img :src="movie.images" class="img-responsive" >
                    <div class="cc-mask">
                      <a href="#" title="开始观看" target="_blank" class="comm-btn c-btn-1">开始观看</a>
                    </div>
                  </section>
                  <h3 class="hLh30 txtOf mt10">
                    <a href="#" :title="movie.title" target="_blank" class="course-title fsize18 c-333">{{movie.title}}</a>
                  </h3>
                </div>
              </li>

            </ul>
            <div class="clear"></div>
          </article>
        </div>
      </section>
    </section>
    <!-- /演员介绍 结束 -->
  </div>
</template>
<script>
  import { listActor, getActor } from '@/api/actor';
export default {
  data(){
    return {
      movieList:[],
      fileUploadHost:"http://localhost:7070"
    }
  },
  //params.id获取路径id值
  asyncData({ params, error }) {
    console.log(params.actorId)
    return getActor(params.actorId)
      .then(response => {
        return {
          actor: response.data.data,
          //movieList: response.data.data.movieList
        }
      });
  }

};
</script>
