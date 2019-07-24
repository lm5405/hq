package com.baizhi.service;

import com.baizhi.entity.CountMonth;
import com.baizhi.entity.User;
import com.baizhi.entity.UserDto;

import java.util.List;
import java.util.Map;

public interface UserService {
    //查询所有用户
    public List<User> squeryAll();
    //修改
     public String modify(User user);
     //删除
     public void remove(String id);
     //添加
     public String add(User user);
     public void updateProfile(User user);
     //用户登录
     public Map<String,Object> userLogin(String phone, String password);
     public Map<String,Object>regist(User user);
     //修改用户状态
     public String modifyStatus(String id);
    //根据id查询一个
    public User queryOneById(String id);
    //查询数量根据月份
    public  Map<String,Object>  queryBymonth();
//    按照省份分组查询
    public Map<String, Object> queryByProvince();

}
