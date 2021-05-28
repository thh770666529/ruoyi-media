import Vue from 'vue'
import { getDicts } from "@/api/dict/data";
import { parseTime, addDateRange, selectDictLabel, selectDictLabels } from "@/utils/ruoyi";

// 全局方法挂载
let method = {
  install(Vue) {
    Vue.prototype.getDicts = getDicts;
    Vue.prototype.parseTime = parseTime;
    Vue.prototype.addDateRange = addDateRange;
    Vue.prototype.selectDictLabel = selectDictLabel;
    Vue.prototype.selectDictLabels = selectDictLabels;


    Vue.prototype.msgSuccess = function (msg) {
      this.$message({ showClose: true, message: msg, type: "success" });
    }

    Vue.prototype.msgError = function (msg) {
      this.$message({ showClose: true, message: msg, type: "error" });
    }

    Vue.prototype.msgInfo = function (msg) {
      this.$message.info(msg);
    }
  }
}

Vue.use(method);
