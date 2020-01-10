package com.hsport.wxprogram.web.controller.userBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.service.IYsxgService;
import com.hsport.wxprogram.domain.Ysxg;
import com.hsport.wxprogram.query.YsxgQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/ysxg")
@CrossOrigin
public class YsxgController {
    @Autowired
    public IYsxgService ysxgService;
    @Autowired
    HttpServletRequest request;
    /**
    * 保存和修改公用的
    * @param ysxg  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Ysxg信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Ysxg ysxg){
        try {
            if(ysxg.getId()!=null){
                ysxgService.updateById(ysxg);
            }else{
                ysxgService.insert(ysxg);
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
    @ApiOperation(value="删除Ysxg信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            ysxgService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Ysxg详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Ysxg get(@PathVariable("id")Integer id)
    {
        return ysxgService.selectById(id);
    }


    @ApiOperation(value="根据user的id来获取详细信息")
    @RequestMapping(value = "/getByUserID",method = RequestMethod.POST)
    public AjaxResult getByUserID(@RequestBody User user) {
        Long id = user.getId();
        EntityWrapper<Ysxg> sportsprogramEntityWrapper = new EntityWrapper<>();
        sportsprogramEntityWrapper.eq("userID",id);
        return AjaxResult.me().setResultObj(ysxgService.selectOne(sportsprogramEntityWrapper));
    }
    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Ysxg详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Ysxg> list(){

        return ysxgService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Ysxg详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Ysxg详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Ysxg> json(@RequestBody YsxgQuery query)
    {
        Page<Ysxg> page = new Page<Ysxg>(query.getPage(),query.getRows());
            page = ysxgService.selectPage(page);
            return new PageList<Ysxg>(page.getTotal(),page.getRecords());
    }
}
