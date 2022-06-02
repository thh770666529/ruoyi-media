package com.ruoyi.web.controller.website;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.util.TokenUtil;
import com.ruoyi.website.domain.IntegralRecord;
import com.ruoyi.website.service.IIntegralRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 积分记录Controller
 * 
 * @author thh
 * @date 2022-05-13
 */
@RestController
@RequestMapping("/website/integralRecord")
public class IntegralRecordController extends BaseController
{
    @Autowired
    private IIntegralRecordService integralRecordService;


    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 查询积分记录列表
     */
    @GetMapping("/myList")
    public TableDataInfo myList(IntegralRecord integralRecord)
    {
        startPage();
        LoginUser loginUser = tokenUtil.getLoginUser(ServletUtils.getRequest());
        integralRecord.setUserId(String.valueOf(loginUser.getUserId()));
        List<IntegralRecord> list = integralRecordService.selectIntegralRecordList(integralRecord);
        return getDataTable(list);
    }


    /**
     * 获取积分记录详细信息
     */
    @GetMapping(value = "/{integralRecordId}")
    public AjaxResult getInfo(@PathVariable("integralRecordId") Long integralRecordId)
    {
        return AjaxResult.success(integralRecordService.selectIntegralRecordByIntegralRecordId(integralRecordId));
    }
}
