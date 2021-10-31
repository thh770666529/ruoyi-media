<!-- 课程班级 -->
<template>
	<view>
		<view class="wrap">
			<u-swiper :list="bannerList" img-mode="scaleToFill" mode="dot"></u-swiper>
			<view class="u-tabs-box">
				<u-tabs-swiper :activeColor="activeColor" ref="tab" :list="tabList" :current="current" @change="change"
					:is-scroll="false" swiperWidth="750"></u-tabs-swiper>
			</view>
			<swiper class="swiper-box">
				<swiper-item class="swiper-item">
					<scroll-view scroll-y style="height: 100%;width: 100%;" @scrolltolower="reachBottom">
						<view class="page-box">
							<view class="article" v-for="(item, index) in articleList" :key="index">
								<view class="top">
									<view class="left">
										<uni-text v-if="item.level == 1" class="topicon">置顶</uni-text>
										<uni-text class="cuIcon-titles text-blue"></uni-text>
										<uni-text @click="goDetail(item)" class="store">{{item.title | ellipsis(10)}}
										</uni-text>
										<u-icon name="arrow-right" color="rgb(203,203,203)" :size="26"></u-icon>
									</view>
									<view class="right">
										<view class="progressBox text-white">
											{{item.categoryName ? item.categoryName:`无`}}
										</view>
									</view>
								</view>
								<view class="item">
									<view class="left" @click="goDetail(item)">
										<image :src="item.images" mode="aspectFill"></image>
									</view>
									<view class="content">
										<view @click="goDetail(item)" class="title u-line-2 text-bold">{{item.title}}
										</view>
										<view class="type">{{item.summary}}</view>
									</view>
								</view>
								<u-line color="#f1f1f1" margin="24rpx 0 15rpx 0"></u-line>
								<view class="bottom">
									<view class="cu-capsule round">
										<view class="cu-tag bg-blue sm">
											<text class="cuIcon-peoplefill"></text>
										</view>
										<view class="cu-tag line-blue sm ">
											{{item.author}}
										</view>
									</view>
									<view class="cu-capsule round">
										<view class="cu-tag bg-mauve sm">
											<text class="cuIcon-file"></text>
										</view>
										<view class="cu-tag line-mauve sm">
											{{item.isOriginal == 1? `原创`:`转载`}}
										</view>
									</view>
									<view class="cu-capsule round">

										<view class="cu-tag bg-orange sm">
											<text class="cuIcon-attentionfill"></text>
										</view>
										<view class="cu-tag line-orange sm">
											{{item.clickCount}}
										</view>
									</view>
									<view class="cu-capsule round">
										<view class="cu-tag bg-red sm">
											<text class="cuIcon-appreciatefill"></text>
										</view>
										<view class="cu-tag line-red sm ">
											{{item.collectCount}}
										</view>
									</view>

									<view class="cu-capsule round">
										<view class="cu-tag  bg-blue sm">
											<text class="cuIcon-timefill"></text>
										</view>
										<view class="cu-tag line-gray sm">
											{{item.datetime}}
										</view>
									</view>

								</view>
							</view>
							<u-loadmore bg-color="rgb(240, 240, 240)" :status="loadStatus" :load-text="loadText"
								@loadmore="loadmore"></u-loadmore>
						</view>
					</scroll-view>
				</swiper-item>

			</swiper>
		</view>
	</view>
</template>

<script>
	import {
		listCategory
	} from '@/api/blog/category.js'
	import {
		getArticleList
	} from '@/api/blog/article.js';
	import {
		friendlyDate
	} from '@/utils/util.js';
	export default {
		data() {
			return {
				bannerList: [],
				tabList: [],
				queryParams: {
					pageNum: 1,
					pageSize: 10
				},
				categoryId: '',
				activeColor: '#0081ff',
				articleList: [],
				current: 0,
				loadText: {
					loadmore: '轻轻上拉',
					loading: '努力加载中',
					nomore: '实在没有了'
				},
				loadStatus: 'loadmore'

			};
		},
		onLoad() {
			listCategory({}).then(res => {
				this.tabList = res.rows;
			});
			const bannerQueryParams = {
				pageNum: 1,
				pageSize: 5,
				level: 1
			}
			getArticleList(bannerQueryParams).then(response => {
				const data = response.rows;
				const data_list = data.map((article) => {
					return {
						title: article.title,
						image: this.$fileUploadHost + article.images
					};
				});
				this.bannerList = data_list

			});
			this.getList();
		},
		computed: {},
		created() {
			this._isWidescreen = false;
			// #ifdef H5
			var mediaQueryOb = uni.createMediaQueryObserver(this)
			mediaQueryOb.observe({
				minWidth: 768
			}, matches => {
				this._isWidescreen = matches;
			})
			// #endif
		},
		methods: {
			loadmore() {
				this.queryParams.pageNum = this.queryParams.pageNum + 1;
				this.getList();
			},
			goDetail(detail) {
				if (this._isWidescreen) { //若为宽屏，则触发右侧详情页的自定义事件
					uni.$emit('updateDetail', {
						detail: encodeURIComponent(JSON.stringify(detail))
					})
				} else {
					uni.navigateTo({
						url: './detail?query=' + encodeURIComponent(JSON.stringify(detail))
					});
				}
			},
			reachBottom() {
				this.loadStatus = "loading"
				setTimeout(() => {
					this.loadmore();
				}, 1200);
			},
			// 页面数据
			getList() {
				this.queryParams.categoryId = this.categoryId
				getArticleList(this.queryParams).then(res => {
					const data = res.rows
					const data_list = data.map((article) => {
						return {
							articleId: article.articleId,
							title: article.title,
							categoryId: article.categoryId,
							categoryName: article.categoryName,
							author: article.author,
							datetime: friendlyDate(new Date(article.createTime.replace(/\-/g, '/'))
								.getTime()),
							createTime: article.createTime,
							images: this.$fileUploadHost + article.images,
							summary: article.summary,
							clickCount: article.clickCount,
							collectCount: article.collectCount,
							isOriginal: article.isOriginal,
							level: article.level
						};
					});

					this.articleList = this.articleList.concat(data_list);
				});
				this.loadStatus = "loadmore"
			},
			// tab栏切换
			change(index) {
				this.current = index;
				this.categoryId = this.tabList[index].categoryId;
				this.articleList = []
				this.queryParams = {
					pageNum: 1,
					pageSize: 10,
					categoryId: this.categoryId
				}
				this.getList();
			}
		}
	};
</script>

<style lang="scss" scoped>
	/* #ifndef H5 */
	page {
		height: 100%;
		background-color: #f2f2f2;
	}

	/* #endif */
</style>

<style lang="scss" scoped>
	.article {
		width: 710rpx;
		background-color: #ffffff;
		margin: 20rpx auto;
		border-radius: 7rpx;
		box-sizing: border-box;
		padding: 18rpx;
		font-size: 28rpx;

		.top {
			display: flex;
			justify-content: space-between;

			.left {
				display: flex;
				align-items: center;
				width: 60%;

				.store {
					margin: 0 10rpx;
					font-size: 30rpx;
					font-weight: bold;
				}
			}

			.right {
				width: 40%;
				position: relative;
				float: right;

				.progressBox {
					position: absolute;
					top: -17rpx;
					right: -17rpx;
					border: 2upx solid currentColor;
					padding: 12rpx;
					box-sizing: border-box;
					border-top-right-radius: 7rpx;
					background-color: $u-type-primary;
					color: white;
				}
			}
		}

		.item {
			display: flex;
			margin: 20rpx 0 0;

			.left {
				margin-right: 20rpx;

				image {
					width: 260rpx;
					height: 190rpx;
					border-radius: 10rpx;
				}
			}

			.content {
				.title {
					font-size: 28rpx;
					line-height: 45rpx;
				}

				.type {
					margin: 6rpx 0;
					font-size: 24rpx;
					color: $u-tips-color;
					text-overflow: -o-ellipsis-lastline;
					overflow: hidden;
					text-overflow: ellipsis;
					display: -webkit-box;
					-webkit-line-clamp: 3;
					line-clamp: 3;
					-webkit-box-orient: vertical;
				}

				.delivery-time {
					color: #0081ff;
					font-size: 24rpx;
				}
			}

			.right {
				margin-left: 10rpx;
				padding-top: 20rpx;
				text-align: right;

				.decimal {
					font-size: 24rpx;
					margin-top: 4rpx;
				}

				.number {
					color: $u-tips-color;
					font-size: 24rpx;
				}
			}
		}

		.bottom {
			line-height: 70rpx;
			display: flex;
			justify-content: space-between;
			align-items: center;

			.btnBox {
				width: 150rpx;
				display: flex;
				justify-content: space-between;

				.btn {
					line-height: 52rpx;
					width: 140rpx;
					border-radius: 12rpx;
					border: 2rpx solid $u-tips-color;
					font-size: 26rpx;
					text-align: center;
					color: $u-tips-color;
				}

				.evaluate {
					color: $u-type-primary;
					border-color: $u-type-primary;
				}
			}
		}
	}

	.centre {
		text-align: center;
		margin: 200rpx auto;
		font-size: 32rpx;

		image {
			width: 300rpx;
			border-radius: 50%;
			margin: 0 auto;
		}

		.tips {
			font-size: 24rpx;
			color: #999999;
			margin-top: 20rpx;
		}

		.btn {
			margin: 80rpx auto;
			width: 200rpx;
			border-radius: 32rpx;
			line-height: 64rpx;
			color: #ffffff;
			font-size: 26rpx;
			background: linear-gradient(270deg, #1cbbb4 0%, #0081ff 100%);
		}
	}

	.wrap {
		display: flex;
		flex-direction: column;
		height: calc(100vh - var(--window-top));
		width: 100%;
	}

	.swiper-box {
		flex: 1;
	}

	.swiper-item {
		height: 100%;
	}

	.topicon {
		color: white;
		position: relative;
		top: -1px;
		background: #ff503f;
		padding: 0 6px;
		border-radius: 12px;
		top: -1px;
	}

	.ksd-iconzhiding {
		font-size: 42px;
		position: absolute;
		top: 10px;
		right: 10px;
		transform: rotate(90deg);
		color: #F44336;
	}
</style>
