package com.baizhi.service;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired(required = false)
    private ArticleDao articleDao;
    @Override
    public String addArticle(Article article) {
        String s = UUID.randomUUID().toString();
        article.setId(s);
        articleDao.insert(article);
        return s;
    }

    @Override
    public void removeArticle(String id) {
        articleDao.delete(id);
    }

    @Override
    public String modifyArticle(Article article) {
        articleDao.updatePath(article);
        return  article.getId();
    }

    @Override
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Map<String , Object> map = new HashMap<>();
        Integer records = articleDao.selectRecords();
        Integer total = records%rows == 0 ?records/rows : records/rows+1;
        Integer begin = (page-1)*rows;
        List<Article> articles = articleDao.selectAll(begin, rows);
        //当前页
        map.put("page",page);
        //总记录数
        map.put("records",records);
        //总页数
        map.put("total",total);
        //查询出的集合
        map.put("rows",articles);
        return map;
    }

    @Override
    public List<Article> queryAllByGuruId(String guruId) {
        return articleDao.selectAllByGuruId(guruId);
    }

    @Override
    public Article queryOneById(String id) {
        return articleDao.selectOneById(id);
    }
}
