package com.hsport.wxprogram.web.controller.system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.hsport.wxprogram.domain.ArticleProduct;
import com.hsport.wxprogram.domain.Coach;
import com.hsport.wxprogram.domain.Product;
import com.hsport.wxprogram.domain.vo.ArticleVo;
import com.hsport.wxprogram.service.IArticleProductService;
import com.hsport.wxprogram.service.IArticleService;
import com.hsport.wxprogram.domain.Article;
import com.hsport.wxprogram.query.ArticleQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import com.hsport.wxprogram.service.IProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    public IArticleService articleService;
    @Autowired
    public  HttpServletRequest request;
    @Autowired
    public IArticleProductService articleProductService;
    @Autowired
    public IProductService productService;
    /**
    * 保存和修改公用的
    * @param articleVo  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Article信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody ArticleVo articleVo){
        Article article = articleVo.getArticle();
        try {
            if(article.getId()!=null){
                articleService.updateById(article);
            }else{
                articleService.insert(article);
                List<Integer> productIDs = articleVo.getProductIDs();
                for (Integer productID : productIDs) {
                    ArticleProduct articleProduct = new ArticleProduct();
                    articleProduct.setArticleID(article.getId());
                    articleProduct.setProductID(productID);
                    articleProductService.insert(articleProduct);
                }
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

    @RequestMapping(value = "/getArticleProductList",method = RequestMethod.POST)
    public AjaxResult getArticleAndProduct(@RequestBody  Article article){

        List<ArticleProduct> articleID = articleProductService.selectList(new EntityWrapper<ArticleProduct>().eq("articleID", article.getId()));
        System.out.println(articleID.size());
        ArrayList<Product> list = new ArrayList<>();
        for (ArticleProduct articleProduct : articleID) {
            Product product = productService.selectById(articleProduct);
            list.add(product);
        }
        return AjaxResult.me().setResultObj(list);
    }
    @ApiOperation(value = "来获取所有Product详细信息")
    @RequestMapping(value = "/getByID", method = RequestMethod.POST)
    public AjaxResult getByID(@RequestBody Article article) {
        return AjaxResult.me().setResultObj(articleService.selectById(article));
    }

    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Article详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Article详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public AjaxResult json(@RequestBody ArticleQuery query)
    {   HashMap<String, Object> bigmap=new HashMap<>();
        HashMap<String, Object> map=null;
        ArrayList<Object> mapList = new ArrayList<>();
        Page<Article> page = new Page<Article>(query.getPage(),query.getRows());
        EntityWrapper<Article> articleEntityWrapper = new EntityWrapper<>();
            page = articleService.selectPage(page,articleEntityWrapper.like("articleType",query.getArticleType()).like("articleTitle",query.getKeyword()));
        PageList<Article> articlePageList = new PageList<>(page.getTotal(), page.getRecords());
        for (Article article : articlePageList.getRows()) {
            map = new HashMap<>();
            List<ArticleProduct> articleID = articleProductService.selectList(new EntityWrapper<ArticleProduct>().eq("articleID", article.getId()));
            ArrayList<Integer> list = new ArrayList<>();
            for (ArticleProduct articleProduct : articleID) {
                list.add(articleProduct.getProductID());
            }
            map.put("article",article);
            map.put("ProductList",list);
            mapList.add(map);
        }
        bigmap.put("article",mapList);
        bigmap.put("total",articlePageList.getTotal());
        return AjaxResult.me().setResultObj(bigmap);
    }
}
