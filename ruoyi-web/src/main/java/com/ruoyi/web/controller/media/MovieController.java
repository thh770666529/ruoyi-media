package com.ruoyi.web.controller.media;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.media.domain.Video;
import com.ruoyi.media.domain.vo.MovieVO;
import com.ruoyi.media.service.IMovieService;
import com.ruoyi.media.service.IVideoService;
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
public class MovieController extends BaseController
{
    @Autowired
    private IMovieService movieService;

    @Autowired
    private IVideoService videoService;

    /**
     * 查询电影管理列表
     */
    @GetMapping("/list")
    public TableDataInfo list(MovieVO movieVO)
    {
        startPage();
        List<MovieVO> list = movieService.selectMovieList(movieVO);
        return getDataTable(list);
    }

    /**
     * 获取电影管理详细信息
     */
    @GetMapping(value = "/{movieId}")
    public AjaxResult getMovie(@PathVariable("movieId") Long movieId)
    {
        return AjaxResult.success(movieService.selectMovieById(movieId));
    }


    /**
     * 获取电影管理详细信息
     */
    @GetMapping(value = "/video/{videoId}")
    public AjaxResult getMovieVideoDetail(@PathVariable("videoId") Long videoId)
    {
        Video video = videoService.getById(videoId);
        Long movieId = video.getMovieId();
        MovieVO movieVO = movieService.selectMovieById(movieId);
        Map data = new HashMap();
        data.put("video",video);
        data.put("movie",movieVO);
        return AjaxResult.success(data);
    }
}
