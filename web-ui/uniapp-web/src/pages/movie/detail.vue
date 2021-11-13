<template>
	<view>
		<view class="video">
			<video class="video-movie" autoplay="true" :title="video.title" :src="$fileUploadHost + video.url"
				poster="initData.images" controls></video>
		</view>
		<view class="video-wapper">
			<navigator :url="initData.images">
				<image class="view-img" :src="initData.images" mode="aspectFill"></image>
			</navigator>
			<view class="video-info">
				<view class="video-title">{{initData.title}}</view>
				<view class="video-desc">{{ $parseTime(initData.publishTime, '{y}') }}/{{initData.countryName}}/科幻/超级英雄
				</view>
				<view class="video-desc">影片总时长：{{formatVideoTime(movie.totalVideoLength)}}</view>
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

		<view class="video-block">
			<view class="margin-bottom-sm flex justify-between">
				<text class="text-bold text-lg">剧集列表</text>
				<text class="text-gray text-df" v-if="videoList.length > 0">共{{videoList.length}}集</text>
			</view>
			<view v-if="videoList.length === 0" style="color:#ef4238"> 暂时没有播放的视频！请联系管理员进行上传！</view>
			<view class="videoBox flex justify-around">
				<view @click="play(index)" :class="active == index ?`active`:``" v-for="(video, index) in videoList">
					{{video.title}}
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
				<image @click="previewPhoto" :data-imgid="index" v-for="(img,index) in movieImagesList" class="photo-photo"
			     :src="img"  mode="aspectFill"></image>
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
				active: 0,
				initData: {},
				movie: {},
				videoList: [],
				actorList: [],
				directorList: [],
				video: {},
				movieImagesList: []
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
		methods: {
			play(index) {
				this.active = index;
				this.video = this.videoList[index]
			},
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
					//获取剧照
					this.getStills(this.movie.stills);
				});
			},
			getStills(val) {
				if (val) {
					let temp = 1;
					// 首先将值转为数组
					const list = val.split(',');
					// 然后将数组转为对象数组
					this.movieImagesList = list.map(item => {
						return this.$fileUploadHost + item;
					});
				} else {
					this.movieImagesList = [];
					return [];
				}
			},
			previewPhoto(e) {
				var index = e.currentTarget.dataset.imgid;
				uni.previewImage({
					urls: this.movieImagesList,
					current: this.movieImagesList[index]
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
