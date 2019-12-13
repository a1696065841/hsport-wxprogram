package com.hsport.wxprogram.service;

import com.hsport.wxprogram.domain.Student;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-12-13
 */
public interface IStudentService extends IService<Student> {

    Student getStudentByAccount(String account);

}
