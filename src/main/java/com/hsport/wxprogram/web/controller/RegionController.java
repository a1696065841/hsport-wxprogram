package com.hsport.wxprogram.web.controller;

import com.hsport.wxprogram.service.IRegionService;
import com.hsport.wxprogram.domain.Region;
import com.hsport.wxprogram.query.RegionQuery;
import com.hsport.wxprogram.util.AjaxResult;
import com.hsport.wxprogram.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/region")
public class RegionController {
    @Autowired
    public IRegionService regionService;

    /**
    * 保存和修改公用的
    * @param region  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Region信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Region region){
        try {
            if(region.getId()!=null){
                regionService.updateById(region);
            }else{
                regionService.insert(region);
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
    @ApiOperation(value="删除Region信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            regionService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Region详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Region get(@PathVariable("id")Integer id)
    {
        return regionService.selectById(id);
    }

    @ApiOperation(value="获取所有的市")
    @RequestMapping(value ="/selectCity",method = RequestMethod.GET)
    public List<Region> selectCity() {
        return regionService.selectCity();
    }


    @ApiOperation(value="获取市下面的地区")
    @RequestMapping(value ="/selectAreaByCityID/{id}",method = RequestMethod.GET)
    public List<Region> selectAreaByCityID(@PathVariable("id")Integer id) {
        return regionService.selectAreaByCityID(id);
    }

    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Region详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Region> list(){
        return regionService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Region详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Region详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Region> json(@RequestBody RegionQuery query)
    {
        Page<Region> page = new Page<Region>(query.getPage(),query.getRows());
            page = regionService.selectPage(page);
            return new PageList<Region>(page.getTotal(),page.getRecords());
    }
}
