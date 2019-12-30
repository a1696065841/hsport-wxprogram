package com.hsport.wxprogram.service;

import com.hsport.wxprogram.domain.Gym;
import com.baomidou.mybatisplus.service.IService;
import com.hsport.wxprogram.query.GymQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-11-26
 */
public interface IGymService extends IService<Gym> {
    List<Gym> selectGymByAreaID(Integer id);
    List<Gym> selectGymByParentID(Integer id);
    List<Object> selectGymWithRegion(GymQuery gymQuery);

}
