<template>
  <!-- 快捷生成完全可以由自己根据需求进行定义，因此这里仅开发了部分功能 -->
  <div class="cron-default-first">
    <el-tabs type="border-card" v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="天" name="day">
        <el-radio-group v-model="type" size="mini" @change="getValue">
          <div class="box-bottom">
            <el-radio label="1" size="mini" border>每天0点整执行</el-radio>
          </div>
          <div class="box-bottom cycle">
            <el-radio label="2" size="mini" border>自定义时分</el-radio>
            <div style="margin-top:8px;">
              <span style="margin-right:8px;">每天</span>
              <el-input-number
                @change="getChangeDayHourTime"
                v-model="day.hour"
                :min="0"
                :max="23"
                size="mini"
                style="width: 100px;"
              ></el-input-number>
              <span style="margin:0 8px;">点</span>
              <el-input-number
                @change="getChangeDayHourTime"
                v-model="day.minute"
                :min="0"
                :max="59"
                size="mini"
                style="width: 100px;"
              ></el-input-number>
              <span style="margin-left:8px;">分执行</span>
            </div>
          </div>
        </el-radio-group>
      </el-tab-pane>
      <el-tab-pane label="周" name="week">
        <el-radio-group v-model="type" size="mini" @change="getValue">
          <div class="box-bottom">
            <el-radio label="11" size="mini" border>每周一0点整执行</el-radio>
          </div>
          <div class="box-bottom">
            <el-radio label="12" size="mini" border
              >每周一至周五0点整执行</el-radio
            >
          </div>
          <div class="box-bottom">
            <el-radio label="13" size="mini" border>每周末0点整执行</el-radio>
          </div>
          <div class="box-bottom cycle">
            <el-radio label="14" size="mini" border
              >自定义执行星期和时分</el-radio
            >
            <div style="margin-top:8px;">
              <span style="margin-right:8px;">每周</span>
              <el-select
                v-model="weekDay.week"
                placeholder="请选择"
                style="width:100px;"
                size="mini"
                @change="getChangeWeekTime"
              >
                <el-option label="日" :value="'1'"></el-option>
                <el-option label="一" :value="'2'"></el-option>
                <el-option label="二" :value="'3'"></el-option>
                <el-option label="三" :value="'4'"></el-option>
                <el-option label="四" :value="'5'"></el-option>
                <el-option label="五" :value="'6'"></el-option>
                <el-option label="六" :value="'7'"></el-option>
              </el-select>
              <el-input-number
                @change="getChangeWeekTime"
                v-model="weekDay.hour"
                :min="0"
                :max="23"
                size="mini"
                style="width: 100px;"
              ></el-input-number>
              <span style="margin:0 8px;">点</span>
              <el-input-number
                @change="getChangeWeekTime"
                v-model="weekDay.minute"
                :min="0"
                :max="59"
                size="mini"
                style="width: 100px;"
              ></el-input-number>
              <span style="margin-left:8px;">分执行</span>
            </div>
          </div>
        </el-radio-group>
      </el-tab-pane>
      <el-tab-pane label="月" name="month">
        <el-radio-group v-model="type" size="mini" @change="getValue">
          <div class="box-bottom">
            <el-radio label="21" size="mini" border>每月1号0点执行</el-radio>
          </div>
          <div class="box-bottom">
            <el-radio label="22" size="mini" border>每月15号0点执行</el-radio>
          </div>
          <div class="box-bottom cycle">
            <el-radio label="23" size="mini" border
              >自定义执行日和时分</el-radio
            >
            <div style="margin-top:8px;">
              <span style="margin-right:8px;">每月</span>
              <el-input-number
                @change="getChangeMonthTime"
                v-model="month.day"
                :min="1"
                :max="31"
                size="mini"
                style="width: 100px;"
              ></el-input-number>
              <span style="margin:0 8px;">(号)</span>
              <el-input-number
                @change="getChangeMonthTime"
                v-model="month.hour"
                :min="0"
                :max="23"
                size="mini"
                style="width: 100px;"
              ></el-input-number>
              <span style="margin:0 8px;">点</span>
              <el-input-number
                @change="getChangeMonthTime"
                v-model="month.minute"
                :min="0"
                :max="59"
                size="mini"
                style="width: 100px;"
              ></el-input-number>
              <span style="margin-left:8px;">分执行</span>
            </div>
          </div>
        </el-radio-group>
      </el-tab-pane>
    </el-tabs>
    <div class="table-box">
      <span>生成的表达式为：{{ cronExpression }}</span>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      activeName: "day",
      // 当前选择
      type: "1",
      // 每天几点执行
      day: {
        hour: "0",
        minute: "0"
      },
      // 每周几 几点执行
      weekDay: {
        week: "2",
        hour: "0",
        minute: "0"
      },
      // 月
      month: {
        day: "1",
        hour: "0",
        minute: "0"
      },
      // 最终表达式
      cronExpression: "0 0 0 * * ?"
    };
  },
  methods: {
    resetValue() {
      this.day = {
        hour: "0",
        minute: "0"
      };
      this.weekDay = {
        week: "2",
        hour: "0",
        minute: "0"
      };
      this.month = {
        day: "1",
        hour: "0",
        minute: "0"
      };
    },
    // 获取当前值
    getValue() {
      this.resetValue();
      if (this.type == "1") {
        // 每天0点整执行
        this.cronExpression = "0 0 0 * * ?";
      } else if (this.type == "2") {
        // 每天*点*分执行
        this.cronExpression =
          "0 " + this.day.minute + " " + this.day.hour + " * * ?";
      } else if (this.type == "11") {
        // 每周一0点整执行
        this.cronExpression = "0 0 0 ? * 2";
      } else if (this.type == "12") {
        // 每周一至周五0点整执行
        this.cronExpression = "0 0 0 ? * 2-6";
      } else if (this.type == "13") {
        // 每周末0点整执行
        this.cronExpression = "0 0 0 ? * 1,7";
      } else if (this.type == "14") {
        // 每周几 几点几分执行
        this.cronExpression =
          "0 " +
          this.weekDay.minute +
          " " +
          this.weekDay.hour +
          " ? * " +
          this.weekDay.week;
      } else if (this.type == "21") {
        // 每月1号0点
        this.cronExpression = "0 0 0 1 * ?";
      } else if (this.type == "22") {
        // 每月15号0点
        this.cronExpression = "0 0 0 15 * ?";
      } else if (this.type == "23") {
        // 每月几号几点几分执行
        this.cronExpression =
          "0 " +
          this.month.minute +
          " " +
          this.month.hour +
          " " +
          this.month.day +
          " * ?";
      }
    },
    // 每天几点开始执行
    getChangeDayHourTime() {
      this.type = "2";
      this.cronExpression =
        "0 " + this.day.minute + " " + this.day.hour + " * * ?";
    },
    // 每周几 几点几分执行
    getChangeWeekTime() {
      this.type = "14";
      this.cronExpression =
        "0 " +
        this.weekDay.minute +
        " " +
        this.weekDay.hour +
        " ? * " +
        this.weekDay.week;
    },
    // 每月几号几点几分执行
    getChangeMonthTime() {
      this.type = "23";
      this.cronExpression =
        "0 " +
        this.month.minute +
        " " +
        this.month.hour +
        " " +
        this.month.day +
        " * ?";
    },
    // tab选择重置内容
    handleClick() {
      if (this.activeName == "day") {
        this.type = "1";
        this.getValue();
      } else if (this.activeName == "week") {
        this.type = "11";
        this.getValue();
      } else {
        this.type = "21";
        this.getValue();
      }
    }
  }
};
</script>
<style lang="css">
.cron-default-first .box-bottom {
  margin-bottom: 8px;
}
.cron-default-first .cycle {
  font-size: 12px;
}
.cron-default-first .el-tabs .el-tabs__content {
  overflow-x: hidden;
  overflow-y: auto;
  height: 220px;
}
.cron-default-first .table-box {
  border-top: 1px solid #ebebeb;
  margin-top: 10px;
  padding-top: 10px;
  min-width: 400px;
}
</style>