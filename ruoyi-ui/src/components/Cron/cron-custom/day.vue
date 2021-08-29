<template>
  <!-- 由于日和周的选择会有冲突性，其中一个必然为? 因此将内容写到一个文件中 -->
  <div class="day-and-week-box">
    <el-radio-group v-model="type" size="mini" @change="getValue">
      <!-- 日 -->
      <div class="box-bottom">
        <el-radio label="1" size="mini" border>每日</el-radio>
      </div>
      <div class="box-bottom cycle">
        <el-radio label="2" size="mini" border>日周期</el-radio>
        <span style="margin-right: 8px;">从</span>
        <el-input-number
          @change="getChangeDay"
          v-model="dayCycle.start"
          :min="1"
          :max="31"
          size="mini"
          style="width: 100px;"
        ></el-input-number>
        <span style="margin-left: 8px; margin-right: 8px;">至</span>
        <el-input-number
          @change="getChangeDay"
          v-model="dayCycle.end"
          :min="2"
          :max="31"
          size="mini"
          style="width: 100px;"
        ></el-input-number>
        <span style="margin-left: 8px;">日</span>
      </div>
      <div class="box-bottom cycle">
        <el-radio label="3" size="mini" border>日循环</el-radio>
        <span style="margin-right: 8px;">从</span>
        <el-input-number
          @change="getLoopDay"
          v-model="dayLoop.start"
          :min="1"
          :max="31"
          size="mini"
          style="width: 100px;"
        ></el-input-number>
        <span style="margin-left: 8px; margin-right: 8px;">日开始，每</span>
        <el-input-number
          @change="getLoopDay"
          v-model="dayLoop.end"
          :min="1"
          :max="31"
          size="mini"
          style="width: 100px;"
        ></el-input-number>
        <span style="margin-left: 8px;">日执行一次</span>
      </div>
      <div class="box-bottom cycle">
        <el-radio label="5" size="mini" border>工作日</el-radio>
        <span style="margin-right: 8px;">每月</span>
        <el-input-number
          @change="getworkDay"
          v-model="work"
          :min="1"
          :max="31"
          size="mini"
          style="width: 100px;"
        ></el-input-number>
        <span style="margin-left: 8px;">号，最近的工作日</span>
      </div>
      <div class="box-bottom">
        <el-radio label="6" size="mini" border>月最后一天</el-radio>
      </div>
      <div class="box-bottom">
        <el-radio label="7" size="mini" border>月最后一个工作日</el-radio>
      </div>
      <!-- 周 -->
      <div class="box-border"></div>
      <div class="box-bottom">
        <el-radio label="11" size="mini" border>每周</el-radio>
      </div>
      <div class="box-bottom cycle">
        <el-radio label="12" size="mini" border>周周期</el-radio>
        <span style="margin-right: 8px;">从星期({{weekFormat[weekCycle.start]}})</span>
        <el-input-number
          @change="getChangeWeek"
          v-model="weekCycle.start"
          :min="1"
          :max="7"
          size="mini"
          style="width: 100px;"
        ></el-input-number>
        <span style="margin-left: 8px; margin-right: 8px;">至星期({{weekFormat[weekCycle.end]}})</span>
        <el-input-number
          @change="getChangeWeek"
          v-model="weekCycle.end"
          :min="2"
          :max="7"
          size="mini"
          style="width: 100px;"
        ></el-input-number>
      </div>
      <div class="box-bottom cycle">
        <el-radio label="13" size="mini" border>周循环</el-radio>
        <span style="margin-right: 8px;">月第</span>
        <el-input-number
          @change="getLoopWeek"
          v-model="weekLoop.start"
          :min="1"
          :max="4"
          size="mini"
          style="width: 100px;"
        ></el-input-number>
        <span style="margin-left: 8px; margin-right: 8px;">个星期({{weekFormat[weekLoop.end]}})</span>
        <el-input-number
          @change="getLoopWeek"
          v-model="weekLoop.end"
          :min="1"
          :max="7"
          size="mini"
          style="width: 100px;"
        ></el-input-number>
        <span style="margin-left: 8px;">开始执行</span>
      </div>
      <div class="box-bottom cycle">
        <el-radio label="15" size="mini" border>月末周</el-radio>
        <span style="margin-right: 8px;">月最后一个星期({{weekFormat[last]}})</span>
        <el-input-number
          @change="getlastWeek"
          v-model="last"
          :min="1"
          :max="7"
          size="mini"
          style="width: 100px;"
        ></el-input-number>
      </div>
      <div class="box-border"></div>
      <!--指定 -->
      <div class="box-bottom">
        <el-radio label="4" size="mini" border>指定日</el-radio>
        <el-radio label="14" size="mini" border>指定周</el-radio>
      </div>
      <div>
        <el-checkbox-group
          v-model="appointDay"
          v-show="type == 4"
          class="second-minute-chekck-group"
        >
          <el-checkbox @change="getAppointDay" v-for="j in 31" :key="j" :label="j"></el-checkbox>
        </el-checkbox-group>
        <el-checkbox-group
          v-model="appointWeek"
          v-show="type == 14"
          class="second-minute-chekck-group"
        >
          <el-checkbox @change="getAppointWeek" v-for="j in 7" :key="j" :label="j"></el-checkbox>
        </el-checkbox-group>
      </div>
    </el-radio-group>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 类型
      type: "1",
      // 日-周期
      dayCycle: {
        start: 0,
        end: 0
      },
      // 日-循环
      dayLoop: {
        start: 0,
        end: 0
      },
      // 日-指定
      appointDay: [],
      // 日-工作日
      work: 1,
      // 周-周期
      weekCycle: {
        start: 0,
        end: 0
      },
      // 周-循环
      weekLoop: {
        start: 0,
        end: 0
      },
      // 周-月末星期几
      last: 1,
      // 周-指定
      appointWeek: [],
      // 周-转义
      weekFormat: {
        1: "天",
        2: "一",
        3: "二",
        4: "三",
        5: "四",
        6: "五",
        7: "六"
      },
      // 结果
      result: {
        day: "",
        week: ""
      }
    };
  },
  methods: {
    // 当前选项
    getValue(value) {
      // 清空指定的选择项
      this.appointWeek = [];
      this.appointDay = [];
      if (value == "1") {
        // 指定每天
        this.result.day = "*";
        // 星期则为？
        this.result.week = "?";
      } else if (value == "2") {
        // 指定日-周期
        this.result.day = this.dayCycle.start + "-" + this.dayCycle.end;
        // 星期则为？
        this.result.week = "?";
      } else if (value == "3") {
        // 指定日-循环
        this.result.day = this.dayLoop.start + "/" + this.dayLoop.end;
        // 星期则为？
        this.result.week = "?";
      } else if (value == "5") {
        // 指定工作日
        this.result.day = this.work + "W";
        // 星期则为？
        this.result.week = "?";
      } else if (value == "6") {
        // 月最后一天
        this.result.day = "L";
        // 星期则为？
        this.result.week = "?";
      } else if (value == "7") {
        // 月最后一个工作日
        this.result.day = "LW";
        // 星期则为？
        this.result.week = "?";
      } else if (value == "4") {
        // 指定日期-默认勾选0
        this.appointDay = [1];
        this.result.day = this.appointDay.join(",");
        // 星期则为？
        this.result.week = "?";
      } else if (value == "11") {
        // 指定每周
        this.result.week = "*";
        // 日则为？
        this.result.day = "?";
      } else if (value == "12") {
        // 指定周-周期
        this.result.week = this.weekCycle.start + "-" + this.weekCycle.end;
        // 日则为？
        this.result.day = "?";
      } else if (value == "13") {
        // 指定周-循环
        this.result.week = this.weekLoop.end + "#" + this.weekLoop.start;
        // 日则为？
        this.result.day = "?";
      } else if (value == "15") {
        // 指定周-月末周几
        this.result.week = this.last + "L";
        // 日则为？
        this.result.day = "?";
      } else if (value == "14") {
        // 指定日期-默认勾选0
        this.appointWeek = [1];
        this.result.week = this.appointWeek.join(",");
        // 日则为？
        this.result.day = "?";
      } else {
        console.log('none');
      }
      this.$emit("dayAndWeek", this.result);
    },
    // 日-周期
    getChangeDay() {
      this.type = "2";
      this.result.day = this.dayCycle.start + "-" + this.dayCycle.end;
      this.result.week = "?";
      this.$emit("dayAndWeek", this.result);
      // 清空指定状态
      this.appointWeek = [];
      this.appointDay = [];
    },
    // 日-循环
    getLoopDay() {
      this.type = "3";
      this.result.day = this.dayLoop.start + "/" + this.dayLoop.end;
      this.result.week = "?";
      this.$emit("dayAndWeek", this.result);
      // 清空指定状态
      this.appointWeek = [];
      this.appointDay = [];
    },
    // 日-指定工作日
    getworkDay() {
      this.type = "5";
      this.result.day = this.work + "W";
      this.result.week = "?";
      this.$emit("dayAndWeek", this.result);
      // 清空指定状态
      this.appointWeek = [];
      this.appointDay = [];
    },
    // 日-指定日期
    getAppointDay() {
      this.type = "4";
      this.appointWeek = [];
      this.result.day = this.appointDay
        .sort((a, b) => {
          return a - b;
        })
        .join(",");
      this.result.week = "?";
      this.$emit("dayAndWeek", this.result);
    },
    // 周-指定日期
    getAppointWeek() {
      this.type = "14";
      this.appointDay = [];
      this.result.week = this.appointWeek
        .sort((a, b) => {
          return a - b;
        })
        .join(",");
      this.result.day = "?";
      this.$emit("dayAndWeek", this.result);
    },
    // 周- 周期
    getChangeWeek() {
      this.type = "12";
      this.result.week = this.weekCycle.start + "-" + this.weekCycle.end;
      this.result.day = "?";
      this.$emit("dayAndWeek", this.result);
      // 清空指定状态
      this.appointWeek = [];
      this.appointDay = [];
    },
    // 周- 循环-指定本月第几周星期几开始
    getLoopWeek() {
      this.type = "13";
      this.result.week = this.weekLoop.end + "#" + this.weekLoop.start;
      this.result.day = "?";
      this.$emit("dayAndWeek", this.result);
      // 清空指定状态
      this.appointWeek = [];
      this.appointDay = [];
    },
    // 周-月末星期几
    getlastWeek() {
      this.type = "15";
      this.result.week = this.last + "L";
      this.result.day = "?";
      this.$emit("dayAndWeek", this.result);
      // 清空指定状态
      this.appointWeek = [];
      this.appointDay = [];
    }
  }
};
</script>

<style lang="css">
.day-and-week-box {
  font-size: 12px;
}
.day-and-week-box .box-border {
  height: 0;
  overflow: hidden;
  border-top: 1px dashed #ebebeb;
  margin-bottom: 8px;
  width: 100%;
}
.day-and-week-box .box-bottom {
  margin-bottom: 8px;
}
.day-and-week-box .cycle {
  font-size: 12px;
}
.day-and-week-box .second-minute-chekck-group {
  display: flex;
  flex-wrap: wrap;
}
.day-and-week-box .el-checkbox + .el-checkbox {
  margin: 0;
}
.day-and-week-box .second-minute-chekck-group .el-checkbox {
  margin: 0;
  width: 10%;
}
</style>
