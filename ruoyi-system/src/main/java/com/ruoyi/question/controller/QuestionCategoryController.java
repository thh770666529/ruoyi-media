package com.ruoyi.question.controller;

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
import com.ruoyi.question.domain.QuestionCategory;
import com.ruoyi.question.service.IQuestionCategoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 问答分类Controller
 *
 * @author thh
 * @date 2021-09-12
 */
@RestController
@RequestMapping("/question/category")
public class QuestionCategoryController extends BaseController {
    @Autowired
    private IQuestionCategoryService questionCategoryService;

    /**
     * 查询问答分类列表
     */
    @PreAuthorize("@ss.hasPermi('question:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(QuestionCategory questionCategory) {
        startPage();
        List<QuestionCategory> list = questionCategoryService.selectQuestionCategoryList(questionCategory);
        return getDataTable(list);
    }

    /**
     * 导出问答分类列表
     */
    @PreAuthorize("@ss.hasPermi('question:category:export')")
    @Log(title = "问答分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QuestionCategory questionCategory) {
        List<QuestionCategory> list = questionCategoryService.selectQuestionCategoryList(questionCategory);
        ExcelUtil<QuestionCategory> util = new ExcelUtil<QuestionCategory>(QuestionCategory.class);
        util.exportExcel(response, list, "问答分类数据");
    }

    /**
     * 获取问答分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('question:category:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Integer categoryId) {
        return success(questionCategoryService.selectQuestionCategoryByCategoryId(categoryId));
    }

    /**
     * 新增问答分类
     */
    @PreAuthorize("@ss.hasPermi('question:category:add')")
    @Log(title = "问答分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QuestionCategory questionCategory) {
        return toAjax(questionCategoryService.insertQuestionCategory(questionCategory));
    }

    /**
     * 修改问答分类
     */
    @PreAuthorize("@ss.hasPermi('question:category:edit')")
    @Log(title = "问答分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QuestionCategory questionCategory) {
        return toAjax(questionCategoryService.updateQuestionCategory(questionCategory));
    }

    /**
     * 删除问答分类
     */
    @PreAuthorize("@ss.hasPermi('question:category:remove')")
    @Log(title = "问答分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Integer[] categoryIds) {
        return toAjax(questionCategoryService.deleteQuestionCategoryByCategoryIds(categoryIds));
    }
}
