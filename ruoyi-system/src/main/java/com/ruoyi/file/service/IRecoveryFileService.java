package com.ruoyi.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.file.domain.RecoveryFile;
import com.ruoyi.file.domain.UserFile;
import com.ruoyi.file.vo.RecoveryFileListVo;


import java.util.List;

public interface IRecoveryFileService extends IService<RecoveryFile> {

    void deleteRecoveryFile(UserFile userFile);

    void restorefile(String deleteBatchNum, String filePath, Long sessionUserId);

    List<RecoveryFileListVo> selectRecoveryFileList(Long userId);
}
