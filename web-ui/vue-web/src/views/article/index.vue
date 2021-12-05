<template>
  <div class="article-container">
    <el-row type="flex">
      <!--左边-->
      <el-col :span="16" class="mt20">
        <FirstRecommend></FirstRecommend>
        <div class="ml20 mb20 fsize14" v-if="queryParams.tagId">
          搜索标签值:
          <template v-for="(item, index) in tagOptions">
            <template v-if="[String(queryParams.tagId)].includes(String(item.tagId))">
              <span
                v-if="item.listClass == 'default' || item.listClass == ''"
                :key="item.tagId"
                :index="index"
                :class="item.cssClass">{{ item.content }}
              </span>
              <el-tag
                v-else
                :key="item.tagId"
                size="mini"
                effect="light"
                :index="index"
                :type="item.listClass === 'primary' ? '' : item.listClass"
                :class="item.cssClass? item.cssClass: ''">
                {{ item.content }}
              </el-tag>
            </template>
          </template>

        </div>
        <div
          v-for="(item,index) in articleList"
          :key="index"
          class="art-item"
          data-scroll-reveal="enter bottom over 1s">
          <h3 class="art-title">
            <a href="javascript:void(0);" @click="toArticleDetail(item)">{{item.title}}</a>
          </h3>
          <el-row :gutter="24">
            <el-col :xs="24" :md="19" :lg="19">
              <p class="art-text">{{item.summary}}</p>
              <div class="art-info">
                <ul>
                  <li class="author">
                    <span class="iconfont">&#xe60f; </span>
                    <a href="javascript:void(0);">{{item.author}}</a>
                  </li>
                  <li class="lmname" v-if="item.categoryName">
                    <span class="iconfont">&#xe603; </span>
                    <a
                      href="javascript:void(0);"
                    >{{item.categoryName}}</a>
                  </li>
                  <li class="lmname">
                    <template v-for="(tag, tagIndex) in tagOptions">
                      <template v-if="getTagArray(item.tagId).includes(String(tag.tagId))">
              <span
                v-if="tag.listClass == 'default' || tag.listClass == ''"
                :key="tag.tagId"
                :index="tagIndex"
                :class="tag.cssClass">{{ tag.content }}
              </span>
                        <el-tag
                          v-else
                          :key="tag.tagId"
                          size="mini"
                          effect="light"
                          :index="tagIndex"
                          :type="tag.listClass === 'primary' ? '' : tag.listClass"
                          :class="tag.cssClass? tag.cssClass: ''">
                          {{ tag.content }}
                        </el-tag>
                      </template>
                    </template>
                  </li>

                  <li class="view">
                    <span class="iconfont">&#xe8c7; </span>
                    <span>{{item.clickCount}}</span>
                  </li>
                  <li class="like">
                    <span class="iconfont">&#xe663; </span>
                    {{item.collectCount}}
                  </li>
                  <li class="createTime">
                    <span class="iconfont">&#xe606; </span>
                    {{item.createTime}}
                  </li>
                </ul>
              </div>
            </el-col>
            <el-col :xs="0" :lg="5">
            <span class="art-image">
            <a href="javascript:void(0);" @click="toArticleDetail(item)" title>
            <el-image lazy class="art-banner" :src="fileUploadHost + item.images" :alt="item.title">
              <div slot="error" class="image-slot">
                 <img class="art-banner" src="../../assets/img/article/vue.jpg">
              </div>
            </el-image>
            </a>
          </span>
            </el-col>
          </el-row>
          <img v-if="item.level === 1" class="star" src="../../assets/img/article/star.png"/>
        </div>

        <div class="block pagination">
          <pagination
            v-show="total>0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="getList"
          />
        </div>
      </el-col>
      <el-col :span="6" class="hidden-sm-and-down mt20" id="side" :offset="1">
        <!-- 标签 -->
        <div class="item">
          <tag @change="changeTagId" :tagId="queryParams.tagId"></tag>
        </div>
        <!-- 友情链接 -->
        <!--<div class="item">
          <friend></friend>
        </div>-->
        <!--热门文章-->
        <div class="item">
          <HotArticle></HotArticle>
        </div>
        <!--四级推荐-->
        <div class="item">
          <FourthRecommend></FourthRecommend>
        </div>
        <!--关注我们-->
        <div class="item">
          <About></About>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import tag from '@/components/article/tag'
  import {getArticleList} from '@/api/blog/article'
  import {listTag} from "@/api/blog/tag";
  import {listCategory} from "@/api/blog/category";
  import FirstRecommend from "@/components/FirstRecommend";

  export default {
    name: 'ArticleList',
    components: {
      tag,
      FirstRecommend
    },
    data() {
      return {
        categoryOptions: [],
        articleList: [],
        total: 0,
        queryParams: {
          pageNum: 1,
          pageSize: 8,
          tagId: ''
        },
        loading: false,
        // 标签字典
        tagOptions: []
      }
    },
    created() {
      listCategory({status: '1'}).then(response => {
        this.categoryOptions = response.rows;
      });
      listTag({status: '1'}).then(response => {
        this.tagOptions = response.rows;
      });

      this.getList();
    },
    methods: {
      changeTagId(value) {
        this.queryParams.tagId = value;
        this.getList();
      },
      getList() {
        getArticleList(this.queryParams).then(response => {
          this.articleList = response.rows;
          this.total = response.total;
        })
      },
      //标签翻译
      getTagArray(tagId) {
        if (!tagId) {
          return []
        }
        return tagId.split(',');
      },
      tagFormat(tagId) {
        if (!tagId) {
          return ''
        }
        const datas = this.tagOptions;
        for (let i = 0; i < datas.length; i++) {
          const tag = datas[i];
          if (tag.tagId == tagId) {
            return tag.content;
          }
        }
        return '';
      }
    }
  }
</script>

<style scoped>
  .article-container {
    width: 1200px;
    margin: auto;
  }

  #side .item {
    margin-bottom: 30px;
    margin-top: 8px;
  }

  h5 {
    font-size: 18px;
  }

  .pagination {
    background-color: #F9F9F9;
  }

  html, body {
    margin: 0;
    padding: 0;
  }

  [v-cloak] {
    display: none;
  }

  .art-item {
    position: relative;
    overflow: hidden;
    margin-bottom: 20px;
    padding: 20px;
    background: #FFF;
    -webkit-box-shadow: 0 2px 5px 0 rgba(146, 146, 146, .1);
    -moz-box-shadow: 0 2px 5px 0 rgba(146, 146, 146, .1);
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);

    -webkit-transition: all 0.6s ease;
    -moz-transition: all 0.6s ease;
    -o-transition: all 0.6s ease;
    transition: all 0.6s ease;
  }


  .art-item .star {
    width: 50px;
    height: 50px;
    position: absolute;
    top: 0;
    right: 0;
  }

  .art-item .art-image {
    float: left;
    max-height: 80px;
    margin-right: 20px;
    display: block;
    overflow: hidden;
  }

  .art-item .art-image img {
    width: 100%;
    height: auto;
    transition: all .6s ease;
    margin-bottom: 10px;

  }


  .art-item .art-image :hover img {
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


  .art-item .art-title {
    margin: 0 0 10px 0;
    font-size: 20px;
    overflow: hidden;
    font-weight: bold;
    display: block;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
  }
  .art-item .art-title a:hover {
    color: #337ab7;
  }

  .art-item .art-text {
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

</style>
