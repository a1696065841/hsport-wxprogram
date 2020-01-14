package com.hsport.wxprogram.service;

import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.hsport.wxprogram.domain.Order;
import com.hsport.wxprogram.domain.User;
import com.baomidou.mybatisplus.service.IService;
import com.hsport.wxprogram.domain.vo.OrderVo;
import com.hsport.wxprogram.query.UserQuery;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-11-29
 */
public interface IUserService extends IService<User> {
    List<User> findUserByCoachID(Long id);
    List<Object> selectUserCoach(UserQuery query);
    void  updateLoginUser(User user);
    Integer selectUserCoachTotal(UserQuery query);
    AjaxResult updateUserCoach(OrderVo orderVo) throws ClientException;
}
