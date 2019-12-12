package com.hsport.wxprogram.web.controller.wxduan;

import com.hsport.wxprogram.service.ITodayintakeService;
import com.hsport.wxprogram.domain.Todayintake;
import com.hsport.wxprogram.query.TodayintakeQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 客户今日实际摄入
 *
 * */
@RestController
@RequestMapping("/todayintake")
@CrossOrigin

public class TodayintakeController {
    @Autowired
    public ITodayintakeService todayintakeService;

    /**
    * 保存和修改公用的
    * @param todayintake  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Todayintake信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Todayintake todayintake){
        try {
            if(todayintake.getId()!=null){
                todayintakeService.updateById(todayintake);
            }else{
                todayintakeService.insert(todayintake);
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
    @ApiOperation(value="删除Todayintake信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            todayintakeService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Todayintake详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Todayintake get(@PathVariable("id")Integer id)
    {
        return todayintakeService.selectById(id);
    }



    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Todayintake详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Todayintake> list(){

        return todayintakeService.selectList(null);
    }
    @ApiOperation(value="来获取用户的平均摄入和总摄入 已过天数等详细信息")
    @RequestMapping(value = "/getAvgAndAllByUserID/{id}",method = RequestMethod.GET)
    public HashMap getAvgAndAllByUserID(@PathVariable("id") Integer id){
        return todayintakeService.getAvgAndAllByUserID(id);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Todayintake详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Todayintake详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Todayintake> json(@RequestBody TodayintakeQuery query) {
        Page<Todayintake> page = new Page<Todayintake>(query.getPage(),query.getRows());
            page = todayintakeService.selectPage(page);
            return new PageList<Todayintake>(page.getTotal(),page.getRecords());
    }
}
