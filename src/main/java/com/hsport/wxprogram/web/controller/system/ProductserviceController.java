package com.hsport.wxprogram.web.controller.system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.domain.Gym;
import com.hsport.wxprogram.domain.Product;
import com.hsport.wxprogram.service.IProductService;
import com.hsport.wxprogram.service.IProductserviceService;
import com.hsport.wxprogram.domain.Productservice;
import com.hsport.wxprogram.query.ProductserviceQuery;
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
@RequestMapping("/productservice")
public class ProductserviceController {
    @Autowired
    public IProductserviceService productserviceService;
    @Autowired
    public IProductService productService;

    @Autowired
    HttpServletRequest request;
    /**
    * 保存和修改公用的
    * @param productservice  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Productservice信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Productservice productservice){
        try {
            if(productservice.getId()!=null){
                productserviceService.updateById(productservice);
            }else{
                productserviceService.insert(productservice);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage()).setSuccess(false);
        }
    }
    @ApiOperation(value = "来获取这个对象的详细信息")
    @RequestMapping(value = "/getByID", method = RequestMethod.POST)
    public AjaxResult getByID(@RequestBody Productservice productservice) {
        return AjaxResult.me().setResultObj(productserviceService.selectById(productservice));
    }
    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @ApiOperation(value="删除Productservice信息", notes="删除对象信息")
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public AjaxResult delete(@RequestBody Productservice id) {
        try {
            productserviceService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage()).setSuccess(false);
        }
    }

    @ApiOperation(value="获取产品下面的服务")
    @RequestMapping(value ="/selectServiceByProductID",method = RequestMethod.POST)
    public AjaxResult selectAreaByCityID(@RequestBody Product product) {
        return AjaxResult.me().setResultObj(productserviceService.selectList(new EntityWrapper<Productservice>().eq("productID",product.getId())));
    }



    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Productservice详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public List<Productservice> list(){

        return productserviceService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Productservice详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Productservice详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Productservice> json(@RequestBody ProductserviceQuery query)
    {
        Page<Productservice> page = new Page<Productservice>(query.getPage(),query.getRows());
            page = productserviceService.selectPage(page);
            return new PageList<Productservice>(page.getTotal(),page.getRecords());
    }
}
