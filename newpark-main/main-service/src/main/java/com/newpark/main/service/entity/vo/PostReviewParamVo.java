package com.newpark.main.service.entity.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.newpark.base.vali.ValidatedStrMsg;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/11/16 15:57
 **/
@Getter
@Setter
public class PostReviewParamVo {
    /**
     * 帖子ID
     */
    @NotNull(message = ValidatedStrMsg.NOT_NULL_MSG)
    @Positive(message = ValidatedStrMsg.ERROR_MSG)
    private  Long postsId;

}
