package com.ruoyi.file.dto;


import lombok.Data;

@Data
//  "预览文件DTO",required = true
public class PreviewDTO {
    private Long userFileId;

    private String token;

   //批次号
    private String shareBatchNum;

   //提取码
    private String extractionCode;

    private String isMin;
}
