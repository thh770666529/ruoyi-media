package com.ruoyi.media.vo;

import lombok.Data;

/**
 * @ClassName CheckPasswordVO
 * @Description 校验视频密码实体类
 * @Author thh
 * @Date 21:15 2021/10/03
 **/
@Data
public class CheckMoviePasswordVO {

    private Long movieId;

    private String password;
}
