package com.baizhi.dao;

import com.baizhi.entity.CountMonth;
import com.baizhi.entity.User;
import com.baizhi.entity.UserDto;

import java.util.List;

public interface UserDao extends BaseDao<User>{

    List<User> selectAll1();
    void updateStatus(User user);
    void updateProfile(User user);
    User userLogin(String phone);
    void regist(User user);
    User queryOne(String id);
    List<CountMonth> queryByMonth();
    List<UserDto> selectByProvince();
}
