package com.ruoyi.web.controller;


import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.media.service.IActorService;
import com.ruoyi.media.service.IMovieService;
import com.ruoyi.website.domain.WebsiteBanner;
import com.ruoyi.website.domain.WebsiteLink;
import com.ruoyi.website.service.IWebsiteBannerService;
import com.ruoyi.website.service.IWebsiteLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Desc 首页controller
 * @author thh
 */
@RestController
@RequestMapping("/index")
public class IndexController {


    @Autowired
    private IMovieService movieService;

    @Autowired
    private IActorService actorService;

    @Autowired
    private IWebsiteBannerService websiteBannerService;

    @Autowired
    private IWebsiteLinkService websiteLinkService;

    @GetMapping("/getHotActorList")
    public AjaxResult getHotActorList(){
        //查询前12的热门演员
        return AjaxResult.success(actorService.selectHotActorList(4));
    }

    @GetMapping("/getHotMovieList")
    public AjaxResult getHotMovieList(){
        //查询前4的热门电影
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
}
