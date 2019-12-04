package com.hsport.wxprogram.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.domain.User;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-11-29
 */
public interface IUserService extends IService<User> {
    List<User> findUserByCoachID(Integer id);

}
