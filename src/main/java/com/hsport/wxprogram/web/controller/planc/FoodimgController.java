package com.hsport.wxprogram.web.controller.planc;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.util.*;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.service.IFoodimgService;
import com.hsport.wxprogram.domain.Foodimg;
import com.hsport.wxprogram.query.FoodimgQuery;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping("/foodimg")
@CrossOrigin
public class FoodimgController {

    @Autowired
    public IFoodimgService foodimgService;
    @Autowired
    HttpServletRequest request;
    /**
    * 保存和修改公用的
    * @param foodimg  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Foodimg信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Foodimg foodimg){
        AjaxResult ajaxResult = new AjaxResult();
        if (!ajaxResult.haveAnyOneLogin(request)){
            return  new AjaxResult("用户无权限或已过期");
        }
        try {
            if(foodimg.getId()!=null){
                foodimgService.updateById(foodimg);
            }else{
                foodimgService.insert(foodimg);
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
    @ApiOperation(value="删除Foodimg信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        AjaxResult ajaxResult = new AjaxResult();
        if (!ajaxResult.haveSysUserLogin(request)){
            return  new AjaxResult("用户无权限或已过期");
        }
        try {
            foodimgService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage()).setSuccess(false);
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Foodimg详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public AjaxResult get(@PathVariable("id")Integer id)
    {
        return AjaxResult.me().setResultObj(foodimgService.selectById(id));
    }

    @ApiOperation(value = "根据该用户的id查询今天上传的食物图片")
    @RequestMapping(value = "/getFoodListByUserID/{id}", method = RequestMethod.GET)
    public AjaxResult getFoodListByUserID(@PathVariable("id")Integer id) {
        AjaxResult ajaxResult = new AjaxResult();
        if (!ajaxResult.haveAnyOneLogin(request)){
            return new AjaxResult("用户无权限或已过期");
        }
        EntityWrapper<Foodimg> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.eq("date", DateUtil.today());
        userEntityWrapper.eq("userID", id);
        return AjaxResult.me().setResultObj(foodimgService.getFoodListByUserID(id));
    }
    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Foodimg详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Foodimg详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody FoodimgQuery query)
    {
        AjaxResult ajaxResult = new AjaxResult();
        if (!ajaxResult.haveAnyOneLogin(request)){
            return new AjaxResult("用户无权限或已过期");
        }
        Page<Foodimg> page = new Page<Foodimg>(query.getPage(),query.getRows());
            page = foodimgService.selectPage(page);
            return AjaxResult.me().setResultObj(new PageList<Foodimg>(page.getTotal(),page.getRecords()));
    }


}
