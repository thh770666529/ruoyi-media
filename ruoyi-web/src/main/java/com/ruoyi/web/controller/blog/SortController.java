package com.ruoyi.web.controller.blog;

import com.ruoyi.blog.domain.Sort;
import com.ruoyi.blog.service.ISortService;
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
 * 博客分类Controller
 *
 * @author thh
 * @date 2021-08-28
 */
@RestController
@RequestMapping("/blog/sort")
public class SortController extends BaseController
{
    @Autowired
    private ISortService sortService;

    /**
     * 查询博客分类列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Sort sort)
    {
        startPage();
        List<Sort> list = sortService.selectSortList(sort);
        return getDataTable(list);
    }

    /**
     * 获取博客分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('blog:sort:query')")
    @GetMapping(value = "/{sortId}")
    public AjaxResult getInfo(@PathVariable("sortId") Long sortId)
    {
        return AjaxResult.success(sortService.selectSortBySortId(sortId));
    }
}
