package com.hsport.wxprogram.web.controller.system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.domain.*;
import com.hsport.wxprogram.domain.vo.ProductVo;
import com.hsport.wxprogram.service.*;
import com.hsport.wxprogram.query.ProductQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin

public class ProductController {
    @Autowired
    public IProductService productService;
    @Autowired
    public RedisService redisService;
    @Autowired
    public  IProductserviceService productserviceService;
    @Autowired
    public HttpServletRequest request;
    @Autowired
    public  IProductGymService productGymService;
    @Autowired
    public IDetailsService detailsService;
    @Autowired
    public IGymService gymService;
    /**
     * 保存和修改公用的
     *
     * @param productVo 传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation(value = "新增或修改Product信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody ProductVo productVo) {
        List<Details> details = productVo.getDetails();
        List<Integer> gyms = productVo.getGyms();
        Product product = productVo.getProduct();
       /* Sysuser sysUserLogin = new AjaxResult().isSysUserLogin(request, redisService);
        if(sysUserLogin==null){
            return new AjaxResult("无权限或未登录！");
        }
        product.setSysuserID(sysUserLogin.getId());*/
        List<Productservice> productservices = productVo.getProductservices();
        try {
            if (product.getId() != null) {
                productService.updateById(product);
                for (Productservice productservice : productservices) {
                    productserviceService.updateById(productservice);
                }

            } else {
                productService.insert(product);
                Integer productId = product.getId();
                //添加具体服务
                for (Productservice productservice : productservices) {
                    productservice.setProductID(productId);
                    productserviceService.insert(productservice);
                }
                //添加支持该服务的健身房
                for (Integer gymID : gyms) {
                    ProductGym productGym = new ProductGym();
                    productGym.setProductID(product.getId());
                    productGym.setGymID(gymID);
                    productGymService.insert(productGym);
                }
                for (Details detail : details) {
                    detail.setProductID(productId);
                    detailsService.insert(detail);
                }
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！" + e.getMessage()).setSuccess(false);
        }
    }

    /**
     * 删除对象信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除Product信息", notes = "删除对象信息")
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public AjaxResult delete(@RequestBody Product id) {
        try {
            productService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！" + e.getMessage()).setSuccess(false);
        }
    }


    /**
     * 查看所有的员工信息
     *
     * @return
     */
    @ApiOperation(value = "来获取所有Product详细信息")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public AjaxResult list() {
        return AjaxResult.me().setResultObj(productService.selectList(null));
    }


    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @ApiOperation(value = "来获取所有Product详细信息并分页", notes = "根据page页数和传入的query查询条件 来获取某些Product详细信息")
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public AjaxResult json(@RequestBody ProductQuery query) {
        Page<Product> page = new Page<Product>(query.getPage(), query.getRows());
        page = productService.selectPage(page);
        return AjaxResult.me().setResultObj(new PageList<Product>(page.getTotal(), page.getRecords()));
    }

    @ApiOperation(value = "来获取所有Product详细信息并分页", notes = "根据page页数和传入的query查询条件 来获取某些Product详细信息")
    @RequestMapping(value = "/map", method = RequestMethod.POST)
    public AjaxResult map(@RequestBody ProductQuery query) {
        return AjaxResult.me().setResultObj(productService.selectMap(query));
    }

    @ApiOperation(value = "来获取所有Product详细信息并分页", notes = "根据page页数和传入的query查询条件 来获取某些Product详细信息")
    @RequestMapping(value = "/productAll", method = RequestMethod.POST)
    public AjaxResult productAll(@RequestBody Product product) {
        HashMap<String, Object> map = new HashMap<>();
        Product product1 = productService.selectById(product);
        map.put("product",product);
        Integer id = product1.getId();
        List<Details> details = detailsService.selectList(new EntityWrapper<Details>().eq("productID", id));
        map.put("details",details);
        List<Productservice> productservices = productserviceService.selectList(new EntityWrapper<Productservice>().eq("productID", id));
        map.put("productservices",productservices);
        List<ProductGym> productGyms = productGymService.selectList(new EntityWrapper<ProductGym>().eq("productID", id));
        List<Gym> gyms=new ArrayList<>();
        for (ProductGym productGym : productGyms) {
            Gym gym = gymService.selectById(productGym.getGymID());
            gyms.add(gym);
        }
        map.put("gyms",gyms);
        return AjaxResult.me().setResultObj(map);
    }
}
