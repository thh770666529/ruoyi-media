package com.ruoyi.blog.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 博客文章对象 blog_article
 *
 * @author thh
 * @date 2021-08-28
 */
@Data
@TableName("blog_article")
public class Article extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Long articleId;

    /** 博客标题 */
    @Excel(name = "博客标题")
    private String title;

    /** 文章简介 */
    @Excel(name = "文章简介")
    private String summary;

    /** 文章内容 */
    @Excel(name = "文章内容")
    private String content;

    /** 标签 */
    @Excel(name = "标签")
    private String tagId;

    /** 博客点击数 */
    private Long clickCount;

    /** 博客收藏数 */
    private Long collectCount;

    /** 标题图片 */
    @Excel(name = "标题图片")
    private String images;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 管理员 */
    private String adminUserId;

    /** 是否原创 */
    @Excel(name = "是否原创")
    private String isOriginal;

    /** 作者 */
    private String author;

    /** 文章出处 */
    private String articlesPart;

    /** 博客分类 */
    @Excel(name = "博客分类")
    private String sortId;

    /** 推荐等级 */
    @Excel(name = "推荐等级")
    private Integer level;

    /** 删除标志 */
    private String delFlag;

    /** 是否发布 */
    @Excel(name = "是否发布")
    private String isPublish;

    /** 排序字段 */
    @Excel(name = "排序字段")
    private Integer sort;

    /** 是否开启评论 */
    @Excel(name = "是否开启评论")
    private Integer openComment;

    /** 类型 */
    @Excel(name = "类型")
    private Integer type;

    /** 外链 */
    private String outsideLink;

    /** 投稿用户 */
    private String userId;

    /** 文章来源 */
    private Integer articleSource;

    /** 文章专属二维码地址 */
    private String qrcodePath;

    /** 是否开启密钥模式 */
    private Integer openPassword;

    /** 文章私密访问时的密钥 */
    private String password;

    /** 点赞数 */
    private Long praiseClickCount;
}
