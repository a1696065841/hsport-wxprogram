package com.hsport.wxprogram.web.controller.wxduan;

import com.hsport.wxprogram.service.IOrderService;
import com.hsport.wxprogram.domain.Order;
import com.hsport.wxprogram.query.OrderQuery;
import com.hsport.wxprogram.util.AjaxResult;
import com.hsport.wxprogram.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin

public class OrderController {
    @Autowired
    public IOrderService orderService;

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
                orderService.insert(order);
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
    @ApiOperation(value="删除Order信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            orderService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Order详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Order get(@PathVariable("id")Integer id)
    {
        return orderService.selectById(id);
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
    public PageList<Order> json(@RequestBody OrderQuery query)
    {
        Page<Order> page = new Page<Order>(query.getPage(),query.getRows());
            page = orderService.selectPage(page);
            return new PageList<Order>(page.getTotal(),page.getRecords());
    }
}
