package com.ruoyi.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.file.domain.FileBean;

/**
 * @author thh
 */
public interface IFileService extends IService<FileBean> {


    void increaseFilePointCount(Long fileId);

    void decreaseFilePointCount(Long fileId);






}
