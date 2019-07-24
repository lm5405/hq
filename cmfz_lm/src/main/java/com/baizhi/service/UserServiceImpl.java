package com.baizhi.service;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.CountMonth;
import com.baizhi.entity.ProvinceCount;
import com.baizhi.entity.User;
import com.baizhi.entity.UserDto;

import com.baizhi.util.MD5Utils;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    UserDao userDao;
    @Override
    //查询所有user
    public List<User> squeryAll() {
        List<User> users = userDao.selectAll1();
        return users;
    }

    @Override
    public String modify(User user) {
        userDao.update(user);
        return null;
    }

    @Override
    public void remove(String id) {
        userDao.delete(id);
    }

    @Override
    public String add(User user) {
        String s = UUID.randomUUID().toString();
        user.setId(s);
        userDao.insert(user);
        return s;
    }

    @Override
    public void updateProfile(User user) {
        //修改路径
        userDao.updateProfile(user);
    }

    @Override
    public Map<String, Object> userLogin(String phone,String password) {
        Map<String, Object> map = new HashMap<>();
        //查询是否为空
        User user = userDao.userLogin(phone);
        if(user==null){
            map.put("error", -200);
            map.put("message", "用户名不存在");
        }else if(!user.getPassword().equals(password)){
            map.put("\"error\"", -300);
            map.put("message","密码错误");
        }else{
            map.put("message", user);
        }
        return map;
    }
    @Override
    public Map<String, Object> regist(User user) {
        //根据用户手机号查询
        String phone = user.getPhone();
        User user1 = userDao.userLogin(phone);
        Map<String, Object> map = new HashMap<>();
        if (user1!=null){
            map.put("error", -400);
            map.put("message", "手机号已存在");
            return map;
        }
        String password = user.getPassword();
        String dharmaName = user.getDharmaName();
        String province = user.getProvince();
        String city = user.getCity();
        String gender = user.getGender();
        if (password==null){throw new RuntimeException("请输入密码");}
        if (dharmaName==null){throw new RuntimeException("请输入用户名");}
        if (province==null){throw new RuntimeException("请输入所在省份");}
        if (city==null){throw new RuntimeException("请输入所在城市");}
        if (gender==null){throw new RuntimeException("请输入性别");}
        if(phone==null){
            throw new RuntimeException("请输入手机号");
        }
        String s = UUID.randomUUID().toString();
        user.setId(s);
        user.setRegistTime(new Date());
        String salt = MD5Utils.getSalt();
        String password2 = MD5Utils.getPassword(password+salt);
        user.setSalt(salt);
        user.setPassword(password2);
        user.setStatus("正常");
        userDao.insert(user);
        map.put("message", user);
       GoEasy goEasy = new GoEasy("https://cdn-hangzhou.goeasy.io", "BC-eae9b507657d4c769b70907caebfa6c1");
       goEasy.publish("demo_channel", "Hello world!");
        return map;
    }

    @Override
    public String modifyStatus(String id) {
        //根据id查出

        User user = userDao.queryOne(id);
        user.setId(id);
        String status = user.getStatus();
        if(status.equals("冻结")){
            user.setStatus("正常");
            userDao.updateStatus(user);
        }else if(status.equals("正常")){
            user.setStatus("冻结");
            userDao.updateStatus(user);
        } else{
            user.setStatus("正常");
            userDao.updateStatus(user);
        }
        return null;
    }
    //根据id查询一个
    public User queryOneById(String id){
        User user = userDao.queryOne(id);
        return user;
    }

    @Override
    public  Map<String,Object> queryBymonth() {
        List<CountMonth> countMonths = userDao.queryByMonth();
        //遍历所有的时间，获取底6 7位数
        List<Object> Months = new ArrayList<>();
        List<Object> Count = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        for (CountMonth countMonth : countMonths) {
            String month1 = countMonth.getMonth();
            String substring = month1.substring(6, 7);
            Months.add(substring);
            Count.add(countMonth.getCount());
        }
        map.put("month", Months);
        map.put("count",Count);
        return map;
    }

    @Override
    public Map<String, Object> queryByProvince() {
        List<UserDto> userDtos = userDao.selectByProvince();
//         大map
        Map<String, Object> map = new HashMap<>();
//        小map
         Map<String, Object> map1 = new HashMap<>();
         List<Object> count = new ArrayList<>();
         List<Object> province = new ArrayList<>();
         List<Object> count1 = new ArrayList<>();
         List<Object> province1 = new ArrayList<>();
         Map<String, Object> map2 = new HashMap<>();
        List<ProvinceCount> provinceCounts = new ArrayList<>();
        List<ProvinceCount> provinceCount2 = new ArrayList<>();




        for (UserDto userDto : userDtos) {
//            遍历所有集合，判断如果为男性，将存入大map集合
            if(userDto.getGender().equals("男")){
               /* count.add(userDto.getCount());
                province.add(userDto.getProvince());
                map1.put("count",count);
                map1.put("province",province);*/
//              将map 存入list集合中
//                list.add(map1);
                map.put("gender", "男");
                ProvinceCount  provinceCount = new  ProvinceCount();
                provinceCount.setValue(userDto.getCount());
                provinceCount.setName(userDto.getProvince());
                provinceCounts.add(provinceCount);
//                map.put("map1", map1);
                map.put("map1", provinceCounts);
            }else{
               /* count1.add(userDto.getCount());
                province1.add(userDto.getProvince());
                map2.put("count", count1);
                map2.put("province",province1);*/
                ProvinceCount  provinceCount = new  ProvinceCount();
                provinceCount.setValue(userDto.getCount());
                provinceCount.setName(userDto.getProvince());
                provinceCount2.add(provinceCount);
                map.put("sex", "女");
                map.put("map2",provinceCount2);
            }
        }
        return map;
    }

}
