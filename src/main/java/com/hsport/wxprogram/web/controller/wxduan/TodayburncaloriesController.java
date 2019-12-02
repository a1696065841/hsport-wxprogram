package com.hsport.wxprogram.web.controller;

import com.hsport.wxprogram.service.ITodayburncaloriesService;
import com.hsport.wxprogram.domain.Todayburncalories;
import com.hsport.wxprogram.query.TodayburncaloriesQuery;
import com.hsport.wxprogram.util.AjaxResult;
import com.hsport.wxprogram.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todayburncalories")
@CrossOrigin

public class TodayburncaloriesController {
    @Autowired
    public ITodayburncaloriesService todayburncaloriesService;

    /**
    * 保存和修改公用的
    * @param todayburncalories  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Todayburncalories信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Todayburncalories todayburncalories){
        try {
            if(todayburncalories.getId()!=null){
                todayburncaloriesService.updateById(todayburncalories);
            }else{
                todayburncaloriesService.insert(todayburncalories);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @ApiOperation(value="删除Todayburncalories信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            todayburncaloriesService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Todayburncalories详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Todayburncalories get(@PathVariable("id")Integer id)
    {
        return todayburncaloriesService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Todayburncalories详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Todayburncalories> list(){

        return todayburncaloriesService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Todayburncalories详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Todayburncalories详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Todayburncalories> json(@RequestBody TodayburncaloriesQuery query)
    {
        Page<Todayburncalories> page = new Page<Todayburncalories>(query.getPage(),query.getRows());
            page = todayburncaloriesService.selectPage(page);
            return new PageList<Todayburncalories>(page.getTotal(),page.getRecords());
    }
}
