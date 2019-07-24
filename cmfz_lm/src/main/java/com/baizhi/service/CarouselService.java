package com.baizhi.service;

import com.baizhi.entity.Carousel;

import java.util.Map;

public interface CarouselService {
    Map<String,Object> queryAll(Integer page,Integer rows);

    String addCarousel(Carousel carousel);

    void modifyImgPath(Carousel carousel);

    void removeCarousel(String id);

    String modifyCarousel(Carousel carousel);
}
