package com.hsport.wxprogram.web.controller.userBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.service.ILivetypeService;
import com.hsport.wxprogram.domain.Livetype;
import com.hsport.wxprogram.query.LivetypeQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/livetype")
@CrossOrigin

public class LivetypeController {
    @Autowired
    public ILivetypeService livetypeService;
    @Autowired
    HttpServletRequest request;
    /**
    * 保存和修改公用的
    * @param livetype  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Livetype信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Livetype livetype){
        try {
            if(livetype.getId()!=null){
                livetypeService.updateById(livetype);
            }else{
                livetypeService.insert(livetype);
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
    @ApiOperation(value="删除Livetype信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            livetypeService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage()).setSuccess(false);
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Livetype详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Livetype get(@PathVariable("id")Integer id)
    {
        return livetypeService.selectById(id);
    }

    @ApiOperation(value="根据user的id来获取详细信息")
    @RequestMapping(value = "/getByUserID",method = RequestMethod.POST)
    public AjaxResult getByUserID(@RequestBody User user) {
        Long id = user.getId();
        EntityWrapper<Livetype> livetypeEntityWrapper = new EntityWrapper<>();
        livetypeEntityWrapper.eq("userID",id);
        return AjaxResult.me().setResultObj(livetypeService.selectOne(livetypeEntityWrapper));
    }
    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Livetype详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Livetype> list(){

        return livetypeService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Livetype详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Livetype详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Livetype> json(@RequestBody LivetypeQuery query)
    {
        Page<Livetype> page = new Page<Livetype>(query.getPage(),query.getRows());
            page = livetypeService.selectPage(page);
            return new PageList<Livetype>(page.getTotal(),page.getRecords());
    }
}
