package com.hsport.wxprogram.web.controller.planc;

import com.hsport.wxprogram.service.IFoodService;
import com.hsport.wxprogram.domain.Food;
import com.hsport.wxprogram.query.FoodQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/food")
@CrossOrigin

public class FoodController {
    @Autowired
    public IFoodService foodService;
    @Autowired
    HttpServletRequest request;
    /**
    * 保存和修改公用的
    * @param food  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Food信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Food food){
        AjaxResult ajaxResult = new AjaxResult();
        if (!ajaxResult.haveAnyOneLogin(request)){
            return new AjaxResult("用户无权限或已过期");
        }
        try {
            if(food.getId()!=null){
                foodService.updateById(food);
            }else{
                foodService.insert(food);
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
    @ApiOperation(value="删除Food信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        AjaxResult ajaxResult = new AjaxResult();
        if (!ajaxResult.haveAnyOneLogin(request)){
            return new AjaxResult("用户无权限或已过期");
        }
        try {
            foodService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage()).setSuccess(false);
        }
    }




    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Food详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Food详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Food> json(@RequestBody FoodQuery query)
    {
        Page<Food> page = new Page<Food>(query.getPage(),query.getRows());
            page = foodService.selectPage(page);
            return new PageList<Food>(page.getTotal(),page.getRecords());
    }


}
