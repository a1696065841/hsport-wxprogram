package com.hsport.wxprogram.web.controller.system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.domain.Article;
import com.hsport.wxprogram.service.IDetailsService;
import com.hsport.wxprogram.domain.Details;
import com.hsport.wxprogram.query.DetailsQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/details")
public class DetailsController {
    @Autowired
    public IDetailsService detailsService;

    /**
    * 保存和修改公用的
    * @param details  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Details信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Details details){
        try {
            if(details.getId()!=null){
                detailsService.updateById(details);
            }else{
                detailsService.insert(details);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage()).setSuccess(false);
        }
    }
    @ApiOperation(value = "来获取所有Product详细信息")
    @RequestMapping(value = "/getByID", method = RequestMethod.POST)
    public AjaxResult getByID(@RequestBody Details details) {
        return AjaxResult.me().setResultObj(detailsService.selectById(details));
    }
    /**
    * 删除对象信息
    * @param details
    * @return
    */
    @ApiOperation(value="删除Details信息", notes="删除对象信息")
    @RequestMapping(value="/re",method=RequestMethod.POST)
    public AjaxResult delete(@RequestBody Details details){
        try {
            detailsService.deleteById(details);
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
    @ApiOperation(value="来获取所有Details详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public AjaxResult list(){
        return AjaxResult.me().setResultObj(detailsService.selectList(null));
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Details详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Details详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody DetailsQuery query)
    {
        Page<Details> page = new Page<Details>(query.getPage(),query.getRows());
            page = detailsService.selectPage(page,new EntityWrapper<Details>().eq("productID",query.getKeyword()));
            return AjaxResult.me().setResultObj(new PageList<Details>(page.getTotal(),page.getRecords()));
    }
}
