package com.hsport.wxprogram.web.controller.userBody;

import com.hsport.wxprogram.service.IJibingService;
import com.hsport.wxprogram.domain.Jibing;
import com.hsport.wxprogram.query.JibingQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jibing")
@CrossOrigin

public class JibingController {
    @Autowired
    public IJibingService jibingService;

    /**
    * 保存和修改公用的
    * @param jibing  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Jibing信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Jibing jibing){
        try {
            if(jibing.getId()!=null){
                jibingService.updateById(jibing);
            }else{
                jibingService.insert(jibing);
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
    @ApiOperation(value="删除Jibing信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            jibingService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Jibing详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Jibing get(@PathVariable("id")Integer id)
    {
        return jibingService.selectById(id);
    }


    @ApiOperation(value="根据user的id来获取Jibing详细信息")
    @RequestMapping(value = "/getByUserID/{id}",method = RequestMethod.GET)
    public Jibing getByUserID(@PathVariable("id")Integer id){
        return jibingService.getByUserID(id);
    }
    
    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Jibing详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Jibing> list(){

        return jibingService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Jibing详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Jibing详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Jibing> json(@RequestBody JibingQuery query)
    {
        Page<Jibing> page = new Page<Jibing>(query.getPage(),query.getRows());
            page = jibingService.selectPage(page);
            return new PageList<Jibing>(page.getTotal(),page.getRecords());
    }
}
