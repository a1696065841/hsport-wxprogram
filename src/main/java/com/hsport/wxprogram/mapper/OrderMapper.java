package com.hsport.wxprogram.mapper;

import com.hsport.wxprogram.domain.Order;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsport.wxprogram.query.OrderQuery;
import com.hsport.wxprogram.util.AjaxResult;
import com.hsport.wxprogram.util.PageList;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface OrderMapper extends BaseMapper<Order>{
    /**
     * 保存和修改公用的
     * @param order  传递的实体
     * @return Ajaxresult转换结果
     */
    AjaxResult save(Order order);

    /**
     * 删除对象信息
     * @param id
     * @return
     */
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    Order get(@RequestParam(value="id",required=true) Integer id);


    /**
     * 查看所有的员工信息
     * @return
     */
    public List<Order> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    PageList<Order> json(@RequestBody OrderQuery query);
}
