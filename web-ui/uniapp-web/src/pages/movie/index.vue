<template>
	<view class="wrap">
		<u-waterfall v-model="flowList" ref="uWaterfall">
			<template v-slot:left="{leftList}">
				<view class="demo-warter" v-for="(item, index) in leftList" :key="index">
					<!-- 警告：微信小程序中需要hx2.8.11版本才支持在template中结合其他组件，比如下方的lazy-load组件 -->
					<u-lazy-load threshold="-450" border-radius="10" @click="goDetail(item)" :image="item.images" :index="index"></u-lazy-load>
					<view class="demo-title" @click="goDetail(item)">
						{{item.title}}
					</view>

					<template v-for="(tagItem, index) in tagOptions">
						<template v-if="getTagArray(item.tagId).includes(String(tagItem.tagId))">
							<view style="margin: 5px 5px 0 0"
								v-if="tagItem.listClass == 'default' || tagItem.listClass == ''" :key="tagItem.tagId"
								:index="index" :class="tagItem.cssClass">{{ tagItem.content }}
							</view>
							<u-tag v-else  shape="circle" size="mini" mode="plain"  :text="tagItem.content" style="margin: 5px 5px 0 0" :key="tagItem.tagId"
								:index="index" :type="tagItem.listClass === 'primary' ? '' : tagItem.listClass"
								:class="tagItem.cssClass? tagItem.cssClass: ''" />
						</template>
					</template>

					<view class="demo-shop">
						{{$selectDictLabel(countryOptions, item.country)}}
						{{actorFormatter(item.actorList) | ellipsis(8)}}
					</view>
					<u-icon name="close-circle-fill" color="#fa3534" size="34" class="u-close" @click="remove(item.id)">
					</u-icon>
				</view>
			</template>
			<template v-slot:right="{rightList}">
				<view class="demo-warter" v-for="(item, index) in rightList" :key="index">
					<u-lazy-load threshold="-450" @click="goDetail(item)" border-radius="10" :image="item.images" :index="index"></u-lazy-load>
					<view class="demo-title" @click="goDetail(item)" >
						{{item.title}}
					</view>
					<template v-for="(tagItem, index) in tagOptions">
						<template v-if="getTagArray(item.tagId).includes(String(tagItem.tagId))">
							<view style="margin: 5px 5px 0 0"
								  v-if="tagItem.listClass == 'default' || tagItem.listClass == ''" :key="tagItem.tagId"
								  :index="index" :class="tagItem.cssClass">{{ tagItem.content }}
							</view>
							<u-tag v-else  shape="circle" size="mini" mode="plain" :text="tagItem.content" style="margin: 5px 5px 0 0" :key="tagItem.tagId"
								   :index="index" :type="tagItem.listClass === 'primary' ? '' : tagItem.listClass"
								   :class="tagItem.cssClass? tagItem.cssClass: ''" />
						</template>
					</template>
					<view class="demo-shop">
						{{$selectDictLabel(countryOptions, item.country)}}
						{{actorFormatter(item.actorList) | ellipsis(8)}}
					</view>
					<u-icon name="close-circle-fill" color="#fa3534" size="34" class="u-close" @click="remove(item.id)">
					</u-icon>
				</view>
			</template>
		</u-waterfall>
		<u-loadmore bg-color="rgb(240, 240, 240)" :status="loadStatus" :load-text="loadText" @loadmore="addRandomData"></u-loadmore>
	</view>
</template>

<script>
	import movieApi from '@/api/media/movie';
	import {
		listTag
	} from "@/api/media/tag";
	import {
		listCategory
	} from "@/api/media/category";
	import {
		getDictsByTypeList
	} from "@/api/system/dict/data";
	export default {
		data() {
			return {
				// 字典
				countryOptions: [],
				tagOptions: [],
				categoryOptions: [],
				queryParams: {
					pageNum: 0,
					pageSize: 12,
					images: null,
					title: null,
					movieType: null,
					country: '',
					tagId: null,
					description: null,
					publishBy: null,
					publishTime: null,
					status: null,
					categoryId: '',
					orderByColumn: 'createTime',
					isAsc: 'desc',
					publishYear: ''
				},
				loadText: {
					loadmore: '轻轻上拉',
					loading: '努力加载中',
					nomore: '实在没有了'
				},
				loadStatus: 'loadmore',
				flowList: [],
				total: 0
			}
		},
		onLoad() {
			const dictTypeList = ['movie_country'];
			getDictsByTypeList(dictTypeList).then(response => {
				this.countryOptions = response.data.movie_country;
			});
			listCategory({
				status: '1'
			}).then(response => {
				this.categoryOptions = response.rows;
			});
			listTag({
				status: '1'
			}).then(response => {
				this.tagOptions = response.rows;
			});
			this.addRandomData();
		},
		onReachBottom() {
			this.loadStatus = 'loading';
			// 模拟数据加载
			setTimeout(() => {
				this.addRandomData();
			}, 1000)
		},
		methods: {
			addRandomData() {
				this.queryParams.pageNum = this.queryParams.pageNum + 1
				movieApi.listMovie(this.queryParams).then(response => {
					const rows = response.rows
					this.total = response.total
					const readyTotal = this.queryParams.pageSize * this.queryParams.pageNum
					this.loadStatus = 'nomore';
					if (this.total === 0 || readyTotal > this.total) {
						this.loadStatus = 'nomore';
						return;
					} else {
						this.loadStatus = 'loadmore';
					}
					for (let i = 0; i < rows.length; i++) {
						// 先转成字符串再转成对象，避免数组对象引用导致数据混乱
						let item = rows[i]
						item.images = this.$fileUploadHost + item.images
						item.id = rows[i].movieId;
						this.flowList.push(item);
					}
				})

			},
			remove(id) {
				this.$refs.uWaterfall.remove(id);
			},
			clear() {
				this.$refs.uWaterfall.clear();
			},
			actorFormatter(actorList) {
				if (!actorList || actorList.length === 0) {
					return '无';
				}
				const currentSeparator = ",";
				let actions = [];
				for (let index = 0; index < actorList.length; index++) {
					actions.push(actorList[index].name + currentSeparator);
				}
				return actions.join('').substring(0, actions.join('').length - 1);
			},
			//标签翻译
			getTagArray(tagId) {
				if (!tagId) {
					return []
				}
				return tagId.split(',');
			},
			goDetail(detail) {
				const countryName = this.$selectDictLabel(this.countryOptions, detail.country)
				const object = {
					movieId: detail.movieId,
					title: detail.title,
					images: detail.images,
					rate: detail.rate,
					country: detail.country,
					countryName: countryName,
					publishTime: detail.publishTime
				}
				uni.navigateTo({
					url: './detail?query=' + encodeURIComponent(JSON.stringify(object))
				});
			}
		}
	}
</script>

<style>
	/* page不能写带scope的style标签中，否则无效 */
	page {
		background-color: rgb(240, 240, 240);
	}
</style>

<style lang="scss" scoped>
	.demo-warter {
		border-radius: 8px;
		margin: 5px;
		background-color: #ffffff;
		padding: 8px;
		position: relative;
	}

	.u-close {
		position: absolute;
		top: 32rpx;
		right: 32rpx;
	}

	.demo-image {
		width: 100%;
		border-radius: 4px;
	}

	.demo-title {
		font-size: 30rpx;
		margin-top: 5px;
		color: $u-main-color;
	}

	.demo-tag {
		display: flex;
		margin-top: 5px;
	}

	.demo-tag-owner {
		background-color: $u-type-error;
		color: #FFFFFF;
		padding: 4rpx 12rpx;
		border-radius: 50rpx;
		font-size: 20rpx;
		line-height: 1;
		width: 26%;
		float: left;
		margin-right: 10rpx;
	}

	.demo-tag-text {
		border: 1px solid $u-type-primary;
		color: $u-type-primary;
		border-radius: 50rpx;
		line-height: 1;
		padding: 4rpx 10rpx;
		display: flex;
		font-size: 20rpx;
		width: 30%;
	}

	.demo-price {
		font-size: 30rpx;
		color: $u-type-error;
		margin-top: 5px;
	}

	.demo-shop {
		font-size: 22rpx;
		color: $u-tips-color;
		margin-top: 5px;
	}
</style>
