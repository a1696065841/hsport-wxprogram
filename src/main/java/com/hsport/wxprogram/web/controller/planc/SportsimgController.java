package com.hsport.wxprogram.web.controller.planc;

import com.hsport.wxprogram.service.ISportsimgService;
import com.hsport.wxprogram.domain.Sportsimg;
import com.hsport.wxprogram.query.SportsimgQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import com.hsport.wxprogram.common.util.picUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/sportsimg")
@CrossOrigin

public class SportsimgController {
    @Autowired
    public ISportsimgService sportsimgService;
    @Autowired
    HttpServletRequest request;

    @ApiOperation(value="新增或修改Sportsimg信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Sportsimg sportsimg){
        AjaxResult ajaxResult = new AjaxResult();
    /*    if (!ajaxResult.haveAnyOneLogin(request)){
            return  new AjaxResult("用户无权限或已过期,请重新登录");
        }*/
        try {
            if(sportsimg.getId()!=null){
                sportsimgService.updateById(sportsimg);
            }else{
                sportsimgService.insert(sportsimg);
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
    @ApiOperation(value="删除Sportsimg信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        AjaxResult ajaxResult = new AjaxResult();
      /*  if (!ajaxResult.haveSysUserLogin(request)){
            return new AjaxResult("用户无权限或已过期,请重新登录");
        }*/
        try {
            sportsimgService.deleteById(id);
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
    @ApiOperation(value="来获取所有Sportsimg详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Sportsimg详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Sportsimg> json(@RequestBody SportsimgQuery query) {
        Page<Sportsimg> page = new Page<Sportsimg>(query.getPage(),query.getRows());
            page = sportsimgService.selectPage(page);
            return new PageList<Sportsimg>(page.getTotal(),page.getRecords());
    }

}
