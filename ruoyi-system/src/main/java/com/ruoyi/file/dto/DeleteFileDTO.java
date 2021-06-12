package com.ruoyi.file.dto;


import lombok.Data;

@Data
//  "删除文件DTO",required = true)
public class DeleteFileDTO {
    //  "用户文件id")
    private Long userFileId;


    //  "文件路径")
    @Deprecated
    private String filePath;
    //  "文件名")
    @Deprecated
    private String fileName;
    //  "是否是目录")
    @Deprecated
    private Integer isDir;
}
