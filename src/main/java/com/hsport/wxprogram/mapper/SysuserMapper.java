package com.hsport.wxprogram.mapper;

import com.hsport.wxprogram.domain.Sysuser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsport.wxprogram.query.SysuserQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface SysuserMapper extends BaseMapper<Sysuser>{
    /**
     * 保存和修改公用的
     * @param sysuser  传递的实体
     * @return Ajaxresult转换结果
     */
    AjaxResult save(Sysuser sysuser);

    /**
     * 删除对象信息
     * @param id
     * @return
     */
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    Sysuser get(@RequestParam(value="id",required=true) Integer id);


    /**
     * 查看所有的员工信息
     * @return
     */
    public List<Sysuser> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    PageList<Sysuser> json(@RequestBody SysuserQuery query);
}
