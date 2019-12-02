package com.hsport.wxprogram.mapper;

import com.hsport.wxprogram.domain.Burncalories;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsport.wxprogram.query.BurncaloriesQuery;
import com.hsport.wxprogram.util.AjaxResult;
import com.hsport.wxprogram.util.PageList;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface BurncaloriesMapper extends BaseMapper<Burncalories>{
    /**
     * 保存和修改公用的
     * @param burncalories  传递的实体
     * @return Ajaxresult转换结果
     */
    AjaxResult save(Burncalories burncalories);

    /**
     * 删除对象信息
     * @param id
     * @return
     */
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    Burncalories get(@RequestParam(value="id",required=true) Long id);


    /**
     * 查看所有的员工信息
     * @return
     */
    public List<Burncalories> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    PageList<Burncalories> json(@RequestBody BurncaloriesQuery query);
}
