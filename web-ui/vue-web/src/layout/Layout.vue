<template>
  <el-container>
    <el-header style="padding: 0">
      <Header/>
    </el-header>
    <el-main class="el-main">
      <router-view/>
    </el-main>
    <el-footer style="padding: 0">
      <Footer/>
    </el-footer>
  </el-container>
</template>

<script>
import Header from "../layout/Header";
import Footer from "../layout/Footer";

export default {
  name: "Layout",
  components: {Footer, Header},
}
</script>

<style scoped>
.el-main {
  overflow: auto;
  padding: 0;
  min-height: 1000px;
  margin-top: 80px;
}
</style>
