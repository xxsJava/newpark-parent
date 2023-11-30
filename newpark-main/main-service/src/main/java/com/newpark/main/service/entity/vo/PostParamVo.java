package com.newpark.main.service.entity.vo;

import com.newpark.base.vali.ValidatedStrMsg;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @Author xxs18
 * @Description 帖子条件查询
 * @Date 2023/11/14 17:46
 **/
@Getter
@Setter
public class PostParamVo {

    /**
     * 学校id
     */
    @NotNull(message = ValidatedStrMsg.NOT_NULL_MSG)
    @Positive(message = ValidatedStrMsg.ERROR_MSG)
    private Integer schoolId;

    /**
     * 帖子Id
     */
    @Positive(message = ValidatedStrMsg.ERROR_MSG)
    private Long postsId;

}
