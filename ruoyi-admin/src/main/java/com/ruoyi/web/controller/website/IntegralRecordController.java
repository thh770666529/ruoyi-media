package com.ruoyi.web.controller.website;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

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
import com.ruoyi.website.domain.IntegralRecord;
import com.ruoyi.website.service.IIntegralRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 积分记录Controller
 *
 * @author thh
 * @date 2022-05-13
 */
@RestController
@RequestMapping("/website/integralRecord")
public class IntegralRecordController extends BaseController {
    @Autowired
    private IIntegralRecordService integralRecordService;

    /**
     * 查询积分记录列表
     */
    @PreAuthorize("@ss.hasPermi('website:integralRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(IntegralRecord integralRecord) {
        startPage();
        List<IntegralRecord> list = integralRecordService.selectIntegralRecordList(integralRecord);
        return getDataTable(list);
    }

    /**
     * 导出积分记录列表
     */
    @PreAuthorize("@ss.hasPermi('website:integralRecord:export')")
    @Log(title = "积分记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IntegralRecord integralRecord) {
        List<IntegralRecord> list = integralRecordService.selectIntegralRecordList(integralRecord);
        ExcelUtil<IntegralRecord> util = new ExcelUtil<IntegralRecord>(IntegralRecord.class);
        util.exportExcel(response, list, "积分记录数据");
    }

    /**
     * 获取积分记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('website:integralRecord:query')")
    @GetMapping(value = "/{integralRecordId}")
    public AjaxResult getInfo(@PathVariable("integralRecordId") Long integralRecordId) {
        return success(integralRecordService.selectIntegralRecordByIntegralRecordId(integralRecordId));
    }

    /**
     * 新增积分记录
     */
    @PreAuthorize("@ss.hasPermi('website:integralRecord:add')")
    @Log(title = "积分记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IntegralRecord integralRecord) {
        return toAjax(integralRecordService.insertIntegralRecord(integralRecord));
    }

    /**
     * 修改积分记录
     */
    @PreAuthorize("@ss.hasPermi('website:integralRecord:edit')")
    @Log(title = "积分记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IntegralRecord integralRecord) {
        return toAjax(integralRecordService.updateIntegralRecord(integralRecord));
    }

    /**
     * 删除积分记录
     */
    @PreAuthorize("@ss.hasPermi('website:integralRecord:remove')")
    @Log(title = "积分记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{integralRecordIds}")
    public AjaxResult remove(@PathVariable Long[] integralRecordIds) {
        return toAjax(integralRecordService.deleteIntegralRecordByIntegralRecordIds(integralRecordIds));
    }
}
