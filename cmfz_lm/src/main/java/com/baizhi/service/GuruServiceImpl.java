package com.baizhi.service;

import com.baizhi.dao.GuruDao;
import com.baizhi.entity.Guru;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class GuruServiceImpl implements GuruService {
    @Autowired(required = false)
    private GuruDao guruDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Map<String,Object> map=new HashMap<>();
        Integer records = guruDao.selectRecords();
        Integer total = records%rows==0?records/rows:records/rows+1;
        Integer begin=(page-1)*rows;
        List<Guru> gurus = guruDao.selectAll(begin, rows);
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        map.put("rows",gurus);
        return map;
    }
    @Override
    public String addGuru(Guru guru) {
        String s = UUID.randomUUID().toString();
        guru.setId(s);
        guruDao.insert(guru);
        return s;
    }
    @Override
    public String modifyGuru(Guru guru) {
        String status = guru.getStatus();
        if(status.equals("冻结")){
            guru.setStatus("正常");
            guruDao.update(guru);
        }else{
            guru.setStatus("冻结");
            guruDao.update(guru);
        }
        return guru.getId();
    }
    @Override
    public void modifyProfile(Guru guru) {
        guruDao.updateProfile(guru);
    }
    @Override
    public void removeGuru(String id) {
        guruDao.delete(id);
    }

    @Override
    public List<Guru> queryAllGuru() {
        return guruDao.selectAllGuru();
    }
}
