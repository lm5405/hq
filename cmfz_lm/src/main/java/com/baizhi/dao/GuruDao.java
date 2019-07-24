package com.baizhi.dao;

import com.baizhi.entity.Guru;

import java.util.List;

public interface GuruDao extends BaseDao<Guru>{

    void updateProfile(Guru guru);
    List<Guru> selectAllGuru();
}
