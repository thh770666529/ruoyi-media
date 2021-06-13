package com.ruoyi.file.dto;


import lombok.Data;

@Data
//  "下载文件DTO",required = true)
public class DownloadFileDTO {
    private Long userFileId;
    private Boolean isMin;
}
