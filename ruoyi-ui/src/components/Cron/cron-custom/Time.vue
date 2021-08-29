<!-- 秒,分钟,小时通用组件 -->
<template>
  <div class="second-minute-hour-box">
    <el-radio-group v-model="type" size="mini" @change="getValue">
      <!-- 每* -->
      <div class="box-bottom">
        <el-radio label="1" size="mini" border>每{{lable}}</el-radio>
      </div>
      <!-- 每个周期 -->
      <div class="box-bottom cycle">
        <el-radio label="2" size="mini" border>周期</el-radio>
        <span style="margin-right:8px;">从</span>
        <el-input-number
          @change="getChangeTime"
          v-model="cycle.start"
          :min="1"
          :max="maxTime - 1"
          size="mini"
          style="width: 100px;"
        ></el-input-number>
        <span style="margin:0 8px;">至</span>
        <el-input-number
          @change="getChangeTime"
          v-model="cycle.end"
          :min="parseInt(cycle.start) + 1"
          :max="maxTime"
          size="mini"
          style="width: 100px;"
        ></el-input-number>
        <span style="margin-left:8px;">{{lable}}</span>
      </div>
      <!-- 每个循环 -->
      <div class="box-bottom cycle">
        <el-radio label="3" size="mini" border>循环</el-radio>
        <span style="margin-right:8px;">从</span>
        <el-input-number
          @change="getLoopTime"
          v-model="loop.start"
          :min="0"
          :max="maxTime"
          size="mini"
          style="width: 100px;"
        ></el-input-number>
        <span style="margin:0 8px;">{{lable}}开始，每</span>
        <el-input-number
          @change="getLoopTime"
          v-model="loop.end"
          :min="1"
          :max="maxTime"
          size="mini"
          style="width: 100px;"
        ></el-input-number>
        <span style="margin-left:8px;">{{lable}}</span>执行一次
      </div>
      <!-- 指定数 -->
      <div class="box-bottom">
        <el-radio label="4" size="mini" border>指定</el-radio>
      </div>
      <div>
        <el-checkbox-group v-model="appoint" class="second-minute-chekck-group">
          <el-checkbox
            @change="getAppointTime"
            v-for="i in appointNumber"
            :key="i-1"
            :label="i-1"
          >{{i-1}}</el-checkbox>
        </el-checkbox-group>
      </div>
    </el-radio-group>
  </div>
</template>

<script>
export default {
  props: {
    // 传递的lable(时，分，秒),用于区分秒和分,必填
    lable: {
      type: String,
      requied: true
    }
  },
  data() {
    return {
      // 根据传递进来的lable值决定数值,秒分为60,时24
      appointNumber: this.lable == "时" ? 24 : 60,
      // 最大值
      maxTime: this.lable == "时" ? 23 : 59,
      // 类型
      type: "4",
      // 周期
      cycle: {
        start: 0,
        end: 0
      },
      // 循环
      loop: {
        start: 0,
        end: 0
      },
      appoint: [0], // 指定
      result: ""
    };
  },
  methods: {
    // 当前选择项
    getValue(value) {
      // 清空指定的选择项
      this.appoint = [];
      if (value == "1") {
        // 指定每*
        this.result = "*";
      } else if (value == "2") {
        // 指定周期
        this.result = this.cycle.start + "-" + this.cycle.end;
      } else if (value == "3") {
        // 指定循环
        this.result = this.loop.start + "/" + this.loop.end;
      } else {
        // 指定日期-默认勾选0
        this.appoint = [0];
        this.result = this.appoint.join(",");
      }
      console.log("result", this.result);
      this.$emit("time", this.result);
    },
    // 选择周期
    getChangeTime() {
      this.type = "2";
      this.result = this.cycle.start + "-" + this.cycle.end;
      console.log("result", this.result);
      this.$emit("time", this.result);
      // 清空指定状态
      this.appoint = [];
    },
    // 选择循环
    getLoopTime() {
      this.type = "3";
      this.result = this.loop.start + "/" + this.loop.end;
      console.log("result", this.result);
      this.$emit("time", this.result);
      // 清空指定状态
      this.appoint = [];
    },
    // 选择指定
    getAppointTime() {
      this.type = "4";
      this.result = this.appoint
        .sort((a, b) => {
          return a - b;
        })
        .join(",");
      console.log("result", this.result);
      this.$emit("time", this.result);
    }
  }
};
</script>

<style lang="css">
.second-minute-hour-box {
  font-size: 12px;
}
.second-minute-hour-box .box-bottom {
  margin-bottom: 8px;
}
.second-minute-hour-box .cycle {
  font-size: 12px;
}
.second-minute-hour-box .second-minute-chekck-group {
  display: flex;
  flex-wrap: wrap;
}
.second-minute-hour-box .el-checkbox + .el-checkbox {
  margin: 0;
}
.second-minute-hour-box .second-minute-chekck-group .el-checkbox {
  margin: 0;
  width: 10%;
}
</style>
