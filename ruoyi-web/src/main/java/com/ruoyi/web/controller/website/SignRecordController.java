package com.ruoyi.web.controller.website;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.website.domain.SignRecord;
import com.ruoyi.website.service.ISignRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/list")
    public TableDataInfo list(SignRecord signRecord)
    {
        startPage();
        List<SignRecord> list = signRecordService.selectSignRecordList(signRecord);
        return getDataTable(list);
    }


    /**
     * 获取个人签到数据
     */
    @GetMapping("/getSignRecord")
    public AjaxResult getSignRecord()
    {
        return AjaxResult.success(signRecordService.getSignRecord());
    }

    /**
     * 签到
     */
    @PostMapping("/signIn")
    public AjaxResult signIn()
    {
        return AjaxResult.success(signRecordService.sign());
    }

}
