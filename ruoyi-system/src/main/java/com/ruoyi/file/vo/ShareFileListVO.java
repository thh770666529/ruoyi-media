package com.ruoyi.file.vo;


import lombok.Data;

//分享文件列表VO")
@Data
public class ShareFileListVO {

    private String shareFileId;
    private String shareBatchNum;


    //文件id")
    private Long fileId;

    //文件时间戳姓名")
    private String timeStampName;

    //文件url")
    private String fileUrl;

    //文件大小")
    private Long fileSize;

    //是否sso存储")
    @Deprecated
    private Integer isOSS;
    //存储类型")
    private Integer storageType;

    //用户文件id")
    private Long userFileId;

    private Long userId;

    //文件名")
    private String fileName;
    //文件路径")
    private String filePath;
    //文件扩展名")
    private String extendName;
    //是否是目录 0-否， 1-是")
    private Integer isDir;
    //上传时间"
    private String uploadTime;
    //分享文件路径")
    private String shareFilePath;

    private String extractionCode;

//    private Long shareId;
//
//    private String shareTime;
//    private String endTime;
//    private Integer shareType;//0公共，1私密，2好友
//    private Integer shareStatus;//0正常，1已失效，2已撤销
}
