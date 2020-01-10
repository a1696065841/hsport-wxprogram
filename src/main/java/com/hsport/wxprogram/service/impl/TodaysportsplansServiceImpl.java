package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.domain.Sportsimg;
import com.hsport.wxprogram.domain.Todayburncalories;
import com.hsport.wxprogram.domain.Todaysportsplans;
import com.hsport.wxprogram.domain.vo.TodaysportsplansVo;
import com.hsport.wxprogram.mapper.SportsimgMapper;
import com.hsport.wxprogram.mapper.TodaysportsplansMapper;
import com.hsport.wxprogram.service.ITodayburncaloriesService;
import com.hsport.wxprogram.service.ITodaysportsplansService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhb
 * @since 2019-11-28
 */
@Service
public class TodaysportsplansServiceImpl extends ServiceImpl<TodaysportsplansMapper, Todaysportsplans> implements ITodaysportsplansService {
    @Autowired
    TodaysportsplansMapper todaysportsplansMapper;
    @Autowired
    SportsimgMapper sportsimgMapper;
    @Autowired
    ITodayburncaloriesService todayburncaloriesService;
    @Override
    public AjaxResult updateSportPlans(TodaysportsplansVo todaysportsplansVo) {
        List<Sportsimg> sportsimgs = todaysportsplansVo.getSportsimgs();
        for (Sportsimg sportsimg : sportsimgs) {
            sportsimg.setUsed(true);
            sportsimgMapper.updateById(sportsimg);
        }
        List<Todaysportsplans> todaysportsplans = todaysportsplansVo.getTodaysportsplans();
        for (Todaysportsplans todaysportsplan : todaysportsplans) {
            todayburncaloriesService.saveTodayburns(todaysportsplan);
            todaysportsplansMapper.updateById(todaysportsplan);
        }
        return AjaxResult.me();
    }
}
