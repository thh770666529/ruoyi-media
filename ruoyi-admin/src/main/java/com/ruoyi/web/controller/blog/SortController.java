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
    @PreAuthorize("@ss.hasPermi('blog:sort:list')")
    @GetMapping("/list")
    public TableDataInfo list(Sort sort)
    {
        startPage();
        List<Sort> list = sortService.selectSortList(sort);
        return getDataTable(list);
    }

    /**
     * 导出博客分类列表
     */
    @PreAuthorize("@ss.hasPermi('blog:sort:export')")
    @Log(title = "博客分类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Sort sort)
    {
        List<Sort> list = sortService.selectSortList(sort);
        ExcelUtil<Sort> util = new ExcelUtil<Sort>(Sort.class);
        return util.exportExcel(list, "博客分类数据");
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

    /**
     * 新增博客分类
     */
    @PreAuthorize("@ss.hasPermi('blog:sort:add')")
    @Log(title = "博客分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Sort sort)
    {
        return toAjax(sortService.insertSort(sort));
    }

    /**
     * 修改博客分类
     */
    @PreAuthorize("@ss.hasPermi('blog:sort:edit')")
    @Log(title = "博客分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Sort sort)
    {
        return toAjax(sortService.updateSort(sort));
    }

    /**
     * 删除博客分类
     */
    @PreAuthorize("@ss.hasPermi('blog:sort:remove')")
    @Log(title = "博客分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sortIds}")
    public AjaxResult remove(@PathVariable Long[] sortIds)
    {
        return toAjax(sortService.deleteSortBySortIds(sortIds));
    }
}
