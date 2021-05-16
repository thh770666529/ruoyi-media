package com.ruoyi.media.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.media.domain.Comment;

/**
 * 电影评论Service接口
 *
 * @author thh
 * @date 2021-05-16
 */
public interface ICommentService extends IService<Comment>
{

    /**
     * 查询电影评论列表
     *
     * @param comment 电影评论
     * @return 电影评论集合
     */
    public List<Comment> selectCommentList(Comment comment);
}
