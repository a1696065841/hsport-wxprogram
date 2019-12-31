package com.hsport.wxprogram.web.controller;

import com.hsport.wxprogram.service.IProductGymService;
import com.hsport.wxprogram.domain.ProductGym;
import com.hsport.wxprogram.query.ProductGymQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/productGym")
public class ProductGymController {
    @Autowired
    public IProductGymService productGymService;

    /**
    * 保存和修改公用的
    * @param productGym  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改ProductGym信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody ProductGym productGym){
        try {
            if(productGym.getId()!=null){
                productGymService.updateById(productGym);
            }else{
                productGymService.insert(productGym);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage()).setSuccess(false);
        }
    }

    /**
    * 删除对象信息
    * @param productGym
    * @return
    */
    @ApiOperation(value="删除ProductGym信息", notes="删除对象信息")
    @RequestMapping(value="/re",method=RequestMethod.POST)
    public AjaxResult delete(@RequestBody ProductGym productGym){
        try {
            productGymService.deleteById(productGym);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }



    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有ProductGym详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些ProductGym详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody ProductGymQuery query){
            return AjaxResult.me().setResultObj(productGymService.selectWithAll(query));
    }

}
