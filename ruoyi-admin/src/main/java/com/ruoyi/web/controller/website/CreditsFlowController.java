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
import com.ruoyi.website.domain.CreditsFlow;
import com.ruoyi.website.service.ICreditsFlowService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 积分流水Controller
 *
 * @author thh
 * @date 2021-12-11
 */
@RestController
@RequestMapping("/website/creditsFlow")
public class CreditsFlowController extends BaseController
{
    @Autowired
    private ICreditsFlowService creditsFlowService;

    /**
     * 查询积分流水列表
     */
    @PreAuthorize("@ss.hasPermi('website:creditsFlow:list')")
    @GetMapping("/list")
    public TableDataInfo list(CreditsFlow creditsFlow)
    {
        startPage();
        List<CreditsFlow> list = creditsFlowService.selectCreditsFlowList(creditsFlow);
        return getDataTable(list);
    }

    /**
     * 导出积分流水列表
     */
    @PreAuthorize("@ss.hasPermi('website:creditsFlow:export')")
    @Log(title = "积分流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CreditsFlow creditsFlow)
    {
        List<CreditsFlow> list = creditsFlowService.selectCreditsFlowList(creditsFlow);
        ExcelUtil<CreditsFlow> util = new ExcelUtil<CreditsFlow>(CreditsFlow.class);
        util.exportExcel(response, list, "积分流水数据");
    }

    /**
     * 获取积分流水详细信息
     */
    @PreAuthorize("@ss.hasPermi('website:creditsFlow:query')")
    @GetMapping(value = "/{creditsId}")
    public AjaxResult getInfo(@PathVariable("creditsId") Long creditsId)
    {
        return AjaxResult.success(creditsFlowService.selectCreditsFlowByCreditsId(creditsId));
    }

    /**
     * 新增积分流水
     */
    @PreAuthorize("@ss.hasPermi('website:creditsFlow:add')")
    @Log(title = "积分流水", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CreditsFlow creditsFlow)
    {
        return toAjax(creditsFlowService.insertCreditsFlow(creditsFlow));
    }

    /**
     * 修改积分流水
     */
    @PreAuthorize("@ss.hasPermi('website:creditsFlow:edit')")
    @Log(title = "积分流水", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CreditsFlow creditsFlow)
    {
        return toAjax(creditsFlowService.updateCreditsFlow(creditsFlow));
    }

    /**
     * 删除积分流水
     */
    @PreAuthorize("@ss.hasPermi('website:creditsFlow:remove')")
    @Log(title = "积分流水", businessType = BusinessType.DELETE)
	@DeleteMapping("/{creditsIds}")
    public AjaxResult remove(@PathVariable Long[] creditsIds)
    {
        return toAjax(creditsFlowService.deleteCreditsFlowByCreditsIds(creditsIds));
    }
}
