package com.hsport.wxprogram.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.common.util.OrderCodeFactory;
import com.hsport.wxprogram.domain.*;
import com.hsport.wxprogram.service.*;
import com.hsport.wxprogram.query.OrderQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    @Autowired
    public IOrderService orderService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    public IProductService productService;
    @Autowired
    public IUserService userService;
    @Autowired
    public ILxxxService lxxxService;
    @Autowired
    public ISpecificationService specificationService;
    @Autowired
    public IDetailsService detailsService;
    @Autowired
    public IGymService gymService;
    private static Logger logger = LoggerFactory.getLogger(OrderController.class);
    /**
     * 保存和修改公用的
     *
     * @param order 传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation(value = "新增或修改Order信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody Order order) {
        try {
            if (order.getId() != null) {
                orderService.updateOrderType(order);
            } else {
                Integer productID = order.getProductID();
                //获取产品 存入产品价格
                Specification specification = specificationService.selectById(order.getSpecificationID());
                if (specification == null) {
                    return new AjaxResult("产品异常,请联系客服!");
                }
                order.setTotalPrice(specification.getSpecificationPrice());
                //生成id
                order.setId(OrderCodeFactory.getOrderCode(order.getUserID()));
                //设置默认订单和订单日期
                order.setOrderType(0);
                order.setStartDate(DateUtil.now());
                orderService.insert(order);
            }
            return AjaxResult.me().setResultObj(order.getTotalPrice());
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！" + e.getMessage()).setSuccess(false);
        }
    }


    @ApiOperation(value = "修改Order信息")
    @RequestMapping(value = "/updateOrderType", method = RequestMethod.POST)
    public AjaxResult updateOrderType(@RequestBody Order order) {
        AjaxResult ajaxResult = null;
        try {
            ajaxResult = orderService.updateOrderType(order);
        } catch (Exception e) {
            return new AjaxResult("服务器异常,请重试或联系客服!");
        }
        return  ajaxResult;
    }

    /**
     *憨憨
     *
     * */
    @ApiOperation(value = "专家解析详情规格参数")
    @RequestMapping(value = "/ceyiceSave", method = RequestMethod.POST)
    public AjaxResult CeyiCesave(@RequestBody Product product) {
        HashMap<String, Object> map = new HashMap<>();
        Product product1 = productService.selectById(77777);
        map.put("product",product1);
        Integer id = product1.getId();
        List<Details> details = detailsService.selectList(new EntityWrapper<Details>().eq("productID", id));
        map.put("details",details);
        List<Specification> specifications = specificationService.selectList(new EntityWrapper<Specification>().eq("productID", id));
        map.put("Specification",specifications);
        return AjaxResult.me().setResultObj(map);
    }

    @ApiOperation(value = "精准瘦身产品详情规格参数")
    @RequestMapping(value = "/JZSSsave", method = RequestMethod.POST)
    public AjaxResult JZSSsave(@RequestBody Product product) {
        HashMap<String, Object> map = new HashMap<>();
        Product product1 = productService.selectById(99999);
        map.put("product",product1);
        Integer id = product1.getId();
        List<Details> details = detailsService.selectList(new EntityWrapper<Details>().eq("productID", id));
        map.put("details",details);
        List<Specification> specifications = specificationService.selectList(new EntityWrapper<Specification>().eq("productID", id));
        map.put("Specification",specifications);
        return AjaxResult.me().setResultObj(map);
    }
    /**
     * 删除对象信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除Order信息", notes = "删除对象信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id) {
        try {
            orderService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！" + e.getMessage()).setSuccess(false);
        }
    }

    //获取用户
    @ApiOperation(value = "根据url的id来获取Order详细信息")
    @RequestMapping(value = "/getByorderID", method = RequestMethod.POST)
    public AjaxResult get(@RequestBody Order order) {
        return AjaxResult.me().setResultObj(orderService.selectById(order));
    }


    @ApiOperation(value = "根据用户的id来获取购买的订单信息")
    @RequestMapping(value = "/selectOrderByUserID", method = RequestMethod.POST)
    public AjaxResult selectOrderByUserID(@RequestBody OrderQuery query) {
        Integer page = (query.getPage()-1)*query.getRows();
        query.setPage(page);
        HashMap<String, Object> map = new HashMap<>();
        map.put("order",orderService.selectOrderByUserID(query));
        map.put("total",orderService.selectOrderByUserIDTotal(query));
        return AjaxResult.me().setResultObj(map);
    }

    @ApiOperation(value = "来获取所有Product详细信息")
    @RequestMapping(value = "/getByID", method = RequestMethod.POST)
    public AjaxResult getByID(@RequestBody Order order) {
        return AjaxResult.me().setResultObj(orderService.selectById(order));
    }


    /**
     * 查看所有的员工信息
     *
     * @RETURN
     */
    @ApiOperation(value = "来获取所有Order详细信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public AjaxResult list() {
        return AjaxResult.me().setResultObj(orderService.selectList(null));
    }


    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @ApiOperation(value = "来获取所有Order详细信息并分页", notes = "根据page页数和传入的query查询条件 来获取某些Order详细信息")
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public AjaxResult json(@RequestBody OrderQuery query) {
        Integer page = (query.getPage()-1)*query.getRows();
        query.setPage(page);
        HashMap<String, Object> bigmap = new HashMap<>();
        List<Object> order = orderService.selectOrderMap(query);
        Integer integer = orderService.selectOrderMapTotal(query);
        bigmap.put("order",order);
        bigmap.put("total",integer);
        return AjaxResult.me().setResultObj(bigmap);
    }
}
