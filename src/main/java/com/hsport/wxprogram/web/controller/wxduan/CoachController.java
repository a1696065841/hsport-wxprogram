package com.hsport.wxprogram.web.controller;

import com.hsport.wxprogram.service.ICoachService;
import com.hsport.wxprogram.domain.Coach;
import com.hsport.wxprogram.query.CoachQuery;
import com.hsport.wxprogram.util.AjaxResult;
import com.hsport.wxprogram.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coach")
@CrossOrigin

public class CoachController {
    @Autowired
    public ICoachService coachService;

    /**
    * 保存和修改公用的
    * @param coach  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Coach信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Coach coach){
        try {
            if(coach.getId()!=null){
                coachService.updateById(coach);
            }else{
                coachService.insert(coach);
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
    @ApiOperation(value="删除Coach信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            coachService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Coach详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Coach get(@PathVariable("id")Integer id)
    {
        return coachService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Coach详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Coach> list(){

        return coachService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Coach详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Coach详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Coach> json(@RequestBody CoachQuery query)
    {
        Page<Coach> page = new Page<Coach>(query.getPage(),query.getRows());
            page = coachService.selectPage(page);
            return new PageList<Coach>(page.getTotal(),page.getRecords());
    }
}
