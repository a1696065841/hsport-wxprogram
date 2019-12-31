package com.hsport.wxprogram.web.controller.system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.service.IRegionService;
import com.hsport.wxprogram.domain.Region;
import com.hsport.wxprogram.query.RegionQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/region")
public class RegionController {
    @Autowired
    public IRegionService regionService;
    @Autowired
    HttpServletRequest request;
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
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage()).setSuccess(false);
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @ApiOperation(value="删除Region信息", notes="删除对象信息")
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public AjaxResult delete(@RequestBody Region id) {
        try {
            regionService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage()).setSuccess(false);
        }
    }



    @ApiOperation(value="获取所有的市")
    @RequestMapping(value ="/selectCity",method = RequestMethod.POST)
    public AjaxResult selectCity() {
        return AjaxResult.me().setResultObj(regionService.selectCity());
    }


    @ApiOperation(value="获取市下面的地区")
    @RequestMapping(value ="/selectAreaByCityID",method = RequestMethod.POST)
    public AjaxResult selectAreaByCityID(@RequestBody Region region) {

        return AjaxResult.me().setResultObj(regionService.selectAreaByCityID(region.getId()));
    }


    @ApiOperation(value="来获取所有Region详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public AjaxResult list(){
        ArrayList<Object> list = new ArrayList<>();
        List<Region> regions = regionService.selectList(null);
        for (Region region : regions) {
            HashMap<String, Object> map = new HashMap<>();
            if (region.getRegionLev()==1){
                map.put("City",region);
                List<Region> children = regionService.selectList(new EntityWrapper<Region>().eq("parentID", region.getId()));
                map.put("children",children);
                list.add(map);
            }
        }
        return AjaxResult.me().setResultObj(list);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Region详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Region详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody RegionQuery query)
    {
        Page<Region> page = new Page<Region>(query.getPage(),query.getRows());
            page = regionService.selectPage(page);
            return AjaxResult.me().setResultObj(new PageList<Region>(page.getTotal(),page.getRecords()));
    }
}
