package com.ruoyi.web.controller.media;

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
import com.ruoyi.media.domain.MediaCategory;
import com.ruoyi.media.service.IMediaCategoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 电影分类Controller
 *
 * @author thh
 * @date 2021-09-11
 */
@RestController
@RequestMapping("/media/category")
public class MediaCategoryController extends BaseController {
    @Autowired
    private IMediaCategoryService mediaCategoryService;

    /**
     * 查询电影分类列表
     */
    @PreAuthorize("@ss.hasPermi('media:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(MediaCategory mediaCategory) {
        startPage();
        List<MediaCategory> list = mediaCategoryService.selectMediaCategoryList(mediaCategory);
        return getDataTable(list);
    }

    /**
     * 导出电影分类列表
     */
    @PreAuthorize("@ss.hasPermi('media:category:export')")
    @Log(title = "电影分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MediaCategory mediaCategory) {
        List<MediaCategory> list = mediaCategoryService.selectMediaCategoryList(mediaCategory);
        ExcelUtil<MediaCategory> util = new ExcelUtil<MediaCategory>(MediaCategory.class);
        util.exportExcel(response, list, "电影分类数据");
    }

    /**
     * 获取电影分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('media:category:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId) {
        return success(mediaCategoryService.selectMediaCategoryByCategoryId(categoryId));
    }

    /**
     * 新增电影分类
     */
    @PreAuthorize("@ss.hasPermi('media:category:add')")
    @Log(title = "电影分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MediaCategory mediaCategory) {
        return toAjax(mediaCategoryService.insertMediaCategory(mediaCategory));
    }

    /**
     * 修改电影分类
     */
    @PreAuthorize("@ss.hasPermi('media:category:edit')")
    @Log(title = "电影分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MediaCategory mediaCategory) {
        return toAjax(mediaCategoryService.updateMediaCategory(mediaCategory));
    }

    /**
     * 删除电影分类
     */
    @PreAuthorize("@ss.hasPermi('media:category:remove')")
    @Log(title = "电影分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds) {
        return toAjax(mediaCategoryService.deleteMediaCategoryByCategoryIds(categoryIds));
    }
}
