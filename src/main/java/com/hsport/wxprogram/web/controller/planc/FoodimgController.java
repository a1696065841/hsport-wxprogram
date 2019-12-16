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

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping("/foodimg")
@CrossOrigin
public class FoodimgController {

    @Autowired
    public IFoodimgService foodimgService;

    /**
    * 保存和修改公用的
    * @param foodimg  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Foodimg信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Foodimg foodimg){
        try {
            if(foodimg.getId()!=null){
                foodimgService.updateById(foodimg);
            }else{
                foodimgService.insert(foodimg);
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
    @ApiOperation(value="删除Foodimg信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            foodimgService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Foodimg详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Foodimg get(@PathVariable("id")Integer id)
    {
        return foodimgService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Foodimg详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Foodimg> list(){

        return foodimgService.selectList(null);
    }


    @ApiOperation(value = "根据该用户的id查询今天上传的食物图片")
    @RequestMapping(value = "/getFoodListByUserID/{id}", method = RequestMethod.GET)
    public List<Foodimg> getFoodListByUserID(@PathVariable("id")Integer id) {
        EntityWrapper<Foodimg> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.eq("date", DateUtil.today());
        userEntityWrapper.eq("userID", id);
        return foodimgService.getFoodListByUserID(id);
    }
    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Foodimg详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Foodimg详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Foodimg> json(@RequestBody FoodimgQuery query)
    {
        Page<Foodimg> page = new Page<Foodimg>(query.getPage(),query.getRows());
            page = foodimgService.selectPage(page);
            return new PageList<Foodimg>(page.getTotal(),page.getRecords());
    }

    @ApiOperation(value="用户上传图片")
    @RequestMapping(value = "/chuanTu",method = RequestMethod.POST)
    public AjaxResult chuanTu(@RequestParam("multipartFile") MultipartFile multipartFile){
        Foodimg foodimg = new Foodimg();
        String UPLOAD_FOLDER = "D:/images/food";
        Path path = Paths.get(UPLOAD_FOLDER + "/");
        //获取当前登录用户  需要修改
        User user = UserContext.getUser();
        foodimg.setUserID(user.getId());
        foodimg.setCoachID(user.getCoachID());
        foodimg.setDate(DateUtil.today());
        try {
            String s = picUtil.singleFileUpload(multipartFile,path);
            if (s.equals("文件为空，请重新上传")){
                return AjaxResult.me().setMessage("文件为空，请重新上传！");
            }
            foodimg.setFoodImgUrl(s);
            foodimgService.insert(foodimg);
            return AjaxResult.me();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.me().setMessage("上传图片失败！"+e.getMessage());
        }
    }
}
