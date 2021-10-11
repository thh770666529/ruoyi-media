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
@Data
public class AuthClientConfig {

    String clientId;

    String clientSecret;
}
