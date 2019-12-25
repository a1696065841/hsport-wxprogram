package com.hsport.wxprogram.query;


/**
 *
 * @author lhb
 * @since 2019-12-05
 */
import com.hsport.wxprogram.common.util.BaseQuery;

public class ArticleQuery extends BaseQuery{
    private String articleType;

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }
}