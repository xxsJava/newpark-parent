package com.newpark.main.service.water.service.impl;

import com.newpark.main.service.water.entity.Orders;
import com.newpark.main.service.water.mapper.OrdersMapper;
import com.newpark.main.service.water.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品订单表 服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-12-28
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

}
