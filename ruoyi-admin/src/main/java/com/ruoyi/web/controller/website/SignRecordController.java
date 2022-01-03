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
import com.ruoyi.website.domain.SignRecord;
import com.ruoyi.website.service.ISignRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 签到日志Controller
 *
 * @author thh
 * @date 2021-12-11
 */
@RestController
@RequestMapping("/website/signRecord")
public class SignRecordController extends BaseController
{
    @Autowired
    private ISignRecordService signRecordService;

    /**
     * 查询签到日志列表
     */
    @PreAuthorize("@ss.hasPermi('website:signRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(SignRecord signRecord)
    {
        startPage();
        List<SignRecord> list = signRecordService.selectSignRecordList(signRecord);
        return getDataTable(list);
    }

    /**
     * 导出签到日志列表
     */
    @PreAuthorize("@ss.hasPermi('website:signRecord:export')")
    @Log(title = "签到日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SignRecord signRecord)
    {
        List<SignRecord> list = signRecordService.selectSignRecordList(signRecord);
        ExcelUtil<SignRecord> util = new ExcelUtil<SignRecord>(SignRecord.class);
        util.exportExcel(response, list, "签到日志数据");
    }

    /**
     * 获取签到日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('website:signRecord:query')")
    @GetMapping(value = "/{signRecordId}")
    public AjaxResult getInfo(@PathVariable("signRecordId") Long signRecordId)
    {
        return AjaxResult.success(signRecordService.selectSignRecordBySignRecordId(signRecordId));
    }

    /**
     * 新增签到日志
     */
    @PreAuthorize("@ss.hasPermi('website:signRecord:add')")
    @Log(title = "签到日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SignRecord signRecord)
    {
        return toAjax(signRecordService.insertSignRecord(signRecord));
    }

    /**
     * 修改签到日志
     */
    @PreAuthorize("@ss.hasPermi('website:signRecord:edit')")
    @Log(title = "签到日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SignRecord signRecord)
    {
        return toAjax(signRecordService.updateSignRecord(signRecord));
    }

    /**
     * 删除签到日志
     */
    @PreAuthorize("@ss.hasPermi('website:signRecord:remove')")
    @Log(title = "签到日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{signRecordIds}")
    public AjaxResult remove(@PathVariable Long[] signRecordIds)
    {
        return toAjax(signRecordService.deleteSignRecordBySignRecordIds(signRecordIds));
    }
}
