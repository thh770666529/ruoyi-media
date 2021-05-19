package com.ruoyi.web.controller.media;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.media.domain.Actor;
import com.ruoyi.media.service.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 演员Controller
 *
 * @author thh
 * @date 2021-05-16
 */
@RestController
@RequestMapping("/media/actor")
public class ActorController extends BaseController
{
    @Autowired
    private IActorService actorService;

    /**
     * 查询演员列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Actor actor)
    {
        startPage();
        List<Actor> list = actorService.selectActorList(actor);
        return getDataTable(list);
    }

    /**
     * 获取演员详细信息
     */
    @GetMapping(value = "/{actorId}")
    public AjaxResult getInfo(@PathVariable("actorId") Long actorId)
    {
        return AjaxResult.success(actorService.getById(actorId));
    }
}
