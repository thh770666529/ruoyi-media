<template>
  <div>
    <label>ckplayer播放器插件Vue使用Demo</label>
    <script charset="utf-8" type="text/javascript" src="http://localhost/ckplayer/ckplayer.js" />
    <vueAliplayer ref="player"  style=" width: 600px; height: 400px;" />
    <button @click="setFull">全屏</button>
    <button @click="player2">播放</button>
  </div>
</template>

<script>
  import vueAliplayer from '@/.nuxt/components/player.vue'
  import movieApi from '@/api/movie'
  export default {
    name: 'ckplayerPlugin',
    components: {
      vueAliplayer: vueAliplayer
    },
    data () {
      return {
      }
    },
    created(){

    },
    mounted(){
      this.$nextTick(() => {
        this.player();
      });
    },
    methods:{
      player() {
        this.$refs.player.loadPlayer('http://ckplayer-video-sample.oss-cn-shanghai.aliyuncs.com/sample-mp4/d30e02a5626c066.mp4');
        this.$refs.player.load('http://ckplayer-video-sample.oss-cn-shanghai.aliyuncs.com/sample-mp4/d30e02a5626c066.mp4');
        // this.$refs.player2.loadPlayer('http://hls01open.ys7.com/openlive/b374fade33b84c34822e8d54cb80d34b.m3u8');

      },
      setFull() {

      },
      player2(){
        this.$refs.player.pause()
      }
    }
  }
</script>

<style scoped>
  #video {
    width: 600px;
    height: 400px;
    margin: 0px auto;
  }
</style>
