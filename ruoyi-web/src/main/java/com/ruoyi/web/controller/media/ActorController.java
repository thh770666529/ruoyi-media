package com.ruoyi.web.controller.media;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.media.domain.Actor;
import com.ruoyi.media.domain.Movie;
import com.ruoyi.media.service.IActorService;
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
 * 演员Controller
 *
 * @author thh
 * @date 2021-05-16
 */
@RestController
@RequestMapping("/media/actor")
@Slf4j
public class ActorController extends BaseController {
    @Autowired
    private IActorService actorService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 查询演员列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Actor actor) {
        startPage();
        List<Actor> list = actorService.selectActorList(actor);
        return getDataTable(list);
    }

    /**
     * 获取演员详细信息
     */
    @GetMapping(value = "/{actorId}")
    public AjaxResult getInfo(HttpServletRequest request, @PathVariable("actorId") Long actorId) {
        log.info("门户获取演员详细信息id={}", actorId);
        String ip = IpUtils.getIpAddr(request);
        Actor actor = actorService.getById(actorId);
        if (actor == null) {
            AjaxResult.error("演员已删除！请刷新查询列表！");
        }
        String clickJson = redisCache.getCacheObject(CacheConstants.ACTOR_CLICK_KEY + actor.getActorId() + "#" + ip);
        // 判断ip用户是否点击过这个演员
        if (StringUtils.isEmpty(clickJson)) {
            //演员点击数
            Long clickCount = actor.getClickCount() + 1;
            actor.setClickCount(clickCount);
            actorService.updateById(actor);
            //将该用户点击记录存储到redis中, 24小时后过期
            redisCache.setCacheObject(CacheConstants.ACTOR_CLICK_KEY + actor.getActorId() + "#" + ip, actor.getClickCount().toString(),
                    24, TimeUnit.HOURS);
        }
        return success(actor);
    }
}
