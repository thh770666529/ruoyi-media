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
import com.ruoyi.question.domain.Question;
import com.ruoyi.question.service.IQuestionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 问答Controller
 *
 * @author thh
 * @date 2021-09-12
 */
@RestController
@RequestMapping("/question/question")
public class QuestionController extends BaseController {
    @Autowired
    private IQuestionService questionService;

    /**
     * 查询问答列表
     */
    @PreAuthorize("@ss.hasPermi('question:question:list')")
    @GetMapping("/list")
    public TableDataInfo list(Question question) {
        startPage();
        List<Question> list = questionService.selectQuestionList(question);
        return getDataTable(list);
    }

    /**
     * 导出问答列表
     */
    @PreAuthorize("@ss.hasPermi('question:question:export')")
    @Log(title = "问答", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Question question) {
        List<Question> list = questionService.selectQuestionList(question);
        ExcelUtil<Question> util = new ExcelUtil<Question>(Question.class);
        util.exportExcel(response, list, "问答数据");
    }

    /**
     * 获取问答详细信息
     */
    @PreAuthorize("@ss.hasPermi('question:question:query')")
    @GetMapping(value = "/{questionId}")
    public AjaxResult getInfo(@PathVariable("questionId") Long questionId) {
        return AjaxResult.success(questionService.selectQuestionByQuestionId(questionId));
    }

    /**
     * 新增问答
     */
    @PreAuthorize("@ss.hasPermi('question:question:add')")
    @Log(title = "问答", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Question question) {
        return toAjax(questionService.insertQuestion(question));
    }

    /**
     * 修改问答
     */
    @PreAuthorize("@ss.hasPermi('question:question:edit')")
    @Log(title = "问答", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Question question) {
        return toAjax(questionService.updateQuestion(question));
    }

    /**
     * 删除问答
     */
    @PreAuthorize("@ss.hasPermi('question:question:remove')")
    @Log(title = "问答", businessType = BusinessType.DELETE)
    @DeleteMapping("/{questionIds}")
    public AjaxResult remove(@PathVariable Long[] questionIds) {
        return toAjax(questionService.deleteQuestionByQuestionIds(questionIds));
    }
}
