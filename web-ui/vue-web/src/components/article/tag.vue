<template>
	<div class="tag">
		<el-card class="box-card">
			<div slot="header" class="d-flex align-items-center">
				<img class="card-icon" src="../../assets/img/article/biaoqian.png" />
				<span>标签</span>
			</div>
			<div class="text item">
        <el-tag
          style="margin: 0 0 5px 3px"
          :index="index"
          @click="getListByTag('')">
          全部
        </el-tag>
        <template v-for="(item, index) in tagList">
          <span
            style="margin: 0 0 5px 3px"
            v-if="item.listClass == 'default' || item.listClass == ''"
            :key="item.dictValue"
            :index="index"
            @click="getListByTag(item.tagId)"
            :class="item.cssClass">{{ item.content }}
              </span>
          <el-tag
            v-else
            style="margin: 0 0 5px 3px"
            :key="item.dictValue"
            :index="index"
            @click="getListByTag(item.tagId)"
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
    props: ["tagId"],
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
			getListByTag(tagId) {
        this.$emit("change", tagId);
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
