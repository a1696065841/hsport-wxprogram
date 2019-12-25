package com.hsport.wxprogram.web.controller.userBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.domain.vo.CeyiceVo;
import com.hsport.wxprogram.service.ICeyiceService;
import com.hsport.wxprogram.domain.Ceyice;
import com.hsport.wxprogram.query.CeyiceQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.CharToString;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ceyice")
public class CeyiceController {
    @Autowired
    public ICeyiceService ceyiceService;

    /**
    * 保存和修改公用的
    * @param
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改Ceyice信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody CeyiceVo ceyiceVo){
        /**
         *
         * userID
         */
        Ceyice ceyice = new Ceyice();
        //vo对象赋值
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
        //多选 赋值
        ceyice.setRcpbqkDX(CharToString.C2S(ceyiceVo.getRcpbqkDX()));
        ceyice.setRcsmzlDX(CharToString.C2S(ceyiceVo.getrcsmzlDX()));
        ceyice.setRcysqkDX(CharToString.C2S(ceyiceVo.getRcysqkDX()));
        String BuweiYdSuns = CharToString.C2S(ceyiceVo.getBuweiYdSunsDX());
        ceyice.setBuweiYdSunsDX(BuweiYdSuns);
        ceyice.setYsxwxgDX(CharToString.C2S(ceyiceVo.getYsxwxgDX()));
        ceyice.setYqJianfeiJLDX(CharToString.C2S(ceyiceVo.getYqJianfeiJLDX()));
        ceyice.setDate(DateUtil.now());
        try {
            if(ceyice.getId()!=null){
               ceyiceService.updateById(ceyice);
            }else{
               ceyiceService.insert(ceyice);
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
    @ApiOperation(value="删除Ceyice信息", notes="删除对象信息")
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Integer id){
        try {
            ceyiceService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @ApiOperation(value="根据url的id来获取Ceyice详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Ceyice get(@PathVariable("id")Integer id) {
        return ceyiceService.selectById(id);
    }

    //获取用户
    @ApiOperation(value="根据用户的id来获取Ceyice详细信息")
    @RequestMapping(value = "/getByUserID/{id}",method = RequestMethod.GET)
    public List<Ceyice> getByUserID(@PathVariable("id")Integer id) {
        Page<Ceyice> page = new Page<Ceyice>(0,1);

        EntityWrapper<Ceyice> ceyiceEntityWrapper = new EntityWrapper<>();
        ceyiceEntityWrapper.eq("userID",id);
        ceyiceEntityWrapper.orderBy("date",false);
        page = ceyiceService.selectPage(page,ceyiceEntityWrapper);
        return page.getRecords();
    }

    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有Ceyice详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Ceyice> list(){

        return ceyiceService.selectList(null);
    }

    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有Ceyice详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些Ceyice详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Ceyice> json(@RequestBody CeyiceQuery query){
        Page<Ceyice> page = new Page<Ceyice>(query.getPage(),query.getRows());
            page = ceyiceService.selectPage(page);
            return new PageList<Ceyice>(page.getTotal(),page.getRecords());
    }

}
