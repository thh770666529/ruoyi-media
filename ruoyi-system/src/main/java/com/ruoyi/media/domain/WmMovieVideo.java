package com.ruoyi.media.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 电影视频对象 wm_movie_video
 *
 * @author thh
 * @date 2021-05-01
 */
public class WmMovieVideo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long movieVideoId;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** url地址 */
    @Excel(name = "url地址")
    private String url;

    /** 文件后缀 */
    @Excel(name = "文件后缀")
    private String ext;

    /** 播放长度 */
    @Excel(name = "播放长度")
    private String length;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 存储类型 */
    @Excel(name = "存储类型")
    private String storageType;

    /** 删除标志 */
    private String delFlag;

    /** 文件大小 */
    private Long filesize;


    /** 电影id */
    @Excel(name = "电影id")
    private Long movieId;

    public void setMovieVideoId(Long movieVideoId)
    {
        this.movieVideoId = movieVideoId;
    }

    public Long getMovieVideoId()
    {
        return movieVideoId;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
    public void setExt(String ext)
    {
        this.ext = ext;
    }

    public String getExt()
    {
        return ext;
    }
    public void setLength(String length)
    {
        this.length = length;
    }

    public String getLength()
    {
        return length;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setStorageType(String storageType)
    {
        this.storageType = storageType;
    }

    public String getStorageType()
    {
        return storageType;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }
    public void setMovieId(Long movieId)
    {
        this.movieId = movieId;
    }

    public Long getMovieId()
    {
        return movieId;
    }


    public Long getFilesize() {
        return filesize;
    }

    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("movieVideoId", getMovieVideoId())
            .append("title", getTitle())
            .append("url", getUrl())
            .append("ext", getExt())
            .append("length", getLength())
            .append("type", getType())
            .append("storageType", getStorageType())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .append("movieId", getMovieId())
            .append("filesize", getFilesize())
            .toString();
    }
}
