<template>
  <!-- 快捷生成完全可以由自己根据需求进行定义，因此这里仅开发了部分功能 -->
  <div class="cron-default-second">
    <el-tabs type="border-card" v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="天" name="day">
        <div>
          <el-radio-group v-model="type" size="mini" @change="getValue">
            <div class="box-bottom cycle">
              <el-radio label="1" size="mini" border>天周期任务</el-radio>
              <div style="margin-top: 8px">
                <span style="margin-right: 8px">每天</span>
                <el-input-number
                  @change="getChangeDayHourTime"
                  v-model="day.hour"
                  :min="0"
                  :max="23"
                  size="mini"
                  style="width: 100px"
                ></el-input-number>
                <span style="margin: 0 8px">点</span>
                <el-input-number
                  @change="getChangeDayHourTime"
                  v-model="day.minute"
                  :min="0"
                  :max="59"
                  size="mini"
                  style="width: 100px"
                ></el-input-number>
                <span style="margin-left: 8px">分开始执行</span>
              </div>
              <div style="margin-top: 8px">
                <span style="margin-right: 8px">每天</span>
                <el-input-number
                  @change="getChangeDayHourTime"
                  v-model="day.hourEnd"
                  :min="parseInt(day.hour)"
                  :max="23"
                  size="mini"
                  style="width: 100px"
                ></el-input-number>
                <span style="margin: 0 8px">点</span>
                <el-input-number
                  @change="getChangeDayHourTime"
                  v-model="day.minuteEnd"
                  :min="day.hourEnd == day.hour ? parseInt(day.minute) : 0"
                  :max="59"
                  size="mini"
                  style="width: 100px"
                ></el-input-number>
                <span style="margin-left: 8px">分结束执行</span>
              </div>
            </div>
          </el-radio-group>
        </div>
      </el-tab-pane>
      <el-tab-pane label="周" name="week">
        <!--周---周期任务 -->
        <div>
          <el-radio-group v-model="type" size="mini" @change="getValue">
            <div class="box-bottom cycle">
              <el-radio label="11" size="mini" border>周周期任务</el-radio>
              <div style="margin-top: 8px">
                <span style="margin-right: 8px">每周</span>
                <el-select
                  v-model="weekDayLoop.week"
                  placeholder="请选择"
                  style="width: 100px"
                  size="mini"
                  @change="getChangeWeekTimeLoop(true)"
                >
                  <el-option label="一" :value="'2'"></el-option>
                  <el-option label="二" :value="'3'"></el-option>
                  <el-option label="三" :value="'4'"></el-option>
                  <el-option label="四" :value="'5'"></el-option>
                  <el-option label="五" :value="'6'"></el-option>
                  <el-option label="六" :value="'7'"></el-option>
                  <el-option
                    label="日"
                    :value="'1'"
                    :disabled="true"
                  ></el-option>
                </el-select>
                <span style="margin: 0 8px">至</span>
                <el-select
                  v-model="weekDayLoop.weekEnd"
                  placeholder="请选择"
                  style="width: 100px"
                  size="mini"
                  @change="getChangeWeekTimeLoop(false)"
                >
                  <el-option
                    label="一"
                    :value="'2'"
                    :disabled="parseInt(weekDayLoop.week) >= 2"
                  ></el-option>
                  <el-option
                    label="二"
                    :value="'3'"
                    :disabled="parseInt(weekDayLoop.week) >= 3"
                  ></el-option>
                  <el-option
                    label="三"
                    :value="'4'"
                    :disabled="parseInt(weekDayLoop.week) >= 4"
                  ></el-option>
                  <el-option
                    label="四"
                    :value="'5'"
                    :disabled="parseInt(weekDayLoop.week) >= 5"
                  ></el-option>
                  <el-option
                    label="五"
                    :value="'6'"
                    :disabled="parseInt(weekDayLoop.week) >= 6"
                  ></el-option>
                  <el-option
                    label="六"
                    :value="'7'"
                    :disabled="parseInt(weekDayLoop.week) >= 7"
                  ></el-option>
                  <el-option label="日" :value="'1'"></el-option>
                </el-select>
              </div>
              <div style="margin-top: 8px">
                <el-input-number
                  @change="getChangeWeekTimeLoop(false)"
                  v-model="weekDayLoop.hour"
                  :min="0"
                  :max="23"
                  size="mini"
                  style="width: 100px"
                ></el-input-number>
                <span style="margin: 0 8px">点</span>
                <el-input-number
                  @change="getChangeWeekTimeLoop(false)"
                  v-model="weekDayLoop.minute"
                  :min="0"
                  :max="59"
                  size="mini"
                  style="width: 100px"
                ></el-input-number>
                <span style="margin-left: 8px">分开始执行</span>
              </div>
              <div style="margin-top: 8px">
                <el-input-number
                  @change="getChangeWeekTimeLoop(false)"
                  v-model="weekDayLoop.hourEnd"
                  :min="parseInt(weekDayLoop.hour)"
                  :max="23"
                  size="mini"
                  style="width: 100px"
                ></el-input-number>
                <span style="margin: 0 8px">点</span>
                <el-input-number
                  @change="getChangeWeekTimeLoop(false)"
                  v-model="weekDayLoop.minuteEnd"
                  :min="
                    weekDayLoop.hourEnd == weekDayLoop.hour
                      ? parseInt(weekDayLoop.minute)
                      : 0
                  "
                  :max="59"
                  size="mini"
                  style="width: 100px"
                ></el-input-number>
                <span style="margin-left: 8px">分结束执行</span>
              </div>
            </div>
          </el-radio-group>
        </div>
        <!--周---自定义周期任务 -->
        <div>
          <el-radio-group v-model="type" size="mini" @change="getValue">
            <div class="box-bottom cycle">
              <el-radio label="12" size="mini" border
                >自定义周周期任务</el-radio
              >
              <div style="margin-top: 8px">
                <span style="margin-right: 8px">每周</span>
                <el-select
                  v-model="weekDayCycle.week"
                  placeholder="请选择周期"
                  style="width: 270px"
                  size="mini"
                  multiple
                  filterable
                  default-first-option
                  @change="getChangeWeekTimeCycle"
                >
                  <el-option label="一" :value="'2'"></el-option>
                  <el-option label="二" :value="'3'"></el-option>
                  <el-option label="三" :value="'4'"></el-option>
                  <el-option label="四" :value="'5'"></el-option>
                  <el-option label="五" :value="'6'"></el-option>
                  <el-option label="六" :value="'7'"></el-option>
                  <el-option label="日" :value="'1'"></el-option>
                </el-select>
              </div>
              <div style="margin-top: 8px">
                <el-input-number
                  @change="getChangeWeekTimeCycle"
                  v-model="weekDayCycle.hour"
                  :min="0"
                  :max="23"
                  size="mini"
                  style="width: 100px"
                ></el-input-number>
                <span style="margin: 0 8px">点</span>
                <el-input-number
                  @change="getChangeWeekTimeCycle"
                  v-model="weekDayCycle.minute"
                  :min="0"
                  :max="59"
                  size="mini"
                  style="width: 100px"
                ></el-input-number>
                <span style="margin-left: 8px">分开始执行</span>
              </div>
              <div style="margin-top: 8px">
                <el-input-number
                  @change="getChangeWeekTimeCycle"
                  v-model="weekDayCycle.hourEnd"
                  :min="parseInt(weekDayCycle.hour)"
                  :max="23"
                  size="mini"
                  style="width: 100px"
                ></el-input-number>
                <span style="margin: 0 8px">点</span>
                <el-input-number
                  @change="getChangeWeekTimeCycle"
                  v-model="weekDayCycle.minuteEnd"
                  :min="
                    weekDayCycle.hourEnd == weekDayCycle.hour
                      ? parseInt(weekDayCycle.minute)
                      : 0
                  "
                  :max="59"
                  size="mini"
                  style="width: 100px"
                ></el-input-number>
                <span style="margin-left: 8px">分结束执行</span>
              </div>
            </div>
          </el-radio-group>
        </div>
      </el-tab-pane>
      <el-tab-pane label="月" name="mouth">
        <!-- 月---周期任务 -->
        <div>
          <el-radio-group v-model="type" size="mini" @change="getValue">
            <div class="box-bottom cycle">
              <el-radio label="21" size="mini" border>月周期任务</el-radio>
              <div style="margin-top: 8px">
                <span style="margin-right: 8px">每月</span>
                <el-select
                  v-model="monthLoop.day"
                  placeholder="请选择"
                  style="width: 100px"
                  size="mini"
                  @change="getChangeMonthTimeLoop(true)"
                >
                  <el-option
                    v-for="item in monthList"
                    :key="'start' + item"
                    :value="item"
                    :label="item"
                  ></el-option>
                </el-select>
                <span style="margin: 0 8px">(号)至</span>
                <el-select
                  v-model="monthLoop.dayEnd"
                  placeholder="请选择"
                  style="width: 100px"
                  size="mini"
                  @change="getChangeMonthTimeLoop(false)"
                >
                  <el-option
                    v-for="item in monthList"
                    :key="'end' + item"
                    :value="item"
                    :label="item"
                    :disabled="parseInt(monthLoop.day) >= item"
                  ></el-option>
                </el-select>
                <span style="margin: 0 8px">(号)</span>
              </div>
              <div style="margin-top: 8px">
                <el-input-number
                  @change="getChangeMonthTimeLoop(false)"
                  v-model="monthLoop.hour"
                  :min="0"
                  :max="23"
                  size="mini"
                  style="width: 100px"
                ></el-input-number>
                <span style="margin: 0 8px">点</span>
                <el-input-number
                  @change="getChangeMonthTimeLoop(false)"
                  v-model="monthLoop.minute"
                  :min="0"
                  :max="59"
                  size="mini"
                  style="width: 100px"
                ></el-input-number>
                <span style="margin-left: 8px">分开始执行</span>
              </div>
              <div style="margin-top: 8px">
                <el-input-number
                  @change="getChangeMonthTimeLoop(false)"
                  v-model="monthLoop.hourEnd"
                  :min="parseInt(monthLoop.hour)"
                  :max="23"
                  size="mini"
                  style="width: 100px"
                ></el-input-number>
                <span style="margin: 0 8px">点</span>
                <el-input-number
                  @change="getChangeMonthTimeLoop(false)"
                  v-model="monthLoop.minuteEnd"
                  :min="
                    monthLoop.hourEnd == monthLoop.hour
                      ? parseInt(monthLoop.minute)
                      : 0
                  "
                  :max="59"
                  size="mini"
                  style="width: 100px"
                ></el-input-number>
                <span style="margin-left: 8px">分结束执行</span>
              </div>
            </div>
          </el-radio-group>
        </div>
        <!-- 月---自定义周期任务 -->
        <div>
          <el-radio-group v-model="type" size="mini" @change="getValue">
            <div class="box-bottom cycle">
              <el-radio label="22" size="mini" border
                >自定义月周期任务</el-radio
              >
              <div style="margin-top: 8px">
                <span style="margin-right: 8px">每月</span>
                <el-select
                  v-model="monthCycle.day"
                  placeholder="请选择周期"
                  style="width: 270px"
                  size="mini"
                  multiple
                  filterable
                  default-first-option
                  @change="getChangeMonthTimeCycle"
                >
                  <el-option
                    v-for="item in monthList"
                    :key="'monthcycle' + item"
                    :value="item"
                    :label="item"
                  ></el-option>
                </el-select>
                <span style="margin-right: 8px">(号)</span>
              </div>
              <div style="margin-top: 8px">
                <el-input-number
                  @change="getChangeMonthTimeCycle"
                  v-model="monthCycle.hour"
                  :min="0"
                  :max="23"
                  size="mini"
                  style="width: 100px"
                ></el-input-number>
                <span style="margin: 0 8px">点</span>
                <el-input-number
                  @change="getChangeMonthTimeCycle"
                  v-model="monthCycle.minute"
                  :min="0"
                  :max="59"
                  size="mini"
                  style="width: 100px"
                ></el-input-number>
                <span style="margin-left: 8px">分开始执行</span>
              </div>
              <div style="margin-top: 8px">
                <el-input-number
                  @change="getChangeMonthTimeCycle"
                  v-model="monthCycle.hourEnd"
                  :min="parseInt(monthCycle.hour)"
                  :max="23"
                  size="mini"
                  style="width: 100px"
                ></el-input-number>
                <span style="margin: 0 8px">点</span>
                <el-input-number
                  @change="getChangeMonthTimeCycle"
                  v-model="monthCycle.minuteEnd"
                  :min="
                    monthCycle.hourEnd == monthCycle.hour
                      ? parseInt(monthCycle.minute)
                      : 0
                  "
                  :max="59"
                  size="mini"
                  style="width: 100px"
                ></el-input-number>
                <span style="margin-left: 8px">分结束执行</span>
              </div>
            </div>
          </el-radio-group>
        </div>
      </el-tab-pane>
    </el-tabs>
    <div class="table-box">
      <div>生成的开始时间表达式为：{{ cronExpression }}</div>
      <div>生成的结束时间表达式为：{{ cronExpressionEnd }}</div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      // 当前tab类型
      activeName: "day",
      // 当前单项选择
      type: "1",
      // 天 --- 天任务
      day: {
        hour: "0", // 开始
        minute: "0",
        hourEnd: "1", // 结束
        minuteEnd: "0"
      },
      // 周 --- 周期任务
      weekDayLoop: {
        week: "2", // 开始
        hour: "0",
        minute: "0",
        weekEnd: "3", // 结束
        hourEnd: "1",
        minuteEnd: "0"
      },
      // 周 --- 自定义周期任务
      weekDayCycle: {
        week: [],
        hour: "0",
        minute: "",
        hourEnd: "1",
        minuteEnd: ""
      },
      // 月 --- 周期任务
      monthLoop: {
        day: "1", // 开始
        hour: "0",
        minute: "0",
        dayEnd: "2", // 结束
        hourEnd: "0",
        minuteEnd: "0"
      },
      // 月 --- 自定义周期任务
      monthCycle: {
        day: [],
        hour: "0",
        minute: "",
        hourEnd: "1",
        minuteEnd: ""
      },
      // 月 --- 循环列表
      monthList: [
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8,
        9,
        10,
        11,
        12,
        13,
        14,
        15,
        16,
        17,
        18,
        19,
        20,
        21,
        22,
        23,
        24,
        25,
        26,
        27,
        28,
        29,
        30,
        31
      ],
      // 开始表达式
      cronExpression: "0 0 0 * * ?",
      // 结束表达式
      cronExpressionEnd: "0 0 1 * * ?"
    };
  },
  methods: {
    /* ===========系统函数========== */
    // tab选择重置内容
    handleClick() {
      if (this.activeName == "day") {
        this.type = "1";
      } else if (this.activeName == "week") {
        this.type = "11";
      } else {
        this.type = "21";
      }
      this.getValue();
    },
    // 重置字段内容
    resetValue() {
      // 天 --- 天任务
      this.day = {
        hour: "0", // 开始
        minute: "0",
        hourEnd: "1", // 结束
        minuteEnd: "0"
      };
      // 周 --- 周期任务
      this.weekDayLoop = {
        week: "2", // 开始
        hour: "0",
        minute: "0",
        weekEnd: "3", // 结束
        hourEnd: "1",
        minuteEnd: "0"
      };
      // 周 --- 自定义周期任务
      this.weekDayCycle = {
        week: [],
        hour: "0",
        minute: "",
        hourEnd: "1",
        minuteEnd: ""
      };
      // 月 --- 周期任务
      this.monthLoop = {
        day: "1", // 开始
        hour: "0",
        minute: "0",
        dayEnd: "2", // 结束
        hourEnd: "0",
        minuteEnd: "0"
      };
      // 月 --- 自定义周期任务
      this.monthCycle = {
        day: [],
        hour: "0",
        minute: "",
        hourEnd: "1",
        minuteEnd: ""
      };
    },

    // 获取当前值
    getValue() {
      this.resetValue();
      switch (this.type) {
        case "1":
          this.createDayHourTime();
          break;
        case "11":
          this.createWeekTimeLoop();
          break;
        case "12":
          this.createWeekTimeCycle();
          break;
        case "21":
          this.createMonthTimeLoop();
          break;
        case "22":
          this.createMonthTimeCycle();
          break;
      }
    },

    /* ==========任务函数=========== */
    // 天 --- 天任务
    getChangeDayHourTime() {
      this.type = "1";
      /* ===========逻辑判断小时和分钟--结束时间不能小于开始时间============== */
      // 限定开始时间小于结束时间
      if (this.day.hour > this.day.hourEnd) {
        // 如果开始小时大于结束小时,则结束小时不能小于开始小时
        this.day.hourEnd = this.day.hour;
      } else if (this.day.hour == this.day.hourEnd) {
        //如果开始小时等于结束小时，则判断开始分钟不能小于结束分钟
        if (this.day.minute > this.day.minuteEnd) {
          this.day.minuteEnd = this.day.minute;
        } else {
        }
      } else {
      }
      // 创建表达式
      this.createDayHourTime();
    },

    // 周 --- 周期任务
    getChangeWeekTimeLoop(IsFirst) {
      this.type = "11";
      /* ============逻辑判断星期---开始时间不能小于结束时间========== */
      // 只有选择开始时间的时候才需要对结束时间进行判断---如果开始时间大于结束时间
      if (
        IsFirst &&
        parseInt(this.weekDayLoop.week) >= parseInt(this.weekDayLoop.weekEnd)
      ) {
        // 则判断第二步，如果开始时间选择是周六'7'且结束时间不是周日'1'，则结束时间赋值周日'1'
        if (parseInt(this.weekDayLoop.week) == 7) {
          this.weekDayLoop.weekEnd = "1";
        } else if (parseInt(this.weekDayLoop.weekEnd) != 1) {
          this.weekDayLoop.weekEnd = parseInt(this.weekDayLoop.week) + 1 + "";
        }
      }
      /* ===========逻辑判断小时和分钟--结束时间不能小于开始时间============== */
      // 限定开始时间小于结束时间
      if (this.weekDayLoop.hour > this.weekDayLoop.hourEnd) {
        // 如果开始小时大于结束小时,则结束小时不能小于开始小时
        this.weekDayLoop.hourEnd = this.weekDayLoop.hour;
      } else if (this.weekDayLoop.hour == this.weekDayLoop.hourEnd) {
        //如果开始小时等于结束小时，则判断开始分钟不能小于结束分钟
        if (this.weekDayLoop.minute > this.weekDayLoop.minuteEnd) {
          this.weekDayLoop.minuteEnd = this.weekDayLoop.minute;
        } else {
        }
      } else {
      }
      // 创建表达式
      this.createWeekTimeLoop();
    },

    // 周 --- 自定义执行周期任务
    getChangeWeekTimeCycle() {
      this.type = "12";
      /* ==============逻辑判断小时和分钟--结束时间不能小于开始时间================= */
      // 限定开始时间小于结束时间
      if (this.weekDayCycle.hour > this.weekDayCycle.hourEnd) {
        // 如果开始小时大于结束小时,则结束小时不能小于开始小时
        this.weekDayCycle.hourEnd = this.weekDayCycle.hour;
      } else if (this.weekDayCycle.hour == this.weekDayCycle.hourEnd) {
        //如果开始小时等于结束小时，则判断开始分钟不能小于结束分钟
        if (this.weekDayCycle.minute > this.weekDayCycle.minuteEnd) {
          this.weekDayCycle.minuteEnd = this.weekDayCycle.minute;
        } else {
        }
      } else {
      }
      // 创建表达式
      this.createWeekTimeCycle();
    },

    // 月 --- 周期任务
    getChangeMonthTimeLoop(IsFirst) {
      this.type = "21";
      /* ============逻辑判断星期---开始时间不能小于结束时间========== */
      // 只有选择开始时间的时候才需要对结束时间进行判断---如果开始时间大于结束时间
      if (
        IsFirst &&
        parseInt(this.monthLoop.day) >= parseInt(this.monthLoop.dayEnd)
      ) {
        this.monthLoop.dayEnd = parseInt(this.monthLoop.day) + 1 + "";
      }
      /* ===========逻辑判断小时和分钟--结束时间不能小于开始时间============== */
      // 限定开始时间小于结束时间
      if (this.monthLoop.hour > this.monthLoop.hourEnd) {
        // 如果开始小时大于结束小时,则结束小时不能小于开始小时
        this.monthLoop.hourEnd = this.monthLoop.hour;
      } else if (this.monthLoop.hour == this.monthLoop.hourEnd) {
        //如果开始小时等于结束小时，则判断开始分钟不能小于结束分钟
        if (this.monthLoop.minute > this.monthLoop.minuteEnd) {
          this.monthLoop.minuteEnd = this.monthLoop.minute;
        } else {
        }
      } else {
      }
      // 创建表达式
      this.createMonthTimeLoop();
    },

    // 月 --- 自定义周期任务
    getChangeMonthTimeCycle() {
      this.type = "22";
      /* ==============逻辑判断小时和分钟--结束时间不能小于开始时间================= */
      // 限定开始时间小于结束时间
      if (this.monthCycle.hour > this.monthCycle.hourEnd) {
        // 如果开始小时大于结束小时,则结束小时不能小于开始小时
        this.monthCycle.hourEnd = this.monthCycle.hour;
      } else if (this.monthCycle.hour == this.monthCycle.hourEnd) {
        //如果开始小时等于结束小时，则判断开始分钟不能小于结束分钟
        if (this.monthCycle.minute > this.monthCycle.minuteEnd) {
          this.monthCycle.minuteEnd = this.monthCycle.minute;
        } else {
        }
      } else {
      }
      // 创建表达式
      this.createMonthTimeCycle();
    },

    /* ==========表达式创建================ */
    // 天 --- 天任务创建
    createDayHourTime() {
      // 开始
      this.cronExpression =
        "0 " + this.day.minute + " " + this.day.hour + " * * ?";
      // 结束
      this.cronExpressionEnd =
        "0 " + this.day.minuteEnd + " " + this.day.hourEnd + " * * ?";
    },

    // 周 --- 周期任务创建
    createWeekTimeLoop() {
      let weeks = this.getWeekLoopNumbers(
        this.weekDayLoop.week,
        this.weekDayLoop.weekEnd
      );
      // 开始
      this.cronExpression =
        "0 " +
        this.weekDayLoop.minute +
        " " +
        this.weekDayLoop.hour +
        " ? * " +
        weeks;
      // 结束
      this.cronExpressionEnd =
        "0 " +
        this.weekDayLoop.minuteEnd +
        " " +
        this.weekDayLoop.hourEnd +
        " ? * " +
        weeks;
    },

    // 周 --- 自定义周期任务创建
    createWeekTimeCycle() {
      let weeks = this.weekDayCycle.week.join(",");
      // 开始
      this.cronExpression =
        "0 " +
        this.weekDayCycle.minute +
        " " +
        this.weekDayCycle.hour +
        " ? * " +
        weeks;
      // 结束
      this.cronExpressionEnd =
        "0 " +
        this.weekDayCycle.minuteEnd +
        " " +
        this.weekDayCycle.hourEnd +
        " ? * " +
        weeks;
    },

    // 月 --- 周期任务创建
    createMonthTimeLoop() {
      let days = this.monthLoop.day + "-" + this.monthLoop.dayEnd;
      // 开始
      this.cronExpression =
        "0 " +
        this.monthLoop.minute +
        " " +
        this.monthLoop.hour +
        " " +
        days +
        " * ?";
      // 结束
      this.cronExpressionEnd =
        "0 " +
        this.monthLoop.minuteEnd +
        " " +
        this.monthLoop.hourEnd +
        " " +
        days +
        " * ?";
    },

    // 月 --- 自定义周期任务
    createMonthTimeCycle() {
      this.monthCycle.day.sort((a, b) => {
        return a - b;
      });
      let days = this.monthCycle.day.join(",");
      // 开始
      this.cronExpression =
        "0 " +
        this.monthCycle.minute +
        " " +
        this.monthCycle.hour +
        " " +
        days +
        " * ?";
      // 结束
      this.cronExpressionEnd =
        "0 " +
        this.monthCycle.minuteEnd +
        " " +
        this.monthCycle.hourEnd +
        " " +
        days +
        " * ?";
    },

    /* =============辅助函数============= */
    // 周 --- 给与开始时间数和结束时间数，获得两者之间是所有数据 如 1，3  获得1,2,3
    getWeekLoopNumbers(numStart, numEnd) {
      let start = parseInt(numStart);
      let end = parseInt(numEnd);
      let result = [];
      // 周日的情况需要注意
      if (start == 1 && end == 1) {
        result.push(1);
      } else if (start != 1 && end == 1) {
        result.push(end);
        end = 7;
        for (let i = start; i <= end; i++) {
          result.push(i);
        }
      } else {
        for (let i = start; i <= end; i++) {
          result.push(i);
        }
      }
      return result.join(",");
    }
  }
};
</script>
<style lang="css">
.cron-default-second .box-bottom {
  margin-bottom: 8px;
}
.cron-default-second .cycle {
  font-size: 12px;
}
.cron-default-second .el-tabs .el-tabs__content {
  overflow-x: hidden;
  overflow-y: auto;
  height: 307px;
}
.cron-default-second .table-box {
  border-top: 1px solid #ebebeb;
  margin-top: 10px;
  padding-top: 10px;
  min-width: 400px;
}
</style>