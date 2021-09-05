package com.ruoyi.web.controller.blog;

import com.ruoyi.blog.domain.Article;
import com.ruoyi.blog.domain.Sort;
import com.ruoyi.blog.domain.Tag;
import com.ruoyi.blog.domain.vo.ArticleVO;
import com.ruoyi.blog.service.IArticleService;
import com.ruoyi.blog.service.ISortService;
import com.ruoyi.blog.service.ITagService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.BaseRedisKeyConstants;
import com.ruoyi.common.constant.BlogConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 博客文章Controller
 *
 * @author thh
 * @date 2021-08-28
 */
@RestController
@RequestMapping("/blog/article")
@Slf4j
public class ArticleController extends BaseController
{
    @Autowired
    private IArticleService articleService;

    @Autowired
    private ITagService tagService;

    @Autowired
    private ISortService sortService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 门户搜索博客文章列表
     */
    @GetMapping("/searchArticleList")
    public TableDataInfo searchArticleList(Article article)
    {
        log.info("门户通关关键字搜索博客文章列表");
        startPage();
        List<Article> list = articleService.selectSearchArticleList(article);
        return getDataTable(list);
    }

    /**
     * 门户搜索博客文章列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Article article)
    {
        log.info("门户搜索博客文章列表");
        startPage();
        List<Article> list = articleService.selectWebArticleList(article);
        return getDataTable(list);
    }

    /**
     * 获取博客文章详细信息
     */
    @GetMapping(value = "/{articleId}")
    public AjaxResult getInfo(HttpServletRequest request, @PathVariable("articleId") Long articleId)
    {
        log.info("门户获取博客文章详细信息id={}", articleId);
        String ip = IpUtils.getIpAddr(request);
        Article article = articleService.selectWebArticleByArticleId(articleId);
        if (article == null || article.getStatus() != 1 || !"1".equals(article.getIsPublish())) {
            AjaxResult.error("博客文章已删除！请刷新查询列表！");
        }
        String clickJson = redisCache.getCacheObject(BaseRedisKeyConstants.BLOG_CLICK + ":" + article.getArticleId() + "#" + ip);
        // 判断ip用户是否点击过这个文章
        if (StringUtils.isEmpty(clickJson)) {
             //增加博客点击数
            Long clickCount = article.getClickCount() + 1;
            article.setClickCount(clickCount);
            articleService.updateArticle(article);
            //将该用户点击记录存储到redis中, 24小时后过期
            redisCache.setCacheObject(BaseRedisKeyConstants.BLOG_CLICK + ":" + article.getArticleId() + "#" + ip, article.getClickCount().toString(),
                    24, TimeUnit.HOURS);
        }
        ArticleVO articleVO = new ArticleVO();
        // 设置文章版权申明
        BeanUtils.copyProperties(article, articleVO);
        setBlogCopyright(articleVO);
        //设置博客标签
        String tagId = articleVO.getTagId();
        if (StringUtils.isNotEmpty(tagId)) {
            List<String> tagIdList = Arrays.asList(tagId.split(","));
            List<Tag> tagList = tagService.selectTagListByIds(tagIdList);
            articleVO.setTagList(tagList);
        }
        //设置博客分类
        String sortId = articleVO.getSortId();
        if (StringUtils.isNotBlank(tagId)){
            Sort sort = sortService.selectSortBySortId(Long.valueOf(sortId));
            articleVO.setSortData(sort);
        }
        //设置作者
        String createBy = articleVO.getCreateBy();
        SysUser sysUser = sysUserService.selectUserByUserName(createBy);
        if (sysUser != null) {
            articleVO.setAuthorUser(sysUser);
            articleVO.setAuthor(sysUser.getNickName());
        }
        return AjaxResult.success(articleVO);
    }

    /**
     * 设置博客版权
     * @param articleVO
     */
    private void setBlogCopyright(ArticleVO articleVO) {
        //如果是原创的话
        if ("1".equals(articleVO.getIsOriginal())) {
            articleVO.setCopyright(BlogConstants.ORIGINAL_TEMPLATE);
        } else {
            String reprintedTemplate = BlogConstants.REPRINTED_TEMPLATE;
            String[] variable = {articleVO.getArticlesPart(), articleVO.getAuthor()};
            String str = String.format(reprintedTemplate, variable);
            articleVO.setCopyright(str);
        }
    }

    @GetMapping("/praiseArticle/{articleId}")
    public AjaxResult praiseArticleById(@PathVariable("articleId") Long articleId) {
        log.info("门户点赞博客文章id={}", articleId);
        articleService.praiseArticleById(articleId);
        return AjaxResult.success();
    }
}
