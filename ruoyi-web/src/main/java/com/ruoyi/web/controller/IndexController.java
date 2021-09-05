package com.ruoyi.web.controller;


import com.ruoyi.blog.domain.Article;
import com.ruoyi.blog.domain.Tag;
import com.ruoyi.blog.service.IArticleService;
import com.ruoyi.blog.service.ITagService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.media.service.IActorService;
import com.ruoyi.media.service.IMovieService;
import com.ruoyi.website.domain.WebsiteBanner;
import com.ruoyi.website.domain.WebsiteLink;
import com.ruoyi.website.service.IWebsiteBannerService;
import com.ruoyi.website.service.IWebsiteLinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Desc 首页controller
 * @author thh
 */
@RestController
@RequestMapping("/index")
@Slf4j
public class IndexController extends BaseController {


    @Autowired
    private IMovieService movieService;

    @Autowired
    private IActorService actorService;

    @Autowired
    private IWebsiteBannerService websiteBannerService;

    @Autowired
    private IWebsiteLinkService websiteLinkService;

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ITagService tagService;

    @GetMapping("/getHotActorList")
    public AjaxResult getHotActorList(){
        //查询前10的热门演员
        return AjaxResult.success(actorService.selectHotActorList(10));
    }

    @GetMapping("/getHotMovieList")
    public AjaxResult getHotMovieList(){
        //查询前12的热门电影
        return AjaxResult.success(movieService.selectHotMovieList(12));
    }

    @GetMapping("/getAllBannerList")
    public AjaxResult getAllBannerList(){
        List<WebsiteBanner> list = websiteBannerService.selectWebsiteBannerList(null);
        return AjaxResult.success(list);
    }

    @GetMapping("/getLinkList")
    public AjaxResult getLinkList(){
        List<WebsiteLink> list = websiteLinkService.selectWebsiteLinkList(null);
        return AjaxResult.success(list);
    }


    @GetMapping("/getHotArticleList")
    public AjaxResult getHotArticleList(){
        log.info("门户获取首页排行博客");
        List<Article> list = articleService.selectHotArticleList(10);
        return AjaxResult.success(list);
    }

    /**
     * 查询最新的博客文章列表
     */
    @GetMapping("/getNewArticleList")
    public TableDataInfo getNewArticleList(Article article)
    {
        log.info("门户获取最新的博客文章列表");
        startPage();
        List<Article> list = articleService.selectNewArticleList();
        return getDataTable(list);
    }


    @GetMapping("/getHotTagList")
    public AjaxResult getHotTagList(){
        log.info("门户获取最热标签");
        List<Tag> list = tagService.selectHotTagList(10);
        return AjaxResult.success(list);
    }
}
