package com.hsport.wxprogram.web.controller;

import com.hsport.wxprogram.service.IStudentService;
import com.hsport.wxprogram.domain.Student;
import com.hsport.wxprogram.query.StudentQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController {
    @Autowired
    public IStudentService studentService;

    /**
    * 保存和修改公用的
    * @param student  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Student信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Student student){
        try {
            if(student.getId()!=null){
                studentService.updateById(student);
            }else{
                studentService.insert(student);
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
    @ApiOperation(value="删除Student信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            studentService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Student详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Student get(@PathVariable("id")Integer id)
    {
        return studentService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Student详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Student> list(){

        return studentService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Student详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Student详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Student> json(@RequestBody StudentQuery query)
    {
        Page<Student> page = new Page<Student>(query.getPage(),query.getRows());
            page = studentService.selectPage(page);
            return new PageList<Student>(page.getTotal(),page.getRecords());
    }
}
