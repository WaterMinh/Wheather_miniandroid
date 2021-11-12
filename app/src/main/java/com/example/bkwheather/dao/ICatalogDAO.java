package com.example.bkwheather.dao;

import com.example.bkwheather.entity.Catalog;

import java.util.List;

public interface ICatalogDAO {
    List<Catalog> selectAll();
    Catalog selectById(int id);


}
