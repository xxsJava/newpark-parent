package com.newpark.main.service.water.service.impl;

import com.newpark.main.service.water.entity.Product;
import com.newpark.main.service.water.mapper.ProductMapper;
import com.newpark.main.service.water.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-12-28
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
