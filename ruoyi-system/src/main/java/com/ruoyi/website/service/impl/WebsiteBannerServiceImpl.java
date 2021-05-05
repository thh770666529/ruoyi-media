package com.ruoyi.website.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.website.mapper.WebsiteBannerMapper;
import com.ruoyi.website.domain.WebsiteBanner;
import com.ruoyi.website.service.IWebsiteBannerService;

/**
 * 首页bannerService业务层处理
 * 
 * @author thh
 * @date 2021-05-05
 */
@Service
public class WebsiteBannerServiceImpl implements IWebsiteBannerService 
{
    @Autowired
    private WebsiteBannerMapper websiteBannerMapper;

    /**
     * 查询首页banner
     * 
     * @param bannerId 首页bannerID
     * @return 首页banner
     */
    @Override
    public WebsiteBanner selectWebsiteBannerById(Long bannerId)
    {
        return websiteBannerMapper.selectWebsiteBannerById(bannerId);
    }

    /**
     * 查询首页banner列表
     * 
     * @param websiteBanner 首页banner
     * @return 首页banner
     */
    @Override
    public List<WebsiteBanner> selectWebsiteBannerList(WebsiteBanner websiteBanner)
    {
        return websiteBannerMapper.selectWebsiteBannerList(websiteBanner);
    }

    /**
     * 新增首页banner
     * 
     * @param websiteBanner 首页banner
     * @return 结果
     */
    @Override
    public int insertWebsiteBanner(WebsiteBanner websiteBanner)
    {
        websiteBanner.setCreateTime(DateUtils.getNowDate());
        return websiteBannerMapper.insertWebsiteBanner(websiteBanner);
    }

    /**
     * 修改首页banner
     * 
     * @param websiteBanner 首页banner
     * @return 结果
     */
    @Override
    public int updateWebsiteBanner(WebsiteBanner websiteBanner)
    {
        websiteBanner.setUpdateTime(DateUtils.getNowDate());
        return websiteBannerMapper.updateWebsiteBanner(websiteBanner);
    }

    /**
     * 批量删除首页banner
     * 
     * @param bannerIds 需要删除的首页bannerID
     * @return 结果
     */
    @Override
    public int deleteWebsiteBannerByIds(Long[] bannerIds)
    {
        return websiteBannerMapper.deleteWebsiteBannerByIds(bannerIds);
    }

    /**
     * 删除首页banner信息
     * 
     * @param bannerId 首页bannerID
     * @return 结果
     */
    @Override
    public int deleteWebsiteBannerById(Long bannerId)
    {
        return websiteBannerMapper.deleteWebsiteBannerById(bannerId);
    }
}
