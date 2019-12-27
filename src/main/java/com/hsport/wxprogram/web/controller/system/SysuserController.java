package com.hsport.wxprogram.web.controller.system;


import com.hsport.wxprogram.domain.Sysuser;
import com.hsport.wxprogram.service.ISysuserService;

import com.hsport.wxprogram.query.SysuserQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/sysuser")
public class SysuserController {
    @Autowired
    public ISysuserService sysuserService;
    @Autowired
    HttpServletRequest request;
    /**
    * 保存和修改公用的
    * @param sysuser  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Sysuser信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Sysuser sysuser){
        try {
            if(sysuser.getId()!=null){
                sysuserService.updateById(sysuser);
            }else{
                sysuserService.insert(sysuser);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage()).setSuccess(false);
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @ApiOperation(value="删除Sysuser信息", notes="删除对象信息")
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public AjaxResult delete(@RequestBody Sysuser id) {
        try {
            sysuserService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage()).setSuccess(false);
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Sysuser详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Sysuser get(@PathVariable("id")Integer id)
    {
        return sysuserService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Sysuser详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Sysuser> list(){

        return sysuserService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Sysuser详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Sysuser详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Sysuser> json(@RequestBody SysuserQuery query)
    {
        Page<Sysuser> page = new Page<Sysuser>(query.getPage(),query.getRows());
            page = sysuserService.selectPage(page);
            return new PageList<Sysuser>(page.getTotal(),page.getRecords());
    }
}
