package com.hsport.wxprogram.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.hsport.wxprogram.common.util.*;
import com.hsport.wxprogram.common.util.wxutil.AppletPayUtil;
import com.hsport.wxprogram.common.util.wxutil.RechargeDto;
import com.hsport.wxprogram.domain.Coupon;
import com.hsport.wxprogram.domain.Order;
import com.hsport.wxprogram.domain.Specification;
import com.hsport.wxprogram.service.*;
import com.hsport.wxprogram.service.impl.PayService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;


/****************************************************
 *
 *
 *
 * @author majker
 * @date 2019-03-07 21:21
 * @version 1.0
 **************************************************/

@RestController
@RequestMapping("/paytest")
public class RechargeAPI {

    @Autowired
    private PayService payService;
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
    @Autowired
    public RedisService redisService;
    @Autowired
    public ICouponService couponService;
    private final static Logger logger = LoggerFactory.getLogger(PayService.class);

    @PostMapping
    public Object order(@RequestBody RechargeDto rechargeDto) throws Exception {
        /**
         微信小程序支付
         */
        //获取code
        String code = rechargeDto.getCode();
        //调用接口获取openId
        String openId = AppletPayUtil.getOpenIdByCode(code);
        Order order = new Order();
        Integer productID = rechargeDto.getProductID();
        order.setProductID(productID);
        order.setUserID(rechargeDto.getUserId());
        order.setOrderType(9);
        //获取产品 存入产品价格
        Specification specification = specificationService.selectById(rechargeDto.getSpecificationID());
        if (specification == null) {
            return new AjaxResult("产品异常,请联系客服!");
        }
        BigDecimal specificationPrice = specification.getSpecificationPrice();
        Coupon coupon = couponService.selectById(rechargeDto.getCouponID());
        BigDecimal totalPrice = specificationPrice;
        if (coupon != null) {
            if (coupon.getCouponType() != null) {
                if (coupon.getCouponType() == 2) {
                    totalPrice = specificationPrice.subtract(coupon.getCouponMoney());
                } else if (coupon.getCouponType() == 1) {
                    if (specificationPrice.compareTo(coupon.getFullmoney()) == 1) {
                        totalPrice = specificationPrice.subtract(coupon.getCouponMoney());
                    }
                } else if (coupon.getCouponType() == 3) {
                    totalPrice = specificationPrice.multiply(coupon.getCouponMoney().divide(new BigDecimal(10), 2, BigDecimal.ROUND_HALF_UP));
                }
            }
        }
        order.setTotalPrice(totalPrice);
        //生成id
        String outTradeNo = OrderCodeFactory.getOrderCode(rechargeDto.getUserId());
        order.setId(outTradeNo);
        //设置默认订单和订单日期
        order.setStartDate(DateUtil.now());
        JSONObject json = (JSONObject) JSONObject.toJSON(order);
        redisService.setWithTime(outTradeNo, json.toJSONString(), 5 * 60);
        logger.debug(json.toJSONString());
        return Render.ok(payService.unifiedOrder(outTradeNo, totalPrice, openId));
    }

}
