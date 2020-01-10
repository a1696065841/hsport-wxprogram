package com.hsport.wxprogram.domain.vo;

import com.hsport.wxprogram.domain.Article;

import java.util.List;

public class  ArticleVo {
 private Article article;
 private List<Integer>  productIDs;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<Integer> getProductIDs() {
        return productIDs;
    }

    public void setProductIDs(List<Integer> productIDs) {
        this.productIDs = productIDs;
    }



}
