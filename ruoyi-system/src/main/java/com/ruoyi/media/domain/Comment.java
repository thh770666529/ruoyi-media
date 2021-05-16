package com.ruoyi.media.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 电影评论对象 wm_comment
 *
 * @author thh
 * @date 2021-05-16
 */
@Data
public class Comment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评论id */
    private Long id;

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
    private String parentCommentId;

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

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserId()
    {
        return userId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setMovieId(String movieId)
    {
        this.movieId = movieId;
    }

    public String getMovieId()
    {
        return movieId;
    }
    public void setMovieTitle(String movieTitle)
    {
        this.movieTitle = movieTitle;
    }

    public String getMovieTitle()
    {
        return movieTitle;
    }
    public void setParentCommentId(String parentCommentId)
    {
        this.parentCommentId = parentCommentId;
    }

    public String getParentCommentId()
    {
        return parentCommentId;
    }
    public void setParentCommentUserId(String parentCommentUserId)
    {
        this.parentCommentUserId = parentCommentUserId;
    }

    public String getParentCommentUserId()
    {
        return parentCommentUserId;
    }
    public void setReplyCommentId(String replyCommentId)
    {
        this.replyCommentId = replyCommentId;
    }

    public String getReplyCommentId()
    {
        return replyCommentId;
    }
    public void setReplyCommentUserId(String replyCommentUserId)
    {
        this.replyCommentUserId = replyCommentUserId;
    }

    public String getReplyCommentUserId()
    {
        return replyCommentUserId;
    }
    public void setCommentLevel(Long commentLevel)
    {
        this.commentLevel = commentLevel;
    }

    public Long getCommentLevel()
    {
        return commentLevel;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setLikes(Long likes)
    {
        this.likes = likes;
    }

    public Long getLikes()
    {
        return likes;
    }
    public void setTop(Long top)
    {
        this.top = top;
    }

    public Long getTop()
    {
        return top;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("movieId", getMovieId())
            .append("movieTitle", getMovieTitle())
            .append("parentCommentId", getParentCommentId())
            .append("parentCommentUserId", getParentCommentUserId())
            .append("replyCommentId", getReplyCommentId())
            .append("replyCommentUserId", getReplyCommentUserId())
            .append("commentLevel", getCommentLevel())
            .append("content", getContent())
            .append("status", getStatus())
            .append("likes", getLikes())
            .append("top", getTop())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
