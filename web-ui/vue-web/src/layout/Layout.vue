<template>
  <el-container>
    <el-header style="padding: 0">
      <Header/>
    </el-header>
    <el-main style="padding: 0;">
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
  /* background: red; */
}
</style>
