<template>
	<view>
		<view class="video">
			<!--<video class="video-movie" src="https://media.w3.org/2010/05/sintel/trailer.mp4"
				poster="https://img2.baidu.com/it/u=1776766142,680351088&fm=26&fmt=auto&gp=0.jpg" controls></video>-->
			<video class="video-movie" :src="$fileUploadHost + video.url"
				:poster="initData.images" controls></video>
		</view>
		<view class="video-wapper">
			<navigator :url="'./cover/cover?coverId=' + 123 ">
				<!--<image class="view-img" src="https://img1.baidu.com/it/u=488054824,781673712&fm=26&fmt=auto&gp=0.jpg"
					mode="aspectFill"></image>-->
				<image class="view-img" :src="initData.images"
					   mode="aspectFill"></image>
			</navigator>
			<view class="video-info">
				<view class="video-title">{{initData.title}}</view>
				<view class="video-desc">{{ $parseTime(initData.publishTime, '{y}') }}/{{initData.countryName}}/科幻/超级英雄</view>
				<view class="video-desc">影片时长：143分钟</view>
				<view class="video-desc">上映时间：{{ $parseTime(initData.publishTime, '{y}-{m}-{d}') }}</view>
				<view class="score-block">
					<view class="score-big">
						<view class="vide-star">综合评分:</view>
						<view class="score-score">{{initData.rate}}</view>
					</view>

					<view class="score-like">
						<view class="like-like">9631人点赞</view>
					</view>

				</view>
			</view>
		</view>

		<view class="line-wapper">
			<view class="line"></view>
		</view>

		<view class="plots-block">
			<view class="plots-title">剧情介绍</view>
			<view class="plots-desc" v-html="movie.description">
			</view>
		</view>

		<view class="photo-block">
			<view class="photo-title">演员列表</view>
			<scroll-view scroll-x="true" class="photo-list">
				<view class="actor-block" v-for="(item, index) in actorList">
					<image class="actor-actor" :src="$fileUploadHost + item.avatar" mode="aspectFill"></image>
					<view class="actor-title">{{item.name}}</view>
					<view class="actor-role">{{item.type === 'actor'?`演员`:`导演`}}</view>
				</view>
			</scroll-view>
		</view>

		<view class="photo-block">
			<view class="photo-title">剧照</view>
			<scroll-view scroll-x="true" class="photo-list">
				<image @click="previewPhoto" :data-imgid="index" v-for="(img,index) in imgs" class="photo-photo"
					:src="img" mode="aspectFill"></image>
			</scroll-view>

		</view>

	</view>
</template>

<script>
	import movieApi from '@/api/media/movie';
	export default {
		components: {},
		data() {
			return {
				initData: {},
				movie: {},
				videoList: [],
				actorList: [],
				directorList: [],
				video: {},
				imgs: ['https://img1.baidu.com/it/u=488054824,781673712&fm=26&fmt=auto&gp=0.jpg',
					'https://img2.baidu.com/it/u=1804272601,468942649&fm=26&fmt=auto&gp=0.jpg',
					'https://img0.baidu.com/it/u=3753972264,2197167502&fm=26&fmt=auto&gp=0.jpg',
					'https://img0.baidu.com/it/u=3033541366,2843962344&fm=26&fmt=auto&gp=0.jpg',
					'https://img2.baidu.com/it/u=1943521773,1045531091&fm=26&fmt=auto&gp=0.jpg',
				]
			}
		},
		onLoad(event) {
			this.initData = JSON.parse(event.query)
			this.load(event.query);
		},
		onShareAppMessage(res) {
			return {
				title: this.initData.title,
				path: '/pages/movie/detail?query=' + JSON.stringify(this.initData)
			}
		},
		onNavigationBarButtonTap(par) {
			if (par.type == 'share') {
				uni.share({
					provider: "weixin",
					scene: "WXSenceTimeline",
					type: 0,
					href: "http://uniapp.dcloud.io/",
					title: "uni-app分享",
					summary: "我正在使用HBuilderX开发uni-app，赶紧跟我一起来体验！",
					imageUrl: "https://bjetxgzv.cdn.bspapp.com/VKCEYUGU-uni-app-doc/d8590190-4f28-11eb-b680-7980c8a877b8.png",
					success: function(res) {
						console.log("success:" + JSON.stringify(res));
					},
					fail: function(err) {
						console.log("fail:" + JSON.stringify(err));
					}
				});

			}

			if (par.type == 'home') {
				uni.switchTab({
					url: "../index/index"
				})
			}

		},
		methods: {
			load(e) {
				this.getDetail();
			},
			getDetail() {
				movieApi.getMovie(this.initData.movieId).then(response => {
					this.movie = response.data;
					this.videoList = this.movie.videoList;
					this.actorList = this.movie.actorList;
					this.directorList = this.movie.directorList;
					if (this.videoList.length > 0) {
						this.video = this.videoList[0]
					}
					console.log(this.video)
				});
			},
			previewPhoto(e) {
				var id = e.currentTarget.dataset.imgid;

				console.log(e)
				uni.previewImage({
					urls: this.imgs,
					current: this.imgs[id]
				})
			},
			showImg(e) {
				uni.navigateTo({
					url: "./cover/cover"
				})
			}
		}
	}
</script>

<style>
	@import url("movie.css");
</style>
