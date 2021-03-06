package com.hsport.wxprogram.service;

import com.hsport.wxprogram.domain.Article;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhb
 * @since 2019-12-05
 */
public interface IArticleService extends IService<Article> {
    List<Object> getArticleType();
}
