package com.hsport.wxprogram.mapper;

import com.hsport.wxprogram.domain.Intake;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsport.wxprogram.query.IntakeQuery;
import com.hsport.wxprogram.util.AjaxResult;
import com.hsport.wxprogram.util.PageList;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface IntakeMapper extends BaseMapper<Intake>{
    /**
     * 保存和修改公用的
     * @param intake  传递的实体
     * @return Ajaxresult转换结果
     */
    AjaxResult save(Intake intake);

    /**
     * 删除对象信息
     * @param id
     * @return
     */
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    Intake get(@RequestParam(value="id",required=true) Integer id);


    /**
     * 查看所有的员工信息
     * @return
     */
    public List<Intake> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    PageList<Intake> json(@RequestBody IntakeQuery query);
}
