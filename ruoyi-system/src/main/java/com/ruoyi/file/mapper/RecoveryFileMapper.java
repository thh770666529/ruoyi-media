package com.ruoyi.file.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.file.domain.RecoveryFile;
import com.ruoyi.file.vo.RecoveryFileListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RecoveryFileMapper extends BaseMapper<RecoveryFile> {
    List<RecoveryFileListVo> selectRecoveryFileList(@Param("userId") Long userId);
}
