package com.hsport.wxprogram.web.controller;

import com.hsport.wxprogram.common.util.IdGen;
import com.hsport.wxprogram.common.util.OrderCodeFactory;
import com.hsport.wxprogram.common.util.Render;
import com.hsport.wxprogram.common.util.wxutil.AppletPayUtil;
import com.hsport.wxprogram.common.util.wxutil.RechargeDto;
import com.hsport.wxprogram.service.impl.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

    @PostMapping
    public Object order(@RequestBody RechargeDto rechargeDto) throws Exception {
        /**
         微信小程序支付
         */
        //获取code
        String code = rechargeDto.getCode();
        //调用接口获取openId
        String openId = AppletPayUtil.getOpenIdByCode(code);
        /*
            生成订单....，这里只创建了订单号
         */
        //订单号  uuid
        String outTradeNo= OrderCodeFactory.getOrderCode(rechargeDto.getUserId());

        return Render.ok(payService.unifiedOrder(outTradeNo,rechargeDto.getRechargeMoney(),openId));
    }
}
