package com.ruoyi.media.service;

import java.util.List;
import com.ruoyi.media.domain.Comment;

/**
 * 电影评论Service接口
 * 
 * @author thh
 * @date 2021-05-16
 */
public interface ICommentService 
{
    /**
     * 查询电影评论
     * 
     * @param id 电影评论ID
     * @return 电影评论
     */
    public Comment selectCommentById(Long id);

    /**
     * 查询电影评论列表
     * 
     * @param comment 电影评论
     * @return 电影评论集合
     */
    public List<Comment> selectCommentList(Comment comment);

    /**
     * 新增电影评论
     * 
     * @param comment 电影评论
     * @return 结果
     */
    public int insertComment(Comment comment);

    /**
     * 修改电影评论
     * 
     * @param comment 电影评论
     * @return 结果
     */
    public int updateComment(Comment comment);

    /**
     * 批量删除电影评论
     * 
     * @param ids 需要删除的电影评论ID
     * @return 结果
     */
    public int deleteCommentByIds(Long[] ids);

    /**
     * 删除电影评论信息
     * 
     * @param id 电影评论ID
     * @return 结果
     */
    public int deleteCommentById(Long id);
}
