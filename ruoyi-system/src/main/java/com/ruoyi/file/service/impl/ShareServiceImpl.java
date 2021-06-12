package com.ruoyi.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.file.domain.Share;
import com.ruoyi.file.dto.ShareListDTO;
import com.ruoyi.file.mapper.ShareMapper;
import com.ruoyi.file.service.IShareService;
import com.ruoyi.file.vo.ShareListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor=Exception.class)
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share> implements IShareService {

    @Resource
    ShareMapper shareMapper;

    @Override
    public List<ShareListVO> selectShareList(ShareListDTO shareListDTO, Long userId) {
        Long beginCount = (shareListDTO.getCurrentPage() - 1) * shareListDTO.getPageCount();
        return shareMapper.selectShareList(shareListDTO.getShareFilePath(),
                shareListDTO.getShareBatchNum(),
                beginCount, shareListDTO.getPageCount(), userId);
    }

    @Override
    public int selectShareListTotalCount(ShareListDTO shareListDTO, Long userId) {
        return shareMapper.selectShareListTotalCount(shareListDTO.getShareFilePath(), shareListDTO.getShareBatchNum(), userId);
    }
}
