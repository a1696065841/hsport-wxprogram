package com.hsport.wxprogram.mapper;

import com.hsport.wxprogram.domain.Certificate;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsport.wxprogram.query.CertificateQuery;
import com.hsport.wxprogram.util.AjaxResult;
import com.hsport.wxprogram.util.PageList;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@org.apache.ibatis.annotations.Mapper
public interface CertificateMapper extends BaseMapper<Certificate>{
    /**
     * 保存和修改公用的
     * @param certificate  传递的实体
     * @return Ajaxresult转换结果
     */
    AjaxResult save(Certificate certificate);

    /**
     * 删除对象信息
     * @param id
     * @return
     */
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    Certificate get(@RequestParam(value="id",required=true) Long id);


    /**
     * 查看所有的员工信息
     * @return
     */
    public List<Certificate> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    PageList<Certificate> json(@RequestBody CertificateQuery query);
}
