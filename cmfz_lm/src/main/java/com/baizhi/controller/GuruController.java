package com.baizhi.controller;

import com.baizhi.entity.Guru;
import com.baizhi.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("guru")
public class GuruController {
    @Autowired
    private GuruService guruService;
    @RequestMapping("queryAll")
    public Map<String , Object> queryAll(Integer page , Integer rows ){
        Map<String, Object> stringObjectMap = guruService.queryAll(page, rows);
        return stringObjectMap;
    }
    @RequestMapping("edit")
    public String edit(Guru guru, String oper, String[] id){
        if("edit".equals(oper)){
            //修改
            String s = guruService.modifyGuru(guru);
            return s;
        }else if("add".equals(oper)){
            //添加
            String s = guruService.addGuru(guru);
            return s;
        }else {
            //删除
            for (String s : id) {
                guruService.removeGuru(s);
            }
        }
        return  null;
    }

    //查询所有上师
    @RequestMapping("queryAll1")
    public List<Guru> queryAll(){
        List<Guru> gurus = guruService.queryAllGuru();
        return gurus;
    }
    //头像上传
    @RequestMapping("upload")
    public void uploadHeader(MultipartFile profile, HttpServletRequest request, HttpServletResponse response, String id){
        //获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("imgHeader");
        //获取原始文件名
        String originalFilename = profile.getOriginalFilename();
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdir();
        }
        try {
            profile.transferTo(new File(realPath,originalFilename));
            //改变图片路径
            Guru guru = new Guru();
            guru.setProfile(originalFilename);
            guru.setId(id);
            guruService.modifyProfile(guru);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
