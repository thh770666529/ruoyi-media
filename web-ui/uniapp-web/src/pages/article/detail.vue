<template>
	<view class="content">
		<view class="banner" auto-focus>
			<cover-image class="controls-pause img" :src="banner.images"></cover-image>
			<view class="title-area">
				<text class="title-text">{{banner.title}}</text>
			</view>
		</view>
		<view class="article-meta">
			<view class="cu-capsule round">
				<view class="cu-tag bg-blue sm">
					<text class="cuIcon-peoplefill"></text>
				</view>
				<view class="cu-tag line-blue sm ">
					{{article.author}}
				</view>
			</view>
			<view class="cu-capsule round">
				<view class="cu-tag bg-mauve sm">
					<text class="cuIcon-file"></text>
				</view>
				<view class="cu-tag line-mauve sm">
					{{article.isOriginal == 1? `原创`:`转载`}}
				</view>
			</view>
			<view class="cu-capsule round">

				<view class="cu-tag bg-orange sm">
					<text class="cuIcon-attentionfill"></text>
				</view>
				<view class="cu-tag line-orange sm">
					{{article.clickCount}}
				</view>
			</view>
			<view class="cu-capsule round">
				<view class="cu-tag bg-red sm">
					<text class="cuIcon-appreciatefill"></text>
				</view>
				<view class="cu-tag line-red sm ">
					{{article.collectCount}}
				</view>
			</view>

			<view class="cu-capsule round">
				<view class="cu-tag  bg-blue sm">
					<text class="cuIcon-timefill"></text>
				</view>
				<view class="cu-tag line-gray sm">
					{{banner.datetime}}
				</view>
			</view>

		</view>
		<jyf-parser class="ck-content" :html="article.content" lazy-load ref="article" selectable show-with-animation
			use-anchor>加载中...</jyf-parser>
		<view class="comment-wrap"></view>
	</view>
</template>

<script>
	import htmlParser from '../../utils/html-parser'
	import {
		getArticle
	} from '@/api/blog/article.js';
	const FAIL_CONTENT = '<p>获取信息失败1</p>';
	import jyfParser from "@/components/jyf-parser/jyf-parser";

	export default {
		data() {
			return {
				banner: {},
				article: {}
			}
		},
		components: {
			jyfParser
		},
		onShareAppMessage() {
			return {
				title: this.banner.title,
				path: '/pages/detail/detail?query=' + JSON.stringify(this.banner)
			}
		},
		onLoad(event) {
			this.banner = JSON.parse(event.query)
			this.load(event.query);
		},
		methods: {
			load(e) {
				console.log(uni.getSystemInfoSync().windowWidth)
				this.getDetail();
			},
			getDetail() {
				getArticle(this.banner.articleId).then(res => {
					this.article = res.data
				})
			}
		}
	}
</script>

<style scoped>
	/* #ifndef APP-PLUS */
	page {
		min-height: 100%;
	}

	/* #endif */

	.banner {
		height: 180px;
		position: relative;
		background-color: #ccc;
		flex-direction: row;
		overflow: hidden;
	}

	.banner-img {
		flex: 1;
	}

	.title-area {
		position: absolute;
		left: 15px;
		right: 15px;
		bottom: 15px;
		z-index: 11;
	}

	.title-text {
		font-size: 16px;
		font-weight: 400;
		line-height: 20px;
		lines: 2;
		color: #ffffff;
		overflow: hidden;
	}

	.article-meta {
		padding: 10px 15px;
		flex-direction: row;
		align-items: center;
		justify-content: flex-start;
		display: inline-flex;
	}

	.article-meta-text {
		color: gray;
	}

	.article-text {
		font-size: 12px;
		line-height: 25px;
		margin: 0 10px;
	}

	.article-time {
		font-size: 15px;
	}

	.article-content {
		font-size: 15px;
		padding: 0 15px;
		margin-bottom: 15px;
		overflow: hidden;
	}

	/* #ifdef H5 */

	.article-content {
		line-height: 1.8;
	}

	.article-content img {
		max-width: 100%;
	}

	.round {
		border-radius: 5000upx;
	}


	.cu-tag {
		font-size: 20upx;
		position: relative;
		display: inline-flex;
		align-items: center;
		justify-content: center;
		box-sizing: border-box;
		padding: 0upx 10upx;
		height: 48upx;
		font-family: Helvetica Neue, Helvetica, sans-serif;
		white-space: nowrap;
	}


	.cu-capsule {
		position: relative;
		display: block;
	}

	/* #endif */
</style>
