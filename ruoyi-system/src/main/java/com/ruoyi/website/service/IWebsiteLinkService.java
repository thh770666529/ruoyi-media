package com.ruoyi.website.service;

import java.util.List;
import com.ruoyi.website.domain.WebsiteLink;

/**
 * 站点友情链接Service接口
 * 
 * @author thh
 * @date 2021-05-05
 */
public interface IWebsiteLinkService 
{
    /**
     * 查询站点友情链接
     * 
     * @param linkId 站点友情链接ID
     * @return 站点友情链接
     */
    public WebsiteLink selectWebsiteLinkById(Long linkId);

    /**
     * 查询站点友情链接列表
     * 
     * @param websiteLink 站点友情链接
     * @return 站点友情链接集合
     */
    public List<WebsiteLink> selectWebsiteLinkList(WebsiteLink websiteLink);

    /**
     * 新增站点友情链接
     * 
     * @param websiteLink 站点友情链接
     * @return 结果
     */
    public int insertWebsiteLink(WebsiteLink websiteLink);

    /**
     * 修改站点友情链接
     * 
     * @param websiteLink 站点友情链接
     * @return 结果
     */
    public int updateWebsiteLink(WebsiteLink websiteLink);

    /**
     * 批量删除站点友情链接
     * 
     * @param linkIds 需要删除的站点友情链接ID
     * @return 结果
     */
    public int deleteWebsiteLinkByIds(Long[] linkIds);

    /**
     * 删除站点友情链接信息
     * 
     * @param linkId 站点友情链接ID
     * @return 结果
     */
    public int deleteWebsiteLinkById(Long linkId);
}
