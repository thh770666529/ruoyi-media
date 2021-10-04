package com.ruoyi.blog.domain.vo;

import lombok.Data;

/**
 * @ClassName CheckPasswordVO
 * @Description 校验视频密码实体类
 * @Author thh
 * @Date 21:15 2021/10/03
 **/
@Data
public class CheckArticlePasswordVO {

    private Long articleId;

    private String password;
}
