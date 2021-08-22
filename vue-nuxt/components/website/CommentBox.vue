<template>
  <div>
    <div class="commentBox">
    <span class="left" v-if="isShowAvatar">
      <img v-if="userInfo && userInfo.avatar" :src="`http://localhost:7070` + userInfo.avatar" onerror="onerror=null;src=defaultAvatar" />
      <img v-else :src="defaultAvatar" />
    </span>

    <span class="right">
      <textarea id="textpanel" class="textArea" placeholder="既然来了，那就留下些什么吧~" v-model="value" @click="hideEmojiPanel" @input="vaildCount"></textarea>
    </span>
    </div>
    <div class="bottom">
      <el-button class="submit p2" type="primary"  @click="handleSubmit">发送评论</el-button>
      <el-button class="cancel p2" type="info" @click="handleCancle">取消评论</el-button>
      <el-popover
        placement="bottom"
        width="500"
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
  import {dateFormat} from '../../utils'
  import EmojiPanel from "@/components/EmojiPanel/EmojiPanel.vue";
  export default {
    name: 'CommentBox',
    props: {
      userInfo: {
        type: Object|String
      },
      // 回复的对象
      toInfo: {
        type: Object
      },
      // 评论主体信息
      commentInfo: {
        type: Object
      },
      showCancel: {
        type: Boolean,
        default: true
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
        defaultAvatar: "http://localhost:7070/profile/avatar/defaul/avatar.jpg"
      }
    },
    computed: {
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
        var count = 1024 - this.value.length;
        if(count <= 0) {
          this.count = 0
        } else {
          this.count = count;
        }
      },
      handleSubmit() {
        let userInfo = this.$store.state.userInfo
        let token = this.$store.state.token

        if (!userInfo){
          this.$notify.error({
            title: '警告',
            message: '登录后才可以评论哦~',
            offset: 100
          });
          this.$confirm('登录后才可以评论，是否进行登录', '提示', {
            confirmButtonText: '登录',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            //如果没有登录 则转到登录页面
            return this.$store.dispatch('LoginPage')
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


        let sid = ''
        let commentId = ''
        if (this.commentInfo) {
          sid = this.commentInfo.sid
        }
        if (this.toInfo){
          sid = this.toInfo.sid
          commentId = this.toInfo.commentId
        }


        let content = this.value.replace(/:.*?:/g, this.emoji);
        this.comments = {
          commentId:commentId,
          content: content,
          sid: sid
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
        // const el = document.getElementById("textpanel");
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
  @import "../../assets/css/emoji.css";

  .emoji-panel-wrap {
    box-sizing: border-box;
    border: 1px solid #cccccc;
    border-radius: 5px;
    background-color: #ffffff;
    width: 500px;
    height: auto;
    position: relative;
    z-index: 99;
    top: 10px;
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
  .commentBox {
    /*min-width: 700px;*/
    width: 100%;
    height: 100px;
    margin: 0 auto;
  }
  .commentBox .left {
    display: inline-block;
    width: 5%;
    height: 100%;
    padding-top: 3px;
  }
  .commentBox .left img {
    cursor: pointer;
    margin: 0 auto;
    width: 40px;
    height: 40px;
    border-radius: 50%;
  }

  .commentBox .right {
    display: inline-block;
    margin: 5px 2px 0 0;
    width: 93%;
    height: 100%;
  }
  textarea::-webkit-input-placeholder {
    color: #909399;
  }
  .commentBox .right textarea {
    color: #606266;
    padding:10px 5px 5px 10px;
    resize: none;
    width: 95%;
    height: 100%;
  }
  .bottom {
    position: relative;
    width: 98%;
    height: 60px;
    line-height: 40px;
    margin-top: 20px;
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
