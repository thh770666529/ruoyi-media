<template>
	<div class="playe-wrap" ref="playerWrap" :id="playerId" :style="playStyle"></div>
</template>

<script>
export default {
	name: 'vue-bplayer',
	props: {
		playStyle: {
			// 播放器样式：内联样式
			type: String,
			default: ''
		},
		sdk: {
			// 版本 sdk
			type: String,
			default: 'https://fjycjd_admin.gitee.io/cdn/ckplayer/ckplayer.min.js'
		},
		variable: {
			//调用播放器的函数名称
			type: String,
			default: 'player'
		},
		source: {
			type: String,
			default: ''
		},
		width: {
			// 播放器宽度，可形如‘100%’或者‘100px’
			type: String,
			default: '100%'
		},
		height: {
			// 播放器高度，可形如‘100%’或者‘100’
			type: String,
			default: '100%'
		},
		live: {
			// 是否是直播视频
			type: Boolean,
			default: false
		},
		loop: {
			// 播放结束后是否循环播放
			type: Boolean,
			default: false
		},
		autoplay: {
			// 建议 false : 一些浏览器这样会报错
			// 播放器是否自动播放
			type: Boolean,
			default: false
		},
		poster: {
			// 设置媒体流的预览图
			type: String,
			default: ''
		},
		loaded: {
			// 播放器加载后触发的函数
			type: String,
			default: ''
		},
		volume: {
			// 设置播放器音量大小，范围（0 - 1）
			type: Number,
			default: 0.8
		},
		preview: {
			type: Array,
			default: () => {
				return [];
			}
		}
	},
	data() {
		return {
			playerId:
				'ckplayer_' +
				Math.random()
					.toString(36)
					.substr(2),
			scriptTagStatus: 0,
			instance: null
		};
	},
	mounted() {
    this.loadPlayer(this.source);
  },
	methods: {
		/**
		 * @param {String} url
		 * 加载播放器
		 */
		loadPlayer(url) {
			if (window.ckplayer !== undefined) {
				// 如果全局对象存在，说明编辑器代码已经初始化完成，直接加载编辑器
				this.scriptTagStatus = 2;
				this.initPlayer(url);
			} else {
				// 如果全局对象不存在，说明编辑器代码还没有加载完成，需要加载编辑器代码
				this.insertScriptTag(url);
			}
		},
		/**
		 * @param {String} url
		 * 插入脚本
		 */
		insertScriptTag(url) {
			const _this = this;
			let playerScriptTag = document.getElementById('bPlayerScriptTag');
			// 如果这个tag不存在，则生成相关代码tag以加载代码
			if (playerScriptTag === null) {
				playerScriptTag = document.createElement('script');
				// playerScriptTag.type = 'text/javascript';
				playerScriptTag.src = this.sdk;
				playerScriptTag.id = 'ckPlayerScriptTag';
				let s = document.getElementsByTagName('body')[0];
				s.appendChild(playerScriptTag);
			}
			if (playerScriptTag.loaded) {
				_this.scriptTagStatus++;

				this.$nextTick(() => {
					this.initPlayer(url);
				});
			} else {
				let loadReponse = function() {
					_this.scriptTagStatus = 2;
					playerScriptTag.loaded = true;
					_this.$nextTick(() => {
						_this.initPlayer(url);
					});

					if (playerScriptTag.loaded) {
						playerScriptTag.removeEventListener('load', loadReponse);
						playerScriptTag.loaded = false;
					}
				};
				playerScriptTag.addEventListener('load', loadReponse);
			}
		},
		/**
		 * @param {String} url
		 * 初始化播放器
		 */
		initPlayer(url) {
			let sourceUrl = url ? url : this.source;
			let videoObject = {
				container: '#' + this.playerId,
				variable: this.variable,
				autoplay: this.autoplay,
				video: sourceUrl,
        withCredentials: false,
				live: this.live,
				loop: this.loop,
				poster: this.poster,
				volume: this.volume,
				// preview: this.preview,
				loaded: this.loaded
			};
			/* eslint-disable */
			this.instance = new ckplayer(videoObject);
		},
		/**
		 *直接播放视频url，time为可选值（单位秒）目前只支持同种格式（mp4/flv/m3u8）之间切换暂不支持直播rtmp流切换
		 *@argument url 视频地址
		 */
		loadByUrl: function(url) {
			if(!this.instance){
			  this.msgInfo(url)
				this.loadPlayer(url);
			} else {
        this.msgInfo(url)
				this.load(url)
			}
		},
		/**
		 * 播放视频
		 */
		play: function() {
			this.instance.videoPlay();
		},
    setFull: function() {
      this.instance.setFull();
    },

		/**
		 * 暂停视频
		 */
		pause: function() {
			this.instance.videoPause();
		},
		/**
		 * 切换播放状态
		 */
		togglePlay: function() {
			this.instance.playOrPause();
		},
		/**
		 * 设置音量，vol为0-1的实数，ios和部分android会失效
		 */
		setVolume: function(vol) {
			this.instance.changeVolume(vol);
		},
		/**
		 * 添加监听
		 */
		addListenerPlayer: function(name, callBack) {
			this.instance.addListener(name, callBack);
		},
		/**
		 * 删除监听
		 */
		removeListenerPlayer: function(name, callBack) {
			this.instance.removeListener(name, callBack);
		},
		/**
		 * 通过视频地址加载视频。
		 * 备注：该方法只能加载对应播放模式下支持的视频格式，Flash 模式支持切换 RTMP、FLV、HLS 和 MP4 ，H5 模式支持 MP4、HLS 和 FLV（HLS、FLV 取决于浏览器是否支持）
		 */
		load: function(url) {
			if(typeof url == 'string'){
				this.instance.newVideo({video: url});
			} else if(typeof url == 'object'){
				this.instance.newVideo(url);
			}

		}
	},
	beforeDestroy() {
		// 移除当前播放器
		this.instance = null
	}
};
</script>

<style lang="postcss"></style>
