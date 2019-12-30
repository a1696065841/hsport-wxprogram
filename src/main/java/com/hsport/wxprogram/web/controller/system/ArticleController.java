package com.hsport.wxprogram.web.controller.system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.hsport.wxprogram.domain.Coach;
import com.hsport.wxprogram.service.IArticleService;
import com.hsport.wxprogram.domain.Article;
import com.hsport.wxprogram.query.ArticleQuery;
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
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    public IArticleService articleService;
    @Autowired
    HttpServletRequest request;
    /**
    * 保存和修改公用的
    * @param article  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Article信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Article article){
        try {
            if(article.getId()!=null){
                articleService.updateById(article);
            }else{
                articleService.insert(article);
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
    @ApiOperation(value="删除Article信息", notes="删除对象信息")
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public AjaxResult delete(@RequestBody Article id) {
        try {
            articleService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage()).setSuccess(false);
        }
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Article详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public AjaxResult list(){
        return AjaxResult.me().setResultObj(articleService.selectList(null));
    }
    //获取文案分类列表
    @RequestMapping(value = "/getArticleType",method = RequestMethod.POST)
    public AjaxResult getArticleType(){
        return AjaxResult.me().setResultObj(articleService.getArticleType());
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Article详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Article详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Article> json(@RequestBody ArticleQuery query)
    {
        Page<Article> page = new Page<Article>(query.getPage(),query.getRows());
        EntityWrapper<Article> articleEntityWrapper = new EntityWrapper<>();
            page = articleService.selectPage(page,articleEntityWrapper.eq("articleType",query.getArticleType()));
            return new PageList<Article>(page.getTotal(),page.getRecords());
    }
}