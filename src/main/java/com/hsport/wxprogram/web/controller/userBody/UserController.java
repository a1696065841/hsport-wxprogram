package com.hsport.wxprogram.web.controller.userBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsport.wxprogram.domain.*;
import com.hsport.wxprogram.domain.vo.MyArchivesVo;
import com.hsport.wxprogram.domain.vo.OrderVo;
import com.hsport.wxprogram.service.*;
import com.hsport.wxprogram.query.UserQuery;
import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import com.hsport.wxprogram.web.controller.LoginController;
import com.jcraft.jsch.HASH;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    public IBodyService bodyService;
    @Autowired
    public IJibingService jibingService;
    @Autowired
    public ILivetypeService livetypeService;
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
    @Autowired
    public IOrderService orderService;
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    /**
     * 保存和修改公用的
     *
     * @param orderVo 传递的实体
     * @return Ajaxresult转换结果
     */
    @ApiOperation(value = "分配教练")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody OrderVo orderVo) {
        try {
            AjaxResult ajaxResult = userService.updateUserCoach(orderVo);
            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("服务器异常,请联系客服人员！").setSuccess(false);
        }
    }

    /**
     * 删除对象信息
     *
     * @param user
     * @return
     */


    //获取用户
    @ApiOperation(value = "根据url的id来获取User详细信息")
    @RequestMapping(value = "/getByID", method = RequestMethod.POST)
    public AjaxResult get(@RequestBody User user) {
        HashMap<String, Object> map = new HashMap<>();
        Long id = user.getId();
        Lxxx lxxx = lxxxService.getByUserID(id);
        map.put("userInfo", lxxx);
        Body body = bodyService.selectOne(new EntityWrapper<Body>().eq("userID", id));
        map.put("body", body);
        return AjaxResult.me().setResultObj(body);
    }


    /**
     * 查看所有的员工信息
     *
     * @return
     */
    @ApiOperation(value = "来获取所有User详细信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> list() {
        return userService.selectList(null);
    }


    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @ApiOperation(value = "来获取所有User详细信息并分页", notes = "根据page页数和传入的query查询条件 来获取某些User详细信息")
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public PageList<User> json(@RequestBody UserQuery query) {
        Page<User> page = new Page<User>(query.getPage(), query.getRows());
        page = userService.selectPage(page);
        return new PageList<User>(page.getTotal(), page.getRecords());
    }


    @ApiOperation(value = "来获取所有User详细信息并分页", notes = "根据page页数和传入的query查询条件 来获取某些User详细信息")
    @RequestMapping(value = "/selectUserCoach", method = RequestMethod.POST)
    public AjaxResult selectUserCoach(@RequestBody UserQuery query) {
        Integer page = (query.getPage()-1)*query.getRows();
        query.setPage(page);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", userService.selectUserCoach(query));
        map.put("total", userService.selectUserCoachTotal(query));

        return AjaxResult.me().setResultObj(map);
    }

    @ApiOperation(value = "接收我的档案页面传送数据并分割开传入各个表")
    @RequestMapping(value = "/insertUserBodyAndSoOn", method = RequestMethod.POST)
    public AjaxResult insertUserBodyAndSoOn(@RequestBody MyArchivesVo myArchivesVo) {
        AjaxResult ajaxResult = new AjaxResult();
        User userLogin = ajaxResult.isUserLogin(request, redisService);
        if (StringUtils.isEmpty(userLogin)) {
            return new AjaxResult("用户令牌过期,请登陆后操作！");
        }
        Long id = userLogin.getId();
        try {
            Body body = myArchivesVo.getBody();
            body.setUserID(id);
            notNUlldoThis(body);
        /*    if (body.getId() != null) {
                bodyService.updateById(body);
            } else {
                bodyService.insert(body);
            }*/
            Jibing jibing = myArchivesVo.getJibing();
            jibing.setUserID(id);
            notNUlldoThis(jibing);
      /*      if (jibing.getId() != null) {
                jibingService.updateById(jibing);
            } else {
                jibingService.insert(jibing);
            }*/
            Livetype livetype = myArchivesVo.getLivetype();
            livetype.setUserID(id);
            notNUlldoThis(livetype);
            /*if (livetype.getId() != null) {
                livetypeService.updateById(livetype);
            } else {
                livetypeService.insert(livetype);
            }*/
            Lxxx lxxx = myArchivesVo.getLxxx();
            lxxx.setUserID(id);
            notNUlldoThis(lxxx);
           /* if (lxxx.getId()!=null){
                lxxxService.updateById(lxxx);
            }else {
                lxxxService.insert(lxxx);
            }*/

            Sportsprogram sportsprogram = myArchivesVo.getSportsprogram();
            sportsprogram.setUserID(id);
            notNUlldoThis(sportsprogram);
          /*  if (sportsprogram.getId()!=null){
                sportsprogramService.updateById(sportsprogram);
            }else {
                sportsprogramService.insert(sportsprogram);
            }*/

            Ysxg ysxg = myArchivesVo.getYsxg();
            ysxg.setUserID(id);
            notNUlldoThis(ysxg);
           /* if (ysxg!=null) {
                ysxg.setUserID(id);
                if (ysxg!=null){
                    ysxgService.updateById(ysxg);
                }else {
                    ysxgService.insert(ysxg);
                }
            }*/
            Sljk sljk = myArchivesVo.getSljk();
           notNUlldoThis(sljk);
        } catch (Exception e) {
            return new AjaxResult("保存失败,服务器异常！" + e.getMessage());
        }
        return AjaxResult.me();
    }

    void notNUlldoThis(Object object){
        if (object.getClass().equals(Sljk.class)){
            Sljk sljk = (Sljk) object;
            if (sljk.getId()!=null){
                sljkService.updateById(sljk);
            }else {
                sljkService.insert(sljk);
            }
        }else if (object.getClass().equals(Ysxg.class)){
                Ysxg ysxg=(Ysxg)object;
            if (ysxg.getId()!=null){
                ysxgService.updateById(ysxg);
            }else {
                ysxgService.insert(ysxg);
            }
        }else if (object.getClass().equals(Sportsprogram.class)){
            Sportsprogram sportsprogram=(Sportsprogram)object;
            if (sportsprogram.getId()!=null){
                sportsprogramService.updateById(sportsprogram);
            }else {
                sportsprogramService.insert(sportsprogram);
            }
        }else if (object.getClass().equals(Lxxx.class)){
            Lxxx lxxx=(Lxxx) object;
            if (lxxx.getId()!=null){
                lxxxService.updateById(lxxx);
            }else {
                lxxxService.insert(lxxx);
            }
        }else if (object.getClass().equals(Livetype.class)){
            Livetype livetype=(Livetype) object;
            if (livetype.getId()!=null){
                livetypeService.updateById(livetype);
            }else {
                livetypeService.insert(livetype);
            }
        }else if (object.getClass().equals(Jibing.class)){
            Jibing jibing=(Jibing) object;
            if (jibing.getId()!=null){
                jibingService.updateById(jibing);
            }else {
                jibingService.insert(jibing);
            }
        }else if (object.getClass().equals(Body.class)){
            Body body=(Body) object;
            if (body.getId()!=null){
                bodyService.updateById(body);
            }else {
                bodyService.insert(body);
            }
        }
    }
    @ApiOperation(value = "查询我的档案页面")
    @RequestMapping(value = "/findUserBodyAndSoOn", method = RequestMethod.POST)
    public AjaxResult findUserBodyAndSoOn(@RequestBody User user) {
        MyArchivesVo myArchivesVo = new MyArchivesVo();
        Long id = user.getId();
        Ysxg ysxg = ysxgService.selectOne(new EntityWrapper<Ysxg>().eq("userID", id));
        myArchivesVo.setYsxg(ysxg);
        Sljk sljk = sljkService.selectOne(new EntityWrapper<Sljk>().eq("userID", id));
        myArchivesVo.setSljk(sljk);
        Sportsprogram sportsprogram = sportsprogramService.selectOne(new EntityWrapper<Sportsprogram>().eq("userID", id));
        myArchivesVo.setSportsprogram(sportsprogram);
        Lxxx lxxx = lxxxService.selectOne(new EntityWrapper<Lxxx>().eq("userID", id));
        myArchivesVo.setLxxx(lxxx);
        Livetype livetype = livetypeService.selectOne(new EntityWrapper<Livetype>().eq("userID", id));
        myArchivesVo.setLivetype(livetype);
        Jibing jibing = jibingService.selectOne(new EntityWrapper<Jibing>().eq("userID", id));
        myArchivesVo.setJibing(jibing);
        Body body = bodyService.selectOne(new EntityWrapper<Body>().eq("userID", id));
        myArchivesVo.setBody(body);
        return AjaxResult.me().setResultObj(myArchivesVo);
    }
}
