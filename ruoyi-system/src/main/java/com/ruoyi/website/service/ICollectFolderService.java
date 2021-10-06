package com.ruoyi.website.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.website.domain.CollectFolder;

/**
 * 收藏夹Service接口
 *
 * @author thh
 * @date 2021-10-05
 */
public interface ICollectFolderService extends IService<CollectFolder>
{
    /**
     * 查询收藏夹
     *
     * @param collectFolderId 收藏夹主键
     * @return 收藏夹
     */
    public CollectFolder selectCollectFolderByCollectFolderId(Long collectFolderId);

    /**
     * 查询收藏夹列表
     *
     * @param collectFolder 收藏夹
     * @return 收藏夹集合
     */
    public List<CollectFolder> selectCollectFolderList(CollectFolder collectFolder);

    /**
     * 新增收藏夹
     *
     * @param collectFolder 收藏夹
     * @return 结果
     */
    public int insertCollectFolder(CollectFolder collectFolder);

    /**
     * 修改收藏夹
     *
     * @param collectFolder 收藏夹
     * @return 结果
     */
    public int updateCollectFolder(CollectFolder collectFolder);

    /**
     * 批量删除收藏夹
     *
     * @param collectFolderIds 需要删除的收藏夹主键集合
     * @return 结果
     */
    public int deleteCollectFolderByCollectFolderIds(Long[] collectFolderIds);

}
