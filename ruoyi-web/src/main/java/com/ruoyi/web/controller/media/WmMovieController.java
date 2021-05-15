package com.ruoyi.web.controller.media;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.media.domain.WmMovie;
import com.ruoyi.media.domain.WmMovieVideo;
import com.ruoyi.media.service.IWmMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电影管理Controller
 *
 * @author thh
 * @date 2021-05-01
 */
@RestController
@RequestMapping("/media/movie")
public class WmMovieController extends BaseController
{
    @Autowired
    private IWmMovieService wmMovieService;

    /**
     * 查询电影管理列表
     */
    @GetMapping("/list")
    public TableDataInfo list(WmMovie wmMovie)
    {
        startPage();
        List<WmMovie> list = wmMovieService.selectWmMovieList(wmMovie);
        return getDataTable(list);
    }

    /**
     * 获取电影管理详细信息
     */
    @GetMapping(value = "/{movieId}")
    public AjaxResult getInfo(@PathVariable("movieId") Long movieId)
    {
        return AjaxResult.success(wmMovieService.selectWmMovieById(movieId));
    }

    /**
     * 获取电影管理详细信息
     */
    @GetMapping(value = "/movieVideo/{movieVideoId}")
    public AjaxResult getMovieVideoDetail(@PathVariable("movieVideoId") Long movieVideoId)
    {
        WmMovieVideo wmMovieVideo = wmMovieService.selectWmMovieVideoById(movieVideoId);
        Long movieId = wmMovieVideo.getMovieId();
        WmMovie wmMovie = wmMovieService.selectWmMovieById(movieId);
        Map data = new HashMap();
        data.put("wmMovieVideo",wmMovieVideo);
        data.put("wmMovie",wmMovie);
        return AjaxResult.success(data);
    }
}
