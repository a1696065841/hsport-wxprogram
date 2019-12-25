package com.hsport.wxprogram.service;

import com.hsport.wxprogram.domain.Body;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-11-25
 */
public interface IBodyService extends IService<Body> {
    List<Body>  selectBody();
    List selectWithUser();
    List<Object> selectBodyByUser();

}
