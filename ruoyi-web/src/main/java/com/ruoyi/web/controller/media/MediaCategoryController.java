package com.ruoyi.web.controller.media;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.media.domain.MediaCategory;
import com.ruoyi.media.service.IMediaCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/list")
    public TableDataInfo list(MediaCategory mediaCategory) {
        startPage();
        List<MediaCategory> list = mediaCategoryService.selectMediaCategoryList(mediaCategory);
        return getDataTable(list);
    }

    /**
     * 获取电影分类详细信息
     */
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId) {
        return success(mediaCategoryService.selectMediaCategoryByCategoryId(categoryId));
    }
}
