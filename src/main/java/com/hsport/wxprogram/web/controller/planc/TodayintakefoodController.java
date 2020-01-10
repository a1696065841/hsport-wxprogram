package com.hsport.wxprogram.web.controller.planc;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.domain.vo.TodayintakefoodVo;
import com.hsport.wxprogram.service.ITodayintakefoodService;
import com.hsport.wxprogram.domain.Todayintakefood;
import com.hsport.wxprogram.query.TodayintakefoodQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/todayintakefood")
public class TodayintakefoodController {
    @Autowired
    public ITodayintakefoodService todayintakefoodService;

    /**
    * 保存和修改公用的
    * @param todayintakefoodVo  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Todayintakefood信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody TodayintakefoodVo todayintakefoodVo){
        try {
            todayintakefoodService.saveFoodUpdateImg(todayintakefoodVo);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage()).setSuccess(false);
        }
    }

    @ApiOperation(value = "来获取这个id的Todayintakefood详细信息")
    @RequestMapping(value = "/getByID", method = RequestMethod.POST)
    public AjaxResult getByID(@RequestBody Todayintakefood todayintakefood) {
        return AjaxResult.me().setResultObj(todayintakefoodService.selectById(todayintakefood));
    }


    @ApiOperation(value = "来获取这个id的Todayintakefood详细信息")
    @RequestMapping(value = "/getTodayByUserID", method = RequestMethod.POST)
    public AjaxResult getByUserID(@RequestBody User user) {
        return AjaxResult.me().setResultObj(todayintakefoodService.selectList(new EntityWrapper<Todayintakefood>().eq("userID",user.getId()).eq("date", DateUtil.today())));
    }
    /**
    * 删除对象信息
    * @param todayintakefood
    * @return
    */
    @ApiOperation(value="删除Todayintakefood信息", notes="删除对象信息")
    @RequestMapping(value="/re",method=RequestMethod.POST)
    public AjaxResult delete(@RequestBody Todayintakefood todayintakefood){
        try {
            todayintakefoodService.deleteById(todayintakefood);
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
    @ApiOperation(value="来获取所有Todayintakefood详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public AjaxResult list(){
        return AjaxResult.me().setResultObj(todayintakefoodService.selectList(null));
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Todayintakefood详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Todayintakefood详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody TodayintakefoodQuery query)
    {
        Page<Todayintakefood> page = new Page<Todayintakefood>(query.getPage(),query.getRows());
            page = todayintakefoodService.selectPage(page);
            return AjaxResult.me().setResultObj(new PageList<Todayintakefood>(page.getTotal(),page.getRecords()));
    }
}
