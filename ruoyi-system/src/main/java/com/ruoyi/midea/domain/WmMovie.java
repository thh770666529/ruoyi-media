package com.ruoyi.midea.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 电影管理对象 wm_movie
 *
 * @author thh
 * @date 2021-05-01
 */
public class WmMovie extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long movieId;

    /** 封面 */
    @Excel(name = "封面")
    private String images;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 电影类型 */
    @Excel(name = "电影类型")
    private String movieType;

    /** 国家 */
    @Excel(name = "国家")
    private String country;

    /** 标签 */
    @Excel(name = "标签")
    private String label;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 发布人 */
    @Excel(name = "发布人")
    private String publishBy;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishTime;

    /** 状态（0发布 1停用） */
    @Excel(name = "状态", readConverterExp = "0=发布,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 阅读数 */
    @Excel(name = "阅读数")
    private Long readCount;

    /** 评论数 */
    @Excel(name = "评论数")
    private Long commentCount;

    /** 关注数 */
    @Excel(name = "关注数")
    private Long followCount;

    /** 收藏数 */
    @Excel(name = "收藏数")
    private Long collectionCount;

    /** 喜欢数 */
    @Excel(name = "喜欢数")
    private Long likesCount;

    /** 不喜欢数 */
    @Excel(name = "不喜欢数")
    private Long unlikesCount;

    /** 分享数 */
    @Excel(name = "分享数")
    private Long shareCount;

    /** 是否可以评论 */
    @Excel(name = "是否可以评论")
    private String isComment;

    /** 是否可以下载 */
    @Excel(name = "是否可以下载")
    private String isDownload;

    /** 电影视频信息 */
    private List<WmMovieVideo> wmMovieVideoList;

    public void setMovieId(Long movieId)
    {
        this.movieId = movieId;
    }

    public Long getMovieId()
    {
        return movieId;
    }
    public void setImages(String images)
    {
        this.images = images;
    }

    public String getImages()
    {
        return images;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setMovieType(String movieType)
    {
        this.movieType = movieType;
    }

    public String getMovieType()
    {
        return movieType;
    }
    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCountry()
    {
        return country;
    }
    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getLabel()
    {
        return label;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }
    public void setPublishBy(String publishBy)
    {
        this.publishBy = publishBy;
    }

    public String getPublishBy()
    {
        return publishBy;
    }
    public void setPublishTime(Date publishTime)
    {
        this.publishTime = publishTime;
    }

    public Date getPublishTime()
    {
        return publishTime;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }
    public void setReadCount(Long readCount)
    {
        this.readCount = readCount;
    }

    public Long getReadCount()
    {
        return readCount;
    }
    public void setCommentCount(Long commentCount)
    {
        this.commentCount = commentCount;
    }

    public Long getCommentCount()
    {
        return commentCount;
    }
    public void setFollowCount(Long followCount)
    {
        this.followCount = followCount;
    }

    public Long getFollowCount()
    {
        return followCount;
    }
    public void setCollectionCount(Long collectionCount)
    {
        this.collectionCount = collectionCount;
    }

    public Long getCollectionCount()
    {
        return collectionCount;
    }
    public void setLikesCount(Long likesCount)
    {
        this.likesCount = likesCount;
    }

    public Long getLikesCount()
    {
        return likesCount;
    }
    public void setUnlikesCount(Long unlikesCount)
    {
        this.unlikesCount = unlikesCount;
    }

    public Long getUnlikesCount()
    {
        return unlikesCount;
    }
    public void setShareCount(Long shareCount)
    {
        this.shareCount = shareCount;
    }

    public Long getShareCount()
    {
        return shareCount;
    }
    public void setIsComment(String isComment)
    {
        this.isComment = isComment;
    }

    public String getIsComment()
    {
        return isComment;
    }
    public void setIsDownload(String isDownload)
    {
        this.isDownload = isDownload;
    }

    public String getIsDownload()
    {
        return isDownload;
    }

    public List<WmMovieVideo> getWmMovieVideoList()
    {
        return wmMovieVideoList;
    }

    public void setWmMovieVideoList(List<WmMovieVideo> wmMovieVideoList)
    {
        this.wmMovieVideoList = wmMovieVideoList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("movieId", getMovieId())
            .append("images", getImages())
            .append("title", getTitle())
            .append("movieType", getMovieType())
            .append("country", getCountry())
            .append("label", getLabel())
            .append("description", getDescription())
            .append("publishBy", getPublishBy())
            .append("publishTime", getPublishTime())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .append("readCount", getReadCount())
            .append("commentCount", getCommentCount())
            .append("followCount", getFollowCount())
            .append("collectionCount", getCollectionCount())
            .append("likesCount", getLikesCount())
            .append("unlikesCount", getUnlikesCount())
            .append("shareCount", getShareCount())
            .append("isComment", getIsComment())
            .append("isDownload", getIsDownload())
            .append("wmMovieVideoList", getWmMovieVideoList())
            .toString();
    }
}
