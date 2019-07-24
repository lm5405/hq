package com.baizhi.service;

import com.baizhi.entity.Guru;

import java.util.List;
import java.util.Map;

public interface GuruService {
    Map<String,Object> queryAll(Integer page, Integer rows);
    String addGuru(Guru guru);
    String modifyGuru(Guru guru);
    void modifyProfile(Guru guru);
    void removeGuru(String id);
    List<Guru> queryAllGuru();
}
