package com.newpark.im.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author xxs18
 * @Description IM成功响应
 * @Date 2024/1/3 11:01
 **/
@Setter
@Getter
public class ImSucceed {
    @JsonProperty("errCode")
    private Integer errCode;

    @JsonProperty("errMsg")
    private String errMsg;

    @JsonProperty("errDlt")
    private String errDlt;
}
