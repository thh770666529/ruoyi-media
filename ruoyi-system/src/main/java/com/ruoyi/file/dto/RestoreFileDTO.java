package com.ruoyi.file.dto;


import lombok.Data;

@Data
//  "回收文件DTO",required = true)
public class RestoreFileDTO {
    // "删除批次号")
    private String deleteBatchNum;
    // "文件路径")
    private String filePath;
}
