package com.hsport.wxprogram.service;

import com.hsport.wxprogram.domain.Ceyice;
import com.baomidou.mybatisplus.service.IService;
import com.hsport.wxprogram.domain.User;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-11-29
 */
public interface ICeyiceService extends IService<Ceyice> {
   HashMap getCeyiceByUserID(User user);

    HashMap getCeiyiceToCaoch(User user);
}
