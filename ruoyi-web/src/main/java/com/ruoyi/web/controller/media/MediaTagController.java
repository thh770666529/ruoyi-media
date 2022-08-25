package com.ruoyi.web.controller.media;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.media.domain.MediaTag;
import com.ruoyi.media.service.IMediaTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 电影标签Controller
 *
 * @author thh
 * @date 2021-09-11
 */
@RestController
@RequestMapping("/media/tag")
public class MediaTagController extends BaseController {
    @Autowired
    private IMediaTagService mediaTagService;

    /**
     * 查询电影标签列表
     */
    @GetMapping("/list")
    public TableDataInfo list(MediaTag mediaTag) {
        startPage();
        List<MediaTag> list = mediaTagService.selectMediaTagList(mediaTag);
        return getDataTable(list);
    }


    /**
     * 获取电影标签详细信息
     */
    @GetMapping(value = "/{tagId}")
    public AjaxResult getInfo(@PathVariable("tagId") Long tagId) {
        return AjaxResult.success(mediaTagService.selectMediaTagByTagId(tagId));
    }
}
