package com.hsport.wxprogram.web.controller.planc;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.domain.Foodimg;
import com.hsport.wxprogram.service.IIntaketypeService;
import com.hsport.wxprogram.domain.Intaketype;
import com.hsport.wxprogram.query.IntaketypeQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/intaketype")
public class IntaketypeController {
    @Autowired
    public IIntaketypeService intaketypeService;
    @Autowired
    HttpServletRequest request;

    /**
    * 保存和修改公用的
    * @param intaketype  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Intaketype信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Intaketype intaketype){
        AjaxResult ajaxResult = new AjaxResult();
        if (!ajaxResult.haveCoachOrSysLogin(request)){
            return  new AjaxResult("用户无权限或已过期");
        }
        request.getHeader("token");
        try {
            if(intaketype.getId()!=null){
                intaketypeService.updateById(intaketype);
            }else{
               intaketypeService.insert(intaketype);
                System.out.println(intaketype.getId());

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
    @ApiOperation(value="删除Intaketype信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        AjaxResult ajaxResult = new AjaxResult();
        if (!ajaxResult.haveCoachOrSysLogin(request)){
            return   new AjaxResult("用户无权限或已过期");
        }
        try {
            intaketypeService.deleteById(id);
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
    @ApiOperation(value="来获取所有Intaketype详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Intaketype详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Intaketype> json(@RequestBody IntaketypeQuery query)
    {
        Page<Intaketype> page = new Page<Intaketype>(query.getPage(),query.getRows());
            page = intaketypeService.selectPage(page);
            return new PageList<Intaketype>(page.getTotal(),page.getRecords());
    }
}
