package com.hsport.wxprogram.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.AliyunSmsUtils;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.common.util.PageList;
import com.hsport.wxprogram.domain.Coach;
import com.hsport.wxprogram.domain.Order;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.domain.vo.OrderVo;
import com.hsport.wxprogram.mapper.CoachMapper;
import com.hsport.wxprogram.mapper.OrderMapper;
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
 * 服务实现类
 * </p>
 *
 * @author lhb
 * @since 2019-11-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    CoachMapper coachMapper;

    @Override
    public List<User> findUserByCoachID(Long id) {
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.eq("coachID", id);
        List<User> users = userMapper.selectList(userEntityWrapper);
        return users;
    }

    @Override
    public List<Object> selectUserCoach(UserQuery query) {
        return userMapper.selectUserCoach(query);

    }

    @Override
    public void updateLoginUser(User user) {
        User updateUser = userMapper.selectById(user.getId());
        updateUser.setLoginCount(updateUser.getLoginCount() + 1);
        updateUser.setLoginTime(DateUtil.now());
        userMapper.updateById(updateUser);
    }

    @Override
    public Integer selectUserCoachTotal(UserQuery query) {
        return userMapper.selectUserCoachTotal(query);
    }

    @Override
    public AjaxResult updateUserCoach(OrderVo orderVo) throws ClientException {
        String orderID = orderVo.getOrderID();
        Order order1 = orderMapper.selectById(orderID);
        order1.setIsDistribution(1);
        orderMapper.updateById(order1);
        User user = userMapper.selectById(orderVo.getUserID());
        user.setCoachID(orderVo.getCoachID());
        userMapper.updateById(user);
        Coach coach = coachMapper.selectById(orderVo.getCoachID());
       // AliyunSmsUtils.sendSms(coach.getPhone(),"您已有新的订单啦,请您十分钟内尽快处理哦!");
        return AjaxResult.me();
    }
}
