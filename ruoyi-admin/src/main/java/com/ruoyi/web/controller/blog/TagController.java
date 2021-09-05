package com.ruoyi.web.controller.blog;

import com.ruoyi.blog.domain.Tag;
import com.ruoyi.blog.service.ITagService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章标签Controller
 *
 * @author thh
 * @date 2021-08-28
 */
@RestController
@RequestMapping("/blog/tag")
public class TagController extends BaseController
{
    @Autowired
    private ITagService tagService;

    /**
     * 查询文章标签列表
     */
    @PreAuthorize("@ss.hasPermi('blog:tag:list')")
    @GetMapping("/list")
    public TableDataInfo list(Tag tag)
    {
        startPage();
        List<Tag> list = tagService.selectTagList(tag);
        return getDataTable(list);
    }

    /**
     * 导出文章标签列表
     */
    @PreAuthorize("@ss.hasPermi('blog:tag:export')")
    @Log(title = "文章标签", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Tag tag)
    {
        List<Tag> list = tagService.selectTagList(tag);
        ExcelUtil<Tag> util = new ExcelUtil<Tag>(Tag.class);
        return util.exportExcel(list, "文章标签数据");
    }

    /**
     * 获取文章标签详细信息
     */
    @PreAuthorize("@ss.hasPermi('blog:tag:query')")
    @GetMapping(value = "/{tagId}")
    public AjaxResult getInfo(@PathVariable("tagId") Long tagId)
    {
        return AjaxResult.success(tagService.selectTagByTagId(tagId));
    }

    /**
     * 新增文章标签
     */
    @PreAuthorize("@ss.hasPermi('blog:tag:add')")
    @Log(title = "文章标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Tag tag)
    {
        return toAjax(tagService.insertTag(tag));
    }

    /**
     * 修改文章标签
     */
    @PreAuthorize("@ss.hasPermi('blog:tag:edit')")
    @Log(title = "文章标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Tag tag)
    {
        return toAjax(tagService.updateTag(tag));
    }

    /**
     * 删除文章标签
     */
    @PreAuthorize("@ss.hasPermi('blog:tag:remove')")
    @Log(title = "文章标签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tagIds}")
    public AjaxResult remove(@PathVariable Long[] tagIds)
    {
        return toAjax(tagService.deleteTagByTagIds(tagIds));
    }
}
