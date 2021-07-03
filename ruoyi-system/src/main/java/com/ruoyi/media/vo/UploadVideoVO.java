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

    String url;

    String title;

    String ext;

    Long filesize;

    public UploadVideoVO(String url, String title, String ext, Long filesize) {
        this.url = url;
        this.title = title;
        this.ext = ext;
        this.filesize = filesize;
    }
}
