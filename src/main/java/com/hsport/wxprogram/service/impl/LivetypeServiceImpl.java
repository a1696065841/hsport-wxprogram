package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.domain.Livetype;
import com.hsport.wxprogram.mapper.LivetypeMapper;
import com.hsport.wxprogram.service.ILivetypeService;
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
public class LivetypeServiceImpl extends ServiceImpl<LivetypeMapper, Livetype> implements ILivetypeService {

    @Autowired
    LivetypeMapper livetypeMapper;
    @Override
    public Livetype getByUserID(Integer id) {
        Livetype livetype = new Livetype();
        livetype.setUserID(id);
        return livetypeMapper.selectOne(livetype);
    }
}
