package com.ruoyi.website.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.system.util.TokenUtil;
import com.ruoyi.website.domain.Account;
import com.ruoyi.website.domain.vo.SignRecordVO;
import com.ruoyi.website.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.website.mapper.SignRecordMapper;
import com.ruoyi.website.domain.SignRecord;
import com.ruoyi.website.service.ISignRecordService;

import javax.xml.crypto.Data;

/**
 * 签到日志Service业务层处理
 *
 * @author thh
 * @date 2021-12-11
 */
@Service
public class SignRecordServiceImpl implements ISignRecordService
{
    @Autowired
    private SignRecordMapper signRecordMapper;

    @Autowired
    private AccountMapper accountMapper;


    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 查询签到日志
     *
     * @param signRecordId 签到日志主键
     * @return 签到日志
     */
    @Override
    public SignRecord selectSignRecordBySignRecordId(Long signRecordId)
    {
        return signRecordMapper.selectSignRecordBySignRecordId(signRecordId);
    }

    /**
     * 查询签到日志列表
     *
     * @param signRecord 签到日志
     * @return 签到日志
     */
    @Override
    public List<SignRecord> selectSignRecordList(SignRecord signRecord)
    {
        return signRecordMapper.selectSignRecordList(signRecord);
    }

    /**
     * 新增签到日志
     *
     * @param signRecord 签到日志
     * @return 结果
     */
    @Override
    public int insertSignRecord(SignRecord signRecord)
    {
        signRecord.setCreateTime(DateUtils.getNowDate());
        return signRecordMapper.insertSignRecord(signRecord);
    }

    /**
     * 修改签到日志
     *
     * @param signRecord 签到日志
     * @return 结果
     */
    @Override
    public int updateSignRecord(SignRecord signRecord)
    {
        return signRecordMapper.updateSignRecord(signRecord);
    }

    /**
     * 批量删除签到日志
     *
     * @param signRecordIds 需要删除的签到日志主键
     * @return 结果
     */
    @Override
    public int deleteSignRecordBySignRecordIds(Long[] signRecordIds)
    {
        return signRecordMapper.deleteSignRecordBySignRecordIds(signRecordIds);
    }

    /**
     * 删除签到日志信息
     *
     * @param signRecordId 签到日志主键
     * @return 结果
     */
    @Override
    public int deleteSignRecordBySignRecordId(Long signRecordId)
    {
        return signRecordMapper.deleteSignRecordBySignRecordId(signRecordId);
    }

    @Override
    public SignRecordVO getSignRecord() {
        SignRecordVO signRecordVO = new SignRecordVO();
        LoginUser loginUser = tokenUtil.getLoginUser(ServletUtils.getRequest());
        //判断最后更新时间，如果更新时间是今天 直接返回账户数据，如果不是则调用一次存过进行返回
        Account account = accountMapper.selectAccountByUserId(loginUser.getUserId() + "");
        Date signDataUpdateTime = account.getSignDataUpdateTime();
        String date = DateUtils.getDate();
        signRecordVO.setUserId(loginUser.getUserId() + "");
        if (signDataUpdateTime!= null && date.equals(DateUtils.dateTime(signDataUpdateTime))){
            BeanUtils.copyProperties(account,signRecordVO);
            Date lastSignTime = account.getLastSignTime();
            if (lastSignTime != null && date.equals(DateUtils.dateTime(lastSignTime))){
                signRecordVO.setSigninTodayFlag(1);
            } else {
                signRecordVO.setSigninTodayFlag(0);
            }
        } else {
            setUpdateSignData(signRecordVO, loginUser.getUserId() + "");
        }
        signRecordVO.setUserId(null);
        return signRecordVO;
    }


    /**
     * 更新签到数据到账户表
     * @param signRecordVO
     * @param userId
     */
    private void setUpdateSignData(SignRecordVO signRecordVO,String userId){
        Map<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("userId", userId);
        Map<String, Object> myNewSignRecord = signRecordMapper.getMyNewSignRecord(objectObjectHashMap);
        signRecordVO.setSeriesDays(Integer.valueOf(myNewSignRecord.get("seriesDays").toString()) );
        signRecordVO.setContinuityDays(Integer.valueOf(myNewSignRecord.get("continuityDays").toString()));
        signRecordVO.setSigninTodayFlag(Integer.valueOf(myNewSignRecord.get("signinTodayFlag").toString()));
        signRecordVO.setSignDataUpdateTime(DateUtils.parseDate(myNewSignRecord.get("signDataUpdateTime")));
    }

    @Override
    public SignRecordVO sign() {
        LoginUser loginUser = tokenUtil.getLoginUser(ServletUtils.getRequest());
        Account account = accountMapper.selectAccountByUserId(loginUser.getUserId() + "");
        Date lastSignTime = account.getLastSignTime();
        String date = DateUtils.getDate();
        if (lastSignTime != null && date.equals(DateUtils.dateTime(lastSignTime))){
            throw new ServiceException("今天已签到,请勿重复签到！");
        }
        //先判断最后签到时间 如果最后签到时间是今天，报错今天已经签到过了
        // 如果今天没有签到，那么增加一条签到数据。并且更新最后签到时间
        SignRecord signRecord = new SignRecord();
        signRecord.setUserId(loginUser.getUserId() + "");
        signRecord.setSignReward("1");
        signRecord.setSignDate(DateUtils.getNowDate());
        signRecord.setSignType(1); // 签到
        signRecord.setCreateTime(DateUtils.getNowDate());
        int count = signRecordMapper.insertSignRecord(signRecord);
        SignRecordVO signRecordVO = new SignRecordVO();
        if (count>0){
            //签到完成后 调用存过来更新 总签到数据 连续签到时间 最后更新数据时间进行返回
            setUpdateSignData(signRecordVO, loginUser.getUserId() + "");
            Account acc = new Account();
            acc.setAccountId(account.getAccountId());
            acc.setUserId(loginUser.getUserId() + "");
            acc.setLastSignTime(DateUtils.getNowDate());
            accountMapper.updateAccount(acc);
            return signRecordVO;
        } else {
            throw new ServiceException("签到失败！");
        }
    }
}
