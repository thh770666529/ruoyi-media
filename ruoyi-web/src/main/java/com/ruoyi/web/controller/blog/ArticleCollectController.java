package com.ruoyi.web.controller.blog;

import com.ruoyi.blog.domain.ArticleCollect;
import com.ruoyi.blog.service.IArticleCollectService;
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
 * 文章收藏Controller
 *
 * @author thh
 * @date 2021-08-28
 */
@RestController
@RequestMapping("/blog/collect")
public class ArticleCollectController extends BaseController
{
    @Autowired
    private IArticleCollectService articleCollectService;

    /**
     * 查询文章收藏列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ArticleCollect articleCollect)
    {
        startPage();
        List<ArticleCollect> list = articleCollectService.selectArticleCollectList(articleCollect);
        return getDataTable(list);
    }
    /**
     * 获取文章收藏详细信息
     */
    @GetMapping(value = "/{articleCollectId}")
    public AjaxResult getInfo(@PathVariable("articleCollectId") Long articleCollectId)
    {
        return AjaxResult.success(articleCollectService.selectArticleCollectByArticleCollectId(articleCollectId));
    }
}
