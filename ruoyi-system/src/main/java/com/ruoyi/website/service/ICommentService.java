package com.ruoyi.website.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.website.domain.Comment;

/**
 * 评论Service接口
 *
 * @author thh
 * @date 2021-06-05
 */
public interface ICommentService extends IService<Comment>
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
     * 修改评论
     *
     * @param comment 评论
     * @return 结果
     */
    public int updateComment(Comment comment);

    /**
     * 批量删除评论
     *
     * @param commentIds 需要删除的评论ID
     * @return 结果
     */
    public int deleteCommentByIds(Long[] commentIds);

    /**
     * 删除评论信息
     *
     * @param commentId 评论ID
     * @return 结果
     */
    public int deleteCommentById(Long commentId);

    /**
     * 回复评论
     * @param comment
     */
    public int reply(Comment comment);

    /**
     * 查询文章电影下的评论列表
     * @param comment
     * @return
     */
    public List<Comment> selectTreeComment(Comment comment);


    public List<Comment> getCommentListByParentCommentId (Comment commentCondition,Long parentCommentId);


    public List<Comment> getAllCommentList(Comment commentCondition,Long parentCommentId);
}
