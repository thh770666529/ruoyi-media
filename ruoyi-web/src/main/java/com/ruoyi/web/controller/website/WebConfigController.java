package com.ruoyi.web.controller.website;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.website.service.IWebConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 获取站点配置
     */
    @Log(title = "getWebConfig", businessType = BusinessType.OTHER)
	@PostMapping("/getWebConfig")
    public AjaxResult getWebConfig()
    {
        return  AjaxResult.success(webConfigService.getWebConfig());
    }
}
