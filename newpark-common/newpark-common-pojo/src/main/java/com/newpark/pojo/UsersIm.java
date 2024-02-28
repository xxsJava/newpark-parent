package com.newpark.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author xxs18
 * @Description IM-USR 参数
 * @Date 2024/1/3 10:57
 **/
@Getter
@Setter
public class UsersIm {
    @JsonProperty("userID")
    private String userID;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("faceURL")
    private String faceURL;
}
