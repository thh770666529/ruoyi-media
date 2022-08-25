package com.ruoyi.web.controller.website;

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
import com.ruoyi.website.domain.Collect;
import com.ruoyi.website.service.ICollectService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;

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

    /**
     * 查询收藏列表
     */
    @PreAuthorize("@ss.hasPermi('website:collect:list')")
    @GetMapping("/list")
    public TableDataInfo list(Collect collect) {
        startPage();
        List<Collect> list = collectService.selectCollectList(collect);
        return getDataTable(list);
    }

    /**
     * 导出收藏列表
     */
    @PreAuthorize("@ss.hasPermi('website:collect:export')")
    @Log(title = "收藏", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Collect collect) {
        List<Collect> list = collectService.selectCollectList(collect);
        ExcelUtil<Collect> util = new ExcelUtil<Collect>(Collect.class);
        util.exportExcel(response, list, "收藏数据");
    }

    /**
     * 获取收藏详细信息
     */
    @PreAuthorize("@ss.hasPermi('website:collect:query')")
    @GetMapping(value = "/{collectId}")
    public AjaxResult getInfo(@PathVariable("collectId") Long collectId) {
        return AjaxResult.success(collectService.selectCollectByCollectId(collectId));
    }

    /**
     * 新增收藏
     */
    @PreAuthorize("@ss.hasPermi('website:collect:add')")
    @Log(title = "收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Collect collect) {
        return toAjax(collectService.insertCollect(collect));
    }

    /**
     * 修改收藏
     */
    @PreAuthorize("@ss.hasPermi('website:collect:edit')")
    @Log(title = "收藏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Collect collect) {
        return toAjax(collectService.updateCollect(collect));
    }

    /**
     * 删除收藏
     */
    @PreAuthorize("@ss.hasPermi('website:collect:remove')")
    @Log(title = "收藏", businessType = BusinessType.DELETE)
    @DeleteMapping("/{collectIds}")
    public AjaxResult remove(@PathVariable Long[] collectIds) {
        return toAjax(collectService.deleteCollectByCollectIds(collectIds));
    }
}
