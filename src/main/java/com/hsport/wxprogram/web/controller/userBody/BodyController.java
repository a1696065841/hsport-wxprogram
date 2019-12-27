package com.hsport.wxprogram.web.controller.userBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.service.IBodyService;
import com.hsport.wxprogram.domain.Body;
import com.hsport.wxprogram.query.BodyQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/body")
public class BodyController {
    @Autowired
    public IBodyService bodyService;
    @Autowired
    HttpServletRequest request;
    /**
    * 保存和修改公用的
    * @param body  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Body信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Body body){
        try {
            if(body.getId()!=null){
                bodyService.updateById(body);
            }else{
                bodyService.insert(body);
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
    @ApiOperation(value="删除Body信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            bodyService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage()).setSuccess(false);
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Body详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Body get(@PathVariable("id")Integer id)
    {
        return bodyService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Body详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Body> list(){

        return bodyService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Body详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Body详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Body> json(@RequestBody BodyQuery query) {
        Page<Body> page = new Page<Body>(query.getPage(),query.getRows());
            page = bodyService.selectPage(page);
            return new PageList<Body>(page.getTotal(),page.getRecords());
    }

    @ApiOperation(value="获取所有User用户 对应的Body身体条件 详细信息并分页", notes="直接请求  啥都不用传!给力嗷")
    @RequestMapping(value = "/userbody",method = RequestMethod.POST)
    public List<Object> Userbody(@RequestBody BodyQuery query) {
        Page<Body> page = new Page<Body>(query.getPage(),query.getRows());
        List<Object> objects = bodyService.selectBodyByUser();
        return objects;
    }

    @ApiOperation(value="根据userID的bodyid来获取Body详细信息")
    @RequestMapping(value = "/selectByUserID/{id}",method = RequestMethod.GET)
    public Body selectByUserID(@PathVariable("id")Integer id) {

        return    bodyService.selectOne(new EntityWrapper<Body>().eq("userID",id));
    }


}
