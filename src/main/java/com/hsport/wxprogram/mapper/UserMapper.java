package com.hsport.wxprogram.mapper;

import com.hsport.wxprogram.domain.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hsport.wxprogram.query.UserQuery;
import com.hsport.wxprogram.util.AjaxResult;
import com.hsport.wxprogram.util.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lhb
 * @since 2019-11-21
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 保存和修改公用的
     * @param User  传递的实体
     * @return Ajaxresult转换结果
     */
    AjaxResult save(User user);

    /**
     * 删除对象信息
     * @param id
     * @return
     */
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    User get(@RequestParam(value="id",required=true) Integer id);


    /**
     * 查看所有的员工信息
     * @return
     */
    public List<User> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    PageList<User> json(@RequestBody UserQuery query);

    List<User>  selectUser();
}
