<template>
  <div id="vue_web">
    <router-view />
    <loginForm></loginForm>
  </div>
</template>
<script>
  import loginForm from '@/components/LoginForm'
  export default  {
    data() {
      return {
      }
    },
    components: {
      loginForm
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
