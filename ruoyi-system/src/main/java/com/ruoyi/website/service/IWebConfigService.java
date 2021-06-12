package com.ruoyi.website.service;

import java.util.List;
import com.ruoyi.website.domain.WebConfig;
import com.ruoyi.website.domain.vo.WebConfigVO;

/**
 * 站点配置Service接口
 *
 * @author thh
 * @date 2021-06-08
 */
public interface IWebConfigService
{

    /**
     * 获取网站配置
     *
     * @return
     */
    WebConfig getWebConfig();



    /**
     * 修改网站配置
     *
     * @param webConfigVO
     * @return
     */
    int updateWebConfig(WebConfigVO webConfigVO);
}
