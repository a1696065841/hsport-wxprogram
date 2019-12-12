package com.hsport.wxprogram.mapper;

import com.hsport.wxprogram.domain.Livetype;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsport.wxprogram.query.LivetypeQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface LivetypeMapper extends BaseMapper<Livetype>{
    /**
     * 保存和修改公用的
     * @param livetype  传递的实体
     * @return Ajaxresult转换结果
     */
    AjaxResult save(Livetype livetype);

    /**
     * 删除对象信息
     * @param id
     * @return
     */
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    Livetype get(@RequestParam(value="id",required=true) Integer id);


    /**
     * 查看所有的员工信息
     * @return
     */
    public List<Livetype> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    PageList<Livetype> json(@RequestBody LivetypeQuery query);
}
