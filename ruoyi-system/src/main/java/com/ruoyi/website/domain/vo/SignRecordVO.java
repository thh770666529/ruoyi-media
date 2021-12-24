package com.ruoyi.website.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName SignRecordVO
 * @Description 签到数据
 * @Author thh
 * @Date 11:02 2021/12/12
 **/
@Data
public class SignRecordVO {

    /**
     * 用户id
     */
    String userId;

    /**
     * 今天是否签到 0未签到 大于1已签到
     */
    Integer signinTodayFlag;

    /**
     * 累计签到总天数
     */
    Integer seriesDays;

    /**
     * 连续签到天数
     */
    Integer continuityDays;


    /**
     * 签到数据更新时间
     */
    Date signDataUpdateTime;


}
