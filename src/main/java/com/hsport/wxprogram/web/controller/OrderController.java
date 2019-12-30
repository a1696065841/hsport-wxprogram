package com.hsport.wxprogram.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.common.util.OrderCodeFactory;
import com.hsport.wxprogram.domain.Product;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.service.IOrderService;
import com.hsport.wxprogram.domain.Order;
import com.hsport.wxprogram.query.OrderQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import com.hsport.wxprogram.service.IProductService;
import com.hsport.wxprogram.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    /**
    * 保存和修改公用的
    * @param order  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Order信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Order order){
        try {
            if(order.getId()!=null){
                orderService.updateById(order);
            }else{
                Integer productID = order.getProductID();
                //获取产品 存入产品价格
                Product product = productService.selectById(productID);
                order.setTotalPrice(product.getPirce());
                //生成id
                order.setId(OrderCodeFactory.getOrderCode(order.getUserID() ));
                //设置默认订单和订单日期
                order.setOrderType(0);
                order.setStratDate(DateUtil.now());
                orderService.insert(order);
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
    @ApiOperation(value="删除Order信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            orderService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage()).setSuccess(false);
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Order详细信息")
    @RequestMapping(value = "/getByorderID",method = RequestMethod.POST)
    public AjaxResult get(@RequestBody Order order)
    {
        return AjaxResult.me().setResultObj(orderService.selectById(order));
    }


    @ApiOperation(value="根据用户的id来获取购买的订单信息")
    @RequestMapping(value = "/selectOrderByUser",method = RequestMethod.POST)
    public AjaxResult selectOrderByUserID(@RequestBody User user) {
        Long id = user.getId();
        return AjaxResult.me().setResultObj(orderService.selectOrderByUserID(id));
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Order详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Order> list(){

        return orderService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Order详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Order详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Order> json(@RequestBody OrderQuery  query)
    {
        Page<Order> page = new Page<Order>(query.getPage(),query.getRows());
            if (query.getKeyword()==null){
                page = orderService.selectPage(page);
            }else {
                page = orderService.selectPage(page,new EntityWrapper<Order>().like("stratDate",query.getKeyword()));
            }
            return new PageList<Order>(page.getTotal(),page.getRecords());
    }
}
