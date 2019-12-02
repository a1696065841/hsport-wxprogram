package com.hsport.wxprogram.web.controller;

import com.hsport.wxprogram.service.ITodaysportsplansService;
import com.hsport.wxprogram.domain.Todaysportsplans;
import com.hsport.wxprogram.query.TodaysportsplansQuery;
import com.hsport.wxprogram.util.AjaxResult;
import com.hsport.wxprogram.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todaysportsplans")
@CrossOrigin

public class TodaysportsplansController {
    @Autowired
    public ITodaysportsplansService todaysportsplansService;

    /**
    * 保存和修改公用的
    * @param todaysportsplans  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Todaysportsplans信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Todaysportsplans todaysportsplans){
        try {
            if(todaysportsplans.getId()!=null){
                todaysportsplansService.updateById(todaysportsplans);
            }else{
                todaysportsplansService.insert(todaysportsplans);
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
    @ApiOperation(value="删除Todaysportsplans信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            todaysportsplansService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Todaysportsplans详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Todaysportsplans get(@PathVariable("id")Integer id)
    {
        return todaysportsplansService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Todaysportsplans详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Todaysportsplans> list(){
        return todaysportsplansService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Todaysportsplans详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Todaysportsplans详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Todaysportsplans> json(@RequestBody TodaysportsplansQuery query)
    {
        Page<Todaysportsplans> page = new Page<Todaysportsplans>(query.getPage(),query.getRows());
            page = todaysportsplansService.selectPage(page);
            return new PageList<Todaysportsplans>(page.getTotal(),page.getRecords());
    }
}