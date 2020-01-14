package com.hsport.wxprogram.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.AliyunSmsUtils;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.domain.*;
import com.hsport.wxprogram.mapper.OrderMapper;
import com.hsport.wxprogram.mapper.ProductMapper;
import com.hsport.wxprogram.mapper.SportsplanMapper;
import com.hsport.wxprogram.mapper.UserMapper;
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
    @Autowired
    UserMapper userMapper;
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
    public AjaxResult updateOrderType(Order order) throws ClientException {
            Order order1 = orderMapper.selectById(order);
            order1.setEndDate(DateUtil.now());
            order1.setOrderType(1);
            Product product = productMapper.selectById(order1.getProductID());
            if (product.getProductType()==2){
                Page<Details> page = new Page<Details>(1,1);
                List<Sportsplan> sportsplans = sportsplanMapper.selectPage(page, new EntityWrapper<Sportsplan>().
                        eq("userID", order1.getUserID()).isNull("planEndDate").eq("planType", 1));
                for (Sportsplan sportsplan : sportsplans) {
                    sportsplan.setPlanEndDate(DateUtil.now());
                    sportsplan.setPlanType(2);
                    sportsplanMapper.updateById(sportsplan);
                }
            }
        User user = userMapper.selectById(order1.getUserID());
       // AliyunSmsUtils.sendSms(user.getPhone(),"尊敬的用户您好,您的测评咨询服务已完成,如果过程中你有任何建议或意见请向我们的会员管家沟通!谢谢您,祝您生活愉快!");
        orderMapper.updateById(order1);
        return AjaxResult.me();
    }

    @Override
    public Integer selectOrderByUserIDTotal(OrderQuery query) {
        return orderMapper.selectOrderByUserIDTotal(query);
    }


}
