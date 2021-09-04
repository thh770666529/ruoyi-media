<template>
  <div style="margin-left: 50px">
    <h2>生成Cron表达式</h2>
    <el-row>
      <el-col :span="12">
        <el-input v-model="cronExpression" placeholder="请输入cron执行表达式">
          <template slot="append">
            <el-button type="primary" @click="handleShowCron">
              生成表达式
              <i class="el-icon-time el-icon--right"></i>
            </el-button>
          </template>
        </el-input>
      </el-col>
    </el-row>
    <el-dialog title="Cron表达式生成器" :visible.sync="openCron" append-to-body class="scrollbar">
      <crontab @hide="openCron=false" @fill="crontabFill" :expression="expression"></crontab>
    </el-dialog>
  </div>
</template>
<script>
import Crontab from '@/components/Crontab'
export default {
  components: {
    Crontab
  },
  data() {
    return {
      // 传入的表达式
      expression: '',
      // 是否显示Cron表达式弹出层
      openCron: false
    }
  },
  methods: {
    /** cron表达式按钮操作 */
    handleShowCron() {
      this.expression = this.cronExpression;
      this.openCron = true;
    },
    /** 确定后回传值 */
    crontabFill(value) {
      this.cronExpression = value;
    },
  }
};
</script>
