package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.domain.Product;
import com.hsport.wxprogram.mapper.ProductMapper;
import com.hsport.wxprogram.query.ProductQuery;
import com.hsport.wxprogram.service.IProductService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    @Autowired
    ProductMapper productMapper;
    @Override
    public List<Object> selectMap(ProductQuery query) {
        return productMapper.selectMap(query);
    }

    @Override
    public Integer selectMapTotal(ProductQuery query) {
        return productMapper.selectMapTotal(query);
    }
}
