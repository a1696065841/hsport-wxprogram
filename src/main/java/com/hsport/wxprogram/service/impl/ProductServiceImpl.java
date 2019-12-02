package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.domain.Product;
import com.hsport.wxprogram.mapper.ProductMapper;
import com.hsport.wxprogram.service.IProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
