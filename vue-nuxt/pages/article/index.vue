<template>
	<div class="mt50">
    <section class="container">
		<el-row id="artList" type="flex"  justify="space-around">
			<el-col :span="16">
				<el-row class="art-item" v-for="article in articleList" :key="article.articleId" >
					<el-card shadow="hover">
						<h3><router-link :to="`/article/` + article.articleId"  tag="a" target="_blank" class="art-title">{{article.title}}</router-link></h3>
						<el-row class="art-info d-flex align-items-center justify-content-start">
							<div class="art-time"><i class="el-icon-time"></i> {{ parseTime(article.updateTime, '{y}-{m}-{d}') }}</div>
							<div class="d-flex align-items-center">
                <img class="tag" src="../../assets/img/article/tag.png" />
                <el-tag
                  size="mini"
                  style="margin-left: 3px"
                  v-if="item"
                  :key="index"
                  v-for="(item, index) in tagArrayFormat(article.tagId)"
                >{{item}}</el-tag>
							</div>
						</el-row>
						<el-row class="art-body">
							<div class="side-img hidden-sm-and-down"><img class="art-banner" :src="fileUploadHost + article.images"></div>
							<div class="side-abstract">
								<div class="art-abstract">
                  {{ article.summary }}
								</div>
								<div class="art-more">
									<router-link :to="`/article/` + article.articleId"  tag="a" target="_blank">
										<el-button plain>阅读全文</el-button>
									</router-link>
									<div class="view"><i class="el-icon-view"></i> {{article.clickCount}}</div>
								</div>
							</div>
						</el-row>
					</el-card>
					<img v-if="article.level === 1" class="star" src="../../assets/img/article/star.png" />
				</el-row>
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
			<el-col :span="6" class="hidden-sm-and-down" id="side">
				<div class="item ">
					<tag></tag>
				</div>
				<div class="item">
         <friend></friend>
				</div>
			</el-col>
		</el-row>
    </section>
	</div>
</template>

<script>
	import friend from '@/components/article/friend'
	import tag from '@/components/article/tag'
	import IndexApi from '@/api/index'
  import { listTag } from "@/api/tag";
	export default {
		name: 'article',
		components: {
			friend,
			tag
		},
    async asyncData({$axios}) {
		  const queryParams = { pageNum: 1, pageSize: 8}
      const articleData = await IndexApi.getNewArticleList(queryParams);
      const total = articleData.total
      return {articleList: articleData.rows, total: total}
    },
    created() {
      listTag({status: '1'}).then(response => {
        this.tagOptions= response.rows;
      })
    },
    methods: {
		  getList() {
        IndexApi.getNewArticleList(this.queryParams).then(response => {
          this.articleList = response.rows;
          this.total = response.total;
        })
      },
      //标签翻译
      tagArrayFormat(value) {
        if(!value) {
          return ''
        }
        const datas =  this.tagOptions;
        let actions = [];
        const currentSeparator = '';
        let temp = value.split(currentSeparator);
        Object.keys(value.split(currentSeparator)).some((val) => {
          Object.keys(datas).some((key) => {
            if (datas[key].tagId == ('' + temp[val])) {
              actions.push(datas[key].content + currentSeparator);
            }
          })
        })
        return actions;
      }
    },
    data(){
		  return {
        loading : false,
		    // 标签字典
        tagOptions: [],
        queryParams: {
          pageNum: 1,
          pageSize: 8
        }
      }

    }
	}
</script>

<style scoped>
  .container{width:1300px}
	#side .item {
		margin-bottom: 30px;
    margin-top: 30px;
	}

/*  #side .item .friend{
    position:fixed;
    z-index: 1520;
    top: 250px;right:310px;!*这句很重要*!
    width: 18%;
  }

  #side .item .tag{
    position:fixed;
    z-index: 1520;
    top: 80px;right:310px;!*这句很重要*!
    width: 18%;
  }*/
	.art-item {
		margin-bottom: 30px;
		position: relative;
	}

	.art-item .star {
		width: 60px;
		height: 60px;
		position: absolute;
		top: 0;
		right: 0;
	}

	img.tag {
		width: 16px;
		height: 16px;
	}

	.art-title {
		border-left: 3px solid #F56C6C;
		padding-left: 5px;
		cursor: pointer;
    font-weight: bold;
    font-size: 20px;
	}

	.art-title:hover {
		padding-left: 10px;
		color: #409EFF;
	}

	.art-time {
		margin-right: 20px;
	}

	.art-body {
		display: flex;
		padding: 10px 0;
	}

	.side-img {
		height: 150px;
		width: 270px;
		overflow: hidden;
		margin-right: 10px;
	}

	img.art-banner {
		width: 100%;
		height: 100%;
		transition: all 0.6s;
	}

	img.art-banner:hover {
		transform: scale(1.4);
	}

	.side-abstract {
		flex: 1;
		display: flex;
		flex-direction: column;
	}

	.art-abstract {
		flex: 1;
		color: #aaa;
	}

	.art-more {
		height: 40px;
		display: flex;
		justify-content: space-between;
		align-items: flex-end;
	}

	.art-more .view {
		color: #aaa;
	}
	h5{
		font-size: 18px;
	}
	.pagination {
		background-color: #F9F9F9;
	}
  html,body{
    margin: 0;
    padding: 0;
  }
  [v-cloak]{
    display: none;
  }
  .d-flex{
    display: flex!important;
  }
  .mr-auto{
    margin-right: auto!important;
  }
  .ml-auto{
    margin-left: auto!important;
  }
  .align-items-center{
    align-items: center!important;
  }
  .flex-center{
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .pageEnd {
    float: left;
    width: 100%;
    height: 80px;
    text-align: center;
  }

  .loadContent {
    width: 120px;
    height: 30px;
    line-height: 30px;
    font-size: 16px;
    margin: 0 auto;
    color: aliceblue;
    cursor: pointer;
    background: rgba(0, 0, 0, 0.8);
  }
</style>
