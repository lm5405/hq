package com.baizhi.service;

import com.baizhi.dao.CarouselDao;
import com.baizhi.entity.Carousel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class CarouselSerivceImpl implements CarouselService {
    @Autowired(required = false)
    private CarouselDao carouselDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Map<String,Object> map=new HashMap<>();
        Integer records = carouselDao.selectRecords();
        System.out.println(carouselDao);
        System.out.println(records+"--"+rows);
        Integer total = records%rows==0?records/rows:records/rows+1;
        Integer begin=(page-1)*rows;
        List<Carousel> carousels = carouselDao.selectAll(begin, rows);
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        map.put("rows",carousels);
        return map;
    }

    @Override
    public String addCarousel(Carousel carousel) {
        String s = UUID.randomUUID().toString();
        carousel.setId(s);
        carouselDao.insert(carousel);
        return s;
    }

    @Override
    public String modifyCarousel(Carousel carousel) {
        carouselDao.update(carousel);
        return carousel.getId();
    }

    @Override
    public void removeCarousel(String  id) {
        carouselDao.delete(id);
    }

    @Override
    public void modifyImgPath(Carousel carousel) {
        carouselDao.updateImgPath(carousel);
    }
}
