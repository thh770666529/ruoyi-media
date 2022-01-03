<template>
  <div>
    <CommentBox
      :commentInfo="commentInfo"
      @submit-box="submitBox"
    ></CommentBox>
    <div class="message_infos">
      <CommentList :tableName="tableName" :comments="comments" :createBy="createBy" :commentInfo="commentInfo"></CommentList>
      <div class="noComment" v-if="comments.length === 0">还没有评论，快来抢沙发吧！</div>
    </div>
  </div>
</template>

<script>
  import CommentList from "@/components/Comment/CommentList";
  import CommentBox from "@/components/Comment/CommentBox"
  import {replyComment,treeListComment} from '@/api/website/comment';
  export default {
    name: 'Comment',
    props: {
      createBy: {
        type: [Number, String],
        default: ''
      },
      targetId: [Number, String],
      tableName: {
        type: String,
        default: '',
      }
    },
    components: {
      //注册组件
      CommentList,
      CommentBox
    },
    data() {
      return {
        comments: [],
        commentInfo: {
          targetId: this.targetId
        }
      }
    },
    computed: {
    },
    watch: {
      targetId:{//深度监听，可监听到对象、数组的变化
        handler(newValue, oldVal){
          this.commentInfo.targetId = newValue;
          this.getCommentList();
        }
      }
    },
    mounted() {
      if (this.targetId){
        this.getCommentList();
      }
    },
    methods: {
      // 发表评论
      submitBox(e) {
        let params = {};
        params.targetId = e.targetId;
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
            this.$modal.msgSuccess("发表成功！");
            this.getCommentList();
          }else{
            this.$modal.msgError("发表失败！")
          }
        });
      },
      getCommentList() {
        let params = {};
        params.targetId = this.commentInfo.targetId;
        params.tableName = this.tableName;
        treeListComment(params).then(response => {
          if (response.code === 200) {
            this.comments = response.rows
          }
        });
      }
    }
  };
</script>


<style scoped>
  .noComment {
    width: 100%;
    text-align: center;
  }
</style>
