package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.domain.ProductGym;
import com.hsport.wxprogram.mapper.ProductGymMapper;
import com.hsport.wxprogram.query.ProductGymQuery;
import com.hsport.wxprogram.service.IProductGymService;
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
 * @since 2019-12-30
 */
@Service
public class ProductGymServiceImpl extends ServiceImpl<ProductGymMapper, ProductGym> implements IProductGymService {
    @Autowired
    ProductGymMapper productGymMapper;
    @Override
    public List<Object> selectWithAll(ProductGymQuery query) {
        return productGymMapper.selectWithAll(query);
    }


}
