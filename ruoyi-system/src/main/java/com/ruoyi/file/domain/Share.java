package com.ruoyi.file.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@TableName("qiwen_share")
public class Share {

    @TableId(type = IdType.AUTO)
    private Long shareId;
    private Long userId;
    private String shareTime;
    private String endTime;
    private String extractionCode;
    private String shareBatchNum;
    private Integer shareType;//0公共，1私密，2好友
    private Integer shareStatus;//0正常，1已失效，2已撤销
}
