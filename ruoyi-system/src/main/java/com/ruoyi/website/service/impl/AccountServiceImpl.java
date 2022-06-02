package com.ruoyi.website.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.website.vo.UserIntegralVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.website.mapper.AccountMapper;
import com.ruoyi.website.domain.Account;
import com.ruoyi.website.service.IAccountService;

/**
 * 用户账户Service业务层处理
 * 
 * @author thh
 * @date 2021-12-11
 */
@Service
public class AccountServiceImpl implements IAccountService 
{
    @Autowired
    private AccountMapper accountMapper;

    /**
     * 查询用户账户
     * 
     * @param accountId 用户账户主键
     * @return 用户账户
     */
    @Override
    public Account selectAccountByAccountId(Long accountId)
    {
        return accountMapper.selectAccountByAccountId(accountId);
    }

    /**
     * 查询用户账户列表
     * 
     * @param account 用户账户
     * @return 用户账户
     */
    @Override
    public List<Account> selectAccountList(Account account)
    {
        return accountMapper.selectAccountList(account);
    }

    /**
     * 新增用户账户
     * 
     * @param account 用户账户
     * @return 结果
     */
    @Override
    public int insertAccount(Account account)
    {
        account.setCreateTime(DateUtils.getNowDate());
        return accountMapper.insertAccount(account);
    }

    /**
     * 修改用户账户
     * 
     * @param account 用户账户
     * @return 结果
     */
    @Override
    public int updateAccount(Account account)
    {
        account.setUpdateTime(DateUtils.getNowDate());
        return accountMapper.updateAccount(account);
    }

    /**
     * 批量删除用户账户
     * 
     * @param accountIds 需要删除的用户账户主键
     * @return 结果
     */
    @Override
    public int deleteAccountByAccountIds(Long[] accountIds)
    {
        return accountMapper.deleteAccountByAccountIds(accountIds);
    }

    /**
     * 删除用户账户信息
     * 
     * @param accountId 用户账户主键
     * @return 结果
     */
    @Override
    public int deleteAccountByAccountId(Long accountId)
    {
        return accountMapper.deleteAccountByAccountId(accountId);
    }

    @Override
    public boolean insertUserAccount(SysUser user) {
        Account account = new Account();
        account.setUserId(String.valueOf(user.getUserId()));
        account.setAccountAmount(0L);
        account.setStatus(1);
        account.setCreateTime(DateUtils.getNowDate());
        account.setUpdateTime(DateUtils.getNowDate());
        account.setSeriesDays(0);
        account.setContinuityDays(0);
        account.setSignNums(0);
        return accountMapper.insertAccount(account) > 0;
    }

    @Override
    public Account selectAccountByUserId(String userId) {
        return accountMapper.selectAccountByUserId(userId);
    }

    @Override
    public List<UserIntegralVO> selectBestUserIntegralList() {
        return accountMapper.selectBestUserIntegralList();
    }
}
