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
import com.ruoyi.website.domain.WebsiteLink;
import com.ruoyi.website.service.IWebsiteLinkService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 站点友情链接Controller
 *
 * @author thh
 * @date 2021-05-05
 */
@RestController
@RequestMapping("/website/link")
public class WebsiteLinkController extends BaseController
{
    @Autowired
    private IWebsiteLinkService websiteLinkService;

    /**
     * 查询站点友情链接列表
     */
    @PreAuthorize("@ss.hasPermi('website:link:list')")
    @GetMapping("/list")
    public TableDataInfo list(WebsiteLink websiteLink)
    {
        startPage();
        List<WebsiteLink> list = websiteLinkService.selectWebsiteLinkList(websiteLink);
        return getDataTable(list);
    }

    /**
     * 导出站点友情链接列表
     */
    @PreAuthorize("@ss.hasPermi('website:link:export')")
    @Log(title = "站点友情链接", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WebsiteLink websiteLink)
    {
        List<WebsiteLink> list = websiteLinkService.selectWebsiteLinkList(websiteLink);
        ExcelUtil<WebsiteLink> util = new ExcelUtil<WebsiteLink>(WebsiteLink.class);
        return util.exportExcel(list, "站点友情链接数据");
    }

    /**
     * 获取站点友情链接详细信息
     */
    @PreAuthorize("@ss.hasPermi('website:link:query')")
    @GetMapping(value = "/{linkId}")
    public AjaxResult getInfo(@PathVariable("linkId") Long linkId)
    {
        return AjaxResult.success(websiteLinkService.selectWebsiteLinkById(linkId));
    }

    /**
     * 新增站点友情链接
     */
    @PreAuthorize("@ss.hasPermi('website:link:add')")
    @Log(title = "站点友情链接", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WebsiteLink websiteLink)
    {
        return toAjax(websiteLinkService.insertWebsiteLink(websiteLink));
    }

    /**
     * 修改站点友情链接
     */
    @PreAuthorize("@ss.hasPermi('website:link:edit')")
    @Log(title = "站点友情链接", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WebsiteLink websiteLink)
    {
        return toAjax(websiteLinkService.updateWebsiteLink(websiteLink));
    }

    /**
     * 删除站点友情链接
     */
    @PreAuthorize("@ss.hasPermi('website:link:remove')")
    @Log(title = "站点友情链接", businessType = BusinessType.DELETE)
	@DeleteMapping("/{linkIds}")
    public AjaxResult remove(@PathVariable Long[] linkIds)
    {
        return toAjax(websiteLinkService.deleteWebsiteLinkByIds(linkIds));
    }
}
