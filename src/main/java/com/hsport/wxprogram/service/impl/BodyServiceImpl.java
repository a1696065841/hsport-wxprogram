package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.domain.Body;
import com.hsport.wxprogram.domain.User;
import com.hsport.wxprogram.mapper.BodyMapper;
import com.hsport.wxprogram.mapper.UserMapper;
import com.hsport.wxprogram.service.IBodyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lhb
 * @since 2019-11-25
 */
@Service
public class BodyServiceImpl extends ServiceImpl<BodyMapper, Body> implements IBodyService {
    @Autowired
    BodyMapper bodyMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public List<Body> selectBody() {
        return bodyMapper.selectBody();
    }

    //查询到所有身体信息所对应的用户信息
    @Override
    public List selectWithUser() {
        List<Body> bodies1 = bodyMapper.selectList(null);
        ArrayList<ArrayList> arrayLists = new ArrayList<>();
        for (int i = 0; i < bodies1.size(); i++) {
            ArrayList<Object> list = new ArrayList<>();
            Body body = bodies1.get(i);
            User user = userMapper.selectById(body.getUserID());
            list.add(body);
            list.add(user);
            arrayLists.add(list);
        }
        return arrayLists;
    }


    @Override
    public List<Object> selectBodyByUser() {
        return bodyMapper.selectBodyByUser();
    }
}
