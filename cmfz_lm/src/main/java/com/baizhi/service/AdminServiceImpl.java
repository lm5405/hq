package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired(required = false)
     AdminDao adminDao;

    @Override
    public Map<String, Object> login(Admin admin) {
        Map<String,Object> map=new HashMap<>();
        Admin selectAdmin = adminDao.selectByName(admin.getUsername());
        if(selectAdmin==null){
            map.put("code",300);
            map.put("message","用户名不存在");
        }else if(!selectAdmin.getPassword().equalsIgnoreCase(admin.getPassword())){
            map.put("code",400);
            map.put("message","密码错误");
        }else{
            map.put("code",200);
            map.put("message","登录成功");
        }
        return map;
    }

    @Override
    public List<Admin> queryAll() {
        List<Admin> admins = adminDao.selectAll();
        return admins;
    }
}
