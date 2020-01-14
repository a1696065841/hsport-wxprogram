package com.hsport.wxprogram.service;

import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.UserContext;
import com.hsport.wxprogram.domain.Code;
import com.hsport.wxprogram.domain.Food;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-11-28
 */
public interface IFoodService extends IService<Food> {
  /*  @RequestMapping(value = "/insertCode", method = RequestMethod.POST)
    public AjaxResult insertCode() {
        try {
            for (int i=0;i<=50;){
                Code code = new Code();
                code.setCodeID(UserContext.getUUID());
                code.setCouponID(2);
                code.setUsed(false);
                codeService.insert(code);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！" + e.getMessage()).setSuccess(false);
        }
        return AjaxResult.me().setMessage("生成兑换码成功");
    }



    @RequestMapping(value = "/useCode", method = RequestMethod.POST)
    public AjaxResult useCode(@RequestBody Code code) {
        System.out.println(code);
        Code code1 = codeService.selectById(code.getCodeID());
        code1.setUserID(code.getUserID());
        code1.setUsed(true);
        boolean b = codeService.updateById(code1);
        return new AjaxResult().setSuccess(b);
    }*/
}
