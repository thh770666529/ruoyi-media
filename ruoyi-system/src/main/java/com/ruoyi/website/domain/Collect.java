package com.ruoyi.website.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;
import java.util.Map;

/**
 * 收藏对象 website_collect
 *
 * @author thh
 * @date 2021-10-05
 */
@Data
@TableName("website_collect")
public class Collect {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long collectId;

    /**
     * 用户
     */
    @Excel(name = "用户")
    private String userId;

    /**
     * 目标ID
     */
    @Excel(name = "目标ID")
    private String targetId;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private String status;

    /**
     * 表名称
     */
    @Excel(name = "表名称")
    private String tableName;


    /**
     * 搜索值
     */
    @TableField(exist = false)
    private String searchValue;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 请求参数
     */
    @TableField(exist = false)
    private Map<String, Object> params;
}
