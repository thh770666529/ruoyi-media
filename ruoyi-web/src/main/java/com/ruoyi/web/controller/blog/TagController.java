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
    @GetMapping("/list")
    public TableDataInfo list(Tag tag)
    {
        startPage();
        List<Tag> list = tagService.selectTagList(tag);
        return getDataTable(list);
    }

    /**
     * 获取文章标签详细信息
     */
    @GetMapping(value = "/{tagId}")
    public AjaxResult getInfo(@PathVariable("tagId") Long tagId)
    {
        return AjaxResult.success(tagService.selectTagByTagId(tagId));
    }
}
