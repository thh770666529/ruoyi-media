<template>
  <div>
    <div v-for="item in comments" :key="item.commentId">
      <div class="commentList">
        <span class="left p1">
          <img v-if="item.user" :src="fileUploadHost + item.user.avatar" onerror="onerror=null;src=defaultAvatar" />
          <img v-else :src="defaultAvatar" />

        </span>

        <span class="right p1">
          <div class="rightTop" v-if="item.user">
            <el-link class="userName" :underline="false">{{item.user.nickName}}</el-link>
            <el-tooltip  content="管理员" placement="top">
               <span class="peopleRole" v-if="createBy && createBy == item.user.userId" >管理员 </span>
             </el-tooltip>
            <span class="timeAgo" v-if="item.createTime">{{timeAgo(item.createTime)}}</span>
            <span class="timeAgo" v-else>刚刚</span>
          </div>

          <div class="rightCenter" v-html="item.content"></div>

          <div class="rightBottom">
            <el-link class="b1" :underline="false" @click="replyTo(item)" v-if="level < maxReplyLevel" >回复</el-link>
            <el-link class="b1" v-if="isLogin&&userId == item.userId" :underline="false" @click="delComment(item)">删除</el-link>
            <span class="b1">level：{{ level }}</span>
          </div>

          <div class="rightCommentList">
            <CommentBox class="commentBoxClass"  :toInfo="toInfo" :id="item.commentId" :commentInfo="commentInfo"
                        @submit-box="submitBox" @cancel-box="cancelBox"></CommentBox>

            <CommentList :tableName="tableName" :createBy="createBy" :level="level + 1" class="commentStyle" :id="'commentStyle:' + item.commentId" :comments="item.children" :commentInfo="commentInfo"></CommentList>
          </div>
        </span>
      </div>
    </div>
  </div>

</template>

<script>

  import { mapGetters } from 'vuex'
  import CommentBox from "@/components/Comment/CommentBox";
  import {timeAgo} from "@/utils"
  import {replyComment,treeListComment,delMyComment} from '@/api/website/comment'
  export default {
    name: "CommentList",
    props: {
      createBy: {
        type: [Number, String],
        default: ''
      },
      // 父级组件传过来的评论列表
      comments: {
        type: Array,
        default: () => []
      },
      // 评论主体信息
      commentInfo: {
        type: Object
      },
      // 递归组件的嵌套层级，递归了1层、2层、3层、4层、5层，在最后一层可以禁止回复，拒绝无休止的评论
      level: {
        type: Number,
        default: 1,
      },
      tableName: {
        type: String
      },
      targetId: [Number, String]
    },
    data() {
      return {
        maxReplyLevel: 5, // 控制最大评论层级
        taggleStatue: true,
        submitting: false,
        value: '',
        toInfo: {
          userId: '',
          commentId: ''
        },
        defaultAvatar: require("@/assets/styles/images/user.png")
      };
    },
    created() {
    },
    components: {
      CommentBox
    },
    mounted() {

    },
    computed:{
      ...mapGetters([
        'name',
        'avatar',
        'isLogin',
        'userId'
      ])
    },
    methods: {
      replyTo: function (item) {
        if (!this.isLogin){
          this.$notify.error({
            title: '警告',
            message: '登录后才可以评论哦~',
            offset: 100
          });
          this.$modal.confirm('登录后才可以评论，是否进行登录', '提示', {
            confirmButtonText: '登录',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            //如果没有登录 则转到登录页面
            this.$store.dispatch('showLoginForm')
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消登录'
            });
          });
          return
        }
        let userId = item.userId;
        let commentId = item.commentId;
        const lists = document.getElementsByClassName("commentBoxClass");
        for (let i = 0; i < lists.length; i++) {
          lists[i].style.display = 'none';
        }
        document.getElementById(commentId).style.display = 'block';
        this.toInfo.commentId = commentId;
        this.toInfo.userId = userId
      },
      submitBox(e) {
        let params = {};
        params.targetId = this.commentInfo.targetId;
        params.content = e.content;
        params.commentId = e.commentId;
        params.createTime = e.createTime;
        params.updateTime = e.createTime;
        params.tableName = this.tableName;
        params.url = this.$route.path;
        params.support = 0;
        params.oppose = 0;
        replyComment(params).then(response => {
          if (response.code === 200){
            const commentData = response.data
            this.$modal.msgSuccess("发表成功！")
            document.getElementById(e.commentId).style.display = 'none';
            this.updateCommentList(this.comments, commentData.parentCommentId, commentData)
          }else{
            this.$modal.msgError("发表失败！")
          }
        });
      },
      getCommentList() {
        let params = {};
        params.targetId = this.commentInfo.targetId;
        params.tableName = this.tableName;
        if (this.toInfo.commentId){
          params.commentId = this.commentInfo.commentId
        }
        treeListComment(params).then(response => {
          if (response.code == 200) {
            this.comments = response.rows;
          }
        });
      }
      ,
      cancelBox(toCommentUid) {
        document.getElementById(toCommentUid).style.display = 'none'
      },
      delComment: function (item) {
        if(!this.isLogin) {
          this.$notify.error({
            title: '错误',
            message: "登录后才能删除评论哦~",
            offset: 100
          });
          this.$modal.confirm('登录后才能删除评论，是否进行登录', '提示', {
            confirmButtonText: '登录',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            //如果没有登录 则转到登录页面
            this.$store.dispatch('showLoginForm')
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消登录'
            });
          });
          return
        }

        this.$modal.confirm("此操作将把本评论和子评论删除, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
            let params = {};
            params.commentId = item.commentId;
            params.targetId = item.targetId
            delMyComment(params).then(response => {
              if (response.code === 200) {
                this.$notify({
                  title: '成功',
                  message: "删除成功",
                  type: 'success',
                  offset: 100
                });

              } else {
                this.$notify.error({
                  title: '错误',
                  message: "删除失败",
                  offset: 100
                });
              }
              this.deleteCommentList(this.comments, params.commentId, null)
            });

          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除"
            });
          });


      },
      /**
       * dateTimeStamp是一个时间毫秒，注意时间戳是秒的形式，在这个毫秒的基础上除以1000，就是十位数的时间戳。13位数的都是时间毫秒。
       * @param dateTimeStamp
       * @returns {string}
       */
      timeAgo(dateTimeStamp) {
        return timeAgo(dateTimeStamp)
      },
      updateCommentList(commentList, commentId, targetComment) {
        if (commentList == undefined || commentList.length <= 0) {
          return;
        }
        for (let item of commentList) {
          if (item.commentId === commentId) {
            item.children.push(targetComment)
          } else {
            this.updateCommentList(item.children, commentId, targetComment);
          }
        }
      },
      deleteCommentList(commentList, commentId, parentList) {
        if (commentList == undefined || commentList.length <= 0) {
          return;
        }
        for (let item of commentList) {
          if (item.commentId === commentId) {
            commentList.splice(commentList.indexOf(item), 1);
          } else {
            this.deleteCommentList(item.children, commentId, item);
          }
        }
      },
    },
  };
</script>

<style scoped>
  .commentStyle {
    display: block;
    margin-top: 10px;
    margin-left: 10px;
    border-left: 1px dashed SlateGray;
  }
  .commentBoxClass {
    display: none;
  }
  .commentList {
    width: 100%;
    margin: 0 auto;
  }
  .commentList .p1 {
    float: left;
  }
  .commentList .left {
    display: inline-block;
    width: 5%;
    height: 100%;
  }
  .commentList .left img {
    margin: 0 auto;
    width: 40px;
    height: 40px;
    border-radius: 50%;
  }
  .commentList .right {
    display: inline-block;
    width: 93%;
    margin-left: 5px;
  }
  .commentList .rightTop {
    height: 30px;
    margin-top: 2px;
  }
  .commentList .rightTop .userName {
    color: #303133;
    margin-left: 10px;
    font-size: 16px;
    font-weight: bold;
  }

  .commentList .rightTop .peopleRole {
    color: #21ba45;
    border: 1px solid #9bd4a9;
    padding: 2px;
    font-size: 12px;
    border-radius: 4px;
    margin-left: 4px;
    cursor: default;
  }

  .commentList .rightTop .timeAgo {
    color: #909399;
    margin-left: 10px;
    font-size: 15px;
  }
  .commentList .rightCenter {
    margin-left: 20px;
    min-height: 50px;
    margin-top: 15px;
  }
  .commentList .rightBottom {
    margin-left: 10px;
    height: 30px;
  }
  .commentList .rightBottom el-link {

  }
  .commentList .rightBottom .b1 {
    margin-left: 8px;
    color: #909399;
  }

  @media only screen and (min-width: 300px) and (max-width: 767px) {
    .commentList .left {
      display: inline-block;
      width: 30px;
      height: 100%;
    }
    .commentList .right {
      display: inline-block;
      width: calc(100% - 35px);
      margin-left: 5px;
    }
  }
</style>
