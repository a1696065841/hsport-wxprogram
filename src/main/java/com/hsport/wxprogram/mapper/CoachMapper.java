package com.hsport.wxprogram.mapper;

import com.hsport.wxprogram.domain.Coach;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsport.wxprogram.query.CoachQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface CoachMapper extends BaseMapper<Coach>{
    /**
     * 保存和修改公用的
     * @param coach  传递的实体
     * @return Ajaxresult转换结果
     */
    AjaxResult save(Coach coach);

    /**
     * 删除对象信息
     * @param id
     * @return
     */
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    Coach get(@RequestParam(value="id",required=true) Integer id);


    /**
     * 查看所有的员工信息
     * @return
     */
    public List<Coach> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    PageList<Coach> json(@RequestBody CoachQuery query);
    List<Object> selectWithGym(@RequestBody CoachQuery query);

    Integer selectWithGymTotal(CoachQuery query);
}
