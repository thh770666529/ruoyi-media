package com.ruoyi.file.dto;


import lombok.Data;

@Data
//  "分享文件DTO",required = true)
public class ShareFileDTO {
    // "文件集合")
    private String files;
    //  "过期日期", example="2020-05-23 22:10:33")
    private String endTime;
    //  "分享类型", example="0公共分享，1私密分享，2好友分享")
    private Integer shareType;
    //  "备注", example="")
    private String remarks;




}
