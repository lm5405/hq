package com.baizhi.controller;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService service;
    @RequestMapping("queryAll")
    public List<User> queryAll(){
        List<User> users = service.squeryAll();
        return users;
    }
    //增删改操作
    @RequestMapping("edit")
    public String  edite(User user, String oper, String []id){
        if("add".equals(oper)){
            String s = service.add(user);
            return s;
        }else if("edit".equals(oper)){
            if (user.getStatus().equals("1")){
                user.setStatus("正常");
            }else{
                user.setStatus("冻结");
            }
            service.modify(user);
            return user.getId();
        }else{
            for (String idd:id){
                service.remove(idd);
            }
        }
        return null;
    }
    //上传头像
    @RequestMapping("upload")
    public void upload(MultipartFile profile, HttpServletRequest request,String id){
        User user1 = service.queryOneById(id);
        //获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("userImg");
        //获取源文件名
        String originalFilename = profile.getOriginalFilename();
        if(originalFilename.equals("")){
            return;
        }
        //判断路径是不是存在
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdir();
        }
        try {
            //修改路径
            User user = new User();
            user.setProfile(originalFilename);
            user.setId(id);
            service.updateProfile(user);
            profile.transferTo(new File(realPath,originalFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //一键导入
    @RequestMapping("export")
    public void outPut(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String realPath = request.getSession().getServletContext().getRealPath("userImg");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=user.xls");
        List<User> users = service.squeryAll();
        for (User user : users) {
            String profile = user.getProfile();
            user.setProfile(realPath+"/"+profile);
        }
        //生成的excel文件
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("普通用户","用户表"),
        User .class, users);
        workbook.write(response.getOutputStream());
    }
    @RequestMapping("importFile")
    public void importFile(MultipartFile file) throws Exception {
        //获取此文件，将文件存入流中
        Workbook workbook = new HSSFWorkbook(file.getInputStream());
        //获取改文件的第一个表
        Sheet sheet = workbook.getSheetAt(0);
        //获取该表格数据行数
        int rowNum = sheet.getLastRowNum();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //创建集合将每一行数据存进集合中
        List<User> users = new ArrayList<>();
        for (int i = 0; i < rowNum; i++) {
            User user = new User();
            //获取该表格的第i+1行
            Row row = sheet.getRow(i+2);
            if(row==null){
                break;
            }
            //存入编号
            if(row!=null){
                Cell cell = row.getCell(0);
                String id = cell.getStringCellValue();
                user.setId(id);
            }
            //存入手机号
            String phone = row.getCell(1).getStringCellValue();
            user.setPhone(phone);
            //存入密码
            user.setPassword(row.getCell(2).getStringCellValue());
            //存入盐
            if(row.getCell(3)!=null){
                user.setSalt(row.getCell(3).getStringCellValue());
            }
            //存入用户名
            user.setDharmaName(row.getCell(4).getStringCellValue());
            //存入省份
            user.setProvince(row.getCell(5).getStringCellValue());
            //存入城市
            user.setCity(row.getCell(6).getStringCellValue());
            //存入性别
            user.setGender(row.getCell(7).getStringCellValue());
            //存入个性签名
            user.setPersonalSign(row.getCell(8).getStringCellValue());
            //存入头像
            if(row.getCell(9)!=null){
                user.setProfile(row.getCell(9).getStringCellValue());
            }
            //存入状态
            user.setStatus(row.getCell(10).getStringCellValue());
            //存入日期
            String stringCellValue = row.getCell(11).getStringCellValue();
            Date parse = simpleDateFormat.parse(stringCellValue);
            user.setRegistTime(parse);
            //将user 存入集合中
            users.add(user);
            }
        for (User user1 : users) {
            System.out.println(user1);
        }
    }
    @RequestMapping("login")
    public Map<String,Object> login(String phone, String password){
        Map<String, Object> map = service.userLogin(phone, password);
        return map;
    }
    //用户注册
    @RequestMapping("regist")
    public Map<String,Object>regist(User user){
        Map<String, Object> map = service.regist(user);
        return map;
    }
    //修改用户状态
    @RequestMapping("changeStatus")
    public void  changeStatus(String id){
        service.modifyStatus(id);
    }

    //拆线呢每个月分注册的人数
    @RequestMapping("queryNumber")
    public Map<String,Object>   queryNumber(){
        Map<String, Object> map = service.queryBymonth();
        return map;
    }
//    按照省份分组查询，
    @RequestMapping("queryProvinceAndGender")
    public Map<String,Object>queryProvinceAndGender(){
        Map<String, Object> map = service.queryByProvince();
        return map;
    }
}
