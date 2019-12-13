package com.hsport.wxprogram.mapper;

import com.hsport.wxprogram.domain.Todayburncalories;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsport.wxprogram.query.TodayburncaloriesQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface TodayburncaloriesMapper extends BaseMapper<Todayburncalories>{
    /**
     * 保存和修改公用的
     * @param todayburncalories  传递的实体
     * @return Ajaxresult转换结果
     */
    AjaxResult save(Todayburncalories todayburncalories);

    /**
     * 删除对象信息
     * @param id
     * @return
     */
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    Todayburncalories get(@RequestParam(value="id",required=true) Integer id);


    /**
     * 查看所有的员工信息
     * @return
     */
    public List<Todayburncalories> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    PageList<Todayburncalories> json(@RequestBody TodayburncaloriesQuery query);
    //获取平均总值和已过天数
    HashMap getAvgAndAllByUserID(Integer id);
    //运动总时长
     HashMap selectSportsTimesAvgAndAll(Integer id);
}
