package com.ruoyi.media.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.media.mapper.CommentMapper;
import com.ruoyi.media.domain.Comment;
import com.ruoyi.media.service.ICommentService;

/**
 * 电影评论Service业务层处理
 * 
 * @author thh
 * @date 2021-05-16
 */
@Service
public class CommentServiceImpl implements ICommentService 
{
    @Autowired
    private CommentMapper commentMapper;

    /**
     * 查询电影评论
     * 
     * @param id 电影评论ID
     * @return 电影评论
     */
    @Override
    public Comment selectCommentById(Long id)
    {
        return commentMapper.selectCommentById(id);
    }

    /**
     * 查询电影评论列表
     * 
     * @param comment 电影评论
     * @return 电影评论
     */
    @Override
    public List<Comment> selectCommentList(Comment comment)
    {
        return commentMapper.selectCommentList(comment);
    }

    /**
     * 新增电影评论
     * 
     * @param comment 电影评论
     * @return 结果
     */
    @Override
    public int insertComment(Comment comment)
    {
        comment.setCreateTime(DateUtils.getNowDate());
        return commentMapper.insertComment(comment);
    }

    /**
     * 修改电影评论
     * 
     * @param comment 电影评论
     * @return 结果
     */
    @Override
    public int updateComment(Comment comment)
    {
        comment.setUpdateTime(DateUtils.getNowDate());
        return commentMapper.updateComment(comment);
    }

    /**
     * 批量删除电影评论
     * 
     * @param ids 需要删除的电影评论ID
     * @return 结果
     */
    @Override
    public int deleteCommentByIds(Long[] ids)
    {
        return commentMapper.deleteCommentByIds(ids);
    }

    /**
     * 删除电影评论信息
     * 
     * @param id 电影评论ID
     * @return 结果
     */
    @Override
    public int deleteCommentById(Long id)
    {
        return commentMapper.deleteCommentById(id);
    }
}
