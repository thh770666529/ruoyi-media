package com.ruoyi.file.dto;


import lombok.Data;

@Data
//  "创建文件DTO",required = true)
public class CreateFileDTO {
    // "文件名")
    private String fileName;
    // "文件路径")
    private String filePath;
}
