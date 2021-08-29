<template>
  <div class="month-box">
    <el-radio-group v-model="type" size="mini" @change="getValue">
      <!-- 每月 -->
      <div class="box-bottom">
        <el-radio label="1" size="mini" border>每月</el-radio>
      </div>
      <!-- 周期 -->
      <div class="cycle box-bottom">
        <el-radio label="2" size="mini" border>周期</el-radio>
        <span style="margin-right:8px;">从</span>
        <el-input-number
          @change="getChangeMonth"
          v-model="cycle.start"
          :min="1"
          :max="11"
          size="mini"
          style="width: 100px;"
        ></el-input-number>
        <span style="margin:0 8px;">至</span>
        <el-input-number
          @change="getChangeMonth"
          v-model="cycle.end"
          :min="parseInt(cycle.start + 1)"
          :max="12"
          size="mini"
          style="width: 100px;"
        ></el-input-number>
        <span style="margin-left:8px;">月</span>
      </div>
      <!-- 循环 -->
      <div class="cycle box-bottom">
        <el-radio label="3" size="mini" border>循环</el-radio>
        <span style="margin-right:8px;">从</span>
        <el-input-number
          @change="getLoopMonth"
          v-model="loop.start"
          :min="1"
          :max="12"
          size="mini"
          style="width: 100px;"
        ></el-input-number>
        <span style="margin:0 8px;">月开始，每</span>
        <el-input-number
          @change="getLoopMonth"
          v-model="loop.end"
          :min="1"
          :max="12"
          size="mini"
          style="width: 100px;"
        ></el-input-number>
        <span style="margin-left:8px;">月执行一次</span>
      </div>
      <!-- 指定 -->
      <div class="box-bottom">
        <el-radio label="4" size="mini" border>指定</el-radio>
      </div>
      <div>
        <el-checkbox-group v-model="appoint" class="second-minute-chekck-group">
          <el-checkbox @change="getAppointMonth" v-for="j in 12" :key="j" :label="j"></el-checkbox>
        </el-checkbox-group>
      </div>
    </el-radio-group>
  </div>
</template>

<script>
export default {
  data() {
    return {
      type: "1", // 类型
      // 周期
      cycle: {
        start: 1,
        end: 2
      },
      // 循环
      loop: {
        start: 1,
        end: 1
      },
      // 指定月份
      appoint: [],
      // 月份结果
      result: ""
    };
  },
  methods: {
    // 当前选择项
    getValue(value) {
      // 清空指定月份的选择项
      this.appoint = [];
      if (value == "-1") {
        // 不指定 月份
        this.result = "?";
      } else if (value == "1") {
        // 指定每月
        this.result = "*";
      } else if (value == "2") {
        // 指定月份周期
        this.result = this.cycle.start + "-" + this.cycle.end;
      } else if (value == "3") {
        // 指定循环周期
        this.result = this.loop.start + "/" + this.loop.end;
      } else {
        this.appoint = [1];
        this.result = this.appoint.join(",");
      }
      console.log("月result", this.result);
      this.$emit("month", this.result);
    },
    // 选择月份周期
    getChangeMonth() {
      this.type = "2";
      this.result = this.cycle.start + "-" + this.cycle.end;
      console.log("月result", this.result);
      this.$emit("month", this.result);
      // 清空指定状态
      this.appoint = [];
    },
    // 选择月份循环
    getLoopMonth() {
      this.type = "3";
      this.result = this.loop.start + "/" + this.loop.end;
      console.log("月result", this.result);
      this.$emit("month", this.result);
      // 清空指定状态
      this.appoint = [];
    },
    // 选择指定月份
    getAppointMonth() {
      this.type = "4";
      this.result = this.appoint
        .sort((a, b) => {
          return a - b;
        })
        .join(",");
      console.log("月result", this.result);
      this.$emit("month", this.result);
    }
  }
};
</script>

<style lang="css">
.month-box {
  font-size: 12px;
}
.month-box .box-bottom {
  margin-bottom: 8px;
}
.month-box .cycle {
  font-size: 12px;
}
.month-box .second-minute-chekck-group {
  display: flex;
  flex-wrap: wrap;
}
.month-box .el-checkbox + .el-checkbox {
  margin: 0;
}
.month-box .second-minute-chekck-group .el-checkbox {
  margin: 0;
  width: 10%;
}
</style>
