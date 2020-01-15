package com.hsport.wxprogram.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.AliyunSmsUtils;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.common.util.PageList;
import com.hsport.wxprogram.domain.Coach;
import com.hsport.wxprogram.domain.CoachOrder;
import com.hsport.wxprogram.domain.Order;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.domain.vo.OrderVo;
import com.hsport.wxprogram.mapper.CoachMapper;
import com.hsport.wxprogram.mapper.CoachOrderMapper;
import com.hsport.wxprogram.mapper.OrderMapper;
import com.hsport.wxprogram.mapper.UserMapper;
import com.hsport.wxprogram.query.UserQuery;
import com.hsport.wxprogram.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hsport.wxprogram.web.controller.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Autowired
    CoachOrderMapper coachOrderMapper;
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

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
    public AjaxResult  updateUserCoach(OrderVo orderVo) throws ClientException {
        String orderID = orderVo.getOrderID();
        Order order1 = orderMapper.selectById(orderID);
        order1.setIsDistribution(1);
        orderMapper.updateById(order1);
        User user = userMapper.selectById(order1.getUserID());
        user.setCoachID(orderVo.getCoachID());
        userMapper.updateById(user);
        Coach coach = coachMapper.selectById(orderVo.getCoachID());
        //生成订单负责关系表
        CoachOrder coachOrder = new CoachOrder();
        coachOrder.setCoachID(coach.getId());
        coachOrder.setDate(DateUtil.now());
        coachOrder.setOrderID(orderID);
        coachOrderMapper.insert(coachOrder);
        System.out.println(coach.getPhone());
       AliyunSmsUtils.sendSms(coach.getPhone(),"123","SMS_182675849");
        logger.debug("教练提醒信息已经发送给"+coach.getCoachName()+"教练!");
        return AjaxResult.me();
    }
}
