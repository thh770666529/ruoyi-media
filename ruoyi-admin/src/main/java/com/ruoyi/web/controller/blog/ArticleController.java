package com.ruoyi.web.controller.blog;

import com.ruoyi.blog.domain.Article;
import com.ruoyi.blog.service.IArticleService;
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
 * 博客文章Controller
 *
 * @author thh
 * @date 2021-08-28
 */
@RestController
@RequestMapping("/blog/article")
public class ArticleController extends BaseController
{
    @Autowired
    private IArticleService articleService;

    /**
     * 查询博客文章列表
     */
    @PreAuthorize("@ss.hasPermi('blog:article:list')")
    @GetMapping("/list")
    public TableDataInfo list(Article article)
    {
        startPage();
        List<Article> list = articleService.selectArticleList(article);
        return getDataTable(list);
    }

    /**
     * 导出博客文章列表
     */
    @PreAuthorize("@ss.hasPermi('blog:article:export')")
    @Log(title = "博客文章", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Article article)
    {
        List<Article> list = articleService.selectArticleList(article);
        ExcelUtil<Article> util = new ExcelUtil<Article>(Article.class);
        return util.exportExcel(list, "博客文章数据");
    }

    /**
     * 获取博客文章详细信息
     */
    @PreAuthorize("@ss.hasPermi('blog:article:query')")
    @GetMapping(value = "/{articleId}")
    public AjaxResult getInfo(@PathVariable("articleId") Long articleId)
    {
        return AjaxResult.success(articleService.selectArticleByArticleId(articleId));
    }

    /**
     * 新增博客文章
     */
    @PreAuthorize("@ss.hasPermi('blog:article:add')")
    @Log(title = "博客文章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Article article)
    {
        return toAjax(articleService.insertArticle(article));
    }

    /**
     * 修改博客文章
     */
    @PreAuthorize("@ss.hasPermi('blog:article:edit')")
    @Log(title = "博客文章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Article article)
    {
        return toAjax(articleService.updateArticle(article));
    }

    /**
     * 删除博客文章
     */
    @PreAuthorize("@ss.hasPermi('blog:article:remove')")
    @Log(title = "博客文章", businessType = BusinessType.DELETE)
	@DeleteMapping("/{articleIds}")
    public AjaxResult remove(@PathVariable Long[] articleIds)
    {
        return toAjax(articleService.deleteArticleByArticleIds(articleIds));
    }
}
