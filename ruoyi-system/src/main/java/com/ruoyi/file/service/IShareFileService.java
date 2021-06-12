package com.ruoyi.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.file.domain.ShareFile;
import com.ruoyi.file.vo.ShareFileListVO;

import java.util.List;

public interface IShareFileService extends IService<ShareFile> {

    void batchInsertShareFile(List<ShareFile> shareFiles);

    List<ShareFileListVO> selectShareFileList(String shareBatchNum, String filePath);
}
