<template>
  <div class="app-container">

      <el-form ref="form" :model="form" :rules="rules" >
        <el-row>
          <el-col :span="12">
            <el-form-item label="标题" :label-width="formLabelWidth" prop="title">
              <el-input v-model="form.title" placeholder="请输入标题"  auto-complete="off" ></el-input>
            </el-form-item>

            <el-form-item label="电影类型" :label-width="formLabelWidth" prop="movieType">
              <el-select v-model="form.movieType" placeholder="请选择电影类型">
                <el-option
                  v-for="dict in movieTypeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="国家" :label-width="formLabelWidth" prop="country">
              <el-select v-model="form.country" placeholder="请输入国家">
                <el-option
                  v-for="dict in countryOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="发布人" :label-width="formLabelWidth" prop="publishBy">
              {{form.publishBy}}
            </el-form-item>

            <el-form-item label="发布时间" :label-width="formLabelWidth" prop="publishTime">
              <el-date-picker clearable size="small"
                              v-model="form.publishTime"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="选择发布时间">
              </el-date-picker>
            </el-form-item>

            <el-form-item label="状态" :label-width="formLabelWidth" >
              <el-select v-model="form.status"  placeholder="请选择状态" clearable size="small">
                <el-option
                  v-for="dict in statusOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>


          </el-col>
          <el-col :span="12">
            <el-form-item label="" class="images-uploader"  :label-width="formLabelWidth" prop="images">
              <el-upload
                class="el-upload"
                :action="uploadImagesUrl"
                :show-file-list="false"
                :on-success="handleImagesSuccess"
                :before-upload="beforeImagesUpload"
                :headers="headers"

              >
                <img v-if="form.images" :src="fileUploadHost+form.images" class="images">
                <i v-else class="el-icon-plus images-uploader-icon"></i>
              </el-upload>
            </el-form-item>
          </el-col>

        </el-row>

        <!--<el-form-item label="标签" :label-width="formLabelWidth" prop="label">
          <el-input v-model="form.label" placeholder="请输入标签" />
        </el-form-item>-->



        <el-form-item label="简介" :label-width="formLabelWidth" prop="description">
          <el-input v-model="form.description" auto-complete="off" placeholder="请输入内容" ></el-input>
        </el-form-item>

        <el-form-item label="备注" prop="remark" :label-width="formLabelWidth" >
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>

        <el-form-item label="是否可以评论" prop="isComment" :label-width="formLabelWidth" >
          <el-select v-model="form.isComment"  placeholder="是否可以评论" clearable size="small">
            <el-option
              v-for="dict in sysYesNoOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否可以下载" prop="isDownload" :label-width="formLabelWidth" >
          <el-select v-model="form.isDownload"  placeholder="请输入是否可以下载" clearable size="small">
            <el-option
              v-for="dict in sysYesNoOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-divider content-position="center">电影视频信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddWmMovieVideo">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="success"
              plain
              icon="el-icon-edit"
              size="mini"
              :disabled="single"
              @click="handleUpdate"
            >修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteWmMovieVideo">删除</el-button>
          </el-col>
        </el-row>

        <el-table :data="wmMovieVideoList" :row-class-name="rowWmMovieVideoIndex" @selection-change="handleWmMovieVideoSelectionChange" ref="wmMovieVideo">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="标题" prop="title"/>
          <el-table-column label="预览" width="200" prop="url" align="center"/>
          <el-table-column label="文件后缀" prop="ext"/>
          <el-table-column label="播放长度" prop="length"/>
          <el-table-column label="文件大小" :formatter="filesizeFormat" prop="length" align="center" />
          <el-table-column label="备注" prop="remark" width="200"/>
         <!-- <el-table-column label="类型" prop="type">
            <template slot-scope="scope">
              <el-input v-model="scope.row.type" placeholder="请输入类型" />
            </template>
          </el-table-column>
          <el-table-column label="存储类型" prop="storageType">
            <template slot-scope="scope">
              <el-input v-model="scope.row.storageType" placeholder="请输入存储类型" />
            </template>
          </el-table-column>-->
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                type="text"
                size="small"
                icon="el-icon-view"
                @click="previewVideo(scope.row.url)"
              >预览</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-form>

    <el-divider content-position="center"></el-divider>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
        <el-button @click="reset">重 置</el-button>
      </div>


    <!-- 添加或修改电影管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="wmMovieVideoForm" :model="wmMovieVideoForm" :rules="videoRules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="wmMovieVideoForm.title" placeholder="请输入标题" />
        </el-form-item>

        <el-form-item label="url地址" prop="url">
          <el-input v-model="wmMovieVideoForm.url" placeholder="请输入url地址" />
        </el-form-item>

        <el-form-item label="文件后缀" prop="ext">
          <el-input v-model="wmMovieVideoForm.ext" placeholder="请输入文件后缀" />
        </el-form-item>
        <el-form-item label="播放长度" prop="length">
          <el-input v-model="wmMovieVideoForm.length" placeholder="请输入播放长度" />
        </el-form-item>

        <el-form-item label="文件大小" prop="length">
          <el-input v-model="wmMovieVideoForm.filesize" placeholder="文件大小" />
        </el-form-item>

        <el-form-item label="上传视频" prop="url">
          <el-upload
            class="upload-demo"
            drag
            :action="uploadVideoUrl"
            :on-success="handleVideoSuccess"
            :before-upload="beforeVideoUpload"
            :headers="headers"
            multiple>
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传mp4文件，且不超过1G</div>
          </el-upload>
        </el-form-item>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitVideoForm">确 定</el-button>
        <el-button @click="open = false">取 消</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
import { listMovie, getMovie, delMovie, addMovie, updateMovie, exportMovie } from "@/api/media/movie";
import { getToken } from "@/utils/auth";
export default {
  name: "Movie",
  components: {
  },
  data() {
    return {
      headers: {
        Authorization: "Bearer " + getToken(),
      },
      fileUploadHost: null,
      uploadImagesUrl: null,
      uploadVideoUrl: null,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedWmMovieVideo: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 电影管理表格数据
      movieList: [],
      // 电影视频表格数据
      wmMovieVideoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //电影国家字典
      countryOptions:[],
      //状态字典
      statusOptions:[],
      //电影类型
      movieTypeOptions:[],

      //字典是否
      sysYesNoOptions: [],
      // 表单参数
      formLabelWidth: "120px",
      lineLabelWidth: "120px",//一行的间隔数
      form: {},
      images:[],
      photoVisible: false,
      //电影视频信息Form
      wmMovieVideoForm:{},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
      },
      // 表单校验
      videoRules: {
        title: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
        filesize: [
          { required: true, message: "视频文件没有上传", trigger: "blur" }
        ]
      }

    };
  },
  mounted(){
    this.init();
  },
  created() {
    this.fileUploadHost = process.env.VUE_APP_FILE_UOLOAD_HOST;
    this.uploadImagesUrl =process.env.VUE_APP_BASE_API+"/media/movie/upload/images";
    this.uploadVideoUrl =process.env.VUE_APP_BASE_API+"/media/movie/upload/video";

    this.getDicts("movie_country").then(response => {
      this.countryOptions = response.data;
    });
    this.getDicts("wm_movie_status").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("wm_movie_type").then(response => {
      this.movieTypeOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.sysYesNoOptions = response.data;
    });
  },
  watch:{
    "$route":{
      handler(route){
        const that = this;
        that.init();
      }
    }
  },
  methods: {
    init(){
      const movieId = this.$route.params && this.$route.params.movieId;
      console.log(this.$route.params, this.$route.params.movieId);
     // const movieId =  this.$route.query.movieId;
      console.log(movieId);
      if (movieId ==undefined){
        this.reset();
      }else {
        getMovie(movieId).then(response => {
          this.form = response.data;
          this.wmMovieVideoList = response.data.wmMovieVideoList;
        });
      }
    },
    /** 查询电影管理列表 */
    getList() {
      this.loading = true;
      listMovie(this.queryParams).then(response => {
        this.movieList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.$router.push({
        path: "/media/movie",
        query: {}
      });
    },
    // 表单重置
    reset() {
      this.form = {
        movieId: null,
        images: null,
        title: null,
        movieType: null,
        country: null,
        label: null,
        description: null,
        publishBy: null,
        publishTime: null,
        status: "0",
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null,
        remark: null,
        readCount: null,
        commentCount: null,
        followCount: null,
        collectionCount: null,
        likesCount: null,
        unlikesCount: null,
        shareCount: null,
        isComment: null,
        isDownload: null
      };
      this.wmMovieVideoList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加电影管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.wmMovieVideoForm = this.checkedWmMovieVideo[0];
      this.open = true;
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.wmMovieVideoList = this.wmMovieVideoList;
          if (this.form.movieId != null) {
            updateMovie(this.form).then(response => {
              this.msgSuccess("修改成功");
            });
          } else {
            addMovie(this.form).then(response => {
              this.msgSuccess("新增成功");
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const movieIds = row.movieId || this.ids;
      this.$confirm('是否确认删除电影管理编号为"' + movieIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delMovie(movieIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
	/** 电影视频序号 */
    rowWmMovieVideoIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 电影视频添加按钮操作 */
    handleAddWmMovieVideo() {
      this.open = true;
      this.title ="添加电影视频";
    },
    submitVideoForm(){
      this.$refs["wmMovieVideoForm"].validate(valid => {
        if (valid) {
          const movieVideoId =  this.wmMovieVideoForm.movieVideoId;
          if (!movieVideoId){
            this.wmMovieVideoList.push(this.wmMovieVideoForm);
          }
          this.open = false;
          this.wmMovieVideoForm ={};
        }
      });
      this.submitForm();
    },
    /** 电影视频删除按钮操作 */
    handleDeleteWmMovieVideo(row) {
      const movieVideoIds = row.movieVideoId || this.ids;
      if (this.checkedWmMovieVideo.length == 0) {
        this.$alert("请先选择要删除的电影视频数据", "提示", { confirmButtonText: "确定" });
      } else {
        this.wmMovieVideoList.splice(this.checkedWmMovieVideo[0].index - 1, 1);
        this.submitForm();
      }
    },


    /** 单选框选中数据 */
    handleWmMovieVideoSelectionChange(selection) {

      if (selection.length > 1) {
        this.$refs.wmMovieVideo.clearSelection();
        this.$refs.wmMovieVideo.toggleRowSelection(selection.pop());
      } else {
        this.checkedWmMovieVideo = selection;
      }
      this.single = selection.length != 1;
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有电影管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportMovie(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },
    // 状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },

    handleImagesSuccess(res, file) {
      const code = res.code;
      if (code == 200) {
        this.form.images =  res.url;
        this.msgSuccess("上传成功！");
      } else {
        this.msgError(res.msg);
      }
    },
    beforeImagesUpload(file) {
      console.log(file.type);
      const isImages = (file.type === 'image/jpeg') || (file.type === 'image/png');
      const isLt10M = file.size / 1024 / 1024 < 10;
      if (!isImages) {
        this.msgError('上传头像图片只能是 JPG 格式 和 PNG 格式!');
      }
      if (!isLt10M) {
        this.msgError('上传头像图片大小不能超过 10MB!');
      }
      return isImages && isLt10M;
    },


    handleVideoSuccess(res, file) {
      const code = res.code;
      if (code == 200) {
        this.wmMovieVideoForm = res.data;
        this.msgSuccess("上传成功！");
      } else {
        this.msgError(res.msg);
      }
    },
    beforeVideoUpload(file) {
      console.log(file.type);
      const isVideo  = file.type === 'video/mp4';
      const isLt1G = file.size / 1024 / 1024 / 1024 < 1;
      if (!isVideo) {
        this.msgError('上传头像图片只能是 video格式!');
      }
      if (!isLt1G) {
        this.msgError('上传头像图片大小不能超过 1GB!');
      }
      return isVideo && isLt1G;
    },
    filesizeFormat(row, column) {
      return this.formatBytes(row.filesize,2);
    },
    formatBytes(filesize,digits){
      if (filesize == null || filesize == 0){
        return '0B';
      }
      const e=['B','KB','MB', 'GB', 'TB', 'EB','ZB','YB'];
      const c = 1024;
      const f=Math.floor(Math.log(filesize)/Math.log(c));
      return parseFloat((filesize / Math.pow(c, f)).toFixed(digits))+" "+e[f];
    },
    previewVideo(url) {
      window.open( process.env.VUE_APP_BASE_API+url);
    },
 }
};
</script>
<style scoped>
.images-uploader .el-upload {
  border: 2px dashed #d9d9d9;
  border-radius: 1px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.images-uploader .el-upload:hover {
  border-color: #409EFF;
}
.images-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 460px;
  height: 270px;
  line-height:270px;
  text-align: center;
}
.images {
  left: 10px;
  width: 480px;
  height: 270px;
  display: block;
}

</style>
