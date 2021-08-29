<template>
  <el-dialog custom-class="cron-box-dialog-first" :visible.sync="dialogVisible" v-if="dialogVisible">
    <div slot="title" style="font-size:16px;color:#666666;">cron表达式生成器</div>
    <el-tabs tab-position="left" style="height: 310px;" v-model="activeName">
      <el-tab-pane label="快捷生成" name="first">
        <CronDefault ref="CronDefault" v-if="activeName == 'first'"></CronDefault>
      </el-tab-pane>
      <el-tab-pane label="自定义生成" name="second">
        <CronExc ref="CronExc" v-if="activeName == 'second'"></CronExc>
      </el-tab-pane>
    </el-tabs>
    <div slot="footer">
      <el-button size="mini" @click="confirmClick" type="primary">确定</el-button>
      <el-button size="mini" @click="dialogVisible = false">取消</el-button>
    </div>
  </el-dialog>
</template>
<script>
import CronExc from "../cron-custom/index";
import CronDefault from "../cron-default/index";
export default {
  components: {
    CronExc,
    CronDefault
  },
  data() {
    return {
      dialogVisible: false,
      activeName: "first",
      result: "" // 最终的结果表达式
    };
  },
  methods: {
    // 确定生成表达式
    confirmClick() {
      if (this.activeName == "first") {
        this.result = this.$refs["CronDefault"].cronExpression;
      } else {
        this.result = this.$refs["CronExc"].cronExpression;
      }
      console.log(this.result);
      this.$emit("cronResult", this.result);
      this.dialogVisible = false;
    }
  }
};
</script>
<style lang="css">
.cron-box-dialog-first {
  width: 700px;
}
.cron-box-dialog-first .el-dialog__header {
  padding: 10px;
  text-align: center;
}
.cron-box-dialog-first .el-dialog__header .el-dialog__headerbtn {
  top: 12px;
  right: 8px;
}
.cron-box-dialog-first .el-dialog__body {
  padding: 0 10px;
}
.cron-box-dialog-first .el-dialog__footer {
  padding: 10px;
  text-align: center;
}
</style>