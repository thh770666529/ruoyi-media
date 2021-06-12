package com.ruoyi.file.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * 文件实体类
 *
 * @author ma116
 */
@Data
@TableName("qiwen_file")
public class FileBean {

    @Id
    @TableId(type = IdType.AUTO)
    private Long fileId;

    private String timeStampName;

    private String fileUrl;


    private Long fileSize;

//    @Column(columnDefinition="int(1)")
//    @Deprecated
//    private Integer isOSS;

    private Integer storageType;


    private Integer pointCount;

    private String identifier;

}
