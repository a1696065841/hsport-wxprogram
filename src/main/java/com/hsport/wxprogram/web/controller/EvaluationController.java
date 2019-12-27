package com.hsport.wxprogram.web.controller;

import com.hsport.wxprogram.service.IEvaluationService;
import com.hsport.wxprogram.domain.Evaluation;
import com.hsport.wxprogram.query.EvaluationQuery;
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
@RequestMapping("/evaluation")
public class EvaluationController {
    @Autowired
    public IEvaluationService evaluationService;
    @Autowired
    HttpServletRequest request;
    /**
    * 保存和修改公用的
    * @param evaluation  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Evaluation信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Evaluation evaluation){
        try {
            if(evaluation.getId()!=null){
                evaluationService.updateById(evaluation);
            }else{
                evaluationService.insert(evaluation);
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
    @ApiOperation(value="删除Evaluation信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            evaluationService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage()).setSuccess(false);
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Evaluation详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Evaluation get(@PathVariable("id")Integer id)
    {
        return evaluationService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Evaluation详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Evaluation> list(){

        return evaluationService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Evaluation详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Evaluation详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Evaluation> json(@RequestBody EvaluationQuery query)
    {
        Page<Evaluation> page = new Page<Evaluation>(query.getPage(),query.getRows());
            page = evaluationService.selectPage(page);
            return new PageList<Evaluation>(page.getTotal(),page.getRecords());
    }
}
