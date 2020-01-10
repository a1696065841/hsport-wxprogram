package com.hsport.wxprogram.service;

import com.hsport.wxprogram.domain.Product;
import com.baomidou.mybatisplus.service.IService;
import com.hsport.wxprogram.query.ProductQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-11-26
 */
public interface IProductService extends IService<Product> {
    List<Object> selectMap(ProductQuery query);
    Integer selectMapTotal(ProductQuery query);}
