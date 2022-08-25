<template>
  <div class="copy-content">
    <!-- 复制按钮 -->
    <div
      class="copy-btn code-data-copy"
      @click="copyMessage"
      data-clipboard-action="copy"
      :data-clipboard-text="code"
    >
      <svg-icon slot="prefix"  class="copy"  icon-class="copy" />
    </div>
  </div>
</template>

<script>
import ClipboardJS from 'clipboard' //复制插件
export default {
  data() {
    return {
      code: null,
    }
  },
  methods: {
    copyMessage() {
      const _this = this
      let clipboard = new ClipboardJS('.code-data-copy')
      clipboard.on('success', function (e) {
        _this.msgSuccess('复制成功')
        clipboard.destroy() // 销毁,避免多次点击重复出现
      })
      clipboard.on('error', function () {
        _this.msgError('复制失败')
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.copy{
  width: 1.5rem !important;
  height: 1.5rem !important;
  fill: white;
}


.copy-content {
  height: 0;
}

.icon {
  width: 0.4rem;
  height: 0.4rem;
  fill: white;
}

.copy-btn {
  user-select: none;
  opacity: 0;
  position: absolute;
  right: 5px;
  top: 5px;
  cursor: pointer;
  padding: 5px;
  border-radius: 3px;
  transition: 0.3s;
  background: rgba(255, 255, 255, 0.2);

  &:active {
    background: rgba(253, 253, 253, 0.575);
  }
}

@keyframes successCopy {
  70% {
    opacity: 1;
    transform: scale(1);
  }
  100% {
    opacity: 0;
    transform: scale(0.5);
  }
}
</style>
