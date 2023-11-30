package com.newpark.main.service.product.service.impl;

import com.newpark.main.service.product.entity.ProductTransaction;
import com.newpark.main.service.product.mapper.ProductTransactionMapper;
import com.newpark.main.service.product.service.IProductTransactionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品订单表 服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-11-30
 */
@Service
public class ProductTransactionServiceImpl extends ServiceImpl<ProductTransactionMapper, ProductTransaction> implements IProductTransactionService {

}
