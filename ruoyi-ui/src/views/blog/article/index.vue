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
          v-model="queryParams.tagId"
          placeholder="请选择标签"
          clearable
          @change="handleQuery"
          multiple size="small">
          <el-option
            v-for="tag in tagOptions"
            :key="tag.tagId"
            :label="tag.content"
            :value="tag.tagId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="博客分类" prop="sortId">
        <el-select v-model="queryParams.sortId" placeholder="请选择博客分类" @change="handleQuery" clearable size="small">
          <el-option
            v-for="sort in sortOptions"
            :key="sort.sortId"
            :label="sort.name"
            :value="sort.sortId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="推荐等级" prop="level">
        <el-select v-model="queryParams.level" placeholder="请选择推荐等级" @change="handleQuery" clearable size="small">
          <el-option
            v-for="dict in levelOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="isPublish">
        <el-select v-model="queryParams.isPublish" placeholder="请选择状态" @change="handleQuery" clearable size="small">
          <el-option
            v-for="dict in isPublishOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否原创" prop="isOriginal">
        <el-select v-model="queryParams.isOriginal" placeholder="请选择是否原创" @change="handleQuery" clearable size="small">
          <el-option
            v-for="dict in isOriginalOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="文章类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择文章类型" @change="handleQuery" clearable size="small">
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
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
          :loading="exportLoading"
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
        :loading="exportLoading"
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

    <el-table v-loading="loading" :data="articleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="articleId" />
      <el-table-column label="标题图" align="center" prop="images" width="100">
        <template slot-scope="scope">
          <el-image :src="fileUploadHost + scope.row.images" lazy />
        </template>
      </el-table-column>
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="推荐等级" align="center" prop="level">
        <template slot-scope="scope">
          <dict-tag :options="levelOptions" :value="scope.row.level"/>
        </template>
      </el-table-column>
      <el-table-column label="是否原创" align="center" prop="isOriginal">
        <template slot-scope="scope">
          <dict-tag :options="isOriginalOptions" :value="scope.row.isOriginal"/>
        </template>
      </el-table-column>
      <el-table-column label="博客分类" align="center" prop="sortId" :formatter="sortFormat" />
      <el-table-column label="标签" align="center" prop="tagId" width="300">
        <template slot-scope="scope">
          <el-tag
            style="margin-left: 3px"
            v-if="item"
            :key="index"
            v-for="(item, index) in tagArrayFormat(scope.row)"
          >{{item}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="typeOptions" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="排序" align="center" prop="sort" />
      <el-table-column label="点击数" align="center" prop="clickCount" />
      <el-table-column label="开启评论" align="center" prop="openComment">
        <template slot-scope="scope">
          <dict-tag :options="openCommentOptions" :value="scope.row.openComment"/>
        </template>
      </el-table-column>
      <el-table-column label="发布状态" align="center" prop="isPublish">
        <template slot-scope="scope">
          <dict-tag :options="isPublishOptions" :value="scope.row.isPublish"/>
        </template>
      </el-table-column>

      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
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
              <el-input v-model="form.title" placeholder="请输入博客标题" />
            </el-form-item>
            <el-form-item label="文章简介" prop="summary">
              <el-input v-model="form.summary" placeholder="请输入文章简介" />
            </el-form-item>
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" placeholder="请输入备注" />
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
            <el-form-item label="分类" prop="sortId">
              <el-select v-model="form.sortId" placeholder="请选择博客分类">
                <el-option
                  v-for="sort in sortOptions"
                  :key="sort.sortId"
                  :label="sort.name"
                  :value="sort.sortId"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="推荐等级" prop="level">
              <el-select v-model="form.level" placeholder="请选择推荐等级">
                <el-option
                  v-for="dict in levelOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="parseInt(dict.dictValue)"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="标签" prop="tagId">
              <el-select style="width: 100%"  multiple v-model="tagValue" placeholder="请选择标签">
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
                  v-for="dict in isOriginalOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{dict.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="文章类型" prop="type">
              <el-radio-group v-model="form.type">
              <el-radio
                v-for="dict in typeOptions"
                :key="dict.dictValue"
                :label="dict.dictValue"
              >{{dict.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="是否开启评论">
              <el-radio-group v-model="form.openComment">
                <el-radio
                  v-for="dict in openCommentOptions"
                  :key="dict.dictValue"
                  :label="parseInt(dict.dictValue)"
                >{{dict.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="4">
            <el-form-item label="状态">
              <el-radio-group v-model="form.isPublish">
                <el-radio
                  v-for="dict in isPublishOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{dict.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="发布状态">
              <el-radio-group v-model="form.status">
                <el-radio
                  v-for="dict in statusOptions"
                  :key="dict.dictValue"
                  :label="parseInt(dict.dictValue)"
                >{{dict.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="开启密钥模式">
              <el-radio-group v-model="form.openPassword">
                <el-radio
                  v-for="dict in openPasswordOptions"
                  :key="dict.dictValue"
                  :label="parseInt(dict.dictValue)"
                >{{dict.dictLabel}}</el-radio>
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
        <el-form-item label="文章内容">
          <editor v-model="form.content" :min-height="192" :height="360"/>
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
      </div>
      <fileUpload :fileType="['doc', 'xls', 'ppt', 'txt', 'pdf', 'png', 'jpg', 'jpeg']" v-model="localUploadFileUrl"/>

    </el-dialog>
  </div>
</template>

<script>
import { listTag } from "@/api/blog/tag";
import { listSort } from "@/api/blog/sort";
import { listArticle, getArticle, delArticle, addArticle, updateArticle, exportArticle } from "@/api/blog/article";

export default {
  name: "Article",
  data() {
    return {
      localUploadFileUrl: '',
      localUploadVisible: false, // 是否显示本地上传弹出层
      multipleSelection: [], //多选，用于批量下载markDown
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
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
      //类型
      sortOptions: [],
      // 状态字典
      statusOptions: [],
      // 是否原创字典
      isOriginalOptions: [],
      // 推荐等级字典
      levelOptions: [],
      // 状态字典
      isPublishOptions: [],
      // 是否开启评论字典
      openCommentOptions: [],
      // 类型字典
      typeOptions: [],
      // 是否开启密钥模式字典
      openPasswordOptions: [],
      //保存选中标签id(编辑时)
      tagValue: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        summary: null,
        content: null,
        tagId: null,
        status: null,
        isOriginal: null,
        sortId: null,
        level: null,
        isPublish: null,
        type: null,
      },
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
    // 查询字典
    this.getDictList();
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
    tagArrayFormat(row) {
      const value = row.tagId
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
    },
    //分类翻译
    sortFormat(row, column) {
      const value = row.sortId
      const datas =  this.sortOptions;
      let actions = [];
      Object.keys(datas).some((key) => {
        if (datas[key].sortId == ('' + value)) {
          actions.push(datas[key].name);
          return true;
        }
      })
      return actions.join('');
    },
    // 是否原创字典翻译
    isOriginalFormat(row, column) {
      return this.selectDictLabel(this.isOriginalOptions, row.isOriginal);
    },
    // 推荐等级字典翻译
    levelFormat(row, column) {
      return this.selectDictLabel(this.levelOptions, row.level);
    },
    // 状态字典翻译
    isPublishFormat(row, column) {
      return this.selectDictLabel(this.isPublishOptions, row.isPublish);
    },
    // 是否开启评论字典翻译
    openCommentFormat(row, column) {
      return this.selectDictLabel(this.openCommentOptions, row.openComment);
    },
    // 类型字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
    },
    // 是否开启密钥模式字典翻译
    openPasswordFormat(row, column) {
      return this.selectDictLabel(this.openPasswordOptions, row.openPassword);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.tagValue = [];
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
        sortId: null,
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
        remark: null,
        praiseClickCount: 0
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
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.articleId)
      this.single = selection.length!==1
      this.multiple = !selection.length
      this.multipleSelection = selection
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加博客文章";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const articleId = row.articleId || this.ids
      getArticle(articleId).then(response => {
        this.form = response.data;
        this.form.type = this.form.type + "";
        this.open = true;
        this.title = "修改博客文章";
        let that = this;
        that.tagValue = [];
        const dbTagValue = this.form.tagId.split(",");
        for (let index = 0; index < dbTagValue.length; index++) {
          if (dbTagValue[index]) {
            that.tagValue.push(dbTagValue[index]);
          }
        }
      });
    },
    /** 提交按钮 */
    submitForm() {
      let that = this;
      this.form.tagId = that.tagValue.join(",");
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.articleId != null) {
            updateArticle(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addArticle(this.form).then(response => {
              this.msgSuccess("新增成功");
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
      this.$confirm('是否确认删除博客文章编号为"' + articleIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delArticle(articleIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有博客文章数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportArticle(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    },
    /** 导出markDown */
    handleExportMarkdown() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出选中的博客文章数据项?', "警告", {
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
        this.msgSuccess("导出成功");
        this.exportLoading = false;
      }).catch(() => {});
    },
    /**
     * 字典查询
     */
    getDictList() {
      const dictTypeList =  [ 'article_publish_status', 'article_original', 'article_level', 'article_status', 'common_switch', 'article_type']
      this.getDictsByTypeList(dictTypeList).then(response => {
        this.statusOptions = response.data.article_status;
        this.isOriginalOptions = response.data.article_original;
        this.levelOptions = response.data.article_level;
        this.isPublishOptions = response.data.article_publish_status;
        this.openCommentOptions = response.data.common_switch;
        this.typeOptions = response.data.article_type;
        this.openPasswordOptions = response.data.common_switch;
      });
      listSort({status: '1'}).then(response => {
        this.sortOptions = response.rows;
      });
      listTag({status: '1'}).then(response => {
        this.tagOptions = response.rows;
      });
    },
    handleLocalUpload() {
      this.localUploadVisible = true;
    }
  }
};
</script>
