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
    @PreAuthorize("@ss.hasPermi('blog:subject:list')")
    @GetMapping("/list")
    public TableDataInfo list(Subject subject)
    {
        startPage();
        List<Subject> list = subjectService.selectSubjectList(subject);
        return getDataTable(list);
    }

    /**
     * 导出文章专题列表
     */
    @PreAuthorize("@ss.hasPermi('blog:subject:export')")
    @Log(title = "文章专题", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Subject subject)
    {
        List<Subject> list = subjectService.selectSubjectList(subject);
        ExcelUtil<Subject> util = new ExcelUtil<Subject>(Subject.class);
        return util.exportExcel(list, "文章专题数据");
    }

    /**
     * 获取文章专题详细信息
     */
    @PreAuthorize("@ss.hasPermi('blog:subject:query')")
    @GetMapping(value = "/{subjectId}")
    public AjaxResult getInfo(@PathVariable("subjectId") Long subjectId)
    {
        return AjaxResult.success(subjectService.selectSubjectBySubjectId(subjectId));
    }

    /**
     * 新增文章专题
     */
    @PreAuthorize("@ss.hasPermi('blog:subject:add')")
    @Log(title = "文章专题", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Subject subject)
    {
        return toAjax(subjectService.insertSubject(subject));
    }

    /**
     * 修改文章专题
     */
    @PreAuthorize("@ss.hasPermi('blog:subject:edit')")
    @Log(title = "文章专题", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Subject subject)
    {
        return toAjax(subjectService.updateSubject(subject));
    }

    /**
     * 删除文章专题
     */
    @PreAuthorize("@ss.hasPermi('blog:subject:remove')")
    @Log(title = "文章专题", businessType = BusinessType.DELETE)
	@DeleteMapping("/{subjectIds}")
    public AjaxResult remove(@PathVariable Long[] subjectIds)
    {
        return toAjax(subjectService.deleteSubjectBySubjectIds(subjectIds));
    }
}
