package com.ruoyi.website.domain.vo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.blog.domain.Article;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.media.domain.Actor;
import com.ruoyi.media.domain.Movie;
import com.ruoyi.media.domain.vo.MovieVO;
import com.ruoyi.question.domain.Question;
import com.ruoyi.website.domain.Collect;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * 收藏对象 website_collect
 *
 * @author thh
 * @date 2021-10-05
 */
@Data
public class CollectVO extends Collect
{
    Movie movie ;

    Article article;

    Actor actor;

    Question question;
}
