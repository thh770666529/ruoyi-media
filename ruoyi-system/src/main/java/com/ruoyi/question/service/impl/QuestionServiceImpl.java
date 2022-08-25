package com.ruoyi.question.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.ip.AddressUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.system.util.TokenUtil;
import com.ruoyi.website.domain.Comment;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.question.mapper.QuestionMapper;
import com.ruoyi.question.domain.Question;
import com.ruoyi.question.service.IQuestionService;

/**
 * 问答Service业务层处理
 *
 * @author thh
 * @date 2021-09-12
 */
@Service
public class QuestionServiceImpl implements IQuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 查询问答
     *
     * @param questionId 问答主键
     * @return 问答
     */
    @Override
    public Question selectQuestionByQuestionId(Long questionId) {
        return questionMapper.selectQuestionByQuestionId(questionId);
    }

    /**
     * 查询问答列表
     *
     * @param question 问答
     * @return 问答
     */
    @Override
    public List<Question> selectQuestionList(Question question) {
        return questionMapper.selectQuestionList(question);
    }

    /**
     * 新增问答
     *
     * @param question 问答
     * @return 结果
     */
    @Override
    public int insertQuestion(Question question) {
        // 设置用户登录信息
        setCurrentLoginUserInfo(question);
        // 保存当前评论时的设备信息
        setCurrentDeviceInfo(question);
        // 保存当前评论时的位置信息
        question.setAddress(AddressUtils.getRealAddressByIP(question.getIp()));
        // 保存默认信息
        question.setCreateBy(String.valueOf(question.getUserId()));
        question.setUpdateBy(String.valueOf(question.getUserId()));
        question.setCreateTime(DateUtils.getNowDate());
        question.setUpdateTime(DateUtils.getNowDate());
        question.setSupportCount(0L);
        question.setOpposeCount(0L);
        question.setClickCount(0L);
        return questionMapper.insertQuestion(question);
    }

    /**
     * 保存当前评论时的设备信息
     *
     * @param question
     */
    private void setCurrentDeviceInfo(Question question) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        Browser browser = userAgent.getBrowser();
        String browserInfo = browser.getName();
        Version version = userAgent.getBrowserVersion();
        if (version != null) {
            browserInfo += " " + version.getVersion();
        }
        question.setBrowser(browserInfo);
        OperatingSystem os = userAgent.getOperatingSystem();
        question.setOs(os.getName());
        question.setIp(ip);
    }

    /**
     * 保存当前登录用户的信息
     *
     * @param question
     */
    private void setCurrentLoginUserInfo(Question question) {
        LoginUser loginUser = tokenUtil.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        question.setUserId(user.getUserId());
        question.setEmail(user.getEmail());
        question.setNickname(user.getNickName());
        question.setAvatar(user.getAvatar());
    }

    /**
     * 修改问答
     *
     * @param question 问答
     * @return 结果
     */
    @Override
    public int updateQuestion(Question question) {
        question.setUpdateTime(DateUtils.getNowDate());
        return questionMapper.updateQuestion(question);
    }

    /**
     * 批量删除问答
     *
     * @param questionIds 需要删除的问答主键
     * @return 结果
     */
    @Override
    public int deleteQuestionByQuestionIds(Long[] questionIds) {
        return questionMapper.deleteQuestionByQuestionIds(questionIds);
    }

    /**
     * 删除问答信息
     *
     * @param questionId 问答主键
     * @return 结果
     */
    @Override
    public int deleteQuestionByQuestionId(Long questionId) {
        return questionMapper.deleteQuestionByQuestionId(questionId);
    }
}
