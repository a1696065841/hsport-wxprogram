package com.hsport.wxprogram.web.controller.system;

import com.hsport.wxprogram.service.ICouponService;
import com.hsport.wxprogram.domain.Coupon;
import com.hsport.wxprogram.query.CouponQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import com.hsport.wxprogram.service.ICouponUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/coupon")
public class CouponController {
    @Autowired
    public ICouponService couponService;
    @Autowired
    public ICouponUserService couponUserService;

    /**
    * 保存和修改公用的
    * @param coupon  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Coupon信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Coupon coupon){
        try {
            if(coupon.getId()!=null){
                couponService.updateById(coupon);
            }else{
                couponService.insert(coupon);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage()).setSuccess(false);
        }
    }

    @ApiOperation(value = "来获取这个id的Coupon详细信息")
    @RequestMapping(value = "/getByID", method = RequestMethod.POST)
    public AjaxResult getByID(@RequestBody Coupon coupon) {

        return AjaxResult.me().setResultObj(couponService.selectById(coupon));
    }

    /**
    * 删除对象信息
    * @param coupon
    * @return
    */
    @ApiOperation(value="删除Coupon信息", notes="删除对象信息")
    @RequestMapping(value="/re",method=RequestMethod.POST)
    public AjaxResult delete(@RequestBody Coupon coupon){
        try {
            couponService.deleteById(coupon);
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
    @ApiOperation(value="来获取所有Coupon详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public AjaxResult list(){
        return AjaxResult.me().setResultObj(couponService.selectList(null));
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Coupon详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Coupon详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody CouponQuery query)
    {
        Page<Coupon> page = new Page<Coupon>(query.getPage(),query.getRows());
            page = couponService.selectPage(page);
            return AjaxResult.me().setResultObj(new PageList<Coupon>(page.getTotal(),page.getRecords()));
    }
}
