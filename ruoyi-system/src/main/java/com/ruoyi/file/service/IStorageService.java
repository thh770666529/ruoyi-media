package com.ruoyi.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.file.domain.StorageBean;

public interface IStorageService extends IService<StorageBean> {

    /**
     * 获取用户的总存储大小
     * @param userId
     * @return
     */
    public Long getTotalStorageSize(Long userId);

    /**
     * 检查用户的存储
     * @param userId
     * @param fileSize
     * @return
     */
    boolean checkStorage(Long userId, Long fileSize);
}
