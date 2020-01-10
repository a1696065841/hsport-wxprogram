package com.hsport.wxprogram.mapper;

import com.hsport.wxprogram.domain.ProductGym;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsport.wxprogram.query.ProductGymQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface ProductGymMapper extends BaseMapper<ProductGym>{
    /**
     * 保存和修改公用的
     * @param productGym  传递的实体
     * @return Ajaxresult转换结果
     */
    AjaxResult save(ProductGym productGym);

    Integer insert(ProductGym productGym);
    /**
     * 删除对象信息
     * @param id
     * @return
     */
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    ProductGym get(@RequestParam(value="id",required=true) Integer id);


    /**
     * 查看所有的员工信息
     * @return
     */
    public List<ProductGym> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    PageList<ProductGym> json(@RequestBody ProductGymQuery query);
    //级联查询
    List<Object> selectWithAll(ProductGymQuery query);
   Integer selectWithAllTotal(ProductGymQuery query);
}
