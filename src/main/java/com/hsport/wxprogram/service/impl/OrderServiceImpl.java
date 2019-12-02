package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.domain.Order;
import com.hsport.wxprogram.mapper.OrderMapper;
import com.hsport.wxprogram.service.IOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhb
 * @since 2019-11-26
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
