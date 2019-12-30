package com.hsport.wxprogram.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.domain.Duibitu;
import com.hsport.wxprogram.mapper.DuibituMapper;
import com.hsport.wxprogram.service.IDuibituService;
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
public class DuibituServiceImpl extends ServiceImpl<DuibituMapper, Duibitu> implements IDuibituService {
    @Autowired
    DuibituMapper duibituMapper;
    @Override
    public List<Duibitu> getListByUserID(Long id) {
        EntityWrapper<Duibitu> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.eq("userID", id);
        return duibituMapper.selectList(userEntityWrapper);
    }
}
