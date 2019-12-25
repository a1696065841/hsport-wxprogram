package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.domain.Gym;
import com.hsport.wxprogram.mapper.GymMapper;
import com.hsport.wxprogram.service.IGymService;
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
public class GymServiceImpl extends ServiceImpl<GymMapper, Gym> implements IGymService {
    @Autowired
    GymMapper gymMapper;

    @Override
    public List<Gym> selectGymByAreaID(Integer id) {
        return gymMapper.selectGymByAreaID(id);
    }

    @Override
    public List<Gym> selectGymByParentID(Integer id) {
        return gymMapper.selectGymByParentID(id);
    }
}
