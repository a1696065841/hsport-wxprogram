package com.hsport.wxprogram.web.controller.userBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.domain.vo.CeyiceVo;
import com.hsport.wxprogram.domain.vo.XztVo;
import com.hsport.wxprogram.service.ICeyiceService;
import com.hsport.wxprogram.domain.Ceyice;
import com.hsport.wxprogram.query.CeyiceQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.CharToString;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import com.hsport.wxprogram.web.controller.LoginController;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ceyice")
public class CeyiceController {
    @Autowired
    public ICeyiceService ceyiceService;
    @Autowired
    HttpServletRequest request;
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 保存和修改公用的
     *
     * @param
     * @return Ajaxresult转换结果
     */
    @ApiOperation(value = "新增或修改Ceyice信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody CeyiceVo ceyiceVo) {
        /**
         *
         * userID
         */
        logger.debug("ceyiceVo----------" + ceyiceVo);
        Ceyice ceyice = new Ceyice();
        //vo对象赋值
        ceyice.setHeight(ceyiceVo.getHeight());
        ceyice.setWeight(ceyiceVo.getWeight());
        ceyice.setName(ceyiceVo.getName());
        ceyice.setSex(ceyiceVo.getSex());
        ceyice.setDsjfqkFantan(ceyiceVo.getDsjfqkFantan());
        ceyice.setFmsfyrbjp(ceyiceVo.getFmsfyrbjp());
        ceyice.setMzcjydpl(ceyiceVo.getMzcjydpl());
        ceyice.setId(ceyiceVo.getId());
        ceyice.setRcylfx(ceyiceVo.getRcylfx());
        ceyice.setPjsmsj(ceyiceVo.getPjsmsj());
        ceyice.setSfjcay(ceyiceVo.getSfjcay());
        ceyice.setSfyydxg(ceyiceVo.getSfyydxg());
        ceyice.setTzhsmxzj(ceyiceVo.getTzhsmxzj());
        ceyice.setYdcxsj(ceyiceVo.getYdcxsj());
        ceyice.setUserID(ceyiceVo.getUserID());
        ceyice.setYaowei(ceyiceVo.getYaowei());
        ceyice.setTunwei(ceyiceVo.getTunwei());
        ceyice.setAge(ceyiceVo.getAge());
        //多选 赋值

        JSONObject getRcpbqkDX = new JSONObject(ceyiceVo.getRcpbqkDX());
        ceyice.setRcpbqkDX(getRcpbqkDX.toJSONString());

        JSONObject getRcsmzlDX = new JSONObject(ceyiceVo.getRcsmzlDX());
        ceyice.setRcsmzlDX(getRcsmzlDX.toJSONString());

        JSONObject getRcysqkDX = new JSONObject(ceyiceVo.getRcysqkDX());
        ceyice.setRcysqkDX(getRcysqkDX.toJSONString());

        JSONObject getBuweiYdSunsDX = new JSONObject(ceyiceVo.getBuweiYdSunsDX());
        ceyice.setBuweiYdSunsDX(getBuweiYdSunsDX.toJSONString());
        JSONObject getYsxwxgDX = new JSONObject(ceyiceVo.getYsxwxgDX());
        ceyice.setYsxwxgDX(getYsxwxgDX.toJSONString());
        JSONObject getYqJianfeiJLDX = new JSONObject(ceyiceVo.getYqJianfeiJLDX());
        ceyice.setYqJianfeiJLDX(getYqJianfeiJLDX.toJSONString());
        ceyice.setDate(DateUtil.now());
        try {
            if (ceyice.getId() != null) {
                ceyiceService.updateById(ceyice);
            } else {
                ceyiceService.insert(ceyice);
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
    @ApiOperation(value = "删除Ceyice信息", notes = "删除对象信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id) {
        try {
            ceyiceService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！" + e.getMessage()).setSuccess(false);
        }
    }

    //获取用户
    @ApiOperation(value = "根据用户的id来获取Ceyice详细信息")
    @RequestMapping(value = "/getByUserID", method = RequestMethod.POST)
    public AjaxResult getByUserID(@RequestBody User user) {
        HashMap ceyiceByUserID = ceyiceService.getCeyiceByUserID(user);
        try {
            if (ceyiceByUserID != null) {
                return AjaxResult.me().setResultObj(ceyiceByUserID);
            } else {
                return new AjaxResult("未填测试表,请填写后再来吧!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("程序异常 请联系客服");
        }
    }


    //获取用户
    @ApiOperation(value = "根据用户的id来获取Ceyice详细信息")
    @RequestMapping(value = "/getCeiyiceToCaoch", method = RequestMethod.POST)
    public AjaxResult getCeiyiceToCaoch(@RequestBody User user) {
        HashMap ceiyiceToCaoch = null;
        try {
            ceiyiceToCaoch = ceyiceService.getCeiyiceToCaoch(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("程序异常 请联系客服");
        }
        if (ceiyiceToCaoch != null) {
            return AjaxResult.me().setResultObj(ceiyiceToCaoch);
        } else {
            return new AjaxResult("未填测试表,请填写后再来吧!");
        }
    }

    /**
     * 查看所有的员工信息
     *
     * @return
     */
    @ApiOperation(value = "来获取所有Ceyice详细信息")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<Ceyice> list() {
        return ceyiceService.selectList(null);
    }

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @ApiOperation(value = "来获取所有Ceyice详细信息并分页", notes = "根据page页数和传入的query查询条件 来获取某些Ceyice详细信息")
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public PageList<Ceyice> json(@RequestBody CeyiceQuery query) {
        Page<Ceyice> page = new Page<Ceyice>(query.getPage(), query.getRows());
        page = ceyiceService.selectPage(page);
        return new PageList<Ceyice>(page.getTotal(), page.getRecords());
    }

}
