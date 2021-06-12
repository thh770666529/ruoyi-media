package com.ruoyi.file.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("qiwen_recovery_file")
public class RecoveryFile {


    @TableId(type = IdType.AUTO)
    private Long recoveryFileId;

    private Long userFileId;

    private String deleteTime;

    private String deleteBatchNum;
}
