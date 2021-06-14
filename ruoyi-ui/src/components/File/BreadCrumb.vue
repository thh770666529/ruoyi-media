<template>
  <div class="breadcrumb-wrapper">
    <!--<div class="title">当前位置：</div>-->
    <el-breadcrumb>
      <el-breadcrumb-item>
        当前位置：
      </el-breadcrumb-item>
    </el-breadcrumb>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item
        :to="routerIndex(fileType)">
        {{ fileTypeMap[fileType] }}
      </el-breadcrumb-item>
    </el-breadcrumb>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item v-for="(item, index) in breadCrumbList" :key="index" :to="getRouteQuery(item)">{{
        item.name
      }}</el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>
<script>
export default {
  name: 'BreadCrumb',
  props: {
    // 文件类型
    fileType: {
      required: true,
      type: Number
    }
  },
  data() {
    return {
      fileTypeMap: {
        0: '全部',
        1: '全部图片',
        2: '全部文档',
        3: '全部视频',
        4: '全部音乐',
        5: '其他',
        6: '回收站'
      }
    }
  },
  computed: {
    /**
     * 面包屑导航栏数组
     */
    breadCrumbList: {
      get() {
        let filePath = this.$route.query.filePath
        let filePathList = filePath ? filePath.split('/') : []
        let res = [] //  返回结果数组
        let _path = [] //  存放祖先路径
        for (let i = 0; i < filePathList.length; i++) {
           if (i === 0) {
            //  根目录
            filePathList[i] = '/'
            _path.push(filePathList[i])
            res.push({
              //path: '/',
              path: this.$router.path,
              name: this.$route.meta.breadCrumbName
            })
          }else if (filePathList[i]) {
            _path.push(filePathList[i] + '/')
            res.push({
              path: _path.join(''),
              name: filePathList[i]
            })
          }
        }
        return res
      },
      set() {
        return []
      }
    }
  },
  methods: {
    routerIndex(fileType){
      //路由首页
      let path = this.$router.path
      return{
        query: {
          filePath: '/', fileType: fileType
        },
        path:path
      }
    },
    // 获取文件参数
    getRouteQuery(item) {
      let routeName = this.$route.name
      if (routeName === 'Share') {
        // 当前是查看他人分享列表的页面
        return { query: { filePath: item.path } }
      } else if (routeName === 'MyShare') {
        // 当前是我的已分享列表页面
        return {
          query: {
            filePath: item.path?item.path:'/',
            shareBatchNum: item.path === '/' ? undefined : this.$route.query.shareBatchNum  //  当查看的是根目录，批次号置空
          }
        }
      } else {
        // 网盘页面
        return { query: { filePath: item.path, fileType: this.fileType } }
      }
    }
  }
}
</script>

<style lang="stylus" scoped>
.breadcrumb-wrapper {
  padding: 0;
  height: 30px;
  line-height: 30px;
  display: flex;

  .title, >>> .el-breadcrumb {
    height: 30px;
    line-height: 30px;
    float:left;
  }
}
</style>
