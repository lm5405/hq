package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {

     String addArticle(Article article);
    //删除
    void removeArticle(String id);
    //修改
    String modifyArticle(Article article);
    //分页查询
    Map<String,Object> queryAll(Integer page, Integer rows);
    //格局GuruId 查询
    List<Article > queryAllByGuruId(String guruId);
    //根据id查询一个
    Article queryOneById(String id);
}
