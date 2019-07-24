package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guru implements Serializable {
    private String id;
    private String name;
    private String profile;
    private String status;
    private String sex;
    //文章集
    private List<Chapter> chapters;
}
