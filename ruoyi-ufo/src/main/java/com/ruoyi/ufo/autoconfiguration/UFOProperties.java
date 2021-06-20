package com.ruoyi.ufo.autoconfiguration;

import com.ruoyi.ufo.config.AliyunConfig;
import com.ruoyi.ufo.domain.ThumbImage;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "ufo")
public class UFOProperties {

    private String storageType;
    private String localStoragePath;
    private AliyunConfig aliyun = new AliyunConfig();
    private ThumbImage thumbImage = new ThumbImage();
}
