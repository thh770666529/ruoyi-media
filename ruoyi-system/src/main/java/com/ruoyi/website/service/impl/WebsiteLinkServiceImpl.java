package com.ruoyi.website.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.website.mapper.WebsiteLinkMapper;
import com.ruoyi.website.domain.WebsiteLink;
import com.ruoyi.website.service.IWebsiteLinkService;

/**
 * 站点友情链接Service业务层处理
 * 
 * @author thh
 * @date 2021-05-05
 */
@Service
public class WebsiteLinkServiceImpl implements IWebsiteLinkService 
{
    @Autowired
    private WebsiteLinkMapper websiteLinkMapper;

    /**
     * 查询站点友情链接
     * 
     * @param linkId 站点友情链接ID
     * @return 站点友情链接
     */
    @Override
    public WebsiteLink selectWebsiteLinkById(Long linkId)
    {
        return websiteLinkMapper.selectWebsiteLinkById(linkId);
    }

    /**
     * 查询站点友情链接列表
     * 
     * @param websiteLink 站点友情链接
     * @return 站点友情链接
     */
    @Override
    public List<WebsiteLink> selectWebsiteLinkList(WebsiteLink websiteLink)
    {
        return websiteLinkMapper.selectWebsiteLinkList(websiteLink);
    }

    /**
     * 新增站点友情链接
     * 
     * @param websiteLink 站点友情链接
     * @return 结果
     */
    @Override
    public int insertWebsiteLink(WebsiteLink websiteLink)
    {
        websiteLink.setCreateTime(DateUtils.getNowDate());
        return websiteLinkMapper.insertWebsiteLink(websiteLink);
    }

    /**
     * 修改站点友情链接
     * 
     * @param websiteLink 站点友情链接
     * @return 结果
     */
    @Override
    public int updateWebsiteLink(WebsiteLink websiteLink)
    {
        websiteLink.setUpdateTime(DateUtils.getNowDate());
        return websiteLinkMapper.updateWebsiteLink(websiteLink);
    }

    /**
     * 批量删除站点友情链接
     * 
     * @param linkIds 需要删除的站点友情链接ID
     * @return 结果
     */
    @Override
    public int deleteWebsiteLinkByIds(Long[] linkIds)
    {
        return websiteLinkMapper.deleteWebsiteLinkByIds(linkIds);
    }

    /**
     * 删除站点友情链接信息
     * 
     * @param linkId 站点友情链接ID
     * @return 结果
     */
    @Override
    public int deleteWebsiteLinkById(Long linkId)
    {
        return websiteLinkMapper.deleteWebsiteLinkById(linkId);
    }
}
