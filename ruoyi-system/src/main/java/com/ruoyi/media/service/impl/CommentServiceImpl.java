package com.ruoyi.media.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class CommentServiceImpl  extends ServiceImpl<CommentMapper, Comment>  implements ICommentService
{
    @Autowired
    private CommentMapper commentMapper;

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

}
