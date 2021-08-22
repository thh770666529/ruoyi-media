package com.ruoyi.website.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 站点配置对象 website_web_config
 *
 * @author thh
 * @date 2021-06-08
 */
@Data
@TableName("website_web_config")
public class WebConfig extends BaseEntity
{
    /** 主键 */
    @TableId(type = IdType.AUTO)
    private String configId;

    /** logo(文件UID) */
    @Excel(name = "logo(文件UID)")
    private String logo;

    /** 网站名称 */
    @Excel(name = "网站名称")
    private String name;

    /** 介绍 */
    @Excel(name = "介绍")
    private String summary;

    /** 关键字 */
    @Excel(name = "关键字")
    private String keyword;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 备案号 */
    @Excel(name = "备案号")
    private String recordNum;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 支付宝收款码FileId */
    @Excel(name = "支付宝收款码FileId")
    private String aliPay;

    /** 微信收款码FileId */
    @Excel(name = "微信收款码FileId")
    private String weixinPay;

    /** github地址 */
    @Excel(name = "github地址")
    private String github;

    /** gitee地址 */
    @Excel(name = "gitee地址")
    private String gitee;

    /** QQ号 */
    @Excel(name = "QQ号")
    private String qqNumber;

    /** QQ群 */
    @Excel(name = "QQ群")
    private String qqGroup;

    /** 微信号 */
    @Excel(name = "微信号")
    private String weChat;

    /** 显示的列表（用于控制邮箱、QQ、QQ群、Github、Gitee、微信是否显示在前端） */
    @Excel(name = "显示的列表", readConverterExp = "用=于控制邮箱、QQ、QQ群、Github、Gitee、微信是否显示在前端")
    private String showList;

    /** 登录方式列表（用于控制前端登录方式，如账号密码,码云,Github,QQ,微信） */
    @Excel(name = "登录方式列表", readConverterExp = "用=于控制前端登录方式，如账号密码,码云,Github,QQ,微信")
    private String loginTypeList;

    /** 是否开启评论(0:否 1:是) */
    @Excel(name = "是否开启评论(0:否 1:是)")
    private String openComment;

    /** 是否开启移动端评论(0:否， 1:是) */
    @Excel(name = "是否开启移动端评论(0:否， 1:是)")
    private Integer openMobileComment;

    /** 是否开启赞赏(0:否， 1:是) */
    @Excel(name = "是否开启赞赏(0:否， 1:是)")
    private Integer openAdmiration;

    /** 是否开启移动端赞赏(0:否， 1:是) */
    @Excel(name = "是否开启移动端赞赏(0:否， 1:是)")
    private Integer openMobileAdmiration;

    /** 七牛云公钥 */
    @Excel(name = "七牛云公钥")
    private String qiNiuAccessKey;

    /** 七牛云私钥 */
    @Excel(name = "七牛云私钥")
    private String qiNiuSecretKey;

    /** 七牛云上传空间 */
    @Excel(name = "七牛云上传空间")
    private String qiNiuBucket;

    /** 七牛云存储区域 华东（z0），华北(z1)，华南(z2)，北美(na0)，东南亚(as0) */
    @Excel(name = "七牛云存储区域 华东", readConverterExp = "z=0")
    private String qiNiuArea;

    @Excel(name = "存储类型")
    private String storageType;

    /** 七牛云域名前缀：http://images.ithhit.cn */
    @Excel(name = "七牛云域名前缀：http://images.ithhit.cn")
    private String qiNiuFileBaseUrl;

    /** 本地服务器域名前缀：http://localhost:8600 */
    @Excel(name = "本地服务器域名前缀：http://localhost:8600")
    private String localFileBaseUrl;

    /** 邮箱账号 */
    @Excel(name = "邮箱账号")
    private String email;

    /** 邮箱发件人用户名 */
    @Excel(name = "邮箱发件人用户名")
    private String emailUserName;

    /** SMTP密码 */
    @Excel(name = "SMTP密码")
    private String emailPassword;

    /** SMTP地址 */
    @Excel(name = "SMTP地址")
    private String smtpAddress;

    /** SMTP端口 */
    @Excel(name = "SMTP端口")
    private String smtpPort;

    /** 是否开启邮件通知(0:否， 1:是) */
    @Excel(name = "是否开启邮件通知(0:否， 1:是)")
    private String startEmailNotification;

    /** 编辑器模式，(0：富文本编辑器CKEditor，1：markdown编辑器Veditor) */
    @Excel(name = "编辑器模式，(0：富文本编辑器CKEditor，1：markdown编辑器Veditor)")
    private Long editorModel;

    /** Minio远程连接地址 */
    @Excel(name = "Minio远程连接地址")
    private String minioEndPoint;

    /** Minio公钥 */
    @Excel(name = "Minio公钥")
    private String minioAccessKey;

    /** Minio私钥 */
    @Excel(name = "Minio私钥")
    private String minioSecretKey;

    /** Minio桶 */
    @Excel(name = "Minio桶")
    private String minioBucket;

    /** 图片是否上传Minio (0:否， 1：是) */
    @Excel(name = "图片是否上传Minio (0:否， 1：是)")
    private Integer uploadMinio;

    /** Minio服务器文件域名前缀 */
    @Excel(name = "Minio服务器文件域名前缀")
    private String minioPictureBaseUrl;

    /** 是否开启仪表盘通知(0:否， 1:是) */
    @Excel(name = "是否开启仪表盘通知(0:否， 1:是)")
    private Integer openDashboardNotification;

    /** 仪表盘通知【用于首次登录弹框】 */
    @Excel(name = "仪表盘通知【用于首次登录弹框】")
    private String dashboardNotification;

    /** 流媒体开关 */
    @Excel(name = "流媒体开关")
    private String openSteamMedia;

}
