package com.hsport.wxprogram.web.controller;

import com.hsport.wxprogram.service.ICoachOrderService;
import com.hsport.wxprogram.domain.CoachOrder;
import com.hsport.wxprogram.query.CoachOrderQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/coachOrder")
public class CoachOrderController {
    @Autowired
    public ICoachOrderService coachOrderService;

    /**
    * 保存和修改公用的
    * @param coachOrder  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改CoachOrder信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody CoachOrder coachOrder){
        try {
            if(coachOrder.getId()!=null){
                coachOrderService.updateById(coachOrder);
            }else{
                coachOrderService.insert(coachOrder);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage()).setSuccess(false);
        }
    }

    @ApiOperation(value = "来获取这个id的CoachOrder详细信息")
    @RequestMapping(value = "/getByID", method = RequestMethod.POST)
    public AjaxResult getByID(@RequestBody CoachOrder coachOrder) {
        return AjaxResult.me().setResultObj(coachOrderService.selectById(coachOrder));
    }
    /**
    * 删除对象信息
    * @param coachOrder
    * @return
    */
    @ApiOperation(value="删除CoachOrder信息", notes="删除对象信息")
    @RequestMapping(value="/re",method=RequestMethod.POST)
    public AjaxResult delete(@RequestBody CoachOrder coachOrder){
        try {
            coachOrderService.deleteById(coachOrder);
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
    @ApiOperation(value="来获取所有CoachOrder详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public AjaxResult list(){
        return AjaxResult.me().setResultObj(coachOrderService.selectList(null));
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有CoachOrder详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些CoachOrder详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody CoachOrderQuery query)
    {
        Page<CoachOrder> page = new Page<CoachOrder>(query.getPage(),query.getRows());
            page = coachOrderService.selectPage(page);
            return AjaxResult.me().setResultObj(new PageList<CoachOrder>(page.getTotal(),page.getRecords()));
    }
}
