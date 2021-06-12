package com.ruoyi.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.file.domain.ShareFile;
import com.ruoyi.file.mapper.ShareFileMapper;
import com.ruoyi.file.mapper.UserFileMapper;
import com.ruoyi.file.service.IShareFileService;
import com.ruoyi.file.vo.ShareFileListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor=Exception.class)
public class ShareFileServiceImpl extends ServiceImpl<ShareFileMapper, ShareFile> implements IShareFileService {
    @Resource
    ShareFileMapper shareFileMapper;
    @Resource
    UserFileMapper userFileMapper;
    @Override
    public void batchInsertShareFile(List<ShareFile> shareFiles) {
        shareFileMapper.batchInsertShareFile(shareFiles);
    }

    @Override
    public List<ShareFileListVO> selectShareFileList(String shareBatchNum, String filePath) {
        return shareFileMapper.selectShareFileList(shareBatchNum, filePath);
    }

}
