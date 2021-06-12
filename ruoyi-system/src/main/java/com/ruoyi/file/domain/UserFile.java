package com.ruoyi.file.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("qiwen_user_file")
public class UserFile {
    @TableId(type = IdType.AUTO)
    private Long userFileId;

    private Long userId;

    private Long fileId;

    private String fileName;

    private String filePath;

    private String extendName;

    private Integer isDir;

    private String uploadTime;

    private Integer deleteFlag;

    private String deleteTime;

    private String deleteBatchNum;

}
