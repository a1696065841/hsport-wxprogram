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
        HashMap<String, Object> map = new HashMap<>();
        Long id = user.getId();
        Page<Ceyice> page = new Page<Ceyice>(0, 1);
        EntityWrapper<Ceyice> ceyiceEntityWrapper = new EntityWrapper<>();
        ceyiceEntityWrapper.eq("userID", id);
        ceyiceEntityWrapper.orderBy("date", false);
        page = ceyiceService.selectPage(page, ceyiceEntityWrapper);
        List<Ceyice> records = page.getRecords();
        Ceyice ceyice = null;
        try {
            if (records.size() > 0) {
                ceyice = records.get(0);
                map.put("name", ceyice.getName());
                map.put("sex", ceyice.getSex());
                map.put("age", ceyice.getAge());
                map.put("height", ceyice.getHeight());
                map.put("weight", ceyice.getWeight());
                map.put("yaowei", ceyice.getYaowei());
                map.put("tunwei", ceyice.getTunwei());
                //bmi
                double bmi = ceyice.getWeight() / ((ceyice.getHeight() / 100) * (ceyice.getHeight() / 100));
                bmi = (double) Math.round(bmi * 100) / 100;
                map.put("bmi", bmi);
                //体脂率
                double bfr = 0;
                double a = ceyice.getYaowei() * 0.74;
                bfr = 1.2 * bmi + 0.23 * ceyice.getAge() - 5.4 - 10.8 * ceyice.getSex();
                bfr = (double) Math.round(bfr * 100) / 100;
                map.put("tizhi", bfr);
                //腰臀比
                map.put("yaoTunBi", ceyice.getYaowei() / ceyice.getTunwei());
                //肥胖原因 数组
                ArrayList<String> strings = new ArrayList<>();
                //饮食习惯
                HashMap ysxg = JSON.parseObject(ceyice.getYsxwxgDX(), HashMap.class);
                ArrayList<Object> ysxglist = new ArrayList<>();
                for (Object o : ysxg.values()) {
                    XztVo xztVo = myStringUtil(o.toString());
                    if (xztVo != null)
                        ysxglist.add(xztVo);
                }
                map.put("ysxg", ysxglist);
                //日常饮食情况
                HashMap hashMap = JSON.parseObject(ceyice.getRcysqkDX(), HashMap.class);
                ArrayList<Object> rcysqklist = new ArrayList<>();
                for (Object o : hashMap.values()) {
                    XztVo xztVo = myStringUtil(o.toString());
                    if (xztVo != null)
                        rcysqklist.add(xztVo);
                }
                if (hashMap.size() > 2) {
                    strings.add("饮食习惯差!");
                }
                map.put("RcysqkDX", rcysqklist);
                //日常运动损伤
                HashMap map1 = JSON.parseObject(ceyice.getBuweiYdSunsDX(), HashMap.class);
                ArrayList<Object> sunShangList = new ArrayList<>();
                for (Object o : map1.values()) {
                    if (!o.toString().equals("0")){
                        sunShangList.add(o);
                    }
                }
                map.put("yunDongSunShang", sunShangList);
                Integer mzcjydpl = ceyice.getMzcjydpl();
                if (mzcjydpl == 1) {
                    map.put("ydxg", "雷打不动,稳如泰山!");
                    strings.add("运动次数少,能量过剩及参与代谢相关营养缺乏");
                } else if (mzcjydpl == 2) {
                    map.put("ydxg", "坚持运动,健康又放松!");
                } else {
                    map.put("ydxg", "运动达人666,但也要有适当休整才行哦");
                }
                map.put("aoye", ceyice.getSfjcay());
                map.put("sleepTime", ceyice.getPjsmsj());
                HashMap rcsmzl = JSON.parseObject(ceyice.getRcsmzlDX(), HashMap.class);
                if (rcsmzl.get("isno").equals("0")) {
                    map.put("smzl", "睡眠质量差");
                    strings.add("熬夜型");
                } else {
                    map.put("smzl", "睡眠质量优");
                }
                //压力
                map.put("yali", ceyice.getRcylfx());
                if (ceyice.getRcylfx() == 4) {
                    strings.add("压力型肥胖");
                }
                HashMap pbqk = JSON.parseObject(ceyice.getRcpbqkDX(), HashMap.class);
                //排便情况
                if (pbqk.size() > 4) {
                    map.put("changDao", 4);
                } else {
                    map.put("changDao", pbqk.size());
                    strings.add("肠道疾病及消化问题");
                }
                if (ceyice.getFmsfyrbjp() != 3) {
                    strings.add("遗传型肥胖!");
                }
                map.put("feipangyuanyin", strings);
                //是否有运动习惯
                map.put("shiFouYunDong", ceyice.getSfyydxg());
                map.put("ydcxsj",ceyice.getYdcxsj());
                map.put("ydpl",ceyice.getMzcjydpl());
            }else {
                return new AjaxResult("未填测试表,请填写后再来吧!");
            }
            return AjaxResult.me().setResultObj(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult("程序异常 请联系客服");
        }
    }

    public XztVo myStringUtil(String s) {
        XztVo xztVo = new XztVo();
        String[] split = s.split(",");
        if (split.length <= 1) {
            return null;
        }
        xztVo.setName(split[0]);
        xztVo.setFatIndex(Integer.valueOf(split[1]));
        return xztVo;
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
