package com.ruoyi.file.vo;


import lombok.Data;

@Data
public class RecoveryFileListVo {
    //回收文件id", example = "1"
    private Long recoveryFileId;

    //id", example = "1"
    private Long userFileId;

    //userId", example = "1"
    private Long userId;

    //fileId", example = "1"
    private Long fileId;

    //文件名", example = "图片"
    private String fileName;

    //文件路径", example = "upload/bddd/caaa"
    private String filePath;

    //文件扩展名", example = "zip"
    private String extendName;

    //是否是目录，1-是，0-否", example = "1"
    private Integer isDir;

    //上传时间", example = "2020-10-10 12:21:22"
    private String uploadTime;

    //删除标志", example = "1"
    private Integer deleteFlag;

    //删除时间", example = "2020-10-10 12:21:22"
    private String deleteTime;

    //删除批次号", example = "1111-222-22"
    private String deleteBatchNum;
}
