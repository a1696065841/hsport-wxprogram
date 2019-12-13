package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.domain.Student;
import com.hsport.wxprogram.mapper.StudentMapper;
import com.hsport.wxprogram.service.IStudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhb
 * @since 2019-12-13
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
    @Autowired
    StudentMapper studentMapper;

    @Override
    public Student getStudentByAccount(String account) {
        return null;
    }
}
