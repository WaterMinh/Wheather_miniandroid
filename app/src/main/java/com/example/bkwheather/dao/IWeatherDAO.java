package com.example.bkwheather.dao;

import com.example.bkwheather.entity.Weather;

import java.util.List;

public interface IWeatherDAO {
    List<Weather> selectAll();
    boolean insert(Weather weather);
    boolean update(Weather weather);
    boolean delete(int id);
    Weather selectById(int id);
}
