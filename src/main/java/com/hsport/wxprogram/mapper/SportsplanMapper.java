package com.hsport.wxprogram.mapper;

import com.hsport.wxprogram.domain.Sportsplan;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsport.wxprogram.query.SportsplanQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface SportsplanMapper extends BaseMapper<Sportsplan>{
    /**
     * 保存和修改公用的
     * @param sportsplan  传递的实体
     * @return Ajaxresult转换结果
     */
    AjaxResult save(Sportsplan sportsplan);

    Integer insert(Sportsplan sportsplan);

    /**
     * 删除对象信息
     * @param id
     * @return
     */
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    Sportsplan get(@RequestParam(value="id",required=true) Integer id);


    /**
     * 查看所有的员工信息
     * @return
     */
    public List<Sportsplan> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    PageList<Sportsplan> json(@RequestBody SportsplanQuery query);

    List<Sportsplan> selectPlanByUserID(Long id);

    List<HashMap> selectEverDayIntakeAndBurn (SportsplanQuery sportsplanQuery);
    HashMap getUserIntakeEverday(Long id);
    Object getMyPlan(SportsplanQuery sportsplanQuery);
}
