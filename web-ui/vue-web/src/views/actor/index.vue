<template>
  <div>
    <div class="acotr-body">
      <section class="c-sort-box unBr">
      <div>
        <section class="no-data-wrap" v-if="total===0">
          没有相关数据，小编正在努力整理中...
        </section>
        <article v-if="total>0" class="i-actor-list">
          <ul class="of">
            <li v-for="actor in actorList" :key="actor.actorId">
              <section class="i-actor-wrap">
                <div class="i-actor-pic">
                  <router-link :to="'/actor/'+actor.actorId" tag="a" target="_blank" exact>
                    <img :src="fileUploadHost + actor.avatar" :alt="actor.name">
                  </router-link>
                </div>
                <div class="mt10 hLh30 txtOf">
                  <router-link :to="'/actor/' + actor.actorId" tag="a" :title="actor.name" target="_blank" active-class="fsize20 #333" exact>
                    <b>{{actor.name | ellipsis(15) }}</b>
                  </router-link>
                </div>
                <div class="hLh30 txtOf">
                  <span class="fsize12 c-999" >{{actor.description}}</span>
                </div>
              </section>
            </li>
          </ul>
          <div class="clear"></div>
        </article>
      </div>
      <!-- 公共分页 -->
      <div>
        <div class="block">
          <pagination
            v-show="total>0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="getList"
          />
        </div>
      </div>
    </section>
    </div>
  </div>
</template>
<script>
  import { listActor, getActor } from '@/api/media/actor';

  export default {
    data() {
      return {
        page:1, //当前页
        actorList:[],  //演员列表
        total: 0,
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          name: null,
          avatar: null,
          description: null,
          awards: null,
          label: null
        }
      }
    },
    methods:{
      getList() {
        listActor(this.queryParams).then(response => {
          this.actorList = response.rows;
          this.total = response.total;
        });
      }
    },
    created() {
      this.getList();
    }
  };
</script>
<style lang="less" scoped>
  .unBr {
    border: none !important
  }
  .of {
    overflow: hidden
  }
  .hLh30 {
    height: 30px;
    line-height: 30px
  }
  .txtOf {
    display: block;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap
  }
  .acotr-body {
    width: 1200px;
    margin: auto;
  }

  .i-actor-list ul {
    margin-left: -30px;
    padding-top: 10px
  }

  .i-actor-list ul li {
    width: 20%;
    float: left
  }

  .i-actor-wrap {
    background: #fff;
    border: 1px solid transparent;
    display: block;
    margin: 0 0 50px 30px;
    overflow: hidden;
    text-align: center;
    padding: 15px 15px 20px;
    overflow: hidden;
    transition: .3s;
    -webkit-transition: .3s
  }

  .i-actor-pic {
    width: 60%;
    height: 165px;
    margin: 8px auto 0
  }

  .i-actor-pic img {
    display: block;
    border-radius: 10%;
    max-width: 100%;
    transition: .5s;
    -webkit-transition: .5s
  }

  .i-actor-wrap .i-q-txt {
    border-top: 1px dotted #e2e2e2;
    padding-top: 15px
  }

  .i-actor-wrap:hover {
    border-color: #e8e8e8;
    box-shadow: 6px 6px 0 rgba(0, 0, 0, .06)
  }

  .i-actor-wrap:hover .i-actor-pic img {
    transform: rotateY(180deg);
    -webkit-transform: rotateY(180deg)
  }

  .c-sort-box {
    border-top: 1px solid #e2e2e2;
    margin-top: 15px
  }
</style>
