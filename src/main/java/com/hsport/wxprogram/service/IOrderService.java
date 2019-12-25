package com.hsport.wxprogram.service;

import com.hsport.wxprogram.domain.Order;
import com.baomidou.mybatisplus.service.IService;

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

    List<Object> selectOrderByUserID(Integer id);
}
