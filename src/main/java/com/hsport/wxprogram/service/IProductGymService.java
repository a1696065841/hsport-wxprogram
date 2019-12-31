package com.hsport.wxprogram.service;

import com.hsport.wxprogram.domain.ProductGym;
import com.baomidou.mybatisplus.service.IService;
import com.hsport.wxprogram.query.ProductGymQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-12-30
 */
public interface IProductGymService extends IService<ProductGym> {
    List<Object> selectWithAll(ProductGymQuery query);


}
