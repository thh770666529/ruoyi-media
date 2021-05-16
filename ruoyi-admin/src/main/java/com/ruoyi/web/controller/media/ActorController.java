package com.ruoyi.web.controller.media;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.media.domain.Actor;
import com.ruoyi.media.service.IActorService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

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
    @PreAuthorize("@ss.hasPermi('media:actor:list')")
    @GetMapping("/list")
    public TableDataInfo list(Actor actor)
    {
        startPage();
        List<Actor> list = actorService.selectActorList(actor);
        return getDataTable(list);
    }

    /**
     * 导出演员列表
     */
    @PreAuthorize("@ss.hasPermi('media:actor:export')")
    @Log(title = "演员", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Actor actor)
    {
        List<Actor> list = actorService.selectActorList(actor);
        ExcelUtil<Actor> util = new ExcelUtil<Actor>(Actor.class);
        return util.exportExcel(list, "演员数据");
    }

    /**
     * 获取演员详细信息
     */
    @PreAuthorize("@ss.hasPermi('media:actor:query')")
    @GetMapping(value = "/{actorId}")
    public AjaxResult getInfo(@PathVariable("actorId") Long actorId)
    {
        return AjaxResult.success(actorService.selectActorById(actorId));
    }

    /**
     * 新增演员
     */
    @PreAuthorize("@ss.hasPermi('media:actor:add')")
    @Log(title = "演员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Actor actor)
    {
        return toAjax(actorService.insertActor(actor));
    }

    /**
     * 修改演员
     */
    @PreAuthorize("@ss.hasPermi('media:actor:edit')")
    @Log(title = "演员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Actor actor)
    {
        return toAjax(actorService.updateActor(actor));
    }

    /**
     * 删除演员
     */
    @PreAuthorize("@ss.hasPermi('media:actor:remove')")
    @Log(title = "演员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{actorIds}")
    public AjaxResult remove(@PathVariable Long[] actorIds)
    {
        return toAjax(actorService.deleteActorByIds(actorIds));
    }
}
