package com.hsport.wxprogram.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.domain.Jibing;
import com.hsport.wxprogram.mapper.JibingMapper;
import com.hsport.wxprogram.service.IJibingService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class JibingServiceImpl extends ServiceImpl<JibingMapper, Jibing> implements IJibingService {
    @Autowired
    JibingMapper jibingMapper;
    @Override
    public Jibing getByUserID(Integer id) {
        Jibing jibingEntityWrapper = new Jibing();
        jibingEntityWrapper.setUserID(id);
        return jibingMapper.selectOne(jibingEntityWrapper);
    }
}
