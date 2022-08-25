package com.ruoyi.question.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 问答对象 qa_question
 *
 * @author thh
 * @date 2021-09-12
 */
public class Question extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long questionId;

    /**
     * 标题
     */
    @Excel(name = "标题")
    private String title;

    /**
     * 内容
     */
    @Excel(name = "内容")
    private String content;

    /**
     * 封面
     */
    @Excel(name = "封面")
    private String images;

    /**
     * 分类id
     */
    @Excel(name = "分类id")
    private Integer categoryId;

    /**
     * 分类名称
     */
    @Excel(name = "分类名称")
    private String categoryName;

    /**
     * 排序
     */
    @Excel(name = "排序")
    private Integer sort;

    /**
     * 作者
     */
    private Long userId;

    /**
     * QQ（登录用户）
     */
    @Excel(name = "QQ", readConverterExp = "登=录用户")
    private String qq;

    /**
     * 昵称（登录用户）
     */
    @Excel(name = "昵称", readConverterExp = "登=录用户")
    private String nickname;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 邮箱地址（登录用户）
     */
    private String email;

    /**
     * 评论人的网站地址（登录用户）
     */
    private String url;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private Integer status;

    /**
     * 评论时的ip
     */
    @Excel(name = "评论时的ip")
    private String ip;

    /**
     * 经度
     */
    private String lng;

    /**
     * 纬度
     */
    private String lat;

    /**
     * 地址
     */
    private String address;

    /**
     * 系统类型
     */
    private String os;

    /**
     * 简称
     */
    private String osShortName;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 浏览器的简称
     */
    private String browserShortName;

    /**
     * 支持（赞）
     */
    private Long supportCount;

    /**
     * 点击数
     */
    private Long clickCount;

    /**
     * 反对（踩）
     */
    private Long opposeCount;

    /**
     * 分数
     */
    private BigDecimal score;

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getImages() {
        return images;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
        return sort;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getQq() {
        return qq;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLng() {
        return lng;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLat() {
        return lat;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOs() {
        return os;
    }

    public void setOsShortName(String osShortName) {
        this.osShortName = osShortName;
    }

    public String getOsShortName() {
        return osShortName;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowserShortName(String browserShortName) {
        this.browserShortName = browserShortName;
    }

    public String getBrowserShortName() {
        return browserShortName;
    }

    public void setSupportCount(Long supportCount) {
        this.supportCount = supportCount;
    }

    public Long getSupportCount() {
        return supportCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setOpposeCount(Long opposeCount) {
        this.opposeCount = opposeCount;
    }

    public Long getOpposeCount() {
        return opposeCount;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public BigDecimal getScore() {
        return score;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("questionId", getQuestionId())
                .append("title", getTitle())
                .append("content", getContent())
                .append("images", getImages())
                .append("categoryId", getCategoryId())
                .append("categoryName", getCategoryName())
                .append("sort", getSort())
                .append("userId", getUserId())
                .append("qq", getQq())
                .append("nickname", getNickname())
                .append("avatar", getAvatar())
                .append("email", getEmail())
                .append("url", getUrl())
                .append("status", getStatus())
                .append("ip", getIp())
                .append("lng", getLng())
                .append("lat", getLat())
                .append("address", getAddress())
                .append("os", getOs())
                .append("osShortName", getOsShortName())
                .append("browser", getBrowser())
                .append("browserShortName", getBrowserShortName())
                .append("supportCount", getSupportCount())
                .append("clickCount", getClickCount())
                .append("opposeCount", getOpposeCount())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("createBy", getCreateBy())
                .append("updateBy", getUpdateBy())
                .append("remark", getRemark())
                .append("score", getScore())
                .toString();
    }
}
