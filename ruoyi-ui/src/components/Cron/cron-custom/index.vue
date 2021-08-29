<template>
  <div class="cron-box">
    <el-tabs v-model="activeName" type="border-card" stretch>
      <el-tab-pane label="秒" name="s">
        <Time lable="秒" @time="second"></Time>
      </el-tab-pane>
      <el-tab-pane label="分" name="m">
        <Time lable="分" @time="minute"></Time>
      </el-tab-pane>
      <el-tab-pane label="时" name="h">
        <Time lable="时" @time="hour"></Time>
      </el-tab-pane>
      <el-tab-pane label="日(周)" name="d">
        <day lable="日" @dayAndWeek="dayAndWeek"></day>
      </el-tab-pane>
      <el-tab-pane label="月" name="month">
        <month lable="月" @month="month"></month>
      </el-tab-pane>
      <el-tab-pane label="年" name="year">
        <year lable="年" @year="year"></year>
      </el-tab-pane>
    </el-tabs>
    <div class="table-box">
      <span>生成的表达式为：{{cronExpression}}</span>
    </div>
  </div>
</template>

<script>
import Time from "./Time";
import day from "./day";
import month from "./month";
import year from "./year";
export default {
  components: {
    Time,
    day,
    month,
    year
  },
  data() {
    return {
      activeName: "s", // el-tab切换
      // 秒
      sVal: "0",
      // 分
      mVal: "0",
      // 时
      hVal: "0",
      // 日
      dVal: "*",
      // 月
      monthVal: "*",
      // 周
      weekVal: "?",
      // 年
      yearVal: " ",
      // 生成的完整cron表达式
      cronExpression: ""
    };
  },
  methods: {
    // 抛出的年份
    year(data) {
      this.yearVal = data;
      this.createCron();
    },
    // 抛出的月份
    month(data) {
      this.monthVal = data;
      this.createCron();
    },
    // 抛出的秒
    second(data) {
      this.sVal = data;
      this.createCron();
    },
    // 抛出的分
    minute(data) {
      this.mVal = data;
      this.createCron();
    },
    // 抛出的时
    hour(data) {
      this.hVal = data;
      this.createCron();
    },
    // 抛出的周和日
    dayAndWeek(data) {
      console.log(data);
      this.dVal = data.day;
      this.weekVal = data.week;
      this.createCron();
    },
    // 生成表达式
    createCron() {
      this.cronExpression =
        this.sVal +
        " " +
        this.mVal +
        " " +
        this.hVal +
        " " +
        this.dVal +
        " " +
        this.monthVal +
        " " +
        this.weekVal +
        " " +
        this.yearVal;
    }
  },
  created() {
    this.createCron();
  }
};
</script>

<style lang="css">
.cron-box .table-box {
  border-top: 1px solid #ebebeb;
  margin-top: 10px;
  padding-top: 10px;
  min-width: 400px;
}
.cron-box .el-tabs .el-tabs__header .el-tabs__nav-prev {
  display: none;
}
.cron-box .el-tabs .el-tabs__header .el-tabs__nav-next {
  display: none;
}
.cron-box .el-tabs .el-tabs__header .el-tabs__nav-wrap {
  padding: 0 !important;
}
.cron-box .el-tabs .el-tabs__header .el-tabs__nav-scroll {
  width: 400px !important;
}
.cron-box
  .el-tabs
  .el-tabs__header
  .el-tabs__nav-scroll
  .el-tabs__item:first-child {
  margin-left: -1px !important;
}
.cron-box .el-tabs .el-tabs__content {
  overflow-x: hidden;
  overflow-y: auto;
  height: 220px;
}
.cron-box .el-tabs .el-tabs__content::-webkit-scrollbar {
  /*滚动条整体样式*/
  width: 5px; /*高宽分别对应横竖滚动条的尺寸*/
}
.cron-box .el-tabs .el-tabs__content::-webkit-scrollbar-thumb {
  /*滚动条里面小方块*/
  border-radius: 10px;
  background: #ebebeb;
}
.cron-box .el-tabs .el-tabs__content::-webkit-scrollbar-track {
  /*滚动条里面轨道*/
  border-radius: 10px;
  background: rgba(0, 0, 0, 0);
}
</style>
