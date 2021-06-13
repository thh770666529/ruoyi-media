package com.ruoyi.file.dto;


import lombok.Data;

@Data
//  "预览文件DTO",required = true
public class PreviewDTO {
    private Long userFileId;
    private String token;
    private Boolean isMin;
}
