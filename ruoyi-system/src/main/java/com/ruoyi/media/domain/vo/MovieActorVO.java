package com.ruoyi.media.domain.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

/**
 * @ClassName MovieActorVO
 * @Description 电影演员关联表
 * @Author thh
 * @Date 8:36 2021/05/22
 **/
@Data
public class MovieActorVO {
    /** 演员ID */
    @Excel(name = "演员ID")
    private Long actorId;

    /** 电影ID */
    @Excel(name = "电影ID")
    private Long movieId;

    /** 类型 */
    @Excel(name = "类型")
    private String type;


    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 头像 */
    @Excel(name = "头像")
    private String avatar;

    /** 标签  */
    @Excel(name = "标签 ")
    private String label;
}
