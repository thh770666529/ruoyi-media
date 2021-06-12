package com.ruoyi.ufo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisUtil {

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 将值放入缓存
     * @param key 键
     * @param value 值
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取对象
     * @param key 键
     * @param <T> 对象类型
     * @return 返回值
     */
    public <T> T getObject(String key) {
        Object o = redisTemplate.opsForValue().get(key);
        if (o != null) {
            return (T) o;
        }
        return null;
    }

    /**
     * 将值放入缓存并设置时间-秒
     * @param key 键
     * @param value 值
     * @param time 时间（单位：秒），如果值为负数，则永久
     */
    public void set(String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            redisTemplate.opsForValue().set(key, value);
        }
    }

    /**
     * 删除key
     * @param key
     */
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 获取自增长值
     * @param key 键
     * @return 返回增长之后的值
     */
    public Long getIncr(String key) {
        Long count = redisTemplate.opsForValue().increment(key, 1);
        return count;
    }

}
