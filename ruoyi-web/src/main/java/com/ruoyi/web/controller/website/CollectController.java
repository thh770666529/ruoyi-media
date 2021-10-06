package com.ruoyi.web.controller.website;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.blog.domain.Article;
import com.ruoyi.blog.service.IArticleService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RateLimiter;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.LimitType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.media.domain.Actor;
import com.ruoyi.media.domain.Movie;
import com.ruoyi.media.service.IActorService;
import com.ruoyi.media.service.IMovieService;
import com.ruoyi.website.domain.Collect;
import com.ruoyi.website.domain.vo.CheckCollectFlagVO;
import com.ruoyi.website.domain.vo.CollectVO;
import com.ruoyi.website.service.ICollectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收藏Controller
 *
 * @author thh
 * @date 2021-10-05
 */
@RestController
@RequestMapping("/website/collect")
public class CollectController extends BaseController {
    @Autowired
    private ICollectService collectService;

    @Autowired
    private IMovieService movieService;

    @Autowired
    private IArticleService articleService;

    @Autowired
    private IActorService actorService;

    /**
     * 查询收藏列表
     */
    @GetMapping("/myCollectList")
    public TableDataInfo selectMyCollectList(Collect collect) {
        startPage();
        collect.setUserId(getUserId() + "");
        List<CollectVO> list = collectService.selectMyCollectList(collect);
        return getDataTable(list);
    }


    /**
     * 点击收藏
     */
    @Log(title = "收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Collect collect) {
        CheckCollectFlagVO checkCollectFlagVO = new CheckCollectFlagVO();
        BeanUtils.copyProperties(collect, checkCollectFlagVO);
        Boolean flag = collectService.checkCollectFlag(checkCollectFlagVO);
        if (flag) {
            return error("抱歉！无法重复收藏！");
        }
        Long userId = getUserId();
        collect.setUserId(userId + "");
        collect.setStatus(Constants.COMMON_SWITCH_OPEN);
        int row = collectService.insertCollect(collect);
        if (row > 0) {
            String tableName = collect.getTableName();
            if ("wm_movie".equals(tableName)) {
                Movie dbMovie = movieService.getById(collect.getTargetId());
                dbMovie.setCollectionCount(dbMovie.getCollectionCount() + 1);
                movieService.updateById(dbMovie);
            } else if ("blog_article".equals(tableName)) {
                Article dbArticle = articleService.getById(collect.getTargetId());
                dbArticle.setCollectCount(dbArticle.getCollectCount() + 1);
                articleService.updateById(dbArticle);
            } else if ("wm_actor".equals(tableName)) {
                Actor dbActor = actorService.getById(collect.getTargetId());
                dbActor.setCollectCount(dbActor.getCollectCount() + 1);
                actorService.updateById(dbActor);
            }
        }
        return toAjax(row);
    }

    /**
     * 检查是否已收藏
     */
    @PostMapping("/checkCollectFlag")
    public AjaxResult checkCollectFlag(@RequestBody CheckCollectFlagVO checkCollectFlagVO) {
        return AjaxResult.success(collectService.checkCollectFlag(checkCollectFlagVO));
    }

    /**
     * 取消收藏
     */
    @Log(title = "收藏", businessType = BusinessType.DELETE)
    @PostMapping("/cancelCollectByTargetId")
    public AjaxResult cancelCollectByTargetId(@RequestBody CheckCollectFlagVO checkCollectFlagVO) {
        Boolean flag = collectService.checkCollectFlag(checkCollectFlagVO);
        if (!flag) {
            return error("抱歉！无法重复取消收藏！");
        }
        Long userId = getUserId();
        LambdaQueryWrapper<Collect> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Collect::getTargetId, checkCollectFlagVO.getTargetId())
                .eq(Collect::getUserId, userId)
                .eq(Collect::getTableName, checkCollectFlagVO.getTableName());
        boolean removeFlag = collectService.remove(lambdaQueryWrapper);

        if (removeFlag) {
            String tableName = checkCollectFlagVO.getTableName();
            if ("wm_movie".equals(tableName)) {
                Movie dbMovie = movieService.getById(checkCollectFlagVO.getTargetId());
                dbMovie.setCollectionCount(dbMovie.getCollectionCount() - 1);
                movieService.updateById(dbMovie);
            } else if ("blog_article".equals(tableName)) {
                Article dbArticle = articleService.getById(checkCollectFlagVO.getTargetId());
                dbArticle.setCollectCount(dbArticle.getCollectCount() - 1);
                articleService.updateById(dbArticle);
            } else if ("wm_actor".equals(tableName)) {
                Actor dbActor = actorService.getById(checkCollectFlagVO.getTargetId());
                dbActor.setCollectCount(dbActor.getCollectCount() - 1);
                actorService.updateById(dbActor);
            }
        }
        return toAjax(removeFlag);
    }
}
