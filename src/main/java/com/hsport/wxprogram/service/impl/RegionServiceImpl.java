package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.domain.Region;
import com.hsport.wxprogram.mapper.RegionMapper;
import com.hsport.wxprogram.service.IRegionService;
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
 * @since 2019-12-05
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements IRegionService {
    @Autowired
    RegionMapper regionMapper;
    @Override
    public List<Region> selectCity() {
        return regionMapper.selectCity();
    }

    @Override
    public List<Region> selectAreaByCityID(Integer id) {
        return regionMapper.selectAreaByCityID(id);
    }
}
