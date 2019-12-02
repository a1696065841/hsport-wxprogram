package com.hsport.wxprogram.web.controller;

import com.hsport.wxprogram.service.ICertificateService;
import com.hsport.wxprogram.domain.Certificate;
import com.hsport.wxprogram.query.CertificateQuery;
import com.hsport.wxprogram.util.AjaxResult;
import com.hsport.wxprogram.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/certificate")
public class CertificateController {
    @Autowired
    public ICertificateService certificateService;

    /**
    * 保存和修改公用的
    * @param certificate  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Certificate certificate){
        try {
            if(certificate.getId()!=null){
                certificateService.updateById(certificate);
            }else{
                certificateService.insert(certificate);
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
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            certificateService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Certificate get(@PathVariable("id")Integer id)
    {
        return certificateService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Certificate> list(){

        return certificateService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Certificate> json(@RequestBody CertificateQuery query)
    {
        Page<Certificate> page = new Page<Certificate>(query.getPage(),query.getRows());
            page = certificateService.selectPage(page);
            return new PageList<Certificate>(page.getTotal(),page.getRecords());
    }
}
