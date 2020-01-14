package com.hsport.wxprogram.service;

import com.aliyuncs.exceptions.ClientException;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.domain.Order;
import com.baomidou.mybatisplus.service.IService;
import com.hsport.wxprogram.query.OrderQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-11-26
 */
public interface IOrderService extends IService<Order> {
    List<Object> selectOrderMap( OrderQuery query);
    List<Object> selectOrderByUserID(OrderQuery query);
    Integer selectOrderMapTotal(OrderQuery query);
    //确认收货  并修改总计划状态
    AjaxResult updateOrderType(Order order) throws ClientException;
    Integer selectOrderByUserIDTotal(OrderQuery query);
}
