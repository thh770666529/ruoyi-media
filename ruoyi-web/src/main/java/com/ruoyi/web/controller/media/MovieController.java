package com.ruoyi.web.controller.media;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.media.domain.Movie;
import com.ruoyi.media.domain.Video;
import com.ruoyi.media.domain.vo.MovieVO;
import com.ruoyi.media.service.IMovieService;
import com.ruoyi.media.service.IVideoService;
import com.ruoyi.media.vo.CheckMoviePasswordVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 电影管理Controller
 *
 * @author thh
 * @date 2021-05-01
 */
@RestController
@RequestMapping("/media/movie")
@Slf4j
public class MovieController extends BaseController {
    @Autowired
    private IMovieService movieService;

    @Autowired
    private IVideoService videoService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 查询电影管理列表
     */
    @GetMapping("/list")
    public TableDataInfo list(MovieVO movieVO) {
        startPage();
        List<MovieVO> list = movieService.selectWebMovieList(movieVO);
        return getDataTable(list);
    }

    /**
     * 通过演员相关电影列表
     */
    @GetMapping("/getListByActorId/{actorId}")
    public TableDataInfo getListByActorId(@PathVariable("actorId") Long actorId) {
        startPage();
        List<MovieVO> list = movieService.getListByActorId(actorId);
        return getDataTable(list);
    }

    /**
     * 获取电影管理详细信息
     */
    @GetMapping(value = "/{movieId}")
    public AjaxResult getMovie(HttpServletRequest request, @PathVariable("movieId") Long movieId) {
        log.info("门户获取电影详细信息id={}", movieId);
        String ip = IpUtils.getIpAddr(request);
        MovieVO movieVO = movieService.selectWebMovieById(movieId);
        if (movieVO == null) {
            AjaxResult.error("电影已删除！请刷新查询列表！");
        }
        String clickJson = redisCache.getCacheObject(CacheConstants.MOVIE_CLICK_KEY + movieVO.getMovieId() + "#" + ip);
        // 判断ip用户是否点击过这个电影
        if (StringUtils.isEmpty(clickJson)) {
            //电影点击数
            Long clickCount = movieVO.getClickCount() + 1;
            movieVO.setClickCount(clickCount);
            Movie movie = new Movie();
            BeanUtils.copyProperties(movieVO, movie);
            movieService.updateById(movie);
            //将该用户点击记录存储到redis中, 24小时后过期
            redisCache.setCacheObject(CacheConstants.MOVIE_CLICK_KEY + movieVO.getMovieId() + "#" + ip, movieVO.getClickCount().toString(),
                    24, TimeUnit.HOURS);
        }
        return success(movieVO);
    }


    /**
     * 获取电影管理详细信息
     */
    @GetMapping(value = "/video/{videoId}")
    public AjaxResult getMovieVideoDetail(@PathVariable("videoId") Long videoId) {
        Video video = videoService.getById(videoId);
        Long movieId = video.getMovieId();
        MovieVO movieVO = movieService.selectWebMovieById(movieId);
        Map<String, Object> data = new HashMap<>(10);
        data.put("video", video);
        data.put("movie", movieVO);
        return success(data);
    }

    @GetMapping("/getSameTypeMovieList/{movieId}")
    public AjaxResult getSameTypeMovieList(@PathVariable("movieId") Long movieId) {
        log.info("门户获取相关电影");
        return success(movieService.getSameTypeMovieList(movieId));
    }

    @Log(title = "校验密码", businessType = BusinessType.PASSWORD)
    @GetMapping(value = "/checkPassword")
    public AjaxResult checkExtractionCode(CheckMoviePasswordVO checkMoviePasswordVO) {
        LambdaQueryWrapper<Movie> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Movie::getMovieId, checkMoviePasswordVO.getMovieId())
                .eq(Movie::getPassword, checkMoviePasswordVO.getPassword());
        List<Movie> list = movieService.list(lambdaQueryWrapper);
        if (list.isEmpty()) {
            return error("校验失败");
        } else {
            return success();
        }
    }
}
