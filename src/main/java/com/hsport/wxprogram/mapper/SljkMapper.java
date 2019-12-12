package com.hsport.wxprogram.mapper;

import com.hsport.wxprogram.domain.Sljk;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsport.wxprogram.query.SljkQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface SljkMapper extends BaseMapper<Sljk>{
    /**
     * 保存和修改公用的
     * @param sljk  传递的实体
     * @return Ajaxresult转换结果
     */
    AjaxResult save(Sljk sljk);

    /**
     * 删除对象信息
     * @param id
     * @return
     */
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    Sljk get(@RequestParam(value="id",required=true) Integer id);


    /**
     * 查看所有的员工信息
     * @return
     */
    public List<Sljk> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    PageList<Sljk> json(@RequestBody SljkQuery query);
}
