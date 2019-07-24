package com.baizhi.controller;

import com.baizhi.entity.Carousel;
import com.baizhi.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Map;

@RestController
@RequestMapping("carousel")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;
    @RequestMapping("queryAll")
    public Map<String,Object> queryAll(Integer page,Integer rows){
        System.out.println(rows);
        Map<String, Object> stringObjectMap = carouselService.queryAll(page, rows);
        return stringObjectMap;
    }
    @RequestMapping("edit")
    public String edit(Carousel carousel,String oper,String[] id){
        if("edit".equals(oper)){
            //修改
            String s = carouselService.modifyCarousel(carousel);
            return s;
        }else if("add".equals(oper)){
            //添加
            String s = carouselService.addCarousel(carousel);
            return s;
        }else {
            //删除
            for (String s : id) {
                carouselService.removeCarousel(s);
            }
        }
        return  null;
    }

    @RequestMapping("upload")
    public void upload(String id, MultipartFile imgPath, HttpServletRequest request, HttpServletResponse response){
        String originalFilename = imgPath.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("carouselPic");
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        try {
            imgPath.transferTo(new File(path, originalFilename));
            Carousel carousel = new Carousel();
            carousel.setId(id);
            carousel.setImgPath(originalFilename);
            carouselService.modifyImgPath(carousel);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
