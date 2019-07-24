package com.baizhi;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleServiceImpl;
import com.baizhi.service.CarouselService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzLmApplicationTests {

    @Autowired
    private CarouselService carouselService;
    @Test
    public void contextLoads() {
       /* Map<String, Object> stringObjectMap = carouselService.queryAll(1, 2);
        Set<String> keys = stringObjectMap.keySet();
        for(String key:keys){
            System.out.println(key+"--"+stringObjectMap.get(key));
        }*/
       /* ArticleServiceImpl articleService = new ArticleServiceImpl();
        Article article = articleService.queryOneById("11");
        System.out.println(article);*/

    }

}
