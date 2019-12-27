package com.hsport.wxprogram.web.controller.coach;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.domain.Coach;
import com.hsport.wxprogram.domain.Sysuser;
import com.hsport.wxprogram.service.ICertificateService;
import com.hsport.wxprogram.domain.Certificate;
import com.hsport.wxprogram.query.CertificateQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import com.hsport.wxprogram.service.RedisService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/certificate")
public class CertificateController {
    @Autowired
    public ICertificateService certificateService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    RedisService redisService;
    /**
    * 保存和修改公用的
    * @param certificate  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新建一个教练证书并绑定该教练")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Certificate certificate){
        AjaxResult ajaxResult = new AjaxResult();
        Coach coachLogin = ajaxResult.isCoachLogin(request);
        Sysuser sysUserLogin = ajaxResult.isSysUserLogin(request);
        if (coachLogin==null||sysUserLogin==null){
            return new AjaxResult("用户已过期，请重新登录");
        }
        try {
            if(certificate.getId()!=null){
                certificateService.updateById(certificate);
            }else{
                certificateService.insert(certificate);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage()).setSuccess(false);
        }
    }

    /**
    * 删除对象信息
    * @param
    * @return
    */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public AjaxResult delete() {
        AjaxResult ajaxResult = new AjaxResult();
        Coach coachLogin = ajaxResult.isCoachLogin(request);
        Sysuser sysUserLogin = ajaxResult.isSysUserLogin(request);
        if (coachLogin==null&&sysUserLogin==null){
            return new AjaxResult("用户已过期，请重新登录");
        }
        try {
            certificateService.deleteById(coachLogin);
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

    @ApiOperation(value="根据教练的ID来获取教练所拥有的证书详细信息")
    @RequestMapping(value = "/getByCoachID/{id}",method = RequestMethod.GET)
    public AjaxResult getByCoachID(@PathVariable("id")Integer id)
    {
        AjaxResult ajaxResult = new AjaxResult();
        Coach coach = ajaxResult.isCoachLogin(request);
        if (coach==null){
            return new AjaxResult("用户已过期，请重新登录");
        }
        EntityWrapper<Certificate> certificateEntityWrapper = new EntityWrapper<>();
        certificateEntityWrapper.eq("coachID",coach.getId());
        return AjaxResult.me().setResultObj(certificateService.selectList(certificateEntityWrapper));
    }
    /**
    * 查看所有的zhengshu信息
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
