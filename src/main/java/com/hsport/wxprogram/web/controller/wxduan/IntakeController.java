package com.hsport.wxprogram.web.controller;

import com.hsport.wxprogram.service.IIntakeService;
import com.hsport.wxprogram.domain.Intake;
import com.hsport.wxprogram.query.IntakeQuery;
import com.hsport.wxprogram.util.AjaxResult;
import com.hsport.wxprogram.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/intake")
@CrossOrigin

public class IntakeController {
    @Autowired
    public IIntakeService intakeService;

    /**
    * 保存和修改公用的
    * @param intake  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Intake信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Intake intake){
        try {
            if(intake.getId()!=null){
                intakeService.updateById(intake);
            }else{
                intakeService.insert(intake);
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
    @ApiOperation(value="删除Intake信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            intakeService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Intake详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Intake get(@PathVariable("id")Integer id)
    {
        return intakeService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Intake详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Intake> list(){

        return intakeService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Intake详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Intake详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Intake> json(@RequestBody IntakeQuery query)
    {
        Page<Intake> page = new Page<Intake>(query.getPage(),query.getRows());
            page = intakeService.selectPage(page);
            return new PageList<Intake>(page.getTotal(),page.getRecords());
    }
}
