<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="积分编号" prop="creditsCode">
        <el-input
          v-model="queryParams.creditsCode"
          placeholder="请输入积分编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="账号" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择账号" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="积分类型" prop="creditsType">
        <el-select v-model="queryParams.creditsType" placeholder="请选择积分类型" clearable size="small">
          <el-option
            v-for="dict in creditsTypeOptions"
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
          v-hasPermi="['website:creditsFlow:add']"
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
          v-hasPermi="['website:creditsFlow:edit']"
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
          v-hasPermi="['website:creditsFlow:remove']"
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
          v-hasPermi="['website:creditsFlow:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="creditsFlowList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="积分类型" align="center" prop="creditsId" />
      <el-table-column label="积分编号" align="center" prop="creditsCode" />
      <el-table-column label="用户id" align="center" prop="userId" />
      <el-table-column label="账户id" align="center" prop="accountId" />
      <el-table-column label="账户积分" align="center" prop="account" />
      <el-table-column label="消耗前的账号积分" align="center" prop="preAccount" />
      <el-table-column label="消耗积分" align="center" prop="postAcount" />
      <el-table-column label="账号" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="statusOptions" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="积分类型" align="center" prop="creditsType">
        <template slot-scope="scope">
          <dict-tag :options="creditsTypeOptions" :value="scope.row.creditsType"/>
        </template>
      </el-table-column>
      <el-table-column label="描述" align="center" prop="creditsDesc" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['website:creditsFlow:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['website:creditsFlow:remove']"
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

    <!-- 添加或修改积分流水对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="积分编号" prop="creditsCode">
          <el-input v-model="form.creditsCode" placeholder="请输入积分编号" />
        </el-form-item>
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="账户id" prop="accountId">
          <el-input v-model="form.accountId" placeholder="请输入账户id" />
        </el-form-item>
        <el-form-item label="账户积分" prop="account">
          <el-input v-model="form.account" placeholder="请输入账户积分" />
        </el-form-item>
        <el-form-item label="消耗前的账号积分" prop="preAccount">
          <el-input v-model="form.preAccount" placeholder="请输入消耗前的账号积分" />
        </el-form-item>
        <el-form-item label="消耗积分" prop="postAcount">
          <el-input v-model="form.postAcount" placeholder="请输入消耗积分" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="账号">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="parseInt(dict.dictValue)"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="积分类型" prop="creditsType">
          <el-select v-model="form.creditsType" placeholder="请选择积分类型">
            <el-option
              v-for="dict in creditsTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="删除标志" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志" />
        </el-form-item>
        <el-form-item label="描述" prop="creditsDesc">
          <el-input v-model="form.creditsDesc" placeholder="请输入描述" />
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
import { listCreditsFlow, getCreditsFlow, delCreditsFlow, addCreditsFlow, updateCreditsFlow, exportCreditsFlow } from "@/api/website/creditsFlow";

export default {
  name: "CreditsFlow",
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
      // 积分流水表格数据
      creditsFlowList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 账号字典
      statusOptions: [],
      // 积分类型字典
      creditsTypeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        creditsCode: null,
        status: null,
        creditsType: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("website_status").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("credits_type").then(response => {
      this.creditsTypeOptions = response.data;
    });
  },
  methods: {
    /** 查询积分流水列表 */
    getList() {
      this.loading = true;
      listCreditsFlow(this.queryParams).then(response => {
        this.creditsFlowList = response.rows;
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
        creditsId: null,
        creditsCode: null,
        userId: null,
        accountId: null,
        account: null,
        preAccount: null,
        postAcount: null,
        remark: null,
        status: 0,
        creditsType: null,
        createTime: null,
        updateTime: null,
        delFlag: null,
        creditsDesc: null
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
      this.ids = selection.map(item => item.creditsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加积分流水";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const creditsId = row.creditsId || this.ids
      getCreditsFlow(creditsId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改积分流水";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.creditsId != null) {
            updateCreditsFlow(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCreditsFlow(this.form).then(response => {
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
      const creditsIds = row.creditsId || this.ids;
      this.$confirm('是否确认删除积分流水编号为"' + creditsIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCreditsFlow(creditsIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有积分流水数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportCreditsFlow(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
