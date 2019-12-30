package com.hsport.wxprogram.web.controller.userBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.service.IDuibituService;
import com.hsport.wxprogram.domain.Duibitu;
import com.hsport.wxprogram.query.DuibituQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import com.hsport.wxprogram.common.util.picUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/duibitu")
@CrossOrigin

public class DuibituController {
    @Autowired
    public IDuibituService duibituService;
    @Autowired
    HttpServletRequest request;
    /**
     * 保存和修改公用的
     *
     * @param duibitu 传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation(value = "新增或修改Duibitu信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody Duibitu duibitu) {
        try {
            if (duibitu.getId() != null) {
                duibituService.updateById(duibitu);
            } else {
                duibituService.insert(duibitu);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！" + e.getMessage()).setSuccess(false);
        }
    }

    /**
     * 删除对象信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除Duibitu信息", notes = "删除对象信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id) {
        try {
            duibituService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！" + e.getMessage()).setSuccess(false);
        }
    }

    //获取用户
    @ApiOperation(value = "根据url的id来获取Duibitu详细信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Duibitu get(@PathVariable("id") Integer id) {
        return duibituService.selectById(id);
    }


    /**
     * 查看所有的员工信息
     *
     * @return
     */
    @ApiOperation(value = "来获取所有Duibitu详细信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Duibitu> list() {
        return duibituService.selectList(null);
    }

    @ApiOperation(value = "根据该用户的id查询对比图")
    @RequestMapping(value = "/getListByUserID", method = RequestMethod.POST)
    public AjaxResult getListByUserID(@RequestBody User user) {
        Long id = user.getId();
        return AjaxResult.me().setResultObj(duibituService.getListByUserID(id));
    }

    @ApiOperation(value = "根据该用户的id查之前和最近的对比图")
    @RequestMapping(value = "/getImgByUserID", method = RequestMethod.POST)
    public AjaxResult getImgByUserID(@RequestBody User user) {
        Long id = user.getId();
        HashMap<String, Object> map = new HashMap<>();
        //最近的正面对比图
        /*EntityWrapper<Duibitu> nowDuibituEntityWrapper = new EntityWrapper<>();
        nowDuibituEntityWrapper.eq("userID",id);
        nowDuibituEntityWrapper.orderBy("date",false);
        nowDuibituEntityWrapper.eq("imgType",1);*/
        Duibitu nowzhengDuibitus = duibituUtil(id, false, 1);
        map.put("nowZhengDuibitus", nowzhengDuibitus);
        //最近的侧面
        Duibitu nowceDuibitus = duibituUtil(id, false, 2);
        map.put("nowCeDuibitus", nowceDuibitus);
        //以前的正面
        Duibitu agoZhengDuibitus = duibituUtil(id, true, 1);
        map.put("agoZhengDuibitus", agoZhengDuibitus);
        //以前的侧面
        Duibitu agoCeDuibitus = duibituUtil(id, true, 2);
        map.put("agoCeDuibitus", agoCeDuibitus);
        return AjaxResult.me().setResultObj(map);
    }

    public Duibitu duibituUtil (Long id, boolean b, int type) {
        EntityWrapper<Duibitu> nowDuibituEntityWrapper = new EntityWrapper<>();
        nowDuibituEntityWrapper.eq("userID", id);
        nowDuibituEntityWrapper.orderBy("date", b);
        nowDuibituEntityWrapper.eq("imgType", type);
        Duibitu duibitu = duibituService.selectOne(nowDuibituEntityWrapper);
        return duibitu;
    }

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @ApiOperation(value = "来获取所有Duibitu详细信息并分页", notes = "根据page页数和传入的query查询条件 来获取某些Duibitu详细信息")
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public PageList<Duibitu> json(@RequestBody DuibituQuery query) {
        Page<Duibitu> page = new Page<Duibitu>(query.getPage(), query.getRows());
        page = duibituService.selectPage(page);
        return new PageList<Duibitu>(page.getTotal(), page.getRecords());
    }


}
