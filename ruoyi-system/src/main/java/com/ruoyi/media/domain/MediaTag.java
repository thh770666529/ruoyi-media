package com.ruoyi.media.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.constant.UserConstants;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 电影标签对象 wm_media_tag
 *
 * @author thh
 * @date 2021-09-11
 */
@TableName("wm_media_tag")
@Data
public class MediaTag extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long tagId;

    /**
     * 标签内容
     */
    @Excel(name = "标签内容")
    private String content;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private Integer status;

    /**
     * 标签点击数
     */
    private Long clickCount;

    /**
     * 排序
     */
    @Excel(name = "排序")
    private Long sort;

    /**
     * 样式属性（其他样式扩展）
     */
    private String cssClass;

    /**
     * 表格字典样式
     */
    private String listClass;

    /**
     * 是否默认（Y是 N否）
     */
    @Excel(name = "是否默认", readConverterExp = "Y=是,N=否")
    private String isDefault;

    public boolean getDefault() {
        return UserConstants.YES.equals(this.isDefault) ? true : false;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }
}
