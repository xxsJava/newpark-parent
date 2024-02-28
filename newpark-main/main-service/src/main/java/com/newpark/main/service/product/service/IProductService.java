package com.newpark.main.service.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.newpark.main.service.entity.Product;
import com.newpark.main.service.entity.vo.ProductParamVo;
import com.newpark.main.service.entity.vo.ProductUptVo;
import com.newpark.pojo.vo.PageInfoVo;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author xxs18
 * @since 2023-11-30
 */
public interface IProductService extends IService<Product> {

    List<Product> productsByFind(PageInfoVo pageInfoVo, ProductParamVo productParamDto);

    Boolean productIns(Product product);

    Boolean productDel(Long pId);

    Boolean productUpt(ProductUptVo productUptDto);
}
