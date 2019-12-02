package com.hsport.wxprogram.service;

import com.hsport.wxprogram.domain.User;
import com.baomidou.mybatisplus.service.IService;
import com.hsport.wxprogram.util.AjaxResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-11-21
 */
public interface IUserService extends IService<User> {
    void insertTest();
}
