<template>
  <div>
    <el-image
      fit="cover"
      :alt="alt"
      :preview-src-list="realSrcList"
      :key="index"
      v-for="(imageUrl, index) in realSrcList"
      :src="imageUrl"
      :style="`width:${realWidth};height:${realHeight};margin: 0 8px 8px 0;`"
    >
      <div slot="error" class="image-slot">
        <i class="el-icon-picture-outline"></i>
      </div>
    </el-image>
  </div>
</template>

<script>
import { isExternal } from "@/utils/validate";

export default {
  name: "ImagePreview",
  props: {
    src: {
      type: String,
      default: ""
    },
    width: {
      type: [Number, String],
      default: ""
    },
    height: {
      type: [Number, String],
      default: ""
    },
    alt: {
      type: String,
      default: ""
    }
  },
  computed: {
    realSrcList() {
      let srcList = [];
      if (!this.src) {
        return srcList;
      }
      let real_src_list = this.src.split(",");
      real_src_list.forEach(item => {
        if (isExternal(item)) {
          return srcList.push(item);
        }
        return srcList.push(this.fileUploadHost + item);
      });
      return srcList;
    },
    realWidth() {
      return typeof this.width == "string" ? this.width : `${this.width}px`;
    },
    realHeight() {
      return typeof this.height == "string" ? this.height : `${this.height}px`;
    }
  },
};
</script>

<style lang="scss" scoped>
.el-image {
  border-radius: 5px;
  background-color: #ebeef5;
  box-shadow: 0 0 5px 1px #ccc;
  ::v-deep .el-image__inner {
    transition: all 0.3s;
    cursor: pointer;
    &:hover {
      transform: scale(1.2);
    }
  }
  ::v-deep .image-slot {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    color: #909399;
    font-size: 30px;
  }
}
</style>
