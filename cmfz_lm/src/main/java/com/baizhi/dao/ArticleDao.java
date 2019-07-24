package com.baizhi.dao;

import com.baizhi.entity.Article;

import java.util.List;

public interface ArticleDao extends BaseDao<Article> {

    //修改文件路径
    void updatePath(Article article);
    //根据GuduId 查询
    List<Article> selectAllByGuruId(String guruId);
    //根据id查询
    Article selectOneById(String id);

}
