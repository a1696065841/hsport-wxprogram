package com.hsport.wxprogram.service;

import com.hsport.wxprogram.domain.Livetype;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-11-26
 */
public interface ILivetypeService extends IService<Livetype> {
    public Livetype getByUserID(Long id);

}
