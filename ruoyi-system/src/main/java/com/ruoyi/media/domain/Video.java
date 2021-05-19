package com.ruoyi.media.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 电影视频对象 wm_video
 *
 * @author thh
 * @date 2021-05-16
 */
@Data
@TableName("wm_video")
public class Video extends BaseEntity
{

    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Long videoId;

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

    /** 电影id */
    @Excel(name = "电影id")
    private Long movieId;

    /** 文件大小字节数 */
    @Excel(name = "文件大小字节数")
    private Long filesize;
}
