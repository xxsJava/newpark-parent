package com.newpark.main.service.product.service.impl;

import com.newpark.main.service.product.entity.Product;
import com.newpark.main.service.product.mapper.ProductMapper;
import com.newpark.main.service.product.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-11-30
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
