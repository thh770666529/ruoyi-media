package com.ruoyi.media.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName NetWorkDiskVO
 * @Description 网盘VO
 * @Author thh
 * @Date 11:21 2021/07/03
 **/
@Data
public class NetWorkDiskVO implements Serializable {

    Long fileId;

    String fileName;
}
