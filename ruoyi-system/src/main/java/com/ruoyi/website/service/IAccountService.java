package com.ruoyi.website.service;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.website.domain.Account;

/**
 * 用户账户Service接口
 * 
 * @author thh
 * @date 2021-12-11
 */
public interface IAccountService 
{
    /**
     * 查询用户账户
     * 
     * @param accountId 用户账户主键
     * @return 用户账户
     */
    public Account selectAccountByAccountId(Long accountId);

    /**
     * 查询用户账户列表
     * 
     * @param account 用户账户
     * @return 用户账户集合
     */
    public List<Account> selectAccountList(Account account);

    /**
     * 新增用户账户
     * 
     * @param account 用户账户
     * @return 结果
     */
    public int insertAccount(Account account);

    /**
     * 修改用户账户
     * 
     * @param account 用户账户
     * @return 结果
     */
    public int updateAccount(Account account);

    /**
     * 批量删除用户账户
     * 
     * @param accountIds 需要删除的用户账户主键集合
     * @return 结果
     */
    public int deleteAccountByAccountIds(Long[] accountIds);

    /**
     * 删除用户账户信息
     * 
     * @param accountId 用户账户主键
     * @return 结果
     */
    public int deleteAccountByAccountId(Long accountId);

    /**
     * 增加用户账号
     * @param user
     * @return
     */
    public boolean insertUserAccount(SysUser user);
}
