package com.ruoyi.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.file.domain.Share;
import com.ruoyi.file.dto.ShareListDTO;
import com.ruoyi.file.vo.ShareListVO;

import java.util.List;

public interface IShareService extends IService<Share> {

    List<ShareListVO> selectShareList(ShareListDTO shareListDTO, Long userId);

    int selectShareListTotalCount(ShareListDTO shareListDTO, Long userId);
}
