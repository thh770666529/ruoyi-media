package com.ruoyi.media.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 电影演员关联对象 wm_movie_actor
 *
 * @author thh
 * @date 2021-05-16
 */
@Data
public class MovieActor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 演员ID */
    @Excel(name = "演员ID")
    private Long actorId;

    /** 电影ID */
    @Excel(name = "电影ID")
    private Long movieId;

    /** 类型 */
    @Excel(name = "类型")
    private String type;
}
