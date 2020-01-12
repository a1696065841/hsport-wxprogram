package com.hsport.wxprogram.web.controller.system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.domain.Product;
import com.hsport.wxprogram.service.ISpecificationService;
import com.hsport.wxprogram.domain.Specification;
import com.hsport.wxprogram.query.SpecificationQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/specification")
public class SpecificationController {
    @Autowired
    public ISpecificationService specificationService;

    /**
    * 保存和修改公用的
    * @param specification  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Specification信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Specification specification){
        try {
            if(specification.getId()!=null){
                specificationService.updateById(specification);
            }else{
                specificationService.insert(specification);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage()).setSuccess(false);
        }
    }

    @ApiOperation(value = "来获取这个id的Specification详细信息")
    @RequestMapping(value = "/getByID", method = RequestMethod.POST)
    public AjaxResult getByID(@RequestBody Specification specification) {
        return AjaxResult.me().setResultObj(specificationService.selectById(specification));
    }

    @ApiOperation(value = "来获取这个id的Specification详细信息")
    @RequestMapping(value = "/getByProductID", method = RequestMethod.POST)
    public AjaxResult getByProductID(@RequestBody Product product) {
        return AjaxResult.me().setResultObj(specificationService.selectList(new EntityWrapper<Specification>().eq("productID",product.getId())));
    }
    /**
    * 删除对象信息
    * @param specification
    * @return
    */
    @ApiOperation(value="删除Specification信息", notes="删除对象信息")
    @RequestMapping(value="/re",method=RequestMethod.POST)
    public AjaxResult delete(@RequestBody Specification specification){
        try {
            specificationService.deleteById(specification);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }



    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Specification详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public AjaxResult list(){
        return AjaxResult.me().setResultObj(specificationService.selectList(null));
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Specification详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Specification详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody SpecificationQuery query)
    {
        Page<Specification> page = new Page<Specification>(query.getPage(),query.getRows());
            page = specificationService.selectPage(page);
            return AjaxResult.me().setResultObj(new PageList<Specification>(page.getTotal(),page.getRecords()));
    }
}
