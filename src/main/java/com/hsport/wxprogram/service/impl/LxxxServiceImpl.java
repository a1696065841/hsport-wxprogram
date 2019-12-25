package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.domain.Lxxx;
import com.hsport.wxprogram.mapper.LxxxMapper;
import com.hsport.wxprogram.service.ILxxxService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhb
 * @since 2019-12-22
 */
@Service
public class LxxxServiceImpl extends ServiceImpl<LxxxMapper, Lxxx> implements ILxxxService {
    @Autowired
    LxxxMapper lxxxMapper;

    @Override
    public Lxxx getByUserID(Integer id) {
        return lxxxMapper.getByUserID(id);
    }
}
