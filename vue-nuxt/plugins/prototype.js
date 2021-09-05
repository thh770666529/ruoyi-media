import Vue from 'vue'
import { getDicts } from "@/api/dict/data";
import { parseTime, addDateRange, selectDictLabel, selectDictLabels } from "@/utils/ruoyi";
import { fileUploadHost } from "@/config/config";
import Pagination from "@/components/Pagination";
// 全局方法挂载
let method = {
  install(Vue) {
    // 全局方法设置
    Vue.prototype.getDicts = getDicts;
    Vue.prototype.parseTime = parseTime;
    Vue.prototype.addDateRange = addDateRange;
    Vue.prototype.selectDictLabel = selectDictLabel;
    Vue.prototype.selectDictLabels = selectDictLabels;

    Vue.prototype.fileUploadHost = fileUploadHost;
    Vue.prototype.msgSuccess = function (msg) {
      this.$message({ showClose: true, message: msg, type: "success" });
    }

    Vue.prototype.msgError = function (msg) {
      this.$message({ showClose: true, message: msg, type: "error" });
    }

    Vue.prototype.msgInfo = function (msg) {
      this.$message.info(msg);
    }

    // 全局组件挂载
    Vue.component('Pagination', Pagination)
    // 全局过滤器设置
    Vue.filter('ellipsis', function (msg, num) {
      const currentNum = num || 5
      if(!msg) {
        return ''
    }
      if(msg.length > currentNum) {
        return msg.slice(0, currentNum) + '...'
      }
      return msg
    })

  }
}

Vue.use(method);
