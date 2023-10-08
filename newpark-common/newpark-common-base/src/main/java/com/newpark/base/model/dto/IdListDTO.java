//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.newpark.base.model.dto;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * idList dto
 *
 * @param <T>
 * @author jack
 * @date 2023/4/13
 */
@Data
@Accessors(chain = true)
public class IdListDTO<T> extends BaseDTO {

    @NotEmpty(message = "ids 不能为空")
    private List<T> ids;

    public IdListDTO() {
        this.ids = new ArrayList();
    }

    public IdListDTO(List<T> data) {
        this.setIds(data);
    }

    public IdListDTO(T initItem) {
        this.ids = new ArrayList();
        this.ids.add(initItem);
    }
}
