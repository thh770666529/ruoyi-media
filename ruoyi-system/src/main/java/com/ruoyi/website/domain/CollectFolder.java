package com.ruoyi.website.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 收藏夹对象 website_collect_folder
 *
 * @author thh
 * @date 2021-10-05
 */
@Data
@TableName("website_collect_folder")
public class CollectFolder extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long collectFolderId;

    /**
     * 用户的id
     */
    private String userId;

    /**
     * 表名称
     */
    @Excel(name = "表名称")
    private String tableName;

    /**
     * 收藏夹名称
     */
    @Excel(name = "收藏夹名称")
    private String name;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private Integer status;
}
