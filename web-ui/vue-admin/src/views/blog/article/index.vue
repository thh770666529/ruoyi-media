<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="博客标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入博客标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文章简介" prop="summary">
        <el-input
          v-model="queryParams.summary"
          placeholder="请输入文章简介"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标签" prop="tagId">
        <el-select
          v-model="queryParams.tagIdList"
          placeholder="请选择标签"
          clearable
          @change="handleQuery"
          multiple size="small">
          <el-option
            v-for="tag in tagOptions"
            :key="tag.tagId"
            :label="tag.content"
            :value="tag.tagId  + ``"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="博客分类" prop="categoryId">
        <el-select v-model="queryParams.categoryId" placeholder="请选择博客分类" @change="handleQuery" clearable size="small">
          <el-option
            v-for="category in categoryOptions"
            :key="category.categoryId"
            :label="category.name"
            :value="category.categoryId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="推荐等级" prop="level">
        <el-select v-model="queryParams.level" placeholder="请选择推荐等级" @change="handleQuery" clearable size="small">
          <el-option
            v-for="dict in dict.type.article_level"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="isPublish">
        <el-select v-model="queryParams.isPublish" placeholder="请选择状态" @change="handleQuery" clearable size="small">
          <el-option
            v-for="dict in dict.type.article_publish_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否原创" prop="isOriginal">
        <el-select v-model="queryParams.isOriginal" placeholder="请选择是否原创" @change="handleQuery" clearable size="small">
          <el-option
            v-for="dict in dict.type.article_original"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="文章类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择文章类型" @change="handleQuery" clearable size="small">
          <el-option
            v-for="dict in dict.type.article_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['blog:article:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['blog:article:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['blog:article:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['blog:article:export']"
        >导出Excel</el-button>
      </el-col>
      <el-col :span="1.5">
      <el-button
        type="warning"
        plain
        icon="el-icon-download"
        size="mini"
        :disabled="multiple"

        @click="handleExportMarkdown"
        v-hasPermi="['blog:article:export']"
      >导出markdown</el-button>
    </el-col>
    <el-col :span="1.5">
       <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleLocalUpload"
          v-hasPermi="['blog:article:export']"
        >本地上传</el-button>
    </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table ref="tables" v-loading="loading" :data="articleList" :default-sort="defaultSort"  @sort-change="handleSortChange"  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="articleId" />
      <el-table-column label="标题图" align="center" prop="images" width="100">
        <template slot-scope="scope">
          <el-image :src="fileUploadHost + scope.row.images" lazy @click="previewPicture(fileUploadHost + scope.row.images)" />
        </template>
      </el-table-column>
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="推荐等级" align="center" prop="level">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.article_level" :value="scope.row.level"/>
        </template>
      </el-table-column>
      <el-table-column label="是否原创" align="center" prop="isOriginal">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.article_original" :value="scope.row.isOriginal"/>
        </template>
      </el-table-column>
      <el-table-column label="博客分类" align="center"  prop="categoryId"  width="100" >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.categoryId">
            {{ categoryFormat(scope.row.categoryId) }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="标签" align="left" prop="tagId" width="300">
        <template slot-scope="scope">
          <template v-for="(item, index) in tagOptions">
            <template v-if="getTagArray(scope.row.tagId).includes(String(item.tagId))">
              <span
                style="margin-left: 3px"
                v-if="item.listClass == 'default' || item.listClass == ''"
                :key="item.tagId"
                :index="index"
                :class="item.cssClass">{{ item.content }}
              </span>
              <el-tag
                v-else
                style="margin-left: 3px"
                :key="item.tagId"
                :index="index"
                :type="item.listClass === 'primary' ? '' : item.listClass"
                :class="item.cssClass? item.cssClass: ''">
                {{ item.content }}
              </el-tag>
            </template>
          </template>
        </template>
      </el-table-column>
      <el-table-column label="类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.article_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="排序" align="center" prop="sort" />
      <el-table-column label="点击数" align="center" prop="clickCount" />
      <el-table-column label="开启评论" align="center" prop="openComment">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.common_switch" :value="scope.row.openComment"/>
        </template>
      </el-table-column>
      <el-table-column label="发布状态" align="center" prop="isPublish">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.article_publish_status" :value="scope.row.isPublish"/>
        </template>
      </el-table-column>

      <el-table-column label="更新时间" align="center" prop="updateTime" sortable="custom" :sort-orders="['descending', 'ascending']" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['blog:article:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['blog:article:remove']"
          >删除</el-button>
          <el-button
            type="text"
            size="small"
            icon="el-icon-view"
            @click="previewArticle(scope.row)"
          >预览</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改博客文章对话框 -->
    <el-dialog :title="title" :visible.sync="open" fullscreen append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-row>
          <el-col :span="16">
            <el-form-item label="博客标题" prop="title">
              <el-input v-model="form.title" placeholder="请输入博客标题"  @input="contentChange" />
            </el-form-item>
            <el-form-item label="文章简介" prop="summary">
              <el-input v-model="form.summary" placeholder="请输入文章简介" @input="contentChange" />
            </el-form-item>
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" placeholder="请输入备注" @input="contentChange" />
            </el-form-item>
            <el-row>
              <el-col :span="12">
                <el-form-item  label="专属二维码" prop="qrcodePath">
                  <el-input v-model="form.qrcodePath" disabled  placeholder="请输入文章专属二维码地址" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="显示排序" prop="orderNum">
                  <el-input-number style="width: 100%"  v-model="form.sort" controls-position="right" :min="0" />
                </el-form-item>
              </el-col>
            </el-row>
          </el-col>
          <el-col :span="8">
            <el-form-item label-width="200px" label="标题图片">
              <imageUpload :limit="1" v-model="form.images"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="4">
            <el-form-item label="分类" prop="categoryId">
              <el-select v-model="form.categoryId" placeholder="请选择博客分类">
                <el-option
                  v-for="category in categoryOptions"
                  :key="category.categoryId"
                  :label="category.name"
                  :value="category.categoryId"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="推荐等级" prop="level">
              <el-select v-model="form.level" placeholder="请选择推荐等级">
                <el-option
                  v-for="dict in dict.type.article_level"
                  :key="dict.value"
                  :label="dict.label"
                  :value="parseInt(dict.value)"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="标签" prop="tagId">
              <el-select style="width: 100%"  multiple v-model="tagList" placeholder="请选择标签">
                <el-option
                  v-for="tag in tagOptions"
                  :key="tag.tagId"
                  :label="tag.content"
                  :value="tag.tagId + ``"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="4">
            <el-form-item label="是否原创">
              <el-radio-group v-model="form.isOriginal">
                <el-radio
                  v-for="dict in dict.type.article_original"
                  :key="dict.value"
                  :label="dict.value"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="文章类型" prop="type">
              <el-radio-group v-model="form.type">
              <el-radio
                v-for="dict in dict.type.article_type"
                :key="dict.value"
                :label="dict.value"
              >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="是否开启评论">
              <el-radio-group v-model="form.openComment">
                <el-radio
                  v-for="dict in dict.type.common_switch"
                  :key="dict.value"
                  :label="parseInt(dict.value)"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="4">
            <el-form-item label="状态">
              <el-radio-group v-model="form.isPublish">
                <el-radio
                  v-for="dict in dict.type.article_publish_status"
                  :key="dict.value"
                  :label="dict.value"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="发布状态">
              <el-radio-group v-model="form.status">
                <el-radio
                  v-for="dict in dict.type.article_status"
                  :key="dict.value"
                  :label="parseInt(dict.value)"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="开启密钥模式">
              <el-radio-group v-model="form.openPassword">
                <el-radio
                  v-for="dict in dict.type.common_switch"
                  :key="dict.value"
                  :label="parseInt(dict.value)"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row v-if="form.isOriginal==0" >
          <el-col :span="5">
            <el-form-item label="作者" prop="author" >
              <el-input v-model="form.author" placeholder="请输入作者" />
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="文章出处" :label-width="formLabelWidth">
              <el-input v-model="form.articlesPart" auto-complete="off"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item v-if="form.type == 1" label="外链" prop="outsideLink">
          <el-input v-model="form.outsideLink" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item v-if="form.openPassword == 1" label="文章私密密钥" prop="password">
          <el-input v-model="form.password" placeholder="请输入文章私密访问时的密钥" />
        </el-form-item>

        <el-form-item label="编辑模式">
          <el-radio-group v-model="editorMode">
            <el-radio label="1">editor</el-radio>
            <el-radio label="2">markdown</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="文章内容">
          <editor ref="editor" v-if="editorMode === '1' " v-model="form.content" :min-height="192" :height="360" />
          <div id="editor-main">
            <le-editor ref="markdownEditor" v-if="editorMode === '2' " v-model="markdownValue" :hljs-css="hljsCss" :image-uploader="imageUploader" @save="save"></le-editor>
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!--本地markdown上传-->
    <el-dialog
      title="本地博客上传"
      :visible.sync="localUploadVisible"
    >
      <div class="tipBox">
        <div class="tip">导入须知</div>
        <div class="tipItem">1）如果你的Markdown文档里面的图片是本地，需要选择本地图片，然后提交到图片服务器</div>
        <div class="tipItem">2）含有本地图片一定需要提前上传图片，否者会出现图片无法替换的问题</div>
        <div class="tipItem">3）如果你的Markdown文档里面的图片不是本地，直接选择博客文件上传即可</div>
        <div class="tipItem">4）目前支持Markdown文件批量上传，步骤是先提交所有图片，在提交全部的博客文件</div>
        <div class="tipItem">5）因为网络或者服务器性能等不可抗拒的原因，因此不推荐一次上传太多</div>
      </div>
      <el-row :gutter="24" class="mb8">
        <el-col :span="12">
          <el-upload
            class="el-upload"
            ref="localImagesUpload"
            :before-upload="handleBeforeLocalImagesUpload"
            :action="commonUploadFileListUrl"
            :file-list="uploadLocalImageList"
            :show-file-list="true"
            :headers="headers"
            :auto-upload="false"
            multiple
          >
            <el-button slot="trigger" size="small" type="primary">选取本地图片</el-button>
            <el-button style="margin-left: 10px;" size="small" type="success" @click="submitLocalImagesUpload">提交到图片服务器</el-button>
          </el-upload>
        </el-col>
        <el-col :span="12">
          <el-upload
            class="el-upload"
            ref="uploadMarkdownFile"
            :headers="headers"
            :action="uploadLocalArticleHost"
            :auto-upload="false"
            :show-file-list="true"
            multiple
          >
            <el-button style="margin-left: 10px;" slot="trigger" size="small" type="primary">选取博客文件</el-button>
            <el-button style="margin-left: 10px;" size="small" type="success" @click="submitLocalMarkdownUpload">提交到服务器</el-button>
          </el-upload>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import { listTag } from "@/api/blog/tag";
import { listCategory } from "@/api/blog/category";
import { listArticle, getArticle, delArticle, addArticle, updateArticle } from "@/api/blog/article";
import { getToken } from "@/utils/auth";
export default {
  name: "Article",
  dicts: [ 'article_publish_status', 'article_original', 'article_level', 'article_status', 'common_switch', 'article_type'],
  data() {
    return {
      headers: {
        Authorization: "Bearer " + getToken(),
      },
      uploadLocalArticleHost: process.env.VUE_APP_BASE_API + '/blog/article/uploadLocalFile',
      uploadLocalImageList: [],
      //上传文章图片URL
      commonUploadFileListUrl: process.env.VUE_APP_BASE_API + '/blog/article/uploadFileList',
      localUploadFileUrl: '',
      localUploadVisible: false, // 是否显示本地上传弹出层
      multipleSelection: [], //多选，用于批量下载markDown
      changeCount: 0, //文章编辑次数
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 博客文章表格数据
      articleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 标签字典
      tagOptions: [],
      //分类字典
      categoryOptions: [],
      //保存选中标签id(编辑时)
      tagList: [],
      editorMode: "1", //编辑模式
      // lemarkdown
      hljsCss: 'agate',
      markdownValue: '',
      // 自定义
      imageUploader: {
        custom: false,
        fileType: 'file',
        fileNameType: '',
        imagePrefix: this.fileUploadHost, // 图片上传成功后，预览地址前缀
        type: 'server',
        url: process.env.VUE_APP_BASE_API + "/common/upload", // 上传的图片服务器地址
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        summary: null,
        content: null,
        tagIdList: null,
        status: null,
        isOriginal: null,
        categoryId: null,
        level: null,
        isPublish: null,
        type: null,
        orderByColumn: 'updateTime',
        isAsc: 'desc'
      },
      // 默认排序
      defaultSort: {prop: 'updateTime', order: 'descending'},
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "博客标题不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "blur" }
        ],
        sort: [
          { required: true, message: "排序字段不能为空", trigger: "blur" }
        ],
        openComment: [
          { required: true, message: "评论开关不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "类型不能为空", trigger: "change" }
        ],
        articleSource: [
          { required: true, message: "文章来源不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    // 查询列表
    this.getList();
    // 查询初始化数据
    this.getInitData();
  },
  watch:{
    // 内容改变备份
    'form.content': function () {
      this.contentChange();
    },
    'editorMode': function () {
      if (this.editorMode === '2'){
        this.markdownValue = this.htmlToMarkdown(this.form.content)
      } else {
        this.$refs.markdownEditor.savePreview()
      }
    },

  },
  methods: {
    /** 查询博客文章列表 */
    getList() {
      this.loading = true;
      listArticle(this.queryParams).then(response => {
        this.articleList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    //标签翻译
    getTagArray(tagId) {
      if (!tagId) {
        return []
      }
      return tagId.split(',');
    },
    //分类翻译
    categoryFormat(categoryId) {
      const value = categoryId
      const datas =  this.categoryOptions;
      let actions = [];
      Object.keys(datas).some((key) => {
        if (datas[key].categoryId == ('' + value)) {
          actions.push(datas[key].name);
          return true;
        }
      })
      return actions.join('');
    },
    previewArticle(row) {
      if(row.isPublish == 0) {
        this.$modal.msgError("文章暂未发布，无法进行浏览")
        return;
      }
      window.open( process.env.VUE_APP_WEB_HOST + '/article/' + row.articleId);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.editorMode = '1'
      this.markdownValue = ''
      this.tagList = [];
      this.changeCount = 0;
      this.form = {
        articleId: null,
        title: null,
        summary: null,
        content: null,
        tagId: null,
        clickCount: null,
        collectCount: null,
        images: null,
        status: 1,
        createTime: null,
        createBy: null,
        updateTime: null,
        updateBy: null,
        adminUserId: null,
        isOriginal: "1",
        author: null,
        articlesPart: null,
        categoryId: null,
        categoryName: null,
        level: 0,
        delFlag: null,
        isPublish: "1",
        sort: null,
        openComment: 1,
        type: "0",
        outsideLink: null,
        userId: null,
        articleSource: null,
        qrcodePath: null,
        openPassword: 1,
        password: null,
        remark: null
      };
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
      this.$refs.tables.sort(this.defaultSort.prop, this.defaultSort.order)
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.articleId)
      this.single = selection.length!==1
      this.multiple = !selection.length
      this.multipleSelection = selection
    },
    /** 排序触发事件 */
    handleSortChange(column, prop, order) {
      this.queryParams.orderByColumn = column.prop;
      this.queryParams.isAsc = column.order;
      this.getList();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.title = "添加博客文章";
      let tempForm = localStorage.getItem("articleForm");

      if(tempForm) {
        tempForm = JSON.parse(tempForm);
      }
      if (tempForm != null && tempForm.title) {
        this.$modal.confirm('还有上次未完成的博客编辑，是否继续编辑?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let that = this
          that.form = tempForm;
          that.tagList = [];
          if (this.form.tagId){
            const dbTagList = this.form.tagId.split(",");
            for (let index = 0; index < dbTagList.length; index++) {
              if (dbTagList[index]) {
                that.tagList.push(dbTagList[index]);
              }
            }
          }
        }).catch(() => {
          localStorage.removeItem("articleForm");
          this.reset();
        });
      } else {
        this.reset();
      }
      this.open = true;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      const loading = this.$loading({
        lock: true,
        text: '正在加载中...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      this.reset();
      const articleId = row.articleId || this.ids
      getArticle(articleId).then(response => {
        loading.close();
        this.form = response.data;
        this.form.type = this.form.type + "";
        this.open = true;
        this.title = "修改博客文章";
        let that = this;
        that.tagList = [];
        if (this.form.tagId){
          const dbTagList = this.form.tagId.split(",");
          for (let index = 0; index < dbTagList.length; index++) {
            if (dbTagList[index]) {
              that.tagList.push(dbTagList[index]);
            }
          }
        }
      });
    },
    contentChange: function() {
      let that = this;
      if(that.changeCount > 1) {
        this.setCategoryName( this.form.categoryId);
        this.form.tagId = that.tagList.join(",");
        // 将内容设置到 WebStorage中
        localStorage.setItem("articleForm", JSON.stringify(that.form));
      }
      this.changeCount = this.changeCount + 1;
    },
    /** 提交按钮 */
    submitForm() {
      let that = this;
      this.setCategoryName( this.form.categoryId);
      this.form.tagId = that.tagList.join(",");
      this.$refs["form"].validate(valid => {
        if (this.editorMode === '2'){
          this.$refs.markdownEditor.savePreview()
        }
        if (valid) {
          if (this.form.articleId != null) {
            updateArticle(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addArticle(this.form).then(response => {
              localStorage.removeItem("articleForm");
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const articleIds = row.articleId || this.ids;
      this.$modal.confirm('是否确认删除博客文章编号为"' + articleIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delArticle(articleIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
        this.download('blog/article/export', {
        ...this.queryParams
        }, `article_${new Date().getTime()}.xlsx`)
    },
    /** 导出markDown */
    handleExportMarkdown() {
      const queryParams = this.queryParams;
      this.$modal.confirm('是否确认导出选中的博客文章数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.exportLoading = true;
      }).then(() => {
        const blogList = this.multipleSelection
        for(let index = 0; index < blogList.length; index++) {
          this.htmlToMarkdownFile(blogList[index].title, blogList[index].content)
        }
        this.$modal.msgSuccess("导出成功");
        this.exportLoading = false;
      }).catch(() => {});
    },
    /**
     * 初始化查询
     */
    getInitData() {
      listCategory({status: '1'}).then(response => {
        this.categoryOptions = response.rows;
      });
      listTag({status: '1'}).then(response => {
        this.tagOptions = response.rows;
      });
    },
    /**
     * 赋值分类名称 冗余数据
     */
    setCategoryName(categoryId){
      if (!categoryId || this.categoryOptions.length === 0 ){
        return
      }
      const categoryOptions = this.categoryOptions;
      for (let index = 0; index < categoryOptions.length; index++) {
        const category = categoryOptions[index];
        if ( category!= null && category.categoryId == categoryId) {
          this.form.categoryName = category.name;
          break;
        }
      }
    },
    /**
     * 本地上传
     */
    handleLocalUpload() {
      this.localUploadVisible = true;
    },
    handleBeforeLocalImagesUpload(file) {
      //this.uploadLocalImageList = []
    },

    // 本地图片上传
    submitLocalImagesUpload() {
      //this.$refs.localImagesUpload.submit();
      let {uploadFiles, action, data} = this.$refs.localImagesUpload
      this.commonUploadFileList({
        uploadFiles,
        action,
        data,
        success: (response) => {
          let res = JSON.parse(response)
          if(res.code === 200) {
            this.$modal.msgSuccess('图片上传成功！');
            let data = res.data;
            this.uploadLocalImageList = data.map((file) => {
              return {
                newFileName: file.newFileName,
                originalFilenames: file.originalFilenames,
                filesize: file.filesize,
                url: this.fileUploadHost + file.url
              };
            });
          } else {
            this.$modal.msgSuccess('图片上传失败！');
          }
        },
        error: (error) => {
          console.log('失败了', error)
        }
      })
    },
    // 本地markdown上传
    submitLocalMarkdownUpload(){
      const loading = this.$loading({
        lock: true,
        text: '正在导入markdown,请稍等...',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      let {uploadFiles, action} = this.$refs.uploadMarkdownFile
      let data = {}
      data.imagesList = JSON.stringify(this.uploadLocalImageList)
      this.commonUploadFileList({
        uploadFiles,
        data,
        action,
        success: (response) => {
          loading.close();
          let res = JSON.parse(response)
          if(res.code === 200) {
            this.$modal.msgSuccess('上传本地文件成功！');
            this.getList();
          } else {
            this.$modal.msgError(res.msg);
          }
          this.localUploadVisible = false
          // 上传成功后，将里面的内容删除
          this.$refs.uploadMarkdownFile.clearFiles();
          this.$refs.localImagesUpload.clearFiles();
        },error: (error) => {
          console.log('失败了', error)
          loading.close();
        }
      })
    },
    /**
     * 自定义上传文件
     * @param fileList 文件列表
     * @param success 成功回调
     * @param error 失败回调
     */
    commonUploadFileList({uploadFiles, headers, data, action, success, error}) {
      let form = new FormData();
      // 文件对象
      uploadFiles.map(file => form.append("fileList", file.raw));
      // 附件参数
      for (let key in data) {
        form.append(key, data[key])
      }
      // 附件参数
      let xhr = new XMLHttpRequest();
      // 异步请求
      xhr.open("post", action, true);
      // 设置请求头
      xhr.setRequestHeader("Authorization", "Bearer " + getToken());
      xhr.onreadystatechange = function() {
        if (xhr.readyState === 4){
          if ((xhr.status >= 200 && xhr.status < 300) || xhr.status === 304){
            success && success(xhr.responseText)
          } else {
            error && error(xhr.status)
          }
        }
      }
      xhr.send(form)
    },
    // 自定义图片上传
    uploadImg: function ($vm, file, fileName) {
      console.log($vm)
      console.log(file)
      console.log(fileName)
      // 添加图片
      // 两个参数 第一个是图片访问路径 第二个是文件名
      $vm.insertImg(`${$vm.config.imageUploader.imagePrefix}${fileName}`, fileName)
    },
    save: function (val) {
      // 获取预览文本
      this.form.content = val
    }
  }
};
</script>
<style scoped>
  .tipBox {
    margin-bottom: 30px;
  }

  #editor-main {
    color: #2c3e50;
    width: 100%;
    height: 600px;
  }
</style>
