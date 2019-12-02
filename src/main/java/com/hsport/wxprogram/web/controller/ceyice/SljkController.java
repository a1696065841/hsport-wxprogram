package com.hsport.wxprogram.web.controller;

import com.hsport.wxprogram.service.ISljkService;
import com.hsport.wxprogram.domain.Sljk;
import com.hsport.wxprogram.query.SljkQuery;
import com.hsport.wxprogram.util.AjaxResult;
import com.hsport.wxprogram.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sljk")
@CrossOrigin

public class SljkController {
    @Autowired
    public ISljkService sljkService;

    /**
    * 保存和修改公用的
    * @param sljk  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Sljk信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Sljk sljk){
        try {
            if(sljk.getId()!=null){
                sljkService.updateById(sljk);
            }else{
                sljkService.insert(sljk);
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
    @ApiOperation(value="删除Sljk信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            sljkService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Sljk详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Sljk get(@PathVariable("id")Integer id)
    {
        return sljkService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Sljk详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Sljk> list(){

        return sljkService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Sljk详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Sljk详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Sljk> json(@RequestBody SljkQuery query)
    {
        Page<Sljk> page = new Page<Sljk>(query.getPage(),query.getRows());
            page = sljkService.selectPage(page);
            return new PageList<Sljk>(page.getTotal(),page.getRecords());
    }
}
