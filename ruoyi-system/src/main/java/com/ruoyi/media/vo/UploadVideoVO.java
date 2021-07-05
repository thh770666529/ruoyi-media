package com.ruoyi.media.vo;
import lombok.Data;
import java.io.Serializable;

/**
 * @ClassName UploadVideoVO
 * @Description 上传视频
 * @Author thh
 * @Date 9:45 2021/07/03
 **/
@Data
public class UploadVideoVO implements Serializable {

    private String url;

    private String title;

    private String ext;

    private Long filesize;

    private String length;

    private String status;

    public UploadVideoVO(String url, String title, String ext, Long filesize,String length,String status) {
        this.url = url;
        this.title = title;
        this.ext = ext;
        this.filesize = filesize;
        this.length = length;
        this.status = status;
    }
}
