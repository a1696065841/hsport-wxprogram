package com.hsport.wxprogram.mapper;

import com.hsport.wxprogram.domain.Todayintake;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsport.wxprogram.query.TodayintakeQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface TodayintakeMapper extends BaseMapper<Todayintake>{
    /**
     * 保存和修改公用的
     * @param todayintake  传递的实体
     * @return Ajaxresult转换结果
     */
    AjaxResult save(Todayintake todayintake);

    /**
     * 删除对象信息
     * @param id
     * @return
     */
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    Todayintake get(@RequestParam(value="id",required=true) Integer id);


    /**
     * 查看所有的员工信息
     * @return
     */
    public List<Todayintake> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    PageList<Todayintake> json(@RequestBody TodayintakeQuery query);

    HashMap getAvgAndAllByUserID(Integer id);
}
