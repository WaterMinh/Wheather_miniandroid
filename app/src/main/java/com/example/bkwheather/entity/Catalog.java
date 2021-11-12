package com.example.bkwheather.entity;

public class Catalog {
    private int id;
    private String weatherName;

    public Catalog(int id, String weatherName) {
        this.id = id;
        this.weatherName = weatherName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeatherName() {
        return weatherName;
    }

    public void setWeatherName(String weatherName) {
        this.weatherName = weatherName;
    }

    @Override
    public String toString() {
        return this.weatherName;
    }
}
