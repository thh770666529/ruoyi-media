package com.ruoyi.media.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 电影评论对象 wm_comment
 *
 * @author thh
 * @date 2021-05-16
 */
@Data
@TableName("wm_comment")
public class Comment
{
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /** 评论id */
    @TableId(type = IdType.AUTO)
    private Long commentId;

    /** 评论人userId */
    @Excel(name = "评论人userId")
    private String userId;

    /** 评论人名称 */
    @Excel(name = "评论人名称")
    private String userName;

    /** 评论的电影id */
    @Excel(name = "评论的电影id")
    private String movieId;

    /** 评论的文章标题 */
    @Excel(name = "评论的文章标题")
    private String movieTitle;

    /** 父评论id */
    @Excel(name = "父评论id")
    private Long parentCommentId;

    /** 父评论的用户id */
    @Excel(name = "父评论的用户id")
    private String parentCommentUserId;

    /** 被回复的评论id */
    @Excel(name = "被回复的评论id")
    private String replyCommentId;

    /** 被回复的评论用户id */
    @Excel(name = "被回复的评论用户id")
    private String replyCommentUserId;

    /** 评论等级[ 1 一级评论 默认 ，2 二级评论] */
    @Excel(name = "评论等级[ 1 一级评论 默认 ，2 二级评论]")
    private Long commentLevel;

    /** 评论的内容 */
    @Excel(name = "评论的内容")
    private String content;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Long likes;

    /** 置顶状态[ 1 置顶，0 不置顶 默认 ] */
    @Excel(name = "置顶状态[ 1 置顶，0 不置顶 默认 ]")
    private Long top;

    @Excel(name = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Excel(name = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
