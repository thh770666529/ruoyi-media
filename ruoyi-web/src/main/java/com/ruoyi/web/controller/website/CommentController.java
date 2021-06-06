package com.ruoyi.web.controller.website;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.website.domain.Comment;
import com.ruoyi.website.service.ICommentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

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
