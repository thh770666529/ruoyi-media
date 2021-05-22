package com.ruoyi.media.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.media.domain.Comment;
import org.springframework.stereotype.Repository;

/**
 * 电影评论Mapper接口
 *
 * @author thh
 * @date 2021-05-16
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment>
{
    /**
     * 查询电影评论列表
     *
     * @param comment 电影评论
     * @return 电影评论集合
     */
    public List<Comment> selectCommentList(Comment comment);
}
