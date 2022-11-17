<template>
  <div class="app-container">
    <el-row>
      <el-col :span="18">
        <div class="grid-content ">
          <h1>{{data.name}}</h1>
          <h4>{{labelFormat(data.label)}}</h4>
          <h3>{{data.description}}</h3>
        </div>
      </el-col>
      <el-col :span="4">
        <div class="grid-content " style="margin-left: 50px;">
          <img :src="fileUploadHost+data.avatar" class="image-avatar">
        </div>
      </el-col>
    </el-row>
    <el-divider></el-divider>


    <el-row style="display: flex;flex-wrap: wrap;" v-loading="loading">
      <el-col v-for="(item, index) in movieList" :key="index" style="margin: 5px 5px; width: 25%;">
        <el-card :body-style="{ padding: '0px' }">
          <img :src="fileUploadHost+item.images" class="image-movie" @click="handleMovie(item)">
          <div style="padding: 14px;">
            <span>{{item.title}}</span>
            <div class="bottom clearfix">
              <!-- <span class="description" >{{labelFormat2(item.label)}}</span> -->
              <span class="description">{{item.categoryName}}</span>
              <el-button size="mini" type="text" icon="el-icon-search" @click="handleMovie(item)"
                v-hasPermi="['media:actor:list']">查看</el-button>

            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getListByActorId"
    />

    <el-dialog :title="title" :visible.sync="open" width="960px" append-to-body>

    <el-divider content-position="center">视频</el-divider>
    <el-row  >
      <div >
        <el-button v-for="(item, index) in videoList" :key="index" @click="previewVideo(item.url)">
          {{item.title}}
        </el-button>
      </div>
    </el-row>

    <el-divider content-position="center">剧照</el-divider>
    <imageUpload :limit="8" v-model="stills" />
    <div slot="footer" class="dialog-footer">
      <!-- <el-button type="primary" @click="submitForm">确 定</el-button> -->
      <el-button @click="cancel">取 消</el-button>
    </div>
    </el-dialog>

  </div>
</template>

<script>
  import {
    getActor
  } from "@/api/media/actor";
  import {
    listMovie,
    getMovie,
    delMovie,
    addMovie,
    updateMovie,
    getListByActorId
  } from "@/api/media/movie";
  export default {
    name: "Details",
    dicts: ['actor_label'],
    data() {
      return {
        // 遮罩层
        loading: true,
        // 总条数
        total: 0,
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          actorId: null
        },
        //演员信息
        data: {},
        //电影列表
        movieList: [],
        //视频列表
        videoList: [],
        //剧照列表
        stills: undefined,
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
      }

    },
    created() {
      this.getActor();
      this.getListByActorId();
    },
    methods: {
      labelFormat(label) {
        if (!label) return '';
        return this.selectDictLabels(this.dict.type.actor_label, label);
      },
      getActor() {
        const actorId = this.$route.params.id;
        this.loading = true;
        getActor(actorId).then(response => {
          this.data = response.data
        })
      },
      /** 查询演员电影列表 */
      getListByActorId() {
        // console.log(this.$route.params.id)
        const actorId = this.$route.params.id;
        this.loading = true;
        getListByActorId(actorId).then(response => {
          this.movieList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      /** 查看按钮操作 */
      handleMovie(row){
        // console.log(row)
        this.open=true
        this.title = row.title;
        const movieId = row.movieId;

        getMovie(movieId).then(response =>{
          this.videoList = response.data.videoList;
          this.stills = response.data.stills;
        })
      },
      /** 取消按钮操作 */
      cancel() {
        this.open = false;
      }

    }
  }
</script>

<style>
  .el-row {
    margin-bottom: 20px;

    &:last-child {
      margin-bottom: 0;
    }
  }

  .el-col {
    border-radius: 4px;
  }

  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }

  .image-avatar {
    width: 200px;
    display: block;
    height: 200px;
    cursor: pointer;
  }
  .image-movie {
    width: 100%;
    display: block;
    height: 200px;
    cursor: pointer;
  }
</style>
