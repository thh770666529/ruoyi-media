<template>
	<div class="tag">
		<el-card class="box-card">
			<div slot="header" class="d-flex align-items-center">
				<img class="card-icon" src="../../assets/img/article/biaoqian.png" />
				<span>标签</span>
			</div>
			<div class="text item">
        <template v-for="(item, index) in tagList">
          <span
            style="margin-left: 3px"
            v-if="item.listClass == 'default' || item.listClass == ''"
            :key="item.dictValue"
            :index="index"
            :class="item.cssClass">{{ item.content }}
              </span>
          <el-tag
            v-else
            style="margin-left: 3px"
            :key="item.dictValue"
            :index="index"
            :type="item.listClass == 'primary' ? '' : item.listClass"
            :class="item.cssClass">
            {{ item.content }}
          </el-tag>
        </template>
			</div>
		</el-card>
	</div>
</template>

<script>
  import { listTag } from "@/api/blog/tag";
	export default {
		name: 'tag',
    data() {
      return {
        tagList: []
      }
    },
    created() {
      listTag({status: '1', pageNum: 1, pageSize: 100}).then(response => {
        this.tagList = response.rows;
      });
    },
		methods: {
			tag(name) {
				this.$router.push({
					name: 'tag',
					params: {
						'name': name
					}
				});
			}
		}
	}
</script>

<style scoped>
	.box-card .item:hover {
		color: #409EFF;
		cursor: pointer;
	}

	.box-card span {
		font-weight: bold;
	}

	.card-icon {
		width: 20px;
		height: 20px;
		margin-right: 10px;
	}

	.tag-item {
		margin-right: 10px;
	}
</style>
