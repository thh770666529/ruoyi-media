package com.ruoyi.web.controller.website;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.system.util.TokenUtil;
import com.ruoyi.website.domain.Comment;
import com.ruoyi.website.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 评论Controller
 *
 * @author thh
 * @date 2021-06-05
 */
@RestController
@RequestMapping("/website/comment")
public class CommentController extends BaseController
{
    @Autowired
    private ICommentService commentService;

    @Autowired
    TokenUtil tokenUtil;
    /**
     * 查询评论列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Comment comment)
    {
        startPage();
        List<Comment> list = commentService.selectCommentList(comment);
        return getDataTable(list);
    }

    /**
     * 查询评论树列表
     */
    @GetMapping("/treeList")
    public TableDataInfo selectTreeComment(Comment comment)
    {
        startPage();
        List<Comment> list = commentService.selectTreeComment(comment);
        return getDataTable(list);
    }


    @PostMapping(value = "/reply")
    @Log(title = "回复评论", businessType = BusinessType.REPLYCOMMENT)
    public AjaxResult reply(@RequestBody Comment comment) {
        int count = commentService.reply(comment);
        if (count>0){
            return AjaxResult.success(comment);
        }else {
            return AjaxResult.error();
        }
    }



    /**
     * 通过UID删除评论
     *
     * @param comment
     * @return
     */
    @Log(title = "删除评论", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public AjaxResult deleteBatch(@RequestBody Comment comment) {
        Long commentId = comment.getCommentId();
        Comment dbComment = commentService.selectCommentById(commentId);
        LoginUser loginUser = tokenUtil.getLoginUser(ServletUtils.getRequest());

        // 判断该评论是否能够删除
        if (dbComment==null || !loginUser.getUserId().equals(dbComment.getUserId())) {
            return error("删除失败");
        }

        Comment commentCondition = new Comment();
        commentCondition.setSid(dbComment.getSid());
        //List<Comment> commentList = commentService.getCommentListByParentCommentId(commentCondition, dbComment.getCommentId());
        List<Comment> commentList = commentService.getAllCommentList(commentCondition, dbComment.getCommentId());
        commentList.add(dbComment);
        if (commentList.size() > 0) {
            List<Long> collect = commentList.stream().map(Comment::getCommentId).collect(Collectors.toList());
            Long[] IdsArr = new Long[collect.size()];
            collect.toArray(IdsArr);
            commentService.deleteCommentByIds(IdsArr);
        }
        return success("删除成功！");
    }

    /**
     * 删除评论
     */
    @PreAuthorize("@ss.hasPermi('website:comment:remove')")
    @Log(title = "评论", businessType = BusinessType.DELETE)
    @DeleteMapping("/{commentIds}")
    public AjaxResult remove(@PathVariable Long[] commentIds)
    {
        return toAjax(commentService.deleteCommentByIds(commentIds));
    }

    /**
     * 获取评论详细信息
     */
    @GetMapping(value = "/{commentId}")
    public AjaxResult getInfo(@PathVariable("commentId") Long commentId)
    {
        return AjaxResult.success(commentService.selectCommentById(commentId));
    }


    /**
     * 修改评论
     */
    @Log(title = "评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Comment comment)
    {
        return toAjax(commentService.updateComment(comment));
    }

}
