package com.hsport.wxprogram.service;

import com.hsport.wxprogram.domain.Region;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-12-05
 */
public interface IRegionService extends IService<Region> {
   List<Region> selectCity();

    List<Region> selectAreaByCityID(Integer id);

}
