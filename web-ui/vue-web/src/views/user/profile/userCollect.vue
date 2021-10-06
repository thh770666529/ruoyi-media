<template>
  <div class="collect-body">
    <el-empty v-if="collectList.length === 0" description="没有收藏数据！"></el-empty>
    <el-card class="mb10" v-for="(item,index) in collectList" :key="index">
      <el-row v-if="tableName === 'wm_movie'">
        <el-col :span="6">
          <div class="movie-item" >
            <a :href="`/movie/` + item.targetId">
              <div class="movie-poster">
                <img class="img-responsive" :src="fileUploadHost + item.movie.images" />
                <div class="movie-overlay movie-overlay-bg">
                  <div class="movie-info">
                    <div class="movie-title"  style="float: left" :title="item.movie.title">{{ item.movie.title | ellipsis(7) }}</div>
                  </div>
                </div>
              </div>
            </a>
          </div>
        </el-col>
        <el-col :span="18">
          <span v-html="item.movie.description">{{item.movie.description}}</span>
          <el-button type="danger" class="cancel-btn" plain size="mini" @click="cancelCollect(item.targetId)">取消收藏</el-button>
        </el-col>
      </el-row>

      <el-row v-if="tableName === 'wm_actor'">
        <el-col :span="6">
          <div class="movie-item" >
            <a :href="`/actor/` + item.targetId">
              <div class="movie-poster">
                <img class="img-responsive" :src="fileUploadHost + item.actor.avatar" />
                <div class="movie-overlay movie-overlay-bg">
                  <div class="movie-info">
                    <div class="movie-title"  style="float: left" :title="item.actor.name">{{ item.actor.name | ellipsis(7) }}</div>
                  </div>
                </div>
              </div>
            </a>
          </div>
        </el-col>
        <el-col :span="18">
          <span v-html="item.actor.description">{{item.actor.description}}</span>
          <el-button type="danger" class="cancel-btn" plain size="mini" @click="cancelCollect(item.targetId)">取消收藏</el-button>
        </el-col>
      </el-row>


      <el-row v-if="tableName === 'blog_article'">
        <h3 class="art-title">
          <a href="javascript:void(0);" @click="toArticleDetail(item.article)">{{item.article.title}}</a>
        </h3>
        <span class="art-image">
          <a href="javascript:void(0);" @click="toArticleDetail(item.article)" title>
            <el-image class="art-banner" :src="fileUploadHost + item.article.images" :alt="item.article.title">
              <div slot="error" class="image-slot">
                 <img class="art-banner" src="../../../assets/img/article/vue.jpg"/>
              </div>
            </el-image>
          </a>
      </span>
        <p class="art-text">{{item.article.summary}}</p>
        <div class="art-info">
          <ul>
            <li class="author">
              <span class="iconfont">&#xe60f; </span>
              <a href="javascript:void(0);">{{item.article.author}}</a>
            </li>
            <li class="lmname" v-if="item.article.categoryId">
              <span class="iconfont">&#xe603; </span>
              <a
                href="javascript:void(0);"
              >{{item.article.categoryName}}</a>
            </li>
            <li class="view">
              <span class="iconfont">&#xe8c7; </span>
              <span>{{item.article.clickCount}}</span>
            </li>
            <li class="like">
              <span class="iconfont">&#xe663; </span>
              {{item.article.collectCount}}
            </li>
            <li class="createTime">
              <span class="iconfont">&#xe606; </span>
              {{item.article.createTime}}
            </li>
          </ul>
        </div>
        <img v-if="item.level === 1"  class="star" src="../../../assets/img/article/star.png"/>
        <el-button type="danger" class="cancel-btn" plain size="mini" @click="cancelCollect(item.targetId)">取消收藏</el-button>

      </el-row>
    </el-card>
  </div>
</template>

<script>
import { listMyCollect,cancelCollectByTargetId } from "@/api/website/collect";

export default {
  props: {
    tableName: {
      type: String
    }

  },
  data() {
    return {
      loading: false,
      // 总条数
      total: 0,
      // 收藏表格数据
      collectList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        targetId: null,
        tableName: null,
      }
    };
  },
  created() {
    this.queryParams.tableName = this.tableName;
    this.getList();
  },
  methods: {
    cancelCollect(targetId) {
      const collectForm =  {
        targetId: targetId,
        tableName: this.tableName
      }
      cancelCollectByTargetId(collectForm).then(res => {
        if (res.code === 200){
          this.getList();
          this.msgSuccess("取消收藏成功！");
        } else {
          this.msgSuccess("取消收藏失败！");
        }
      });
    },
    getList() {
      this.loading = true;
      listMyCollect(this.queryParams).then(response => {
        this.collectList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    }
  }
};
</script>
<style scoped>
  .collect-body {
    width: 95%;
    margin: auto;
  }
  .star {
    width: 50px;
    height: 50px;
    position: absolute;
    top: 0;
    right: 0;
  }

  .art-image {
    height: 121px;
    float: left;
    width: 30%;
    margin-right: 20px;
    display: block;
    overflow: hidden;
  }

  .art-image img {
    width: 100%;
    height: auto;
    -webkit-transition: all 0.6s ease;
    -moz-transition: all 0.6s ease;
    -o-transition: all 0.6s ease;
    transition: all 0.6s ease;
    margin-bottom: 10px
  }

  .art-image :hover img {
    transform: scale(1.4)
  }

  .art-banner {
    width: 100%;
    height: 100%;
    transition: all 0.6s;
  }

  .art-banner:hover {
    transform: scale(1.4);
  }


  .art-title {
    margin: 0 0 10px 0;
    font-size: 20px;
    overflow: hidden;
  }
  .art-title a:hover {
    color: #337ab7;
  }

  .art-text {
    font-size: 14px;
    color: #566573;
    overflow: hidden;
    text-overflow: ellipsis;
    -webkit-box-orient: vertical;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    margin-top: 20px
  }

  .art-info {
    overflow: hidden;
    margin-top: 30px
  }

  .art-info ul li {
    float: left;
    font-size: 12px;
    padding: 0 20px 0 0;
    margin: 0 15px 0 0;
    color: #748594;
    line-height: 1.5;
    display: inline-block;
  }

  .art-info ul li a {
    color: #748594;
  }

  .art-info ul li a:hover {
    color: #000
  }




  .movie-item {
    width: 106px;
    text-align: center;
    margin: 3px 3px 0 0;
    position: relative;
  }

  .movie-item img{
    width: 100%;
  }

  .movie-item .movie-poster {
    background-color: #fcfcfc;
    width: 106px;
    height: 145px;
    overflow: hidden;
    position: relative;
  }

  .movie-item .movie-poster .movie-overlay-bg {
    background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAKAAAABQCAMAAACpg44GAAAAbFBMVEUiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiIiK2HsfSAAAAJHRSTlPMxr63q6OahzclHRULBgHCsZOOf3p1cGtmYVxXUk1IQz4wLBAszz8hAAAAhklEQVRo3u3OxY0DAAADsBxDmZn337FTVPIjnsAZ4/zgCOcHhzg/+MBlgPOD/zg/+Ifzg3dcbrj84vzgFZcLLmdcTrgccTngssdlh8sWlw0ua1xWuCxxWeDyg/ODc1xmuHzj/OAXzg9+4vzgFJcPnB98x/nBCS5vOD9YVVVVVVVVVVVVr/IEUdzLcLhtP2AAAAAASUVORK5CYII=) repeat-x bottom;
  }

  .movie-item .movie-poster .movie-overlay {
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
  }

  .movie-item .movie-info {
    color: #fff;
    position: absolute;
    bottom: 7px;
    width: 100%;
  }
  .movie-item .movie-info .movie-title {
    font-size: 15px;
    line-height: 22px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    margin: 0 10px
  }

  .cancel-btn {
    position:absolute;right:0;bottom:0;
  }
</style>
