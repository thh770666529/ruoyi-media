package com.ruoyi.website.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.CommentStatusEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.XssKillerUtil;
import com.ruoyi.common.utils.ip.AddressUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.media.domain.Actor;
import com.ruoyi.media.mapper.ActorMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.util.TokenUtil;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.website.mapper.CommentMapper;
import com.ruoyi.website.domain.Comment;
import com.ruoyi.website.service.ICommentService;

/**
 * 评论Service业务层处理
 *
 * @author thh
 * @date 2021-06-05
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>  implements ICommentService
{
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private TokenUtil tokenUtil;


    /**
     * 查询评论
     *
     * @param commentId 评论ID
     * @return 评论
     */
    @Override
    public Comment selectCommentById(Long commentId)
    {
        return commentMapper.selectCommentById(commentId);
    }


    /**
     * 查询评论列表
     *
     * @param comment 评论
     * @return 评论
     */
    @Override
    public List<Comment> selectCommentList(Comment comment)
    {
        return commentMapper.selectCommentList(comment);
    }


    /**
     * 修改评论
     *
     * @param comment 评论
     * @return 结果
     */
    @Override
    public int updateComment(Comment comment)
    {
        return commentMapper.updateById(comment);
    }

    /**
     * 批量删除评论
     *
     * @param commentIds 需要删除的评论ID
     * @return 结果
     */
    @Override
    public int deleteCommentByIds(Long[] commentIds)
    {
        return commentMapper.deleteCommentByIds(commentIds);
    }

    /**
     * 删除评论信息
     *
     * @param commentId 评论ID
     * @return 结果
     */
    @Override
    public int deleteCommentById(Long commentId)
    {
        return commentMapper.deleteCommentById(commentId);
    }


    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public int reply(Comment comment) {
        comment.setStatus(CommentStatusEnum.APPROVED.toString());
        comment.setParentCommentId(comment.getCommentId());
        comment.setCommentId(null);
        // 设置用户登录信息
        setCurrentLoginUserInfo(comment);
        setCurrentDeviceInfo(comment);
        // 过滤评论内容
        filterContent(comment);
        // 保存当前评论时的位置信息
        comment.setAddress(AddressUtils.getRealAddressByIP(comment.getIp()));
        int insert = commentMapper.insert(comment);
        Long userId = comment.getUserId();
        // 查询用户数据
        SysUser sysUser = sysUserMapper.selectUserById(userId);
        comment.setUser(sysUser);
        return insert;
    }

    @Override
    public List<Comment> selectTreeComment(Comment commentCondition) {
        return this.getCommentListByParentCommentId(commentCondition,null);
    }

    /**
     * 获取到子节点的评论数据
     * @param commentCondition
     * @param parentCommentId
     * @return
     */
    @Override
    public List<Comment> getCommentListByParentCommentId (Comment commentCondition,Long parentCommentId){
        List<Comment> commentList = commentMapper.selectCommentList(commentCondition);
        // 定义一个空数组，用来存放最终的树结构数据
        List<Comment> result = new ArrayList<>();
        // 第一步遍历获取到的数据，将根节点数据存放 result 里
        for (Comment comment: commentList) {
            // 判断是否是根节点，就是 parentId，这里是从 0 开始，如果 parentId 为 0 ，则表示根节点
            Long parentId = comment.getParentCommentId();
            if (parentCommentId!=null && parentCommentId.equals(parentId)) {
                result.add(comment);
            }else if (parentCommentId==null && parentId ==null){
                result.add(comment);
            }
        }
        // 根节点添加完就需要添加每个节点的子节点了，这里需要调用 递归方法 getChildren();
        // 遍历根节点数据，为其设置子节点集合
        for (Comment comment: result) {
            // 获取子节点数据，传入 当前节点 id 和 所有 list
            List<Comment> childList = getChildren(comment.getCommentId(), commentList);
            // 将获取到的子节点集合添加到根节点里
            comment.setChildren(childList);
        }
        return result;
    }

    @Override
    public List<Comment> getAllCommentList(Comment commentCondition,Long parentCommentId){
        List<Comment> commentList = this.getCommentListByParentCommentId(commentCondition, parentCommentId);
        List<Comment> resultList = new ArrayList<>();
        resultList.addAll(commentList);
        for (Comment comment : commentList) {
            List<Comment> children = getChildren(comment.getChildren());
            if (children!=null){
                resultList.addAll(children);
            }
        }
        return resultList;
    }


    private List<Comment> getChildren(List<Comment>  commentList) {
        List<Comment> resultList = new ArrayList<>();
        if (commentList==null){
            return resultList;
        }
        // 遍历所有节点数据
        resultList.addAll(commentList);
        for (Comment comment : commentList) {
            resultList.addAll(getChildren(comment.getChildren()));
        }
        if (resultList.size() == 0) {
            return new ArrayList<>();
        }
        return resultList;
    }

    /**
     * 获取子节点数据
     * @param id 父节点 ID
     * @param  commentList 所有节点集合
     * @return 返回子节点列表
     */
    private List<Comment> getChildren(Long id, List<Comment>  commentList) {
        // 存在子节点数据
        List<Comment> childList = new ArrayList<>();
        // 遍历所有节点数据
        for (Comment comment : commentList) {
            // 如果当前节点 ID 与父节点 ID 一致，表示当前数据是该节点的子节点
            if (String.valueOf(StringUtils.toStr(id)).
                    equals(String.valueOf(StringUtils.toStr(comment.getParentCommentId())))) {
                childList.add(comment);
            }
        }
        // 重点来了，递归调用
        for (Comment item : childList) {
            // 调用自身方法，依次添加子节点数据
            item.setChildren(getChildren(item.getCommentId(), commentList));
        }
        // 如果当前节点无子节点数据添加空数据，递归退出
        if (childList.size() == 0) {
            return new ArrayList<>();
        }
        // 返回最终的子节点数据
        return childList;
    }



    /**
     * 过滤评论内容
     *
     * @param comment
     */
    private void filterContent(Comment comment) {
        String content = comment.getContent();
        if (StringUtils.isEmpty(content) || "\n".equals(content)) {
            throw new ServiceException("说点什么吧");
        }
        // 过滤非法属性和无用的空标签
        if (!XssKillerUtil.isValid(content) || !XssKillerUtil.isValid(comment.getAvatar())) {
            throw new ServiceException("请不要使用特殊标签");
        }
        content = XssKillerUtil.clean(content.trim()).replaceAll("(<p><br></p>)|(<p></p>)", "");
        if (StringUtils.isEmpty(content) || "\n".equals(content)) {
            throw new ServiceException("说点什么吧");
        }
        comment.setContent(content);
    }

    /**
     * 保存当前登录用户的信息
     *
     * @param comment
     */
    private void setCurrentLoginUserInfo(Comment comment) {
        LoginUser loginUser = tokenUtil.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        comment.setUserId(user.getUserId());
        comment.setEmail(user.getEmail());
        comment.setNickname(user.getNickName());
        comment.setAvatar(user.getAvatar());
    }

    /**
     * 保存当前评论时的设备信息
     *
     * @param comment
     */
    private void setCurrentDeviceInfo(Comment comment) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        Browser browser = userAgent.getBrowser();
        String browserInfo = browser.getName();
        Version version = userAgent.getBrowserVersion();
        if (version != null) {
            browserInfo += " " + version.getVersion();
        }
        comment.setBrowser(browserInfo);
        OperatingSystem os = userAgent.getOperatingSystem();
        comment.setOs(os.getName());
        comment.setIp(ip);
    }
}
