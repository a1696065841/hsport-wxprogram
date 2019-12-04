package com.hsport.wxprogram.web.controller.wxduan;

import com.hsport.wxprogram.domain.Foodimg;
import com.hsport.wxprogram.service.ISportsimgService;
import com.hsport.wxprogram.domain.Sportsimg;
import com.hsport.wxprogram.query.SportsimgQuery;
import com.hsport.wxprogram.util.AjaxResult;
import com.hsport.wxprogram.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import com.hsport.wxprogram.util.picUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/sportsimg")
@CrossOrigin

public class SportsimgController {
    @Autowired
    public ISportsimgService sportsimgService;

    /**
    * 保存和修改公用的
    * @param sportsimg  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Sportsimg信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Sportsimg sportsimg){
        try {
            if(sportsimg.getId()!=null){
                sportsimgService.updateById(sportsimg);
            }else{
                sportsimgService.insert(sportsimg);
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
    @ApiOperation(value="删除Sportsimg信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            sportsimgService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Sportsimg详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Sportsimg get(@PathVariable("id")Integer id)
    {
        return sportsimgService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Sportsimg详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Sportsimg> list(){

        return sportsimgService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Sportsimg详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Sportsimg详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Sportsimg> json(@RequestBody SportsimgQuery query)
    {
        Page<Sportsimg> page = new Page<Sportsimg>(query.getPage(),query.getRows());
            page = sportsimgService.selectPage(page);
            return new PageList<Sportsimg>(page.getTotal(),page.getRecords());
    }
    @ApiOperation(value="用户上传图片")
    @RequestMapping(value = "/chuanTu",method = RequestMethod.POST)
    public AjaxResult chuanTu(@RequestParam("multipartFile") MultipartFile multipartFile){
        Sportsimg sportsimg = new Sportsimg();
        String UPLOAD_FOLDER = "D:/images/sports";
        Path path = Paths.get(UPLOAD_FOLDER + "/");
        //获取当前登录用户  需要修改
        sportsimg.setUserID(1);
        sportsimg.setTodayspID(1);
        try {
            String s = picUtil.singleFileUpload(multipartFile, path);
            if (s.equals("文件为空，请重新上传")){
                return AjaxResult.me().setMessage("文件为空，请重新上传！");
            }
            sportsimg.setSportsImgUrl(s);
            sportsimg.setSportsImgUrl(s);
            sportsimgService.insert(sportsimg);
            return AjaxResult.me();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.me().setMessage("上传图片失败！"+e.getMessage());
        }
    }
}
