<template>
  <div>
    <div class="commentBox">
    <span class="left" v-if="isShowAvatar">
      <img v-if="isLogin && avatar" :src="avatar" onerror="onerror=null;src=defaultAvatar" />
      <img v-else :src="defaultAvatar" />
    </span>
    <span class="right">
      <textarea class="commentTextArea" placeholder="既然来了，那就留下些什么吧~" v-model="value" @click="hideEmojiPanel" @input="vaildCount"></textarea>
    </span>
    </div>
    <div class="bottom">
      <el-button class="submit p2" type="danger"  @click="handleSubmit">发送评论</el-button>
      <el-button class="cancel p2" type="info" @click="handleCancle">取消评论</el-button>
      <el-popover
        placement="bottom"
        width="520"
        trigger="click">
        <div >
          <emoji-panel class="emojiPanel" @emojiClick="appendEmoji" ></emoji-panel>
        </div>
          <div  slot="reference" class="emoji-panel-btn p2" @click="showEmojiPanel">
          <img src="../../assets/img/face_logo.png" />
        </div>
      </el-popover>

      <span class="allow p2" v-if="isShowAvatar">还能输入{{count}}个字符</span>

    </div>
  </div>

</template>

<script>
  import '@/assets/styles/css/emoji.css';
  import {dateFormat} from '@/utils/index'
  import EmojiPanel from "@/components/EmojiPanel/EmojiPanel.vue";
  import { mapGetters } from 'vuex'
  export default {
    name: 'CommentBox',
    props: {
      // 回复的对象
      toInfo: {
        type: Object
      },
      // 评论主体信息
      commentInfo: {
        type: Object
      }
    },
    components: {
      EmojiPanel
    },

    data() {
      return {
        comments: {},
        submitting: false,
        value: '',
        user: {},
        count: 1024,
        isShowEmojiPanel: false, // 是否显示表情面板
        isShowAvatar: true, // 是否显示头像
        defaultAvatar: require("@/assets/styles/images/user.png")
      }
    },
    computed: {
      ...mapGetters([
        'name',
        'avatar',
        'isLogin'
      ])
    },
    mounted() {
      // 页面加载的时候调用监听
      this.hideEmojiPanel()

      // 获取宽高
      window.onresize = () => {
        return (() => {
          this.resizeWin();
        })();
      };
      this.resizeWin();
    },
    methods: {
      vaildCount: function() {
        let count = 1024 - this.value.length;
        if(count <= 0) {
          this.count = 0
        } else {
          this.count = count;
        }
      },
      handleSubmit() {
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


        if(this.value === '') {
          this.$notify.error({
            title: '警告',
            message: '评论内容不能为空哦~',
            offset: 100
          });
          return;
        }
        let targetId = '';
        let commentId = '';
        if (this.commentInfo) {
          targetId = this.commentInfo.targetId
        }
        if (this.toInfo){
          targetId = this.toInfo.targetId
          commentId = this.toInfo.commentId
        }


        let content = this.value.replace(/:.*?:/g, this.emoji);
        this.comments = {
          commentId:commentId,
          content: content,
          targetId: targetId
        }
        this.value = '';
        this.count = 1024;
        this.comments.createTime = dateFormat("YYYY-mm-dd HH:MM:SS", new Date());
        this.hideEmojiPanel()
        this.$emit("submit-box", this.comments)
      },
      emoji(word) {
        // 生成html
        const type = word.substring(1, word.length - 1);
        return `<span class="emoji-item-common emoji-${type} emoji-size-small"></span>`;
      },
      handleCancle() {
        this.value = '';
        this.count = 1024;
        this.isShowEmojiPanel = false
        if(this.toInfo) {
          this.$emit("cancel-box", this.toInfo.commentId)
        }
        this.hideEmojiPanel()
      },
      showEmojiPanel() {
        this.isShowEmojiPanel = !this.isShowEmojiPanel;
      },
      hideEmojiPanel() {
        this.isShowEmojiPanel = false
      },
      appendEmoji(text) {
        this.value = this.value + ":" + text + ":";
      },
      resizeWin() {
        //当前window 宽
        let centerWidth = document.documentElement.scrollWidth;
        if (centerWidth > 800) {
          this.isShowAvatar = true
        } else {
          // 是否显示头像框
          this.isShowAvatar = false
        }
      },
    },
  };
</script>


<style>

  .commentBox {
    width: 100%;
    height: 100px;
  }
  .commentBox .left {
    display: inline-block;
    width: 7%;
    height: 100%;
  }
  .commentBox .left img {
    position: relative;
    top: -40%;
    cursor: pointer;
    width: 40px;
    height: 40px;
    border-radius: 50%;
  }

  .commentBox .right {
    display: inline-block;
    margin: 5px 2px 2px 0;
    width: 90%;
    height: 100%;
  }
  .commentTextArea::-webkit-input-placeholder {
    color: #909399;
  }
  .commentBox .right .commentTextArea {
    color: #606266;
    padding:8px 5px 5px 10px;
    width: 95%;
    height: 99%;
  }
  .bottom {
    width: 98%;
    height: 60px;
    line-height: 40px;
    margin-top: 25px;
  }
  .bottom .p2 {
    float: right;
    margin-top: 5px;
    margin-right: 10px;
  }
  .emoji-panel-btn img{
    height: 35px;
    width: 35px;
  }
  .emoji-panel-btn:hover {
    cursor: pointer;
    opacity: 0.8;
  }


  .emoji-size-small {
    zoom: 0.3;
    margin: 5px;
    vertical-align: middle;
  }
  .emoji-size-large {
    zoom: 0.5;
    margin: 5px;
  }
  .emoji-item-common {
    background: url("../../assets/img/emoji_sprite.png");
    display: inline-block;
  }
  .emoji-item-common:hover {
     cursor: pointer;
   }
  .emoji-size-small {
    zoom: 0.3;
  }

  @media only screen and (min-width: 300px) and (max-width: 767px) {
    .commentBox .right {
      display: inline-block;
      margin: 5px 2px 0 0;
      width: 99%;
      height: 100%;
    }

    .emoji-panel-wrap {
      box-sizing: border-box;
      border: 1px solid #cccccc;
      border-radius: 5px;
      background-color: #ffffff;
      width: 300px;
      height: 270px;
      position: absolute;
      z-index: 99;
      top: 10px;
    }
  }

</style>
