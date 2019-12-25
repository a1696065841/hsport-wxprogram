package com.hsport.wxprogram.service.impl;

import com.hsport.wxprogram.domain.Article;
import com.hsport.wxprogram.mapper.ArticleMapper;
import com.hsport.wxprogram.service.IArticleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhb
 * @since 2019-12-05
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
@Autowired
 ArticleMapper articleMapper;
    @Override
    public List<Object> getArticleType() {
        return articleMapper.getArticleType();
    }
}
