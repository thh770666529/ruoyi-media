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
import com.ruoyi.website.domain.SignConfig;
import com.ruoyi.website.service.ISignConfigService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 签到配置Controller
 *
 * @author thh
 * @date 2021-12-11
 */
@RestController
@RequestMapping("/website/signConfig")
public class SignConfigController extends BaseController {
    @Autowired
    private ISignConfigService signConfigService;

    /**
     * 查询签到配置列表
     */
    @PreAuthorize("@ss.hasPermi('website:signConfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(SignConfig signConfig) {
        startPage();
        List<SignConfig> list = signConfigService.selectSignConfigList(signConfig);
        return getDataTable(list);
    }

    /**
     * 导出签到配置列表
     */
    @PreAuthorize("@ss.hasPermi('website:signConfig:export')")
    @Log(title = "签到配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SignConfig signConfig) {
        List<SignConfig> list = signConfigService.selectSignConfigList(signConfig);
        ExcelUtil<SignConfig> util = new ExcelUtil<SignConfig>(SignConfig.class);
        util.exportExcel(response, list, "签到配置数据");
    }

    /**
     * 获取签到配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('website:signConfig:query')")
    @GetMapping(value = "/{signConfigId}")
    public AjaxResult getInfo(@PathVariable("signConfigId") Long signConfigId) {
        return AjaxResult.success(signConfigService.selectSignConfigBySignConfigId(signConfigId));
    }

    /**
     * 新增签到配置
     */
    @PreAuthorize("@ss.hasPermi('website:signConfig:add')")
    @Log(title = "签到配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SignConfig signConfig) {
        return toAjax(signConfigService.insertSignConfig(signConfig));
    }

    /**
     * 修改签到配置
     */
    @PreAuthorize("@ss.hasPermi('website:signConfig:edit')")
    @Log(title = "签到配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SignConfig signConfig) {
        return toAjax(signConfigService.updateSignConfig(signConfig));
    }

    /**
     * 删除签到配置
     */
    @PreAuthorize("@ss.hasPermi('website:signConfig:remove')")
    @Log(title = "签到配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{signConfigIds}")
    public AjaxResult remove(@PathVariable Long[] signConfigIds) {
        return toAjax(signConfigService.deleteSignConfigBySignConfigIds(signConfigIds));
    }
}
