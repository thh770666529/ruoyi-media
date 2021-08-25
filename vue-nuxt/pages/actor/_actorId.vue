<template>
  <div id="aMovieList" class="bg-fa of">
    <section class="container">
      <header class="comm-title">
        <h2 class="fl actor">
          <span class="c-333">演员介绍</span>
        </h2>
      </header>
      <div class="t-infor-wrap">
        <section class="fl t-infor-box c-desc-content">
          <div class="mt20 ml20">
            <section class="t-infor-pic">
              <img :src="fileUploadHost + actor.avatar" :alt="actor.name">
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
              <p class="mt20" v-html="actor.awards">{{actor.awards}}</p>
            </section>
            <div class="clear"></div>
          </div>
        </section>
        <div class="clear"></div>
      </div>

      <section class="mt30">
        <header class="comm-title all-teacher-title c-course-content">
          <h2 class="fl actor">
            <span class="c-333">主演电影</span>
          </h2>
          <section class="c-tab-title">
            <a href="javascript: void(0)">&nbsp;</a>
          </section>
        </header>

        <section class="no-data-wrap" v-if="movieList.length==0">
          <em class="icon30 no-data-ico">&nbsp;</em>
          <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
        </section>
        <article class="comm-movie-list">
          <ul class="of">
            <li v-for="movie in movieList" :key="movie.movieId">
              <div class="cc-l-wrap">
                <section class="movie-img">
                  <img :src="movie.images" class="img-responsive">
                  <div class="cc-mask">
                    <a href="#" title="开始观看" target="_blank" class="comm-btn c-btn-1">开始观看</a>
                  </div>
                </section>
                <h3 class="hLh30 txtOf mt10">
                  <a href="#" :title="movie.title" target="_blank"
                     class="course-title fsize18 c-333">{{movie.title}}</a>
                </h3>
              </div>
            </li>

          </ul>
          <div class="clear"></div>
        </article>
      </section>
    </section>
  </div>
</template>
<script>
  import {listActor, getActor} from '@/api/actor';

  export default {
    data() {
      return {
        movieList: []
      }
    },
    async asyncData({params, $axios, error}) {
      const response = await getActor(params.actorId);
      return {actor: response.data}
    }

  };
</script>
