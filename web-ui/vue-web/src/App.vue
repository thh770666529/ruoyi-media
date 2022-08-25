<template>
  <div id="vue_web">
    <router-view />
    <loginForm></loginForm>
    <img-preview></img-preview>
  </div>
</template>
<script>
  import loginForm from '@/components/LoginForm'
  import imgPreview from '@/components/ImgPreview'
  import Vue from "vue";
  import CodeCopy from "@/components/CodeCopy";
  export default  {
    data() {
      return {
      }
    },
    updated() {
      setTimeout(() => {
        // 将class类包含 pre的，加上 code-copy-added，代码高亮 可以右上角进行复制
        document.querySelectorAll('pre').forEach(el => {
          if (el.classList.contains('code-copy-added')) {
            return
          }
          //   https://cn.vuejs.org/v2/api/index.html#Vue-extend
          /* 使用基础 Vue 构造器，创建一个“子类”。参数是一个包含组件选项的对象 */
          let ComponentClass = Vue.extend(CodeCopy)
          let instance = new ComponentClass()
          instance.code = el.innerText
          instance.parent = el
          /* 手动挂载 */
          instance.$mount()
          el.classList.add('code-copy-added')
          el.appendChild(instance.$el)
        })
      }, 500)
    },
    components: {
      loginForm,
      imgPreview
    },
    computed: {
    }
  }
</script>
<style lang="less" module>
.header {
  margin: 1.5em;
  text-align: center;
  a {
    margin: 0 0.3em;
    color: inherit;
    font-weight: bold;
    &:global(.router-link-exact-active) {
      color: #42b983;
    }
  }
}
</style>

<style lang="scss" >
.codeContent {
  max-width: 400px;
  margin: 0 auto;
  padding-top: 25vh;
}
.code-copy-added {
  background-color: #282c34;
  color: white;
  padding: 2px 2px;
  margin: 10px 0;
  text-align: left;
  border-radius: 3px;
  position: relative;
}
.code-copy-added:hover .copy-btn {
  opacity: 1;
}
</style>
