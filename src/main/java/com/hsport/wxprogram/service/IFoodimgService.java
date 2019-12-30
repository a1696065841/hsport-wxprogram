package com.hsport.wxprogram.service;

import com.hsport.wxprogram.domain.Foodimg;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-11-28
 */
public interface IFoodimgService extends IService<Foodimg> {
    List<Foodimg> getFoodListByUserID(Long id);
}
