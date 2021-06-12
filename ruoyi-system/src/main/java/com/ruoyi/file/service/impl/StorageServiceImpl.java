package com.ruoyi.file.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.constant.FileConstant;
import com.ruoyi.file.domain.StorageBean;
import com.ruoyi.file.mapper.StorageMapper;
import com.ruoyi.file.mapper.UserFileMapper;
import com.ruoyi.file.service.IStorageService;
import com.ruoyi.system.service.ISysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service
@Transactional(rollbackFor=Exception.class)
public class StorageServiceImpl extends ServiceImpl<StorageMapper, StorageBean> implements IStorageService {
    @Resource
    StorageMapper storageMapper;
    @Autowired
    private ISysConfigService configService;
    @Resource
    UserFileMapper userFileMapper;

    @Override
    public Long getTotalStorageSize(Long userId) {
        LambdaQueryWrapper<StorageBean> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(StorageBean::getUserId, userId);

        StorageBean storageBean = storageMapper.selectOne(lambdaQueryWrapper);
        Long totalStorageSize = null;
        if (storageBean == null || storageBean.getTotalStorageSize() == null) {
            totalStorageSize = getInitStorageSize();
            storageBean = new StorageBean();
            storageBean.setUserId(userId);
            storageBean.setTotalStorageSize(totalStorageSize);
            storageMapper.insert(storageBean);
        } else  {
            totalStorageSize = storageBean.getTotalStorageSize();
        }

        if (totalStorageSize != null) {
            totalStorageSize = totalStorageSize * 1024 * 1024;
        }
        return totalStorageSize;
    }

    /**
     * 获取初始化存储大小
     * @return
     */
    private Long getInitStorageSize(){
        String initStorageSize = configService.selectConfigByKey(FileConstant.INIT_STORAGE_SIZE);
        return Long.parseLong(initStorageSize);
    }

    @Override
    public boolean checkStorage(Long userId, Long fileSize) {
        // 获取用户总存储大小
        Long totalStorageSize = this.getTotalStorageSize(userId);
        Long storageSize = userFileMapper.selectStorageSizeByUserId(userId);
        if (storageSize == null ){
            storageSize = 0L;
        }
        if (storageSize + fileSize > totalStorageSize) {
            return false;
        }
        return true;
    }
}
