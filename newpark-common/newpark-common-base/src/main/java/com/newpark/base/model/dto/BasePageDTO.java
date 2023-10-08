package com.newpark.base.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 分页基类
 *
 * @author jack
 * @date 2023/4/13
 */
@Data
public class BasePageDTO extends BaseDTO {

    /**
     * 每页条数
     */
    @NotNull(message = "pageSize 不能为空")
    private Integer pageSize = 10;

    /**
     * 分页 页码
     */
    @NotNull(message = "pageIndex 不能为空")
    private Integer pageIndex = 1;

    /**
     * 排序 字段
     */
    private List<SortItem> sortItem;

    /**
     * 排序字符串
     */
    private String pageOrder;

    public String getPageOrder() {
        StringBuffer pageOrderSql = new StringBuffer();
        if (sortItem != null) {
            for (int i = 0; i < sortItem.size(); i++) {
                SortItem si = sortItem.get(i);
                String column = this.underscoreName(si.getColumn());
                if (column != null && !column.trim().equals("")) {
                    String sort = si.getAsc() ? "ASC" : "DESC";
                    pageOrderSql.append(column + " " + sort);
                }
                if (i != sortItem.size() - 1) {
                    pageOrderSql.append(",");
                }
            }
        }
        pageOrder = pageOrderSql.toString();
        return pageOrder;
    }

    public void setPageOrder(String pageOrder) {
        this.pageOrder = pageOrder;
    }

    /**
     * 转换为下划线
     *
     * @param camelCaseName
     * @return
     */
    public static String underscoreName(String camelCaseName) {
        StringBuilder result = new StringBuilder();
        if (camelCaseName != null && camelCaseName.length() > 0) {
            result.append(camelCaseName.substring(0, 1).toLowerCase());
            for (int i = 1; i < camelCaseName.length(); i++) {
                char ch = camelCaseName.charAt(i);
                if (Character.isUpperCase(ch)) {
                    result.append("_");
                    result.append(Character.toLowerCase(ch));
                } else {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }

    /**
     * 排序字段
     */
    @Data
    public static class SortItem {

        /**
         * 排序字段
         */
        private String column;

        /**
         * 是否升序
         */
        private Boolean asc = true;

    }


}
