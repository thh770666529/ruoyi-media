package com.ruoyi.file.dto;


import lombok.Data;


@Data
//  "上传文件DTO",required = true)
public class UploadFileDTO {

    //  "文件路径")
    private String filePath;

    //  "文件名")
    private String filename;

    //  "切片数量")
    private int chunkNumber;

    //  "切片大小")
    private long chunkSize;

    //  "所有切片")
    private int totalChunks;
    //  "总大小")
    private long totalSize;
    //  "当前切片大小")
    private long currentChunkSize;
    //  "md5码")
    private String identifier;

}
