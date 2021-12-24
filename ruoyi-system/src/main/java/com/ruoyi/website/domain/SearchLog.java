package com.ruoyi.website.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 搜索日志对象 website_search_log
 *
 * @author thh
 * @date 2021-12-11
 */
@Data
public class SearchLog
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long searchLogId;

    /** 用户id */
    @Excel(name = "用户id")
    private String userId;

    /** 设备id */
    @Excel(name = "设备id")
    private String equId;

    /** 关键字 */
    @Excel(name = "关键字")
    private String keyword;

    /** ip地址 */
    @Excel(name = "ip地址")
    private String ip;

    /** 省份名称 */
    @Excel(name = "省份名称")
    private String province;

    /** 城市名称 */
    @Excel(name = "城市名称")
    private String city;

    /** 来源域名 */
    @Excel(name = "来源域名")
    private String referer;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private String device;

    /** 操作系统 */
    @Excel(name = "操作系统")
    private String operatingSystem;

    /** 浏览器类型 */
    @Excel(name = "浏览器类型")
    private String browser;

    /** 终端类型 */
    @Excel(name = "终端类型")
    private String terminal;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


    /** 搜索值 */
    @TableField(exist = false)
    private String searchValue;

    /** 请求参数 */
    @TableField(exist = false)
    private Map<String, Object> params;


    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }
}
