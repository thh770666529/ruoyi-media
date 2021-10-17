package com.ruoyi.index.vo;
import lombok.Data;

import java.util.Map;

/**
 * @ClassName SearchParamVO
 * @Description 搜索实体类
 * @Author thh
 * @Date 20:52 2021/10/14
 **/
@Data
public class SearchParamVO {
    /**
     * 关键字
     */
    private String keyword;

    private String tableName;

    private String author;

    private Map<String, Object> params;
}
