package com.hsport.wxprogram.web.controller;

import com.hsport.wxprogram.service.ISportsprogramService;
import com.hsport.wxprogram.domain.Sportsprogram;
import com.hsport.wxprogram.query.SportsprogramQuery;
import com.hsport.wxprogram.util.AjaxResult;
import com.hsport.wxprogram.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sportsprogram")
@CrossOrigin

public class SportsprogramController {
    @Autowired
    public ISportsprogramService sportsprogramService;

    /**
    * 保存和修改公用的
    * @param sportsprogram  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Sportsprogram信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Sportsprogram sportsprogram){
        try {
            if(sportsprogram.getId()!=null){
                sportsprogramService.updateById(sportsprogram);
            }else{
                sportsprogramService.insert(sportsprogram);
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
    @ApiOperation(value="删除Sportsprogram信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            sportsprogramService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Sportsprogram详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Sportsprogram get(@PathVariable("id")Integer id)
    {
        return sportsprogramService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Sportsprogram详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Sportsprogram> list(){

        return sportsprogramService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Sportsprogram详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Sportsprogram详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Sportsprogram> json(@RequestBody SportsprogramQuery query)
    {
        Page<Sportsprogram> page = new Page<Sportsprogram>(query.getPage(),query.getRows());
            page = sportsprogramService.selectPage(page);
            return new PageList<Sportsprogram>(page.getTotal(),page.getRecords());
    }
}