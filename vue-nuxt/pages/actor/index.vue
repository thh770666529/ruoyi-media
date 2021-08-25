<template>
  <div id="aMovieList" class="bg-fa of">
    <!-- 演员列表 开始 -->
    <section class="container">
      <header class="comm-title all-teacher-title">
        <h2 class="fl actor">
          <span class="c-333">全部演员</span>
        </h2>
        <section class="c-tab-title">
          <a id="subjectAll" title="全部" href="#">全部</a>
        </section>
      </header>
      <section class="c-sort-box unBr">
        <div>
          <!-- /无数据提示 开始-->
          <section class="no-data-wrap" v-if="total===0">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
          </section>
          <!-- /无数据提示 结束-->
          <article v-if="total>0" class="i-actor-list">
            <ul class="of">
              <li v-for="actor in actorList" :key="actor.actorId">
                <section class="i-actor-wrap">
                  <div class="i-actor-pic">
                    <router-link :to="'/actor/'+actor.actorId" tag="a" target="_blank" exact>
                      <img :src="fileUploadHost + actor.avatar" :alt="actor.name">
                    </router-link>
                  </div>
                  <div class="mt10 hLh30 txtOf actor">
                    <router-link :to="'/actor/' + actor.actorId" tag="a" title="actor.name" target="_blank" active-class="fsize18 c-666" exact>
                      {{actor.name}}
                    </router-link>
                  </div>
                  <div class="hLh30 txtOf actor">
                    <span class="fsize14 c-999" v-html="actor.description"></span>
                  </div>
                  <div class="mt15 i-q-txt">
                    <span class="c-999 f-fA" v-html="actor.awards"></span>
                  </div>
                </section>
              </li>

            </ul>
            <div class="clear"></div>
          </article>
        </div>
        <!-- 公共分页 开始 -->
        <div>
          <div class="paging">
            <a
              :class="{undisable: true}"
              href="#"
              title="首页"
              @click.prevent="gotoPage(1)">首</a>
            <a
              :class="{undisable: true}"
              href="#"
              title="前一页"
              @click.prevent="gotoPage(queryParams.pageNum-1)">&lt;</a>


            <a
              v-for="page in totalPage"
              :key="page"
              :class="{current: queryParams.pageNum === page, undisable: queryParams.pageNum === page}"
              :title="'第'+page+'页'"
              href="#"
              @click.prevent="gotoPage(page)">{{ page }}</a>
            <a
              href="#"
              title="后一页"
              @click.prevent="queryParams.pageNum<pages?gotoPage(queryParams.pageNum+1):gotoPage(queryParams.pageNum)">&gt;</a>
            <a
              href="#"
              title="末页"
              @click.prevent="gotoPage(pages)">末</a>

            <div class="clear"/>
          </div>
        </div>
      <!-- 公共分页 结束 -->
        <!-- 公共分页 结束 -->
      </section>
    </section>
    <!-- /演员列表 结束 -->
  </div>
</template>
<script>
  import { listActor, getActor } from '@/api/actor';

export default {
  data() {
    return {
      page:1, //当前页
      actorList:[],  //演员列表
      total: 0,
      totalPage:[],
      pages:0,
      queryParams: {
        pageNum: 1,
        pageSize: 8,
        name: null,
        avatar: null,
        description: null,
        awards: null,
        label: null
      }
    }
  },
  methods:{
    async getList() {
        listActor(this.queryParams).then(response => {
        this.actorList = response.rows;
        this.total = response.total;
        this.pages = 0;
        this.pages = this.total / this.queryParams.pageSize;
        if (this.total % this.queryParams.pageSize !== 0) {
          this.pages++;
        }
        if (this.pages>0){
          this.totalPage = [];
          for (let i = 1; i < this.pages; i++) {
            this.totalPage.push(i);
          }
        }
      });
    },
    gotoPage(page) {
      this.queryParams.pageNum = page;
      this.getList();
    },
    getTotalPage(){

    },
  },
  async created() {
    this.getList();
  }

};
</script>
