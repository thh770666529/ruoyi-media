package com.ruoyi.file.dto;


import lombok.Data;

@Data
//  "解压缩文件DTO",required = true)
public class UnzipFileDTO {
    //  "文件url")
    private String fileUrl;
    //  "文件路径")
    private String filePath;
}
