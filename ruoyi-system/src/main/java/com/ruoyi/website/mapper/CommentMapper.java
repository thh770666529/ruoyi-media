package com.ruoyi.website.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.website.domain.Comment;
import org.springframework.stereotype.Repository;

/**
 * 评论Mapper接口
 *
 * @author thh
 * @date 2021-06-05
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment>
{
    /**
     * 查询评论
     *
     * @param commentId 评论ID
     * @return 评论
     */
    public Comment selectCommentById(Long commentId);

    /**
     * 查询评论列表
     *
     * @param comment 评论
     * @return 评论集合
     */
    public List<Comment> selectCommentList(Comment comment);


    /**
     * 删除评论
     *
     * @param commentId 评论ID
     * @return 结果
     */
    public int deleteCommentById(Long commentId);

    /**
     * 批量删除评论
     *
     * @param commentIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCommentByIds(Long[] commentIds);
}
