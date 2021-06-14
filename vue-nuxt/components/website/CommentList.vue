<template>
  <div>
    <div v-for="item in comments" :key="item.commentId">
      <div class="commentList">
        <span class="left p1">
          <img v-if="item.user" :src="`http://localhost:7070`+item.user.avatar" onerror="onerror=null;src=defaultAvatar" />
          <img v-else :src="defaultAvatar" />
        </span>

        <span class="right p1">
          <div class="rightTop" v-if="item.user">
            <el-link class="userName" :underline="false">{{item.user.nickName}}</el-link>
            <span class="timeAgo" v-if="item.createTime">{{timeAgo(item.createTime)}}</span>
            <span class="timeAgo" v-else>刚刚</span>
          </div>

          <div class="rightCenter" v-html="item.content"></div>

          <div class="rightBottom">
            <el-link class="b1" :underline="false" @click="replyTo(item)">回复</el-link>
            <el-link class="b1" v-if="$store.state.userInfo&&$store.state.userInfo.userId == item.userId" :underline="false" @click="delComment(item)">删除</el-link>
          </div>

          <div class="rightCommentList">
            <CommentBox class="comment" :userInfo="userInfo" :toInfo="toInfo" :id="item.commentId" :commentInfo="commentInfo"
                        @submit-box="submitBox" @cancel-box="cancelBox"></CommentBox>

            <CommentList class="commentStyle" :id="'commentStyle:' + item.commentId" :comments="item.children" :commentInfo="commentInfo"></CommentList>
          </div>
        </span>
      </div>
    </div>
  </div>

</template>

<script>

  import {mapState} from 'vuex';
  import CommentBox from "@/components/website/CommentBox";
  import {timeAgo} from "../../utils"
  import {replyComment,treeListComment,delMyComment} from '@/api/comment'
  export default {
    name: "CommentList",
    props: ['comments', 'userInfos', 'commentInfo'],
    data() {
      return {
        taggleStatue: true,
        submitting: false,
        value: '',
        toInfo: {
          userId: "",
          commentId: ""
        },
        defaultAvatar: "http://localhost:7070/profile/avatar/defaul/avatar.jpg"
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
      ...mapState(['userInfo'])
    },
    methods: {
      replyTo: function (item) {
        if(!this.validLogin()) {
          this.$notify.error({
            title: '错误',
            message: "登录后才能回复评论哦~",
            offset: 100
          });
          return
        }
        let userId = item.userId;
        let commentId = item.commentId;
        var lists = document.getElementsByClassName("comment");
        for (var i = 0; i < lists.length; i++) {
          lists[i].style.display = 'none';
        }
        document.getElementById(commentId).style.display = 'block';
        this.toInfo.commentId = commentId
        this.toInfo.userId = userId
      },
      submitBox(e) {
        let params = {};
        params.sid = this.commentInfo.sid;
        params.content = e.content;
        params.commentId = e.commentId;
        params.createTime = e.createTime;
        params.updateTime = e.createTime;
        params.tableName = 'wm_movie';
        params.url = this.$route.path;
        params.support = 0;
        params.oppose = 0;
        replyComment(params).then(response => {
          if (response.code == 200){
            const commentData = response.data
            console.log("commentData", commentData)
            this.msgSuccess("发表成功！")
            document.getElementById(e.commentId).style.display = 'none';
            this.updateCommentList(this.comments, commentData.parentCommentId, commentData)
          }else{
            this.msgError("发表失败！")
          }
        });

        /*addComment(params).then(response => {
              let commentData = response.data
              document.getElementById(commentData.toUid).style.display = 'none'
              let comments = this.$store.state.app.commentList;
              commentData.user = this.userInfo;
              // 设置回复为空
              commentData.children = [];
              commentData.user = this.$store.state.user.userInfo
              this.updateCommentList(comments, commentData.toUid, commentData)
              console.log('得到的评论', comments)
              this.$store.commit("setCommentList", comments);

          })*/
      },
      getCommentList: function () {
        let params = {};
        params.sid = this.commentInfo.sid
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
      }
      ,
      taggleAll: function (item) {

        /*this.taggleStatue = !this.taggleStatue;
        var lists = document.getElementsByClassName("commentStyle");
        for (var i = 0; i < lists.length; i++) {
          lists[i].style.display = 'block';
        }
        if (this.taggleStatue) {
          document.getElementById('commentStyle:' + item.uid).style.display = 'block';
        } else {
          document.getElementById(item.uid).style.display = 'none';
        }*/
      },

      delComment: function (item) {
        if(!this.validLogin()) {
          this.$notify.error({
            title: '错误',
            message: "登录后才能删除评论哦~",
            offset: 100
          });
          return
        }

        this.$confirm("此操作将把本评论和子评论删除, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        })
          .then(() => {
            let params = {};
            params.commentId = item.commentId;
            params.sid = item.sid
            delMyComment(params).then(response => {
              if (response.code == 200) {
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
              //this.$emit("deleteComment", "")
            });

          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除"
            });
          });


      },
      // 校验是否登录
      validLogin() {
        let userInfo = this.$store.state.userInfo
        if(userInfo.userId == undefined) {
          return false;
        } else {
          return true;
        }
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
  .comment {
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
    margin-left: 10px;
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
