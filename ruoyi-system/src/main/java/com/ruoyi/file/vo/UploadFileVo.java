package com.ruoyi.file.vo;


import lombok.Data;

import java.util.List;

@Data
//文件上传Vo"
public class UploadFileVo {

   //"时间戳", example = "123123123123"
    private String timeStampName;
   //跳过上传", example = true
    private boolean skipUpload;
    //是否需要合并分片"
    private boolean needMerge;
   //已经上传的分片", example = "[1,2,3]"
    private List<Integer> uploaded;


}
