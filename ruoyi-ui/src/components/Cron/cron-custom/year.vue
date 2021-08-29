<template>
  <div class="year-box">
    <el-radio-group v-model="type" size="mini" @change="getValue">
      <div class="box-bottom">
        <el-radio label="1" size="mini" border>每年</el-radio>
      </div>
      <div class="box-bottom">
        <el-radio label="-1" size="mini" border>不指定</el-radio>
      </div>
      <div class="cycle">
        <el-radio label="2" size="mini" border>周期</el-radio>
        <span style="margin-right:8px;">从</span>
        <el-input-number
          @change="getChangeYear"
          v-model="cycle.start"
          :min="0"
          size="mini"
          style="width: 120px;"
        ></el-input-number>
        <span style="margin:0 8px;">至</span>
        <el-input-number
          @change="getChangeYear"
          v-model="cycle.end"
          :min="parseInt(cycle.start)"
          size="mini"
          style="width: 120px;"
        ></el-input-number>
        <span style="margin-left:8px;">年</span>
      </div>
    </el-radio-group>
  </div>
</template>

<script>
export default {
  data() {
    return {
      type: "-1", // 类型
      cycle: {
        // 周期
        start: 2000,
        end: 2020
      },
      // 年份选择结果
      result: ""
    };
  },
  methods: {
    // 当前选择项
    getValue(value) {
      if (value == "-1") {
        // 不指定 年份为空(年份允许为空)
        this.result = " ";
      } else if (value == "1") {
        // 指定每年
        this.result = "*";
      } else {
        // 指定年份周期
        this.result = this.cycle.start + "-" + this.cycle.end;
      }
      console.log("年result", this.result);
      this.$emit("year", this.result);
    },
    // 选择年份周期
    getChangeYear() {
      this.type = "2";
      this.result = this.cycle.start + "-" + this.cycle.end;
      console.log("年result", this.result);
      this.$emit("year", this.result);
    }
  }
};
</script>

<style>
.year-box {
  font-size: 12px;
}
.year-box .box-bottom {
  margin-bottom: 8px;
}
.year-box .cycle {
  font-size: 12px;
}
.year-box .second-minute-chekck-group {
  display: flex;
  flex-wrap: wrap;
}
.year-box .el-checkbox + .el-checkbox {
  margin: 0;
}
.year-box .second-minute-chekck-group .el-checkbox {
  margin: 0;
  width: 10%;
}
</style>