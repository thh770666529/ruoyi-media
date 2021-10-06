<template>
  <div class="collect">
    <span  @click="addCollect" v-if="!collectFlag" class="iconfont">&#xe663; &nbsp;{{collectCount}} 未收藏</span>
    <span  v-else @click="cancelCollect" class="iconfont active">&#xe663; &nbsp;{{collectCount}} 已收藏</span>
  </div>
</template>

<script>
  import { checkCollectFlag, addCollect, cancelCollectByTargetId } from "@/api/website/collect";

  export default {
		name: 'Collect',
    props: {
      targetId: [Number, String],
      tableName: {
        type: String,
        default: '',
      },
      collectCount: {
        type: [Number, String],
        default: '0'
      }
    },
    data() {
      return {
        collectFlag: false,
        collectForm: {
          targetId: '',
          tableName: ''
        }
      }
    },
    created() {
      const isLogin =  this.$store.getters.isLogin;
      this.collectForm.targetId = this.targetId;
      this.collectForm.tableName = this.tableName;
      if (isLogin){
        this.checkCollectFlag();
      }
    },
		methods: {
      vailLogin () {
        const isLogin = this.$store.getters.isLogin;
        if (!isLogin){
          this.$notify.error({
            title: '警告',
            message: '登录后才可以收藏哦~',
            offset: 100
          });
          this.$confirm('登录后才可以收藏哦，是否进行登录', '提示', {
            confirmButtonText: '登录',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            //如果没有登录 则转到登录页面
            return this.$store.dispatch('LoginPage')
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消登录'
            });
          });
          return false;
        }else {
          return true;
        }
      },
      checkCollectFlag() {
        checkCollectFlag(this.collectForm).then(response => {
          this.collectFlag = response.data
        });
      },
      cancelCollect(){
        cancelCollectByTargetId(this.collectForm).then(res => {
          if (res.code === 200){
            this.collectFlag = false;
            this.collectCount = Number(this.collectCount) - 1;
            this.msgSuccess("取消收藏成功！");
          } else {
            this.msgSuccess("取消收藏失败！");
          }
        });
      },
      addCollect(){
        if (!this.vailLogin()) {
          return;
        }
        addCollect(this.collectForm).then(res => {
          if (res.code === 200){
            this.collectFlag = true;
            this.collectCount = Number(this.collectCount) + 1;
            this.msgSuccess("收藏成功！");
          } else {
            this.msgSuccess("收藏失败！");
          }
        });
      }
		}
	}
</script>

<style scoped>
  .collect {
    height: 36px;
  }
  .active {
    color: #ef4238
  }
</style>
