package com.ruoyi.file.dto;


import lombok.Data;

@Data
//  "分享列表DTO",required = true)
public class ShareListDTO {
    // "分享文件路径")
    private String shareFilePath;
    // "批次号")
    private String shareBatchNum;
    //  "当前页码")
    private Long currentPage;
    //  "一页显示数量")
    private Long pageCount;
}
