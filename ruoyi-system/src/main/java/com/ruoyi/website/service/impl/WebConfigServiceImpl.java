package com.ruoyi.website.service.impl;


import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.website.domain.vo.WebConfigVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.website.mapper.WebConfigMapper;
import com.ruoyi.website.domain.WebConfig;
import com.ruoyi.website.service.IWebConfigService;

import java.util.Date;

/**
 * 站点配置Service业务层处理
 *
 * @author thh
 * @date 2021-06-08
 */
@Service
public class WebConfigServiceImpl implements IWebConfigService {
    @Autowired
    private WebConfigMapper webConfigMapper;

    @Autowired
    private RedisCache redisCache;

    public static final String WEB_CONFIG_ID = "web_config";


    @Override
    public WebConfig getWebConfig() {
        String webConfigResult = redisCache.getCacheObject(CacheConstants.WEB_CONFIG_KEY);
        if (StringUtils.isNotEmpty(webConfigResult)) {
            return JSONObject.parseObject(webConfigResult, WebConfig.class);
        }
        WebConfig webConfig = webConfigMapper.selectById(WEB_CONFIG_ID);
        redisCache.setCacheObject(CacheConstants.WEB_CONFIG_KEY, JSON.toJSONString(webConfig));
        return webConfig;
    }

    @Override
    public int updateWebConfig(WebConfigVO webConfigVO) {
        int count = 0;
        if (StringUtils.isEmpty(webConfigVO.getConfigId())) {
            WebConfig webConfig = new WebConfig();
            // 设置网站配置【使用Spring工具类提供的深拷贝】
            BeanUtils.copyProperties(webConfigVO, webConfig);
            webConfigMapper.insert(webConfig);
        } else {
            WebConfig webConfig = webConfigMapper.selectById(WEB_CONFIG_ID);
            // 更新网站配置【使用Spring工具类提供的深拷贝】
            BeanUtils.copyProperties(webConfigVO, webConfig);
            webConfig.setUpdateTime(new Date());
            count = webConfigMapper.updateById(webConfig);
        }

        // 修改配置后，清空Redis中的 WEB_CONFIG
        redisCache.deleteObject(CacheConstants.WEB_CONFIG_KEY);
        // 同时清空Redis中的登录方式
        //Collection<String> keys = redisCache.keys(Constants.LOGIN_TOKEN_KEY + "*");
        //redisCache.deleteObject(keys);
        return count;
    }
}
