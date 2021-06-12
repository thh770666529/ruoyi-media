package com.ruoyi.file.dto;


import lombok.Data;

@Data
//  "回收文件列表DTO",required = true)
public class RecoveryFileListDTO {
    //  "当前页码")
    private Long currentPage;
    //  "一页显示数量")
    private Long pageCount;
}
