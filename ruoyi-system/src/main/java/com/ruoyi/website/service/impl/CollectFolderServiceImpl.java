package com.ruoyi.website.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.website.mapper.CollectFolderMapper;
import com.ruoyi.website.domain.CollectFolder;
import com.ruoyi.website.service.ICollectFolderService;

/**
 * 收藏夹Service业务层处理
 *
 * @author thh
 * @date 2021-10-05
 */
@Service
public class CollectFolderServiceImpl extends ServiceImpl<CollectFolderMapper, CollectFolder> implements ICollectFolderService {
    @Autowired
    private CollectFolderMapper collectFolderMapper;

    /**
     * 查询收藏夹
     *
     * @param collectFolderId 收藏夹主键
     * @return 收藏夹
     */
    @Override
    public CollectFolder selectCollectFolderByCollectFolderId(Long collectFolderId) {
        return collectFolderMapper.selectCollectFolderByCollectFolderId(collectFolderId);
    }

    /**
     * 查询收藏夹列表
     *
     * @param collectFolder 收藏夹
     * @return 收藏夹
     */
    @Override
    public List<CollectFolder> selectCollectFolderList(CollectFolder collectFolder) {
        return collectFolderMapper.selectCollectFolderList(collectFolder);
    }

    /**
     * 新增收藏夹
     *
     * @param collectFolder 收藏夹
     * @return 结果
     */
    @Override
    public int insertCollectFolder(CollectFolder collectFolder) {
        return collectFolderMapper.insert(collectFolder);
    }

    /**
     * 修改收藏夹
     *
     * @param collectFolder 收藏夹
     * @return 结果
     */
    @Override
    public int updateCollectFolder(CollectFolder collectFolder) {
        return collectFolderMapper.updateById(collectFolder);
    }

    /**
     * 批量删除收藏夹
     *
     * @param collectFolderIds 需要删除的收藏夹主键
     * @return 结果
     */
    @Override
    public int deleteCollectFolderByCollectFolderIds(Long[] collectFolderIds) {
        return collectFolderMapper.deleteCollectFolderByCollectFolderIds(collectFolderIds);
    }
}
