package com.ruoyi.ufo.autoconfiguration;

import com.ruoyi.ufo.config.AliyunConfig;
import com.ruoyi.ufo.factory.UFOFactory;
import com.ruoyi.ufo.operation.delete.product.FastDFSDeleter;
import com.ruoyi.ufo.operation.download.product.FastDFSDownloader;
import com.ruoyi.ufo.operation.read.product.FastDFSReader;
import com.ruoyi.ufo.operation.upload.product.FastDFSUploader;
import com.ruoyi.ufo.util.RedisUtil;
import com.ruoyi.ufo.util.concurrent.locks.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
//@ConditionalOnClass(UFOService.class)
@EnableConfigurationProperties({UFOProperties.class})
public class UFOAutoConfiguration {
    public static String localStoragePath;
    public static AliyunConfig aliyunConfig;

    @Bean
    public UFOFactory ufoFactory(UFOProperties ufoProperties) {
        localStoragePath = ufoProperties.getLocalStoragePath();
        aliyunConfig = ufoProperties.getAliyun();
        return new UFOFactory(ufoProperties.getStorageType());
    }
    @Bean
    public FastDFSUploader fastDFSUploader() {
        return new FastDFSUploader();
    }
    @Bean
    public FastDFSDownloader fastDFSDownloader() {
        return new FastDFSDownloader();
    }
    @Bean
    public FastDFSDeleter fastDFSDeleter() {
        return new FastDFSDeleter();
    }
    @Bean
    public FastDFSReader fastDFSReader() {
        return new FastDFSReader();
    }

    @Bean
    public RedisLock redisLock() {
        return new RedisLock();
    }
    @Bean
    public RedisUtil redisUtil() {
        return new RedisUtil();
    }

}
