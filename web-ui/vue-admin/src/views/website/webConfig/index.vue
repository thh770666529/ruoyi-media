<template>
  <div class="app-container">
    <el-tabs type="border-card" @tab-click="handleClick">
      <el-tab-pane v-permission="'website:webConfig:query'">
        <span slot="label">
          <i class="el-icon-date"></i> 网站信息
        </span>

        <el-form
          style="margin-left: 20px;"
          label-position="left"
          :model="webConfig"
          label-width="80px"
          ref="from">
        <el-form-item label="LOGO" prop="logo">
          <imageUpload :limit="1" v-model="webConfig.logo"/>
        </el-form-item>

          <el-row :gutter="24">
            <el-col :span="10">
              <el-form-item label="网站名称" prop="oldPwd">
                <el-input v-model="webConfig.name" style="width: 400px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="标题" prop="newPwd1">
                <el-input v-model="webConfig.title" style="width: 400px"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="24">
            <el-col :span="10">
              <el-form-item label="关键字" prop="newPwd2">
                <el-input v-model="webConfig.keyword" style="width: 400px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="描述" prop="newPwd1">
                <el-input v-model="webConfig.summary" style="width: 400px"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="24">
            <el-col :span="10">
              <el-form-item label="作者" prop="newPwd2">
                <el-input v-model="webConfig.author" style="width: 400px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="备案号" prop="newPwd2">
                <el-input v-model="webConfig.recordNum" style="width: 400px"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="24">
            <el-col :span="10">
              <el-form-item label="登录方式">
                <el-checkbox-group v-model="webConfig.loginTypeList">
                  <el-checkbox label="1" style="margin-left: 10px">账号密码</el-checkbox>
                  <el-checkbox label="2" style="margin-left: 10px">码云</el-checkbox>
                  <el-checkbox label="3" style="margin-left: 10px">Github</el-checkbox>
                  <el-checkbox label="4" style="margin-left: 10px">QQ</el-checkbox>
                  <el-checkbox label="5" style="margin-left: 10px">微信</el-checkbox>
                </el-checkbox-group>
              </el-form-item>
            </el-col>
          </el-row>

          <el-form-item>
            <el-button type="primary" @click="submitForm()" v-permission="'website:webConfig:edit'">保 存</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>

      <el-tab-pane v-permission="'website:webConfig:query'">
        <span slot="label">
          <i class="el-icon-date"></i> 评论&打赏
        </span>

        <el-form
          style="margin-left: 20px;"
          label-position="left"
          :model="form"
          label-width="90px"
          ref="from"
        >

          <el-row :gutter="24">
            <el-col :span="4">
              <el-form-item label="阿里支付">
                <imageUpload :limit="1" v-model="webConfig.aliPay"/>
              </el-form-item>
            </el-col>
            <el-col :span="4">
              <el-form-item label="微信支付">
                <imageUpload :limit="1" v-model="webConfig.weixinPay"/>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="24">
            <el-col :span="6">
              <el-form-item label="网站评论">
                <el-radio v-for="dict in switchOptions" :key="dict.dictValue" v-model="webConfig.openComment"  :label="dict.dictValue" border size="medium">{{dict.dictLabel}}</el-radio>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="24">
            <el-col :span="6">
              <el-form-item label="网站打赏">
                <el-radio v-for="dict in switchOptions" :key="dict.dictValue" v-model="webConfig.openAdmiration"  :label="dict.dictValue" border size="medium">{{dict.dictLabel}}</el-radio>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="24">
            <el-col :span="6">
              <el-form-item label="移动端评论">
                <el-radio v-for="dict in switchOptions" :key="dict.dictValue" v-model="webConfig.openMobileComment"  :label="dict.dictValue" border size="medium">{{dict.dictLabel}}</el-radio>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="24">
            <el-col :span="6">
              <el-form-item label="移动端打赏">
                <el-radio v-for="dict in switchOptions" :key="dict.dictValue" v-model="webConfig.openMobileAdmiration"  :label="dict.dictValue" border size="medium">{{dict.dictLabel}}</el-radio>
              </el-form-item>
            </el-col>
          </el-row>

          <el-form-item>
            <el-button type="primary" @click="submitForm()" v-permission="'website:webConfig:edit'">保 存</el-button>
          </el-form-item>
        </el-form>

      </el-tab-pane>

      <el-tab-pane v-permission="'website:webConfig:query'">
        <span slot="label">
          <i class="el-icon-date"></i> 关注我们
        </span>

        <el-form
          style="margin-left: 20px;"
          label-position="left"
          :model="webConfig"
          label-width="80px"
          :rules="rules"
          ref="form"
        >
          <el-checkbox-group v-model="webConfig.showList">

            <el-form-item label="邮箱" prop="email">
              <el-input v-model="webConfig.email" style="width: 400px"></el-input>
              <el-checkbox label="1" style="margin-left: 10px">在首页显示</el-checkbox>
            </el-form-item>

            <el-form-item label="QQ号" prop="qqNumber">
              <el-input v-model="webConfig.qqNumber" style="width: 400px"></el-input>
              <el-checkbox label="2" style="margin-left: 10px">在首页显示</el-checkbox>
            </el-form-item>

            <el-form-item label="QQ群" prop="qqGroup">
              <el-input v-model="webConfig.qqGroup" style="width: 400px"></el-input>
              <el-checkbox label="3" style="margin-left: 10px">在首页显示</el-checkbox>
            </el-form-item>

            <el-form-item label="github" prop="github">
              <el-input v-model="webConfig.github" style="width: 400px"></el-input>
              <el-checkbox label="4" style="margin-left: 10px">在首页显示</el-checkbox>
            </el-form-item>

            <el-form-item label="Gitee" prop="gitee">
              <el-input v-model="webConfig.gitee" style="width: 400px"></el-input>
              <el-checkbox label="5" style="margin-left: 10px">在首页显示</el-checkbox>
            </el-form-item>

            <el-form-item label="微信" prop="weChat">
              <el-input v-model="webConfig.weChat" style="width: 400px"></el-input>
              <el-checkbox label="6" style="margin-left: 10px">在首页显示</el-checkbox>
            </el-form-item>

          </el-checkbox-group>

          <el-form-item>
            <el-button type="primary" @click="submitForm()" v-permission="'website:webConfig:edit'">保 存</el-button>
          </el-form-item>

        </el-form>
      </el-tab-pane>

      <el-tab-pane name="three" v-permission="'website:webConfig:query'">
        <span slot="label">
          <i class="el-icon-date"></i> 本地文件存储
        </span>
        <el-form
          style="margin-left: 20px;"
          label-position="left"
          :model="webConfig"
          label-width="120px"
        >

          <aside>
            使用IO流将文件存储本地磁盘中<br/>
          </aside>

          <el-form-item label="本地文件域名" prop="localPictureBaseUrl">
            <el-input v-model="webConfig.localFileBaseUrl" auto-complete="new-password" style="width: 400px"></el-input>
          </el-form-item>

          <el-form-item label="存储类型">
            <el-radio v-for="dict in storageTypeOptions" :key="dict.dictValue" v-model="webConfig.storageType"  :label="dict.dictValue" border size="medium">{{dict.dictLabel}}</el-radio>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitForm()" v-permission="'website:webConfig:edit'">保 存</el-button>
          </el-form-item>

        </el-form>
      </el-tab-pane>

      <el-tab-pane name="four"  v-permission="'website:webConfig:query'">
        <span slot="label">
          <i class="el-icon-date"></i> 七牛云对象存储
        </span>

        <el-form
          style="margin-left: 20px;"
          label-position="left"
          :model="webConfig"
          label-width="120px"
          ref="form"
        >
          <aside>
            使用 <a href="http://www.moguit.cn/#/info?blogUid=735ed389c4ad1efd321fed9ac58e646b">七牛云</a> 构建对象存储服务<br/>
          </aside>

          <el-form-item label="七牛云文件域名" prop="qiNiuPictureBaseUrl">
            <el-input v-model="webConfig.qiNiuFileBaseUrl" auto-complete="new-password" style="width: 400px"></el-input>
          </el-form-item>

          <el-form-item label="七牛云公钥">
            <el-input v-model="webConfig.qiNiuAccessKey" auto-complete="new-password" style="width: 400px"></el-input>
          </el-form-item>

          <el-form-item label="七牛云私钥">
            <el-input type="password" v-model="webConfig.qiNiuSecretKey" auto-complete="new-password" style="width: 400px"></el-input>
          </el-form-item>

          <el-form-item label="上传空间">
            <el-input  v-model="webConfig.qiNiuBucket" style="width: 400px"></el-input>
          </el-form-item>

          <el-form-item label="存储区域">
            <el-select v-model="webConfig.qiNiuArea" placeholder="请选择存储区域" clearable>
              <el-option v-for="dict in qiNiuAreaOptions"
                         :key="dict.dictValue"
                         :label="dict.dictLabel"
                         :value="dict.dictValue"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="存储类型">
            <el-radio v-for="dict in storageTypeOptions" :key="dict.dictValue" v-model="webConfig.storageType" :label="dict.dictValue" border size="medium">{{dict.dictLabel}}</el-radio>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitForm()" v-permission="'website:webConfig:edit'">保 存</el-button>
          </el-form-item>

        </el-form>
      </el-tab-pane>

      <el-tab-pane name="five" v-permission="'website:webConfig:query'">
        <span slot="label">
          <i class="el-icon-date"></i> Minio对象存储
        </span>

        <el-form
          style="margin-left: 20px;"
          label-position="left"
          :model="webConfig"
          label-width="120px"
          ref="form"
        >

          <aside>
            使用 <a href="http://www.moguit.cn/#/info?blogUid=a1058b2d030310e2c5d7b0584e514f1f">Minio</a> 构建本地对象存储服务<br/>
          </aside>

          <el-form-item label="Minio访问域名" prop="localFileBaseUrl">
            <el-input v-model="webConfig.minioFileBaseUrl" auto-complete="new-password" style="width: 400px"></el-input>
          </el-form-item>

          <el-form-item label="Minio连接地址" prop="qiNiuFileBaseUrl">
            <el-input v-model="webConfig.minioEndPoint" auto-complete="new-password" style="width: 400px"></el-input>
          </el-form-item>

          <el-form-item label="Minio公钥">
            <el-input v-model="webConfig.minioAccessKey" auto-complete="new-password" style="width: 400px"></el-input>
          </el-form-item>

          <el-form-item label="Minio私钥">
            <el-input type="password" v-model="webConfig.minioSecretKey" auto-complete="new-password" style="width: 400px"></el-input>
          </el-form-item>

          <el-form-item label="Minio上传空间">
            <el-input  v-model="webConfig.minioBucket" style="width: 400px"></el-input>
          </el-form-item>

          <el-form-item label="存储类型">
            <el-radio v-for="dict in storageTypeOptions" :key="dict.dictValue" v-model="webConfig.storageType"  :label="dict.dictValue" border size="medium">{{dict.dictLabel}}</el-radio>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitForm()" v-permission="'website:webConfig:edit'">保 存</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>

      <el-tab-pane name="six" v-permission="'website:webConfig:query'">
        <span slot="label"><i class="el-icon-edit"></i> 邮箱配置</span>
        <el-form style="margin-left: 20px;" label-position="left"   label-width="80px" >

          <aside>
            邮箱配置主要用于配置网站消息的接收<br/>
            例如：友链申请、网站评论、网站反馈等，可以在系统配置处选择是否开启邮件通知<br/>
          </aside>

          <el-form-item label="邮箱" prop="email">
            <el-input  v-model="webConfig.email" style="width: 400px"></el-input>
          </el-form-item>

          <el-form-item label="密码" prop="newPwd1">
            <el-input type="password" v-model="webConfig.emailPassword" style="width: 400px"></el-input>
          </el-form-item>

          <el-form-item label="用户名" prop="newPwd2">
            <el-input  v-model="webConfig.emailUserName" style="width: 400px"></el-input>
          </el-form-item>

          <el-form-item label="SMTP地址" prop="newPwd2">
            <el-input  v-model="webConfig.smtpAddress" style="width: 400px"></el-input>
          </el-form-item>

          <el-form-item label="SMTP端口" prop="newPwd2">
            <el-input  v-model="webConfig.smtpPort" style="width: 400px"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitForm()" v-permission="'website:webConfig:edit'">保 存</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>

      <el-tab-pane name="seven" v-permission="'website:webConfig:query'">
        <span slot="label"><i class="el-icon-edit"></i> 媒资配置</span>
        <el-form style="margin-left: 20px;" label-position="left"   label-width="100px" >

          <aside>
            媒资配置置主要用于配置媒资视频的相关配置<br/>
            例如：是否开启流媒体功能<br/>
          </aside>

          <el-form-item label="流媒体开关" prop="openSteamMedia">
            <el-radio v-for="dict in switchOptions" :key="dict.dictValue" v-model="webConfig.openSteamMedia"  :label="dict.dictValue" border size="medium">{{dict.dictLabel}}</el-radio>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitForm()" v-permission="'website:webConfig:edit'">保 存</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>


    </el-tabs>



  </div>
</template>

<script>
import {  getWebConfig, updateWebConfig } from "@/api/website/webConfig";

export default {
  name: "WebConfig",
  components: {
  },
  data() {
    return {
      webConfig: {},
      showList:[],
      //字典是否
      storageTypeOptions: [],
      qiNiuAreaOptions: [],
      switchOptions: []
    };
  },
  created() {
    this.getWebConfig()
    this.getDicts("storage_type").then(response => {
      this.storageTypeOptions = response.data;
    });

    this.getDicts("qi_niu_area").then(response => {
      this.qiNiuAreaOptions = response.data;
    });
    this.getDicts("common_switch").then(response => {
      this.switchOptions = response.data;
    });

    let data = {
      filePath: '/',
      currentPage: 1,
      pageCount: 10
    }
  },
  methods: {
    // 查询站点信息
    getWebConfig() {
      this.loading = true;
      getWebConfig().then(response => {
        const data = response.data
        if (data.showList) {
          let showList = JSON.parse(data.showList)
          let loginTypeList = JSON.parse(data.loginTypeList)
          data.showList = showList;
          data.loginTypeList = loginTypeList;
          this.webConfig = data;
        } else {
          data.showList = []
          this.webConfig = data;
        }
        this.webConfig.openComment = this.webConfig.openComment + ``
        this.webConfig.openAdmiration = this.webConfig.openAdmiration + ``
        this.webConfig.openMobileComment = this.webConfig.openMobileComment + ``
        this.webConfig.openMobileAdmiration = this.webConfig.openMobileAdmiration + ``
        this.loading = false
      });
    },
    // 更新站点信息
    submitForm() {
      this.webConfig.showList = JSON.stringify(this.webConfig.showList)
      this.webConfig.loginTypeList = JSON.stringify(this.webConfig.loginTypeList)
      updateWebConfig(this.webConfig).then(response => {
        this.getWebConfig();
        this.msgSuccess("更新成功");
      });
    },

  }
};
</script>
