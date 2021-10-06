package com.ruoyi.website.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.website.domain.CollectFolder;

/**
 * 收藏夹Mapper接口
 *
 * @author thh
 * @date 2021-10-05
 */
public interface CollectFolderMapper extends BaseMapper<CollectFolder>
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
     * 批量删除收藏夹
     *
     * @param collectFolderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCollectFolderByCollectFolderIds(Long[] collectFolderIds);
}
