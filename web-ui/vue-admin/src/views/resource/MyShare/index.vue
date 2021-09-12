<template>
  <div class="my-share-wrapper">
    <div class="operation-wrapper">
      <!-- 面包屑导航栏 -->
      <BreadCrumb class="breadcrumb" :fileType="8"></BreadCrumb>
    </div>
    <!-- 文件列表-表格模式 -->
    <FileTable
      :fileType="8"
      :filePath="filePath"
      :fileList="fileList"
      :loading="loading"
      @setSelectionFile="setSelectionFile"
    ></FileTable>
    <div class="pagination-wrapper">
      <div class="current-page-count">当前页{{ fileList.length }}条</div>
      <el-pagination
        :current-page="pageData.currentPage"
        :page-size="pageData.pageCount"
        :total="pageData.total"
        :page-sizes="[10, 50, 100, 200]"
        layout="sizes, total, prev, pager, next"
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
import BreadCrumb from '@/components/File/BreadCrumb'
import FileTable from '@/components/File/FileTable'
import { getMyShareFileList } from '@/api/file/index'

export default {
  name: 'MyShare',
  components: {
    BreadCrumb,
    FileTable
  },
  data() {
    return {
      fileList: [],
      loading: false,
      pageData: {
        currentPage: 1,
        pageCount: 50,
        total: 0
      },
      selectionFile: []
    }
  },
  computed: {
    filePath() {
      let filePath = this.$route.query.filePath
      /*if (!filePath) {
        let MyShare = this.$store.getters.MyShare;
        if (MyShare.filePath){
          filePath =MyShare.filePath
        }else {
          filePath = '/'
        }
        this.$router.replace({
          query: {
            filePath: filePath
          }
        })
      }*/
      return   filePath?filePath:'/'
    },
    shareBatchNum() {
      let shareBatchNum =  this.$route.query.shareBatchNum
      if (this.filePath ==='/'){
        return null;
      }
      /*if (!shareBatchNum){
        let MyShare = this.$store.getters.MyShare;
        if (MyShare.shareBatchNum){
          shareBatchNum =MyShare.shareBatchNum
        }
      }*/
      return shareBatchNum||null
    }
  },
  watch: {
    filePath() {
      this.getMyShareList()
    }
  },
  mounted() {
    let filePath = this.$route.query.filePath;
    if (!filePath) {
      this.$router.replace({
        query: {
          filePath: '/'
        }
      })
    }
    this.getMyShareList()
  },
  methods: {
    /**
     * 获取我的分享文件列表
     */
    getMyShareList() {
      let data = {
        shareFilePath: this.filePath,
        shareBatchNum: this.shareBatchNum,
        currentPage: this.pageData.currentPage,
        pageCount: this.pageData.pageCount
      }
      this.$store.commit('setMyShareData', { filePath: this.filePath, shareBatchNum: this.shareBatchNum})
      getMyShareFileList(data).then((res) => {
        this.loading = false
        if (res.code === 200) {
          this.fileList = res.data.list
          this.pageData.total = res.data.total
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    /**
     * 表格数据获取相关事件 | 分页组件 | 当前页码改变
     */
    handleCurrentChange(currentPage) {
      this.pageData.currentPage = currentPage
      this.getMyShareList()
    },
    /**
     * 表格数据获取相关事件 | 分页组件 | 页大小改变时
     */
    handleSizeChange(pageCount) {
      this.pageData.pageCount = pageCount
      this.getMyShareList()
    },
    /**
     * 表格勾选框事件 | 保存被勾选的文件
     * @param {object[]} selection 被勾选的文件数组
     */
    setSelectionFile(selection) {
      this.selectionFile = selection
    }
  }
}
</script>

<style lang="stylus" scoped>
.my-share-wrapper {
  display: block !important;

  .operation-wrapper {
    padding: 8px 0;
  }

  .pagination-wrapper {
    position: relative;
    border-top: 1px solid $BorderBase;
    height: 44px;
    line-height: 44px;
    text-align: center;

    .current-page-count {
      position: absolute;
      left: 16px;
      height: 32px;
      line-height: 32px;
      font-size: 13px;
      color: $RegularText;
    }
  }
}
</style>
