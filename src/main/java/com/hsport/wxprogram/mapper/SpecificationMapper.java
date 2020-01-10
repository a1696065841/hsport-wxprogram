package com.hsport.wxprogram.mapper;

import com.hsport.wxprogram.domain.Specification;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsport.wxprogram.query.SpecificationQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface SpecificationMapper extends BaseMapper<Specification>{
    /**
     * 保存和修改公用的
     * @param specification  传递的实体
     * @return Ajaxresult转换结果
     */
    AjaxResult save(Specification specification);

    Integer insert(Specification specification);
    /**
     * 删除对象信息
     * @param id
     * @return
     */
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    Specification get(@RequestParam(value="id",required=true) Integer id);


    /**
     * 查看所有的员工信息
     * @return
     */
    public List<Specification> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    PageList<Specification> json(@RequestBody SpecificationQuery query);
}
