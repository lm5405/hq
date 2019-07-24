package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.Map;

public interface AlbumService {
    Map<String,Object> queryAll(Integer page,Integer rows);
    String addAlbum(Album album);
    void modifyCover(Album album);
    void removeAlbum(String id);
    String modifyAlbum(Album album);
}
