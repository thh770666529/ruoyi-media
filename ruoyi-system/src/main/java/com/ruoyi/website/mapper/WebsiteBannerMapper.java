package com.ruoyi.website.mapper;

import java.util.List;
import com.ruoyi.website.domain.WebsiteBanner;

/**
 * 首页bannerMapper接口
 * 
 * @author thh
 * @date 2021-05-05
 */
public interface WebsiteBannerMapper 
{
    /**
     * 查询首页banner
     * 
     * @param bannerId 首页bannerID
     * @return 首页banner
     */
    public WebsiteBanner selectWebsiteBannerById(Long bannerId);

    /**
     * 查询首页banner列表
     * 
     * @param websiteBanner 首页banner
     * @return 首页banner集合
     */
    public List<WebsiteBanner> selectWebsiteBannerList(WebsiteBanner websiteBanner);

    /**
     * 新增首页banner
     * 
     * @param websiteBanner 首页banner
     * @return 结果
     */
    public int insertWebsiteBanner(WebsiteBanner websiteBanner);

    /**
     * 修改首页banner
     * 
     * @param websiteBanner 首页banner
     * @return 结果
     */
    public int updateWebsiteBanner(WebsiteBanner websiteBanner);

    /**
     * 删除首页banner
     * 
     * @param bannerId 首页bannerID
     * @return 结果
     */
    public int deleteWebsiteBannerById(Long bannerId);

    /**
     * 批量删除首页banner
     * 
     * @param bannerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteWebsiteBannerByIds(Long[] bannerIds);
}
