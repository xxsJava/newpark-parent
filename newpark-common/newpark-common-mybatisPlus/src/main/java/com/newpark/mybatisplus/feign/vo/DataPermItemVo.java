package com.newpark.mybatisplus.feign.vo;

import lombok.Data;

/**
 * 数据权限明细
 * @author jack
 * @since 2023/3/18
 */
@Data
public class DataPermItemVo {

    private Long id;

    private Long menuId;

    private Long configId;

    private String fieldName;

    private String fieldCode;

    private Integer fieldVal;

    private Object value;

}
