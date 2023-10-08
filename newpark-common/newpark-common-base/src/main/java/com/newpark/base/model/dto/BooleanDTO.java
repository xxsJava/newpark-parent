//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.newpark.base.model.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * boolean dto
 *
 * @author jack
 * @date 2023/3/14
 */
@Data
public class BooleanDTO extends BaseDTO {

    @NotNull(message = "isTrue 不能为空")
    private Boolean isTrue;

    public BooleanDTO() {
    }

    public BooleanDTO(Boolean id) {
        this.isTrue = id;
    }


}
