package com.ruoyi.file.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@TableName("qiwen_share_file")
public class ShareFile {

    @TableId(type = IdType.AUTO)
    private Long shareFileId;

    private String shareBatchNum;

    private Long userFileId;

    private String shareFilePath;

}
