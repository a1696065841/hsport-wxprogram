package com.hsport.wxprogram.service;

import com.hsport.wxprogram.domain.Lxxx;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-12-22
 */
public interface ILxxxService extends IService<Lxxx> {
    Lxxx getByUserID(Integer id);
}
