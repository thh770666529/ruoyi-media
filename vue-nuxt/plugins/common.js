import Vue from 'vue'
import { getDicts } from "@/api/dict/data";
import { parseTime, addDateRange, selectDictLabel, selectDictLabels } from "@/utils/ruoyi";

let method = {
  install(Vue) {
    Vue.prototype.getDicts = getDicts;
    Vue.prototype.parseTime = parseTime;
    Vue.prototype.addDateRange = addDateRange;
    Vue.prototype.selectDictLabel = selectDictLabel;
    Vue.prototype.selectDictLabels = selectDictLabels;
  }
}


Vue.use(method);
