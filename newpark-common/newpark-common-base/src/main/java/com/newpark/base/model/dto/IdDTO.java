//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.newpark.base.model.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * id dto
 *
 * @param <T>
 * @author jack
 * @date 2023/3/14
 */
@Data
public class IdDTO<T> extends BaseDTO {

    @NotNull(message = "id 不能为空")
    private T id;

    public IdDTO() {
    }

    public IdDTO(T id) {
        this.id = id;
    }

}
