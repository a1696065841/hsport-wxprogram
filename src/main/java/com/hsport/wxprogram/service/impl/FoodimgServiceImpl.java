package com.hsport.wxprogram.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.domain.Foodimg;
import com.hsport.wxprogram.mapper.FoodimgMapper;
import com.hsport.wxprogram.service.IFoodimgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hsport.wxprogram.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhb
 * @since 2019-11-28
 */
@Service
public class FoodimgServiceImpl extends ServiceImpl<FoodimgMapper, Foodimg> implements IFoodimgService {
    @Autowired
    FoodimgMapper foodimgMapper;

    @Override
    public List<Foodimg> getFoodListByUserID(Integer id) {
        EntityWrapper<Foodimg> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.eq("date", DateUtil.today());
        userEntityWrapper.eq("userID", id);
        return foodimgMapper.selectList(userEntityWrapper);
    }
}
