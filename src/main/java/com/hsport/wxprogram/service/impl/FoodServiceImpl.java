package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.domain.Food;
import com.hsport.wxprogram.mapper.FoodMapper;
import com.hsport.wxprogram.service.IFoodService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhb
 * @since 2019-11-28
 */
@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements IFoodService {

}
