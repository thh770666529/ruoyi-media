package com.ruoyi.media.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 播放记录对象 wm_play_logs
 *
 * @author thh
 * @date 2021-12-05
 */
@Data
@ToString
public class PlayLogs {
    private static final long serialVersionUID = 1L;

    /**
     * 主键 随机数
     */
    private Long playLogId;

    /**
     * 用户id
     */
    @Excel(name = "用户id")
    private String userId;

    /**
     * 视频id
     */
    @Excel(name = "视频id")
    private String videoId;

    /**
     * 自定义id
     */
    @Excel(name = "自定义id")
    private String customId;

    /**
     * 播放时长
     */
    @Excel(name = "播放时长")
    private Long playDuration;

    /**
     * 最后播放位置
     */
    @Excel(name = "最后播放位置")
    private Long playPosition;

    /**
     * ip地址
     */
    @Excel(name = "ip地址")
    private String ip;

    /**
     * 省份名称
     */
    @Excel(name = "省份名称")
    private String province;

    /**
     * 城市名称
     */
    @Excel(name = "城市名称")
    private String city;

    /**
     * 来源域名
     */
    @Excel(name = "来源域名")
    private String referer;

    /**
     * 设备类型
     */
    @Excel(name = "设备类型")
    private String device;

    /**
     * 操作系统
     */
    @Excel(name = "操作系统")
    private String operatingSystem;

    /**
     * 浏览器类型
     */
    @Excel(name = "浏览器类型")
    private String browser;

    /**
     * 终端类型
     */
    @Excel(name = "终端类型")
    private String terminal;

    /**
     * 开始播放时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始播放时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 结束播放时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束播放时间")
    private Date endTime;


    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    /**
     * 搜索值
     */
    @TableField(exist = false)
    private String searchValue;


    /**
     * 请求参数
     */
    private Map<String, Object> params;


    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

}
