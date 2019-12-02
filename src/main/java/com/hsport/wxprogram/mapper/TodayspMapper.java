package com.hsport.wxprogram.mapper;

import com.hsport.wxprogram.domain.Todaysp;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsport.wxprogram.query.TodayspQuery;
import com.hsport.wxprogram.util.AjaxResult;
import com.hsport.wxprogram.util.PageList;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface TodayspMapper extends BaseMapper<Todaysp>{
    /**
     * 保存和修改公用的
     * @param todaysp  传递的实体
     * @return Ajaxresult转换结果
     */
    AjaxResult save(Todaysp todaysp);

    /**
     * 删除对象信息
     * @param id
     * @return
     */
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    Todaysp get(@RequestParam(value="id",required=true) Integer id);


    /**
     * 查看所有的员工信息
     * @return
     */
    public List<Todaysp> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    PageList<Todaysp> json(@RequestBody TodayspQuery query);
}
