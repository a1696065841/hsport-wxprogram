package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.mapper.UserMapper;
import com.hsport.wxprogram.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhb
 * @since 2019-11-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public void insertTest() {

    }
}
