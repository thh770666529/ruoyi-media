<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="目标ID" prop="targetId">
        <el-input
          v-model="queryParams.targetId"
          placeholder="请输入目标ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="表名称" prop="tableName">
        <el-select v-model="queryParams.tableName" placeholder="请选择表名称" clearable size="small">
          <el-option
            v-for="dict in tableNameOptions"
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
          v-hasPermi="['website:collect:add']"
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
          v-hasPermi="['website:collect:edit']"
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
          v-hasPermi="['website:collect:remove']"
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
          v-hasPermi="['website:collect:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="collectList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="collectId" />
      <el-table-column label="用户" align="center" prop="userId" />
      <el-table-column label="目标ID" align="center" prop="targetId" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="statusOptions" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="表名称" align="center" prop="tableName">
        <template slot-scope="scope">
          <dict-tag :options="tableNameOptions" :value="scope.row.tableName"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['website:collect:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['website:collect:remove']"
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

    <!-- 添加或修改收藏对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户" />
        </el-form-item>
        <el-form-item label="目标ID" prop="targetId">
          <el-input v-model="form.targetId" placeholder="请输入目标ID" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择状态">
            <el-option
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="表名称" prop="tableName">
          <el-select v-model="form.tableName" placeholder="请选择表名称">
            <el-option
              v-for="dict in tableNameOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCollect, getCollect, delCollect, addCollect, updateCollect, exportCollect } from "@/api/website/collect";

export default {
  name: "Collect",
  data() {
    return {
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
      // 收藏表格数据
      collectList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 状态字典
      statusOptions: [],
      // 表名称字典
      tableNameOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        targetId: null,
        status: null,
        tableName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "用户不能为空", trigger: "blur" }
        ],
        targetId: [
          { required: true, message: "目标ID不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "change" }
        ],
        tableName: [
          { required: true, message: "表名称不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("common_switch").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("website_table_name").then(response => {
      this.tableNameOptions = response.data;
    });
  },
  methods: {
    /** 查询收藏列表 */
    getList() {
      this.loading = true;
      listCollect(this.queryParams).then(response => {
        this.collectList = response.rows;
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
        collectId: null,
        userId: null,
        targetId: null,
        status: null,
        createTime: null,
        updateTime: null,
        tableName: null,
        createBy: null,
        updateBy: null
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
      this.ids = selection.map(item => item.collectId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加收藏";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const collectId = row.collectId || this.ids
      getCollect(collectId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改收藏";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.collectId != null) {
            updateCollect(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCollect(this.form).then(response => {
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
      const collectIds = row.collectId || this.ids;
      this.$confirm('是否确认删除收藏编号为"' + collectIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCollect(collectIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有收藏数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportCollect(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
