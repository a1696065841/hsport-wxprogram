package com.hsport.wxprogram.web.controller.system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.service.IGymService;
import com.hsport.wxprogram.domain.Gym;
import com.hsport.wxprogram.query.GymQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/gym")
@CrossOrigin

public class GymController {
    @Autowired
    public IGymService gymService;
    @Autowired
    HttpServletRequest request;
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
                gym.setGymDate(DateUtil.today());
                gymService.insert(gym);
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
    @ApiOperation(value="删除Gym信息", notes="删除对象信息")
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public AjaxResult delete(@RequestBody Gym id) {
        try {
            gymService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage()).setSuccess(false);
        }
    }



    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Gym详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public AjaxResult list(){
        return AjaxResult.me().setResultObj(gymService.selectList(null));
    }

    @ApiOperation(value="获取地区的健身房")
    @RequestMapping(value ="/selectGymByAreaID",method = RequestMethod.POST)
    public AjaxResult selectGymByAreaID(@RequestBody  Gym gym) {
        Integer id = gym.getId();
        List<Gym> gyms = gymService.selectGymByAreaID(id);
        if (gyms.size()>0){
            return AjaxResult.me().setResultObj(gymService.selectGymByAreaID(id));
        }else {
            return  AjaxResult.me().setResultObj(gymService.selectGymByParentID(id));
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
    public AjaxResult json(@RequestBody GymQuery query)
    {
        HashMap<String, Object> map = new HashMap<>();
        List<Object> objects = gymService.selectGymWithRegion(query);
        map.put("gym",objects);
        map.put("rows",objects.size());
        return AjaxResult.me().setResultObj(map);
    }
    @ApiOperation(value="来获取所有Gym详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Gym详细信息")
    @RequestMapping(value = "/selectByName",method = RequestMethod.POST)
    public AjaxResult selectByName(@RequestBody Gym gym)
    {
        String gym_name = gym.getGym_name();
        return AjaxResult.me().setResultObj(gymService.selectList(new EntityWrapper<Gym>().like("gym_name",gym_name)));
    }
}
