package com.ruoyi.web.controller.media;

import java.util.Arrays;
import java.util.List;

import com.ruoyi.common.utils.StringUtils;
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
import com.ruoyi.media.domain.Comment;
import com.ruoyi.media.service.ICommentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 电影评论Controller
 *
 * @author thh
 * @date 2021-05-16
 */
@RestController
@RequestMapping("/media/comment")
public class CommentController extends BaseController
{
    @Autowired
    private ICommentService commentService;

    /**
     * 查询电影评论列表
     */
    @PreAuthorize("@ss.hasPermi('media:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(Comment comment)
    {
        startPage();
        List<Comment> list = commentService.selectCommentList(comment);
        return getDataTable(list);
    }

    /**
     * 导出电影评论列表
     */
    @PreAuthorize("@ss.hasPermi('media:comment:export')")
    @Log(title = "电影评论", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Comment comment)
    {
        List<Comment> list = commentService.selectCommentList(comment);
        ExcelUtil<Comment> util = new ExcelUtil<Comment>(Comment.class);
        return util.exportExcel(list, "电影评论数据");
    }

    /**
     * 获取电影评论详细信息
     */
    @PreAuthorize("@ss.hasPermi('media:comment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(commentService.getById(id));
    }

    /**
     * 新增电影评论
     */
    @PreAuthorize("@ss.hasPermi('media:comment:add')")
    @Log(title = "电影评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Comment comment)
    {
        return toAjax(commentService.saveOrUpdate(comment));
    }

    /**
     * 修改电影评论
     */
    @PreAuthorize("@ss.hasPermi('media:comment:edit')")
    @Log(title = "电影评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Comment comment)
    {
        return toAjax(commentService.saveOrUpdate(comment));
    }

    /**
     * 删除电影评论
     */
    @PreAuthorize("@ss.hasPermi('media:comment:remove')")
    @Log(title = "电影评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        if (StringUtils.isNotEmpty(ids)){
            List<Long> idList = Arrays.asList(ids);
            return toAjax(commentService.removeByIds(idList));
        }
        return error();
    }
}
