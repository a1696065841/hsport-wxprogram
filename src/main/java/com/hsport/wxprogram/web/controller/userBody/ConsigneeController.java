package com.hsport.wxprogram.web.controller.userBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.service.IConsigneeService;
import com.hsport.wxprogram.domain.Consignee;
import com.hsport.wxprogram.query.ConsigneeQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import com.hsport.wxprogram.service.RedisService;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/consignee")
public class ConsigneeController {
    @Autowired
    public IConsigneeService consigneeService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    RedisService redisService;

    /**
     *
    * 保存和修改公用的
    * @param consignee  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Consignee信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Consignee consignee){
        AjaxResult ajaxResult = new AjaxResult();
        System.out.println(redisService);
        User userLogin = ajaxResult.isUserLogin(request,redisService);
        if (userLogin==null){
            return  new AjaxResult("用户令牌失效，重新登录");
        }
        Long userid = userLogin.getId();
        consignee.setUserID(userid);
        try {
            if(consignee.getId()!=null){
                consignee.isFirst();
                consigneeService.updateById(consignee);
            }else{
                consigneeService.insert(consignee);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param consignee
    * @return
    */
    @ApiOperation(value="删除Consignee信息", notes="删除对象信息")
    @RequestMapping(value="/re",method=RequestMethod.POST)
    public AjaxResult delete(@RequestBody Consignee consignee){
        try {
            consigneeService.deleteById(consignee);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage()).setSuccess(false);
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Consignee详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Consignee get(@PathVariable("id")Integer id)
    {
        return consigneeService.selectById(id);
    }


    @ApiOperation(value="根据用户的ID来获取用户的收件地址等信息")
    @RequestMapping(value = "/getByUserID",method = RequestMethod.POST)
    public AjaxResult getByUserID(@RequestBody User user) {
        Long id = user.getId();
        EntityWrapper<Consignee> certificateEntityWrapper = new EntityWrapper<>();
        certificateEntityWrapper.eq("userID",id);
        return AjaxResult.me().setResultObj(consigneeService.selectList(certificateEntityWrapper));
    }
    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Consignee详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Consignee详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Consignee> json(@RequestBody ConsigneeQuery query)
    {
        Page<Consignee> page = new Page<Consignee>(query.getPage(),query.getRows());
            page = consigneeService.selectPage(page);
            return new PageList<Consignee>(page.getTotal(),page.getRecords());
    }
}
