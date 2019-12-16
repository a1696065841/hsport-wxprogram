package com.hsport.wxprogram.web.controller.userBody;

import com.hsport.wxprogram.service.IUserProService;
import com.hsport.wxprogram.domain.UserPro;
import com.hsport.wxprogram.query.UserProQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userPro")
@CrossOrigin

public class UserProController {
    @Autowired
    public IUserProService userProService;

    /**
    * 保存和修改公用的
    * @param userPro  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改UserPro信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody UserPro userPro){
        try {
            if(userPro.getId()!=null){
                userProService.updateById(userPro);
            }else{
                userProService.insert(userPro);
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
    @ApiOperation(value="删除UserPro信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            userProService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取UserPro详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public UserPro get(@PathVariable("id")Integer id)
    {
        return userProService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有UserPro详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<UserPro> list(){

        return userProService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有UserPro详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些UserPro详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<UserPro> json(@RequestBody UserProQuery query)
    {
        Page<UserPro> page = new Page<UserPro>(query.getPage(),query.getRows());
            page = userProService.selectPage(page);
            return new PageList<UserPro>(page.getTotal(),page.getRecords());
    }
}
