package com.ruoyi.file.dto;


import lombok.Data;

@Data
//  "批量移动文件DTO",required = true)
public class BatchMoveFileDTO {
    // "文件集合")
    private String files;
    // "文件路径")
    private String filePath;


}
