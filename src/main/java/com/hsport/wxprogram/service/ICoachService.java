package com.hsport.wxprogram.service;

import com.hsport.wxprogram.domain.Coach;
import com.baomidou.mybatisplus.service.IService;
import com.hsport.wxprogram.query.CoachQuery;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-11-26
 */
public interface ICoachService extends IService<Coach> {
    List<Object> selectWithGym(@RequestBody CoachQuery query);
    Integer selectWithGymTotal(CoachQuery query);

}
