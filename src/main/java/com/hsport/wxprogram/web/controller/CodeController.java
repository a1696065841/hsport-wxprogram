package com.hsport.wxprogram.web.controller;

import com.hsport.wxprogram.common.util.*;
import com.hsport.wxprogram.domain.CouponUser;
import com.hsport.wxprogram.service.ICodeService;
import com.hsport.wxprogram.domain.Code;
import com.hsport.wxprogram.query.CodeQuery;
import com.baomidou.mybatisplus.plugins.Page;
import com.hsport.wxprogram.service.ICouponUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/code")
public class CodeController {
    @Autowired
    public ICodeService codeService;
    @Autowired
    public ICouponUserService couponUserService;
    /**
    * 保存和修改公用的
    * @param code  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Code信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Code code){
        try {
            if(code.getCouponID()!=null){
                codeService.updateById(code);
            }else{
                codeService.insert(code);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage()).setSuccess(false);
        }
    }
     @RequestMapping(value = "/insertCode", method = RequestMethod.POST)
       public AjaxResult insertCode() {
           try {
               for (int i=0;i<=50;){
                   Code code = new Code();
                   code.setId(UserContext.getUUID());
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
           Code code1 = codeService.selectById(code.getId());
           if(code1.getUsed()){
               return new AjaxResult("兑换码错误!");
           }
           code1.setUserID(code.getUserID());
           code1.setUsed(true);
           CouponUser couponUser = new CouponUser();
           couponUser.setUserID(code.getUserID());
           couponUser.setStatus(0);
           couponUser.setCreateTime(DateUtil.now());
           couponUser.setCouponID(code1.getCouponID());
           couponUserService.insert(couponUser);
           boolean b = codeService.updateById(code1);
           return new AjaxResult().setSuccess(b);
       }

    @ApiOperation(value = "来获取这个id的Code详细信息")
    @RequestMapping(value = "/getByID", method = RequestMethod.POST)
    public AjaxResult getByID(@RequestBody Code code) {
        return AjaxResult.me().setResultObj(codeService.selectById(code));
    }

    /**
    * 删除对象信息
    * @param code
    * @return
    */
    @ApiOperation(value="删除Code信息", notes="删除对象信息")
    @RequestMapping(value="/re",method=RequestMethod.POST)
    public AjaxResult delete(@RequestBody Code code){
        try {
            codeService.deleteById(code);
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
    @ApiOperation(value="来获取所有Code详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public AjaxResult list(){
        return AjaxResult.me().setResultObj(codeService.selectList(null));
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Code详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Code详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody CodeQuery query)
    {
        Page<Code> page = new Page<Code>(query.getPage(),query.getRows());
            page = codeService.selectPage(page);
            return AjaxResult.me().setResultObj(new PageList<Code>(page.getTotal(),page.getRecords()));
    }
}
