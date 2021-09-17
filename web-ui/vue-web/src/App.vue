<template>
  <div id="vue_web">
    <router-view />
  </div>
</template>

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
