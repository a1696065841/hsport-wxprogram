package com.hsport.wxprogram.web.controller;

import com.hsport.wxprogram.service.IArticleProductService;
import com.hsport.wxprogram.domain.ArticleProduct;
import com.hsport.wxprogram.query.ArticleProductQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/articleProduct")
public class ArticleProductController {
    @Autowired
    public IArticleProductService articleProductService;

    /**
    * 保存和修改公用的
    * @param articleProduct  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改ArticleProduct信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody ArticleProduct articleProduct){
        try {
            if(articleProduct.getId()!=null){
                articleProductService.updateById(articleProduct);
            }else{
                articleProductService.insert(articleProduct);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage()).setSuccess(false);
        }
    }

    /**
    * 删除对象信息
    * @param articleProduct
    * @return
    */
    @ApiOperation(value="删除ArticleProduct信息", notes="删除对象信息")
    @RequestMapping(value="/re",method=RequestMethod.POST)
    public AjaxResult delete(@RequestBody ArticleProduct articleProduct){
        try {
            articleProductService.deleteById(articleProduct);
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
    @ApiOperation(value="来获取所有ArticleProduct详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public AjaxResult list(){
        return AjaxResult.me().setResultObj(articleProductService.selectList(null));
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有ArticleProduct详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些ArticleProduct详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody ArticleProductQuery query)
        {
        Page<ArticleProduct> page = new Page<ArticleProduct>(query.getPage(),query.getRows());
            page = articleProductService.selectPage(page);
            return AjaxResult.me().setResultObj(new PageList<ArticleProduct>(page.getTotal(),page.getRecords()));
    }
}
