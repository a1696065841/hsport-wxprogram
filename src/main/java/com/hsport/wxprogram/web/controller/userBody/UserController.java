package com.hsport.wxprogram.web.controller.userBody;

import com.hsport.wxprogram.domain.*;
import com.hsport.wxprogram.domain.vo.MyArchivesVo;
import com.hsport.wxprogram.service.*;
import com.hsport.wxprogram.query.UserQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    public IUserService userService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    public  IBodyService bodyService;
    @Autowired
    public  IJibingService jibingService;
    @Autowired
    public    ILivetypeService livetypeService;
    @Autowired
    public ISportsprogramService sportsprogramService;
    @Autowired
    public ILxxxService lxxxService;
    @Autowired
    public ISljkService sljkService;
    @Autowired
    public IYsxgService ysxgService;
    @Autowired
    public RedisService redisService;
    /**
    * 保存和修改公用的
    * @param user  传递的实体
    * @return Ajaxresult转换结果
    */
    @ApiOperation(value="新增或修改User信息")
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody User user){
        try {
            if(user.getId()!=null){
                userService.updateById(user);
            }else{
                userService.insert(user);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage()).setSuccess(false);
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */


    //获取用户
    @ApiOperation(value="根据url的id来获取User详细信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User get(@PathVariable("id")Integer id) {
        return userService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @ApiOperation(value="来获取所有User详细信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<User> list(){
        return userService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @ApiOperation(value="来获取所有User详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些User详细信息")
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<User> json(@RequestBody UserQuery query) {
        Page<User> page = new Page<User>(query.getPage(),query.getRows());
            page = userService.selectPage(page);
            return new PageList<User>(page.getTotal(),page.getRecords());
    }


    @ApiOperation(value="来获取所有User详细信息并分页", notes="根据page页数和传入的query查询条件 来获取某些User详细信息")
    @RequestMapping(value = "/selectUserCoach",method = RequestMethod.POST)
    public List<Object> selectUserCoach(@RequestBody UserQuery query) {
        return  userService.selectUserCoach(query);
    }

    @ApiOperation(value="接收我的档案页面传送数据并分割开传入各个表")
    @RequestMapping(value = "/insertUserBodyAndSoOn",method = RequestMethod.POST)
    public AjaxResult insertUserBodyAndSoOn(@RequestBody MyArchivesVo myArchivesVo) {
        User userLogin = new AjaxResult().isUserLogin(request,redisService);
        if (userLogin==null){
            new AjaxResult("请登陆后操作！");
        }
        Long id = userLogin.getId();
        try {
            Body body = myArchivesVo.getBody();
            body.setUserID(id);
            bodyService.insert(body);
            Jibing jibing = myArchivesVo.getJibing();
            jibing.setUserID(id);
            jibingService.insert(jibing);
            Livetype livetype = myArchivesVo.getLivetype();
            livetype.setUserID(id);
            livetypeService.insert(livetype);
            Lxxx lxxx = myArchivesVo.getLxxx();
            lxxx.setUserID(id);
            lxxxService.insert(lxxx);
            Sportsprogram sportsprogram = myArchivesVo.getSportsprogram();
            sportsprogram.setUserID(id);
            sportsprogramService.insert(sportsprogram);
            Ysxg ysxg = myArchivesVo.getYsxg();
            ysxg.setUserID(id);
            ysxgService.insert(ysxg);
            Sljk sljk = myArchivesVo.getSljk();
            sljk.setUserID(id);
            sljkService.insert(sljk);
        } catch (Exception e) {
            return  new AjaxResult("保存失败,服务器异常！"+e.getMessage());
        }
        return AjaxResult.me();
    }

}
