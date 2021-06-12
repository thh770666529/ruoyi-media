package com.ruoyi.file.dto;


import lombok.Data;

@Data
//  "保存分享文件DTO",required = true)
public class SaveShareFileDTO {
    // "文件集合", example = "[{\"userFileId\":12},{\"userFileId\":13}]")
    private String files;
    //  "文件路径")
    private String filePath;
}
