package com.hsport.wxprogram.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.domain.Duibitu;
import com.baomidou.mybatisplus.service.IService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-11-26
 */
public interface IDuibituService extends IService<Duibitu> {

    List<Duibitu> getListByUserID(Integer id);
}
