package com.hsport.wxprogram.mapper;

import com.hsport.wxprogram.domain.Intaketype;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsport.wxprogram.domain.Sportsplan;
import com.hsport.wxprogram.query.IntaketypeQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface IntaketypeMapper extends BaseMapper<Intaketype>{
    /**
     * 保存和修改公用的
     * @param intaketype  传递的实体
     * @return Ajaxresult转换结果
     */
    AjaxResult save(Intaketype intaketype);


    /**
     * 删除对象信息
     * @param id
     * @return
     */
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    Intaketype get(@RequestParam(value="id",required=true) Integer id);


    /**
     * 查看所有的员工信息
     * @return
     */
    public List<Intaketype> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    PageList<Intaketype> json(@RequestBody IntaketypeQuery query);
}
