package com.hsport.wxprogram.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.domain.Details;
import com.hsport.wxprogram.domain.Order;
import com.hsport.wxprogram.domain.Product;
import com.hsport.wxprogram.domain.Sportsplan;
import com.hsport.wxprogram.mapper.OrderMapper;
import com.hsport.wxprogram.mapper.ProductMapper;
import com.hsport.wxprogram.mapper.SportsplanMapper;
import com.hsport.wxprogram.query.OrderQuery;
import com.hsport.wxprogram.service.IOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hsport.wxprogram.service.IProductService;
import com.hsport.wxprogram.service.ISportsplanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    SportsplanMapper sportsplanMapper;
    @Autowired
    ProductMapper productMapper;
    @Override
    public List<Object> selectOrderMap(OrderQuery query) {
        return orderMapper.selectOrderMap(query);
    }

    @Override
    public List<Object> selectOrderByUserID(OrderQuery query) {
        return orderMapper.selectOrderByUserID(query);
    }

    @Override
    public Integer selectOrderMapTotal(OrderQuery query) {
        return orderMapper.selectOrderMapTotal(query);
    }

    @Override
    public AjaxResult updateOrderType(Order order) {
            Order order1 = orderMapper.selectById(order);
            order1.setEndDate(DateUtil.now());
            order1.setOrderType(1);
            Product product = productMapper.selectById(order.getProductID());
            if (product.getProductType()==2){
                Page<Details> page = new Page<Details>(1,1);
                List<Sportsplan> sportsplans = sportsplanMapper.selectPage(page, new EntityWrapper<Sportsplan>().
                        eq("userID", order.getUserID()).isNull("planEndDate").eq("planType", 1));
                for (Sportsplan sportsplan : sportsplans) {
                    sportsplan.setPlanEndDate(DateUtil.now());
                    sportsplan.setPlanType(2);
                    sportsplanMapper.updateById(sportsplan);
                }
            }
            orderMapper.updateById(order);
        return AjaxResult.me();
    }

    @Override
    public Integer selectOrderByUserIDTotal(OrderQuery query) {
        return orderMapper.selectOrderByUserIDTotal(query);
    }


}
