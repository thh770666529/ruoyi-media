<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
     <el-form-item label="描述" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入描述"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['media:actor:add']"
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
          v-hasPermi="['media:actor:edit']"
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
          v-hasPermi="['media:actor:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['media:actor:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="actorList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="left" prop="actorId" width="50" />
      <el-table-column label="姓名" align="center" prop="name"  width="150" />
      <el-table-column prop="avatar" label="头像" align="center" width="80">
        <template slot-scope="scope">
          <el-image class="imagesList" :src="fileUploadHost+scope.row.avatar" lazy />
        </template>
      </el-table-column>
      <el-table-column prop="description" label="简述" align="left"  show-overflow-tooltip  ></el-table-column>

      <el-table-column label="标签 " align="center" prop="label" :formatter="labelFormat" width="200" />
      <el-table-column label="操作" align="center" width="100"  class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['media:actor:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['media:actor:remove']"
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

    <!-- 添加或修改演员对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="960px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-row>
          <el-col :span="16">
            <el-form-item label="姓名" prop="name"  >
              <el-input v-model="form.name" style="width: 50%" placeholder="请输入姓名" />
            </el-form-item>
            <el-form-item label="标签"   prop="label" >
              <el-select v-model="labelList" multiple style="width: 60%"  placeholder="请选择标签">
                <el-option
                  v-for="dict in labelOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue">
                  <span style="float: left">{{ dict.dictLabel }}</span>
                  <span style="float: right; color: #8492a6; font-size: 13px">{{ dict.dictValue }}</span>
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="简述" prop="description">
            <Editor ref="descriptionEditor" :value="form.description"   :height="200"></Editor>
            </el-form-item>
            <el-form-item label="奖项" prop="awards">
              <Editor ref="awardsEditor" :value="form.awards"  :height="200"></Editor>
            </el-form-item>
          </el-col>
        <el-col :span="8">
          <el-form-item label="" class="images-uploader"  :label-width="formLabelWidth" prop="avatar">
            <el-upload
              class="el-upload"
              :action="commonUploadImagesUrl"
              :show-file-list="false"
              :on-success="handleImagesSuccess"
              :before-upload="beforeImagesUpload"
              :headers="headers">
              <img v-if="form.avatar" :src="fileUploadHost+form.avatar" class="imagesDetail">
              <i v-else class="el-icon-plus images-uploader-icon"></i>
            </el-upload>
          </el-form-item>
        </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listActor, getActor, delActor, addActor, updateActor, exportActor } from "@/api/media/actor";
  import { getToken } from "@/utils/auth";
  import Editor from "../../../components/Editor";
  export default {
    name: "Actor",
    components: {
      Editor
    },
    data() {
      return {
        headers: {
          Authorization: "Bearer " + getToken(),
        },
        formLabelWidth: "80px",
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
        // 演员表格数据
        actorList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          name: null,
          avatar: null,
          description: null,
          awards: null,
          label: null
        },
        //标签类型
        labelOptions:[],
        //标签数据
        labelList:[],
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          name: [
            { required: true, message: "姓名不能为空", trigger: "blur" }
          ],
          avatar:[{ required: true, message: "必须上传一张图片", trigger: "blur" }],
          label:[{ required: true, message: "标签不能为空", trigger: "blur" }]
        }
      };
    },
    created() {
      this.getDicts("actor_label").then(response => {
        this.labelOptions = response.data;
      });
      this.getList();
    },
    methods: {
      //标签类型字典翻译
      labelFormat(row, column){
        if (!row.label) return '';
        return this.selectDictLabels(this.labelOptions, row.label);
      },
      handleImagesSuccess(res, file) {
        const code = res.code;
        if (code === 200) {
          this.form.avatar =  res.url;
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
      /** 查询演员列表 */
      getList() {
        this.loading = true;
        listActor(this.queryParams).then(response => {
          this.actorList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          actorId: null,
          name: null,
          avatar: null,
          description: null,
          awards: null,
          label: null
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
        this.ids = selection.map(item => item.actorId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加演员";
        this.labelList=[];
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const actorId = row.actorId || this.ids
        getActor(actorId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改演员";
          let that = this;
          that.labelList = [];
          var dbLabelList = that.form.label.split(",");
          for (var a = 0; a < dbLabelList.length; a++) {
            if (dbLabelList[a] != null && dbLabelList[a] != "") {
              that.labelList.push(dbLabelList[a]);
            }
          }
        });
      },
      /** 提交按钮 */
      submitForm() {
        if(this.labelList.length <= 0) {
          this.msgError("标签不能为空!");
          return;
        }
        var that = this;
        that.form.label = that.labelList.join(",");
        console.log(that.form.label);
        this.$refs["form"].validate(valid => {
         this.form.description = this.$refs.descriptionEditor.currentValue;
         this.form.awards = this.$refs.awardsEditor.currentValue;
          if (valid) {
            if (this.form.actorId != null) {
              updateActor(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addActor(this.form).then(response => {
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
        const actorIds = row.actorId || this.ids;
        this.$confirm('是否确认删除演员编号为"' + actorIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delActor(actorIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有演员数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportActor(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
      }
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
    width: 200px;
    height: 288px;
    line-height:288px;
    text-align: center;
  }
  .imagesList {
    left: 5px;
    width: 20px;
    height: 28.8px;
    display: block;
  }

  .imagesDetail {
    left: 5px;
    width: 200px;
    height: 288px;
    display: block;
  }

</style>
