package com.hsport.wxprogram.web.controller.planc;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.domain.Ceyice;
import com.hsport.wxprogram.service.IStageplanService;
import com.hsport.wxprogram.domain.Stageplan;
import com.hsport.wxprogram.query.StageplanQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/stageplan")
public class StageplanController {
    @Autowired
    public IStageplanService stageplanService;

    /**
    * 保存和修改公用的
    * @param stageplan  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Stageplan信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Stageplan stageplan){
        try {
            if(stageplan.getId()!=null){
                stageplanService.updateById(stageplan);
            }else{
                stageplanService.insert(stageplan);
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
    @ApiOperation(value="删除Stageplan信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            stageplanService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Stageplan详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Stageplan get(@PathVariable("id")Integer id)
    {
        return stageplanService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Stageplan详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Stageplan> list(){

        return stageplanService.selectList(null);
    }
    //获取用户
    @ApiOperation(value="根据用户的id来获取阶段计划详细信息")
    @RequestMapping(value = "/getByUserID/{id}",method = RequestMethod.GET)
    public List<Stageplan> getByUserID(@PathVariable("id")Integer id) {
        EntityWrapper<Stageplan> ceyiceEntityWrapper = new EntityWrapper<>();
        ceyiceEntityWrapper.eq("userID",id);
        return stageplanService.selectList(ceyiceEntityWrapper);
    }

    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Stageplan详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Stageplan详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Stageplan> json(@RequestBody StageplanQuery query)
    {
        Page<Stageplan> page = new Page<Stageplan>(query.getPage(),query.getRows());
            page = stageplanService.selectPage(page);
            return new PageList<Stageplan>(page.getTotal(),page.getRecords());
    }
}
