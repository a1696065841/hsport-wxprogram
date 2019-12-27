package com.hsport.wxprogram.web.controller.system;

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

    //获取用户
    @ApiOperation(value="根据url的id来获取Productservice详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Productservice get(@PathVariable("id")Integer id)
    {
        return productserviceService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Productservice详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
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
