package com.newpark.main.service.entity.vo;

import com.newpark.base.vali.ValidatedStrMsg;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @Positive(message = ValidatedStrMsg.ERROR_MSG)
    private Integer schoolId;


    /**
     * 最新排序
     */
    @Pattern(regexp = "^(?i)(ASC|DESC)$",message = ValidatedStrMsg.ERROR_MSG)
    private String tPubTimeSort;
}
