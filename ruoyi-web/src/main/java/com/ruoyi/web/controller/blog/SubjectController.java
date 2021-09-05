package com.ruoyi.web.controller.blog;

import com.ruoyi.blog.domain.Subject;
import com.ruoyi.blog.service.ISubjectService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章专题Controller
 *
 * @author thh
 * @date 2021-08-28
 */
@RestController
@RequestMapping("/blog/subject")
public class SubjectController extends BaseController
{
    @Autowired
    private ISubjectService subjectService;

    /**
     * 查询文章专题列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Subject subject)
    {
        startPage();
        List<Subject> list = subjectService.selectSubjectList(subject);
        return getDataTable(list);
    }

    /**
     * 获取文章专题详细信息
     */
    @GetMapping(value = "/{subjectId}")
    public AjaxResult getInfo(@PathVariable("subjectId") Long subjectId)
    {
        return AjaxResult.success(subjectService.selectSubjectBySubjectId(subjectId));
    }
}
