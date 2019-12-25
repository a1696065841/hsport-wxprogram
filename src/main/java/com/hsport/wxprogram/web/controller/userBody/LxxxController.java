package com.hsport.wxprogram.web.controller.userBody;

import com.hsport.wxprogram.service.ILxxxService;
import com.hsport.wxprogram.domain.Lxxx;
import com.hsport.wxprogram.query.LxxxQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/lxxx")
public class LxxxController {
    @Autowired
    public ILxxxService lxxxService;

    /**
    * 保存和修改公用的
    * @param lxxx  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Lxxx信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Lxxx lxxx){
        try {
            if(lxxx.getId()!=null){
                lxxxService.updateById(lxxx);
            }else{
                lxxxService.insert(lxxx);
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
    @ApiOperation(value="删除Lxxx信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            lxxxService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Lxxx详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Lxxx get(@PathVariable("id")Integer id)
    {
        return lxxxService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Lxxx详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Lxxx> list(){

        return lxxxService.selectList(null);
    }


    @ApiOperation(value="根据用户的id来获取他的联系信息和基础信息")
    @RequestMapping(value = "/getByUserID/{id}",method = RequestMethod.GET)
    public Lxxx getByUserID(@PathVariable("id")Integer id)
    {
        return lxxxService.getByUserID(id);
    }

    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Lxxx详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Lxxx详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Lxxx> json(@RequestBody LxxxQuery query)
    {
        Page<Lxxx> page = new Page<Lxxx>(query.getPage(),query.getRows());
            page = lxxxService.selectPage(page);
            return new PageList<Lxxx>(page.getTotal(),page.getRecords());
    }
}
