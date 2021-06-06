package com.ruoyi.website.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysUser;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论对象 website_comment
 *
 * @author thh
 * @date 2021-06-05
 */
@Data
@TableName("website_comment")
public class Comment
{
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /** 评论id */
    @TableId(type = IdType.AUTO)
    private Long commentId;

    /** 被评论的文章电影或者页面的ID */
    @Excel(name = "被评论的文章电影或者页面的ID")
    private Long sid;

    /** 评论人的ID */
    @Excel(name = "评论人的ID")
    private Long userId;

    /** 父级评论的id */
    @Excel(name = "父级评论的id")
    private Long parentCommentId;

    /** 评论人的QQ（登录用户） */
    @Excel(name = "评论人的QQ", readConverterExp = "登=录用户")
    private String qq;

    /** 评论人的昵称（登录用户） */
    @Excel(name = "评论人的昵称", readConverterExp = "登=录用户")
    private String nickname;

    /** 评论人的头像地址 */
    @Excel(name = "评论人的头像地址")
    private String avatar;

    /** 评论人的邮箱地址（登录用户） */
    @Excel(name = "评论人的邮箱地址", readConverterExp = "登=录用户")
    private String email;

    /** 评论人的网站地址（登录用户） */
    @Excel(name = "评论人的网站地址", readConverterExp = "登=录用户")
    private String url;

    /** 评论的状态 */
    @Excel(name = "评论的状态")
    private String status;

    /** 评论时的ip */
    @Excel(name = "评论时的ip")
    private String ip;

    /** 经度 */
    @Excel(name = "经度")
    private String lng;

    /** 纬度 */
    @Excel(name = "纬度")
    private String lat;

    /** 评论时的地址 */
    @Excel(name = "评论时的地址")
    private String address;

    /** 评论时的系统类型 */
    @Excel(name = "评论时的系统类型")
    private String os;

    /** 评论时的系统的简称 */
    @Excel(name = "评论时的系统的简称")
    private String osShortName;

    /** 评论时的浏览器类型 */
    @Excel(name = "评论时的浏览器类型")
    private String browser;

    /** 评论时的浏览器的简称 */
    @Excel(name = "评论时的浏览器的简称")
    private String browserShortName;

    /** 评论的内容 */
    @Excel(name = "评论的内容")
    private String content;

    /** 支持（赞） */
    @Excel(name = "支持", readConverterExp = "赞=")
    private Integer support;

    /** 反对（踩） */
    @Excel(name = "反对", readConverterExp = "踩=")
    private Integer oppose;

    /** 评论表名称 有电影表和文章表 */
    @Excel(name = "评论表名称 有电影表和文章表")
    private String tableName;


    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date createTime;


    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;



    /**
     * 本条评论下的回复
     */
    @TableField(exist = false)
    private List<Comment> children  = new ArrayList<Comment>();



    /**
     * 本条评论下的评论用户
     */
    @TableField(exist = false)
    private SysUser user;



}
