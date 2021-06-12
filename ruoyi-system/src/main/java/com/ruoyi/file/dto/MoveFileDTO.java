package com.ruoyi.file.dto;


import lombok.Data;

@Data
//  "移动文件DTO",required = true)
public class MoveFileDTO {

    //  "文件路径")
    private String filePath;

    //  "文件名")
    private String fileName;

    //  "旧文件名")
    private String oldFilePath;
    //  "扩展名")
    private String extendName;

}
