package com.ruoyi.file.dto;


import lombok.Data;

@Data
//  "文件列表DTO",required = true)
public class FileListDTO {
    //  "文件路径")
    private String filePath;
    //  "当前页码")
    private Long currentPage;
    //  "一页显示数量")
    private Long pageCount;
}
