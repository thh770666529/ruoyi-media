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
import com.ruoyi.website.domain.Account;
import com.ruoyi.website.service.IAccountService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 用户账户Controller
 *
 * @author thh
 * @date 2021-12-11
 */
@RestController
@RequestMapping("/website/account")
public class AccountController extends BaseController {
    @Autowired
    private IAccountService accountService;

    /**
     * 查询用户账户列表
     */
    @PreAuthorize("@ss.hasPermi('website:account:list')")
    @GetMapping("/list")
    public TableDataInfo list(Account account) {
        startPage();
        List<Account> list = accountService.selectAccountList(account);
        return getDataTable(list);
    }

    /**
     * 导出用户账户列表
     */
    @PreAuthorize("@ss.hasPermi('website:account:export')")
    @Log(title = "用户账户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Account account) {
        List<Account> list = accountService.selectAccountList(account);
        ExcelUtil<Account> util = new ExcelUtil<Account>(Account.class);
        util.exportExcel(response, list, "用户账户数据");
    }

    /**
     * 获取用户账户详细信息
     */
    @PreAuthorize("@ss.hasPermi('website:account:query')")
    @GetMapping(value = "/{accountId}")
    public AjaxResult getInfo(@PathVariable("accountId") Long accountId) {
        return success(accountService.selectAccountByAccountId(accountId));
    }

    /**
     * 新增用户账户
     */
    @PreAuthorize("@ss.hasPermi('website:account:add')")
    @Log(title = "用户账户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Account account) {
        return toAjax(accountService.insertAccount(account));
    }

    /**
     * 修改用户账户
     */
    @PreAuthorize("@ss.hasPermi('website:account:edit')")
    @Log(title = "用户账户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Account account) {
        return toAjax(accountService.updateAccount(account));
    }

    /**
     * 删除用户账户
     */
    @PreAuthorize("@ss.hasPermi('website:account:remove')")
    @Log(title = "用户账户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{accountIds}")
    public AjaxResult remove(@PathVariable Long[] accountIds) {
        return toAjax(accountService.deleteAccountByAccountIds(accountIds));
    }
}
