package com.ruoyi.web.controller.website;

import java.util.List;

import com.ruoyi.website.domain.vo.WebConfigVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
import com.ruoyi.website.domain.WebConfig;
import com.ruoyi.website.service.IWebConfigService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 站点配置Controller
 *
 * @author thh
 * @date 2021-06-08
 */
@RestController
@RequestMapping("/website/webConfig")
public class WebConfigController extends BaseController
{
    @Autowired
    private IWebConfigService webConfigService;


    /**
     * 修改站点配置
     */
    @PreAuthorize("@ss.hasPermi('website:webConfig:edit')")
    @Log(title = "站点配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WebConfigVO webConfigVO)
    {
        return toAjax(webConfigService.updateWebConfig(webConfigVO));
    }

    /**
     * 获取站点配置
     */
    @PreAuthorize("@ss.hasPermi('website:webConfig:query')")
    @Log(title = "getWebConfig", businessType = BusinessType.OTHER)
	@PostMapping("/getWebConfig")
    public AjaxResult getWebConfig()
    {
        return  AjaxResult.success(webConfigService.getWebConfig());
    }


}
