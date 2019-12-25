package com.hsport.wxprogram.web.controller.system;

import com.hsport.wxprogram.service.IGymService;
import com.hsport.wxprogram.domain.Gym;
import com.hsport.wxprogram.query.GymQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gym")
@CrossOrigin

public class GymController {
    @Autowired
    public IGymService gymService;

    /**
    * 保存和修改公用的
    * @param gym  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Gym信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Gym gym){
        try {
            if(gym.getId()!=null){
                gymService.updateById(gym);
            }else{
                gymService.insert(gym);
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
    @ApiOperation(value="删除Gym信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            gymService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Gym详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Gym get(@PathVariable("id")Integer id)
    {
        return gymService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Gym详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Gym> list(){

        return gymService.selectList(null);
    }

    @ApiOperation(value="获取地区的健身房")
    @RequestMapping(value ="/selectGymByAreaID/{id}",method = RequestMethod.GET)
    public List<Gym> selectGymByAreaID(@PathVariable("id")Integer id) {
        List<Gym> gyms = gymService.selectGymByAreaID(id);
        if (gyms.size()>0){
            return gymService.selectGymByAreaID(id);
        }else {
            return  gymService.selectGymByParentID(id);
        }
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Gym详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Gym详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Gym> json(@RequestBody GymQuery query)
    {
        Page<Gym> page = new Page<Gym>(query.getPage(),query.getRows());
            page = gymService.selectPage(page);
            return new PageList<Gym>(page.getTotal(),page.getRecords());
    }
}
