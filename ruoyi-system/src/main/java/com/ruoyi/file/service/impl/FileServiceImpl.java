package com.ruoyi.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.file.domain.FileBean;
import com.ruoyi.file.mapper.FileMapper;
import com.ruoyi.file.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service
@Transactional(rollbackFor=Exception.class)
public class FileServiceImpl extends ServiceImpl<FileMapper, FileBean> implements IFileService {

    @Resource
    FileMapper fileMapper;


    @Override
    public void increaseFilePointCount(Long fileId) {
        FileBean fileBean = fileMapper.selectById(fileId);
        if (fileBean == null) {
            log.error("文件不存在，fileId : {}", fileId );
            return;
        }
        fileBean.setPointCount(fileBean.getPointCount()+1);
        fileMapper.updateById(fileBean);
    }

    @Override
    public void decreaseFilePointCount(Long fileId) {
        FileBean fileBean = fileMapper.selectById(fileId);
        fileBean.setPointCount(fileBean.getPointCount()-1);
        fileMapper.updateById(fileBean);
    }


}
