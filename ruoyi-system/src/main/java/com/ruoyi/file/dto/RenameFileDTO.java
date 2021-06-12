package com.ruoyi.file.dto;


import lombok.Data;

@Data
//  "重命名文件DTO",required = true)
public class RenameFileDTO {
    private Long userFileId;
    /**
     * 文件路径
     */
    //  "文件路径")
    @Deprecated
    private String filePath;

    /**
     * 文件名
     */
    //  "文件名")
    private String fileName;
    //  "是否是目录")
    @Deprecated
    private Integer isDir;
    //  "旧文件名")
    @Deprecated
    private String oldFileName;
    //  "是否是OSS")
    @Deprecated
    private Integer isOSS;
}
