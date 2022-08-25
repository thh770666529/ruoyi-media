package com.ruoyi.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName JustAuthConfig
 * @Description 第三方认证中心配置类
 * @Author thh
 * @Date 13:28 2021/10/10
 **/
@Component
@ConfigurationProperties(prefix = "just-auth")
public class JustAuthConfig {

    private AuthClientConfig gitee = new AuthClientConfig();

    private AuthClientConfig gihub = new AuthClientConfig();

    private AuthClientConfig qq = new AuthClientConfig();

    private String redirectUri;

    /**
     * 默认密码
     */
    private String defaultPwd;

    /**
     * 站点地址
     */
    private String websiteUrl;

    public String getDefaultPwd() {
        return defaultPwd;
    }

    public void setDefaultPwd(String defaultPwd) {
        this.defaultPwd = defaultPwd;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public AuthClientConfig getGitee() {
        return gitee;
    }

    public void setGitee(AuthClientConfig gitee) {
        this.gitee = gitee;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public AuthClientConfig getGihub() {
        return gihub;
    }

    public void setGihub(AuthClientConfig gihub) {
        this.gihub = gihub;
    }

    public AuthClientConfig getQq() {
        return qq;
    }

    public void setQq(AuthClientConfig qq) {
        this.qq = qq;
    }

    public AuthClientConfig getConfig(String source) {
        if ("gitee".equals(source)) {
            return getGitee();
        } else if ("github".equals(source)) {
            return getGihub();
        } else if ("qq".equals(source)) {
            return getQq();
        }
        return null;
    }
}
