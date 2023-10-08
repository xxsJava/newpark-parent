package com.newpark.mybatisplus.feign.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author jack
 * @since 2023/3/18
 */
@Data
public class SysDictVo  {

    private Long id;

    private String code;

    private String value;

    private String label;

    private Integer seq;

    private Integer def;

    private String memo;

    private Long createId;

    private Date createTime;

    private Long updateId;

    private Date updateTime;
}
