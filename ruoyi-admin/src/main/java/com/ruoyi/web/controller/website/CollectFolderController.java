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
import com.ruoyi.website.domain.CollectFolder;
import com.ruoyi.website.service.ICollectFolderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 收藏夹Controller
 *
 * @author thh
 * @date 2021-10-05
 */
@RestController
@RequestMapping("/website/collectFolder")
public class CollectFolderController extends BaseController {
    @Autowired
    private ICollectFolderService collectFolderService;

    /**
     * 查询收藏夹列表
     */
    @PreAuthorize("@ss.hasPermi('website:collectFolder:list')")
    @GetMapping("/list")
    public TableDataInfo list(CollectFolder collectFolder) {
        startPage();
        List<CollectFolder> list = collectFolderService.selectCollectFolderList(collectFolder);
        return getDataTable(list);
    }

    /**
     * 导出收藏夹列表
     */
    @PreAuthorize("@ss.hasPermi('website:collectFolder:export')")
    @Log(title = "收藏夹", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CollectFolder collectFolder) {
        List<CollectFolder> list = collectFolderService.selectCollectFolderList(collectFolder);
        ExcelUtil<CollectFolder> util = new ExcelUtil<CollectFolder>(CollectFolder.class);
        util.exportExcel(response, list, "收藏夹数据");
    }

    /**
     * 获取收藏夹详细信息
     */
    @PreAuthorize("@ss.hasPermi('website:collectFolder:query')")
    @GetMapping(value = "/{collectFolderId}")
    public AjaxResult getInfo(@PathVariable("collectFolderId") Long collectFolderId) {
        return AjaxResult.success(collectFolderService.removeById(collectFolderId));
    }

    /**
     * 新增收藏夹
     */
    @PreAuthorize("@ss.hasPermi('website:collectFolder:add')")
    @Log(title = "收藏夹", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CollectFolder collectFolder) {
        return toAjax(collectFolderService.insertCollectFolder(collectFolder));
    }

    /**
     * 修改收藏夹
     */
    @PreAuthorize("@ss.hasPermi('website:collectFolder:edit')")
    @Log(title = "收藏夹", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CollectFolder collectFolder) {
        return toAjax(collectFolderService.updateCollectFolder(collectFolder));
    }

    /**
     * 删除收藏夹
     */
    @PreAuthorize("@ss.hasPermi('website:collectFolder:remove')")
    @Log(title = "收藏夹", businessType = BusinessType.DELETE)
    @DeleteMapping("/{collectFolderIds}")
    public AjaxResult remove(@PathVariable Long[] collectFolderIds) {
        return toAjax(collectFolderService.deleteCollectFolderByCollectFolderIds(collectFolderIds));
    }
}
