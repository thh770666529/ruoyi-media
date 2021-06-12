package com.ruoyi.file.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * 存储信息类
 */
@Data
@TableName("qiwen_storage")
public class StorageBean {

    @TableId(type = IdType.AUTO)
    private Long storageId;

    private Long userId;

    private Long storageSize;

    private Long totalStorageSize;

    public StorageBean() {

    }

    public StorageBean(long userId) {
        this.userId = userId;
    }

}
