package com.ruoyi.website.service.impl;



import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.WebConfigConstants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.website.domain.vo.WebConfigVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.website.mapper.WebConfigMapper;
import com.ruoyi.website.domain.WebConfig;
import com.ruoyi.website.service.IWebConfigService;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import static com.ruoyi.common.constant.Constants.WEB_CONFIG_KEY;

/**
 * 站点配置Service业务层处理
 *
 * @author thh
 * @date 2021-06-08
 */
@Service
public class WebConfigServiceImpl implements IWebConfigService
{
    @Autowired
    private WebConfigMapper webConfigMapper;

    @Autowired
    private RedisCache redisCache;


    @Override
    public WebConfig getWebConfig() {
        String webConfigResult = redisCache.getCacheObject(WEB_CONFIG_KEY);
        if (StringUtils.isNotEmpty(webConfigResult)) {
            WebConfig webConfig = JSONObject.parseObject(webConfigResult, WebConfig.class);
            return webConfig;
        }
        WebConfig webConfig = webConfigMapper.selectById(WebConfigConstants.WEB_CONFIG_ID);
        redisCache.setCacheObject(WEB_CONFIG_KEY,JSONObject.toJSONString(webConfig));
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
            WebConfig webConfig = webConfigMapper.selectById(WebConfigConstants.WEB_CONFIG_ID);
            // 更新网站配置【使用Spring工具类提供的深拷贝】
            BeanUtils.copyProperties(webConfigVO, webConfig);
            webConfig.setUpdateTime(new Date());
            count = webConfigMapper.updateById(webConfig);
        }

        // 修改配置后，清空Redis中的 WEB_CONFIG
        redisCache.deleteObject(Constants.WEB_CONFIG_KEY);
        // 同时清空Redis中的登录方式
        //Collection<String> keys = redisCache.keys(Constants.LOGIN_TOKEN_KEY + "*");
        //redisCache.deleteObject(keys);
        return count;
    }
}
