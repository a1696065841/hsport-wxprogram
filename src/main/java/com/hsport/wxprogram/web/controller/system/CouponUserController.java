package com.hsport.wxprogram.web.controller.system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.domain.Coupon;
import com.hsport.wxprogram.domain.vo.CouponVo;
import com.hsport.wxprogram.service.ICouponService;
import com.hsport.wxprogram.service.ICouponUserService;
import com.hsport.wxprogram.domain.CouponUser;
import com.hsport.wxprogram.query.CouponUserQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import com.hsport.wxprogram.service.IProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/couponUser")
public class CouponUserController {
    @Autowired
    public ICouponUserService couponUserService;
    @Autowired
    public ICouponService couponService;
    @Autowired
    public IProductService productService;
    /**
    * 保存和修改公用的
    * @param couponUser  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改CouponUser信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody CouponUser couponUser){
        try {
            if(couponUser.getId()!=null){
                couponUserService.updateById(couponUser);
            }else{
                couponUserService.insert(couponUser);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage()).setSuccess(false);
        }
    }

    @ApiOperation(value = "来获取这个id的CouponUser详细信息")
    @RequestMapping(value = "/getByID", method = RequestMethod.POST)
    public AjaxResult getByID(@RequestBody CouponUser couponUser) {
        return AjaxResult.me().setResultObj(couponUserService.selectById(couponUser));
    }

    @ApiOperation(value = "该用户所有能用的优惠卷")
    @RequestMapping(value = "/getByuserID", method = RequestMethod.POST)
    public AjaxResult getByuserID(@RequestBody CouponUser couponUser) {
        List<CouponUser> couponUsers = couponUserService.selectList(new EntityWrapper<CouponUser>().eq("userID", couponUser.getUserID()).eq("status",0));
        System.out.println(couponUser);
        ArrayList<Coupon> coupons = new ArrayList<>();
        for (CouponUser couponUser1 : couponUsers) {
            Coupon coupon = couponService.selectById(couponUser1.getCouponID());
            if (coupon!=null) {
                coupon.setCouponUserID(couponUser1.getCouponID());
                coupons.add(coupon);
            }
        }
        return AjaxResult.me().setResultObj(coupons);
    }

    @ApiOperation(value = "该订单下用户所有能用的优惠卷")
    @RequestMapping(value = "/getCanUseCoupon", method = RequestMethod.POST)
    public AjaxResult getCanUseCoupon(@RequestBody CouponVo couponVo) {
        List<CouponUser> couponUsers = couponUserService.selectList(new EntityWrapper<CouponUser>().eq("userID", couponVo.getUserID()).eq("status",0));
        System.out.println(couponUsers);
        ArrayList<Coupon> coupons = new ArrayList<>();
        for (CouponUser couponUser1 : couponUsers) {
            Coupon coupon = couponService.selectOne(new EntityWrapper<Coupon>().eq("id", couponUser1.getCouponID()).
                    eq("productID", couponVo.getProductID()).le("startTime", DateUtil.now()).ge("endTime", DateUtil.today()));
            if (coupon!=null) {
                coupon.setCouponUserID(couponUser1.getCouponID());
                coupons.add(coupon);
            }
        }
        return AjaxResult.me().setResultObj(coupons);
    }


    /**
    * 删除对象信息
    * @param couponUser
    * @return
    */
    @ApiOperation(value="删除CouponUser信息", notes="删除对象信息")
    @RequestMapping(value="/re",method=RequestMethod.POST)
    public AjaxResult delete(@RequestBody CouponUser couponUser){
        try {
            couponUserService.deleteById(couponUser);
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
    @ApiOperation(value="来获取所有CouponUser详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public AjaxResult list(){
        return AjaxResult.me().setResultObj(couponUserService.selectList(null));
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有CouponUser详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些CouponUser详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody CouponUserQuery query){
        Page<CouponUser> page = new Page<CouponUser>(query.getPage(),query.getRows());
            page = couponUserService.selectPage(page);
            return AjaxResult.me().setResultObj(new PageList<CouponUser>(page.getTotal(),page.getRecords()));
    }
}
