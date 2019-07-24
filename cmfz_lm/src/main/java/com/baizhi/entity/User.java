package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Excel(name="用户编号")
    @Annotation(name="用户编号")
    private String id;
    @Excel(name="手机号")
    @Annotation(name="手机号")
    private String phone;
    @Excel(name="用户密码")
    @Annotation(name="用户密码")
    private String password;
    @Excel(name="盐")
    @Annotation(name="盐")
    private String salt;
    @Excel(name="用户名")
    @Annotation(name="用户名")
    private String dharmaName;
    @Excel(name="省份")
    @Annotation(name="省份")
    private String province;
    @Excel(name="城市")
    @Annotation(name="城市")
    private String city;
    @Excel(name="性别")
    @Annotation(name="性别")
    private String gender;
    @Excel(name="个性签名")
    @Annotation(name="个性签名")
    private String personalSign;
    @Excel(name="头像",type = 2 ,width = 40 , height = 20)
    @Annotation(name="头像")
    private String profile;
    @Excel(name="状态")
    @Annotation(name="状态")
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @Excel(name="注册日期",format = "yyyy-MM-dd")
    @Annotation(name="注册日期")
    private Date registTime;
}
