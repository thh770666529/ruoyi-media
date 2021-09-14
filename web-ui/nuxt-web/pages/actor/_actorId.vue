<template>
  <div id="aMovieList">
    <section class="container">
      <header class="comm-title">
        <h2 class="fl actor">
          <span class="c-333">演员介绍</span>
        </h2>
      </header>

      <el-card>
        <div class="mt20 ml20">
          <el-row>
            <el-col :span="8">
              <el-image  class="actorImages" :src="fileUploadHost + actor.avatar" :alt="actor.name" />
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
        <header class="comm-title all-teacher-title c-course-content">
          <h2 class="fl actor">
            <span class="c-333">主演电影</span>
          </h2>
          <section class="c-tab-title">
            <a href="javascript: void(0)">&nbsp;</a>
          </section>
        </header>

        <article  class="comm-movie-list">
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
      </section>
    </section>
  </div>
</template>
<script>
  import {listActor, getActor} from '@/api/media/actor';
  import movieApi from '@/api/media/movie';
  import { listTag } from "@/api/media/tag";
  import { listCategory } from "@/api/media/category";
  import { getDictsByTypeList } from "@/api/system/dict/data";
  export default {
    data() {
      return {
      }
    },
    async asyncData({params, $axios, error}) {
      const tagOptions = await listTag({status: '1'});
      const categoryOptions = await listCategory({status: '1'});
      const dictTypeList =  ['movie_country', 'movie_status', 'movie_type'];
      const dictDataList = await getDictsByTypeList(dictTypeList);
      const response = await getActor(params.actorId);
      const movieList = await movieApi.getListByActorId(params.actorId);
      return {
        actor: response.data,
        movieList: movieList.rows,
        countryOptions: dictDataList.data.movie_country,
        tagOptions: tagOptions.rows,
        categoryOptions: categoryOptions.rows
      }
    },
    methods: {
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
    }
  };
</script>
<style scoped>
  .actorImages {
    width: 250px;
    height: 360px;
    display: block;
  }
  .actorTitle {
    font-size: 20px;
    font-weight: bold;
  }

  .actorDescription {
    display: inline-block;
    padding: 10px 8px;
    overflow: hidden;
    font-size: 15px;
    color: #888;
  }
</style>
