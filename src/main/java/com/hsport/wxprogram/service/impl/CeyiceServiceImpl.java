package com.hsport.wxprogram.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hsport.wxprogram.domain.Ceyice;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.domain.vo.XztVo;
import com.hsport.wxprogram.mapper.CeyiceMapper;
import com.hsport.wxprogram.service.ICeyiceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lhb
 * @since 2019-11-29
 */
@Service
public class CeyiceServiceImpl extends ServiceImpl<CeyiceMapper, Ceyice> implements ICeyiceService {
    @Autowired
    CeyiceMapper ceyiceMapper;

    @Override
    public HashMap getCeyiceByUserID(User user) {
        HashMap<String, Object> map = new HashMap<>();
        Long id = user.getId();
        Page<Ceyice> page = new Page<Ceyice>(0, 1);
        EntityWrapper<Ceyice> ceyiceEntityWrapper = new EntityWrapper<>();
        ceyiceEntityWrapper.eq("userID", id);
        ceyiceEntityWrapper.orderBy("date", false);
        List<Ceyice> records = ceyiceMapper.selectPage(page, ceyiceEntityWrapper);
        Ceyice ceyice = null;
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
                if (!o.toString().equals("0") && !o.toString().equals("1")) {
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
            map.put("ydcxsj", ceyice.getYdcxsj());
            map.put("ydpl", ceyice.getMzcjydpl());
            return map;
        }
        return null;
    }

    @Override
    public HashMap getCeiyiceToCaoch(User user) {
        HashMap<String, Object> map = new HashMap<>();
        Long id = user.getId();
        Page<Ceyice> page = new Page<Ceyice>(0, 1);
        EntityWrapper<Ceyice> ceyiceEntityWrapper = new EntityWrapper<>();
        ceyiceEntityWrapper.eq("userID", id);
        ceyiceEntityWrapper.orderBy("date", false);
        List<Ceyice> records= ceyiceMapper.selectPage(page, ceyiceEntityWrapper);
        Ceyice ceyice = null;
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
            map.put("yaoTunBi", (ceyice.getYaowei() / ceyice.getTunwei()) * 100);
            //饮食习惯
            HashMap ysxg = JSON.parseObject(ceyice.getYsxwxgDX(), HashMap.class);
            ArrayList<Object> ysxglist = new ArrayList<>();
            for (Object o : ysxg.values()) {
                XztVo xztVo = myStringUtil(o.toString());
                if (xztVo != null)
                    ysxglist.add(xztVo);
            }
            map.put("ysxg",ysxglist);
            //日常饮食情况
            HashMap hashMap = JSON.parseObject(ceyice.getRcysqkDX(), HashMap.class);
            ArrayList<Object> rcysqklist = new ArrayList<>();
            for (Object o : hashMap.values()) {
                XztVo xztVo = myStringUtil(o.toString());
                if (xztVo != null)
                    rcysqklist.add(xztVo);
            }
            map.put("RcysqkDX", rcysqklist);
            //日常运动损伤
            HashMap map1 = JSON.parseObject(ceyice.getBuweiYdSunsDX(), HashMap.class);
            ArrayList<Object> sunShangList = new ArrayList<>();
            for (Object o : map1.values()) {
                if (!o.toString().equals("0") && !o.toString().equals("1")) {
                    sunShangList.add(o);
                }
            }
            map.put("yunDongSunShang", sunShangList);
            Integer mzcjydpl = ceyice.getMzcjydpl();
            if (mzcjydpl == 1) {
                map.put("ydxg", "雷打不动,稳如泰山!");
            } else if (mzcjydpl == 2) {
                map.put("ydxg", "坚持运动,健康又放松!");
            } else {
                map.put("ydxg", "运动达人666,但也要有适当休整才行哦");
            }
            map.put("aoye", ceyice.getSfjcay());
            map.put("sleepTime", ceyice.getPjsmsj());
            HashMap rcsmzl = JSON.parseObject(ceyice.getRcsmzlDX(), HashMap.class);
            ArrayList<Object> rcsmzllist = new ArrayList<>();
            for (Object o : rcsmzl.values()) {
                if (!o.toString().equals("0") && !o.toString().equals("1")) {
                    rcsmzllist.add(o);
                }
            }
            map.put("sleep", rcsmzllist);
            //压力
            map.put("yali", ceyice.getRcylfx());
            HashMap pbqk = JSON.parseObject(ceyice.getRcpbqkDX(), HashMap.class);
            ArrayList<Object> pbqklist = new ArrayList<>();
            for (Object o : pbqk.values()) {
                if (!o.toString().equals("0") && !o.toString().equals("1")) {
                    System.out.println(o.toString());
                    pbqklist.add(o);
                }
            }
            map.put("pbqk", pbqklist);
            //是否有运动习惯
            map.put("shiFouYunDong", ceyice.getSfyydxg());
            map.put("ydcxsj", ceyice.getYdcxsj());
            map.put("ydpl", ceyice.getMzcjydpl());
            map.put("fmsfyrbjp", ceyice.getFmsfyrbjp());
            map.put("tzhsmxzj", ceyice.getTzhsmxzj());
            HashMap jfjL = JSONObject.parseObject(ceyice.getYqJianfeiJLDX(), HashMap.class);
            //减肥经历
            ArrayList<Object> jfjlList = new ArrayList<>();
            for (Object o : jfjL.values()) {
                if (!o.toString().equals("0") && !o.toString().equals("1")) {
                    System.out.println(o.toString());
                    jfjlList.add(o);
                }
            }
            map.put("jianfei", jfjlList);
            map.put("dsjfqkFantan", ceyice.getDsjfqkFantan());
            return map;
        }
        return null;
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
}
