package com.hsport.wxprogram.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.common.util.PageList;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.mapper.UserMapper;
import com.hsport.wxprogram.query.UserQuery;
import com.hsport.wxprogram.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhb
 * @since 2019-11-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> findUserByCoachID(Long id) {
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.eq("coachID",id);
        List<User> users = userMapper.selectList(userEntityWrapper);
        return users;
    }

    @Override
    public List<Object> selectUserCoach(UserQuery query) {
        return  userMapper.selectUserCoach(query);

    }

    @Override
    public void updateLoginUser(User user) {
        User updateUser = userMapper.selectById(user.getId());
        updateUser.setLoginCount(updateUser.getLoginCount() + 1);
        updateUser.setLoginTime(DateUtil.now());
        userMapper.updateById(updateUser);
    }
}
