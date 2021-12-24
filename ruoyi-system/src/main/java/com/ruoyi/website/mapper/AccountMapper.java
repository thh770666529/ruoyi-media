package com.ruoyi.website.mapper;

import java.util.List;
import com.ruoyi.website.domain.Account;
import org.springframework.stereotype.Repository;

/**
 * 用户账户Mapper接口
 *
 * @author thh
 * @date 2021-12-11
 */
@Repository
public interface AccountMapper
{
    /**
     * 查询用户账户
     *
     * @param accountId 用户账户主键
     * @return 用户账户
     */
    public Account selectAccountByAccountId(Long accountId);

    /**
     * 查询用户账户
     *
     * @param userId 用户主键
     * @return 用户账户
     */
    public Account selectAccountByUserId(String  userId);

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
     * 删除用户账户
     *
     * @param accountId 用户账户主键
     * @return 结果
     */
    public int deleteAccountByAccountId(Long accountId);

    /**
     * 批量删除用户账户
     *
     * @param accountIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAccountByAccountIds(Long[] accountIds);
    }
