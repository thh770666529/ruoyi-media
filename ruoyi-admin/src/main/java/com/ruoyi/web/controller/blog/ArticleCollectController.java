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
    @PreAuthorize("@ss.hasPermi('blog:collect:list')")
    @GetMapping("/list")
    public TableDataInfo list(ArticleCollect articleCollect)
    {
        startPage();
        List<ArticleCollect> list = articleCollectService.selectArticleCollectList(articleCollect);
        return getDataTable(list);
    }

    /**
     * 导出文章收藏列表
     */
    @PreAuthorize("@ss.hasPermi('blog:collect:export')")
    @Log(title = "文章收藏", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ArticleCollect articleCollect)
    {
        List<ArticleCollect> list = articleCollectService.selectArticleCollectList(articleCollect);
        ExcelUtil<ArticleCollect> util = new ExcelUtil<ArticleCollect>(ArticleCollect.class);
        return util.exportExcel(list, "文章收藏数据");
    }

    /**
     * 获取文章收藏详细信息
     */
    @PreAuthorize("@ss.hasPermi('blog:collect:query')")
    @GetMapping(value = "/{articleCollectId}")
    public AjaxResult getInfo(@PathVariable("articleCollectId") Long articleCollectId)
    {
        return AjaxResult.success(articleCollectService.selectArticleCollectByArticleCollectId(articleCollectId));
    }

    /**
     * 新增文章收藏
     */
    @PreAuthorize("@ss.hasPermi('blog:collect:add')")
    @Log(title = "文章收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ArticleCollect articleCollect)
    {
        return toAjax(articleCollectService.insertArticleCollect(articleCollect));
    }

    /**
     * 修改文章收藏
     */
    @PreAuthorize("@ss.hasPermi('blog:collect:edit')")
    @Log(title = "文章收藏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ArticleCollect articleCollect)
    {
        return toAjax(articleCollectService.updateArticleCollect(articleCollect));
    }

    /**
     * 删除文章收藏
     */
    @PreAuthorize("@ss.hasPermi('blog:collect:remove')")
    @Log(title = "文章收藏", businessType = BusinessType.DELETE)
	@DeleteMapping("/{articleCollectIds}")
    public AjaxResult remove(@PathVariable Long[] articleCollectIds)
    {
        return toAjax(articleCollectService.deleteArticleCollectByArticleCollectIds(articleCollectIds));
    }
}
