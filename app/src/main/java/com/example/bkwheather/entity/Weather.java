package com.example.bkwheather.entity;

public class Weather {
    private int id;
    private String cityName;
    private int weatherType;
    private String describe;
    private String temperature;
    //item spinner (*)
    private String weatherName;

    public Weather(int id, String cityName, int weatherType, String describe, String temperature) {
        this.id = id;
        this.cityName = cityName;
        this.weatherType = weatherType;
        this.describe = describe;
        this.temperature = temperature;
    }

    public Weather(String cityName, int weatherType, String describe, String temperature) {
        this.cityName = cityName;
        this.weatherType = weatherType;
        this.describe = describe;
        this.temperature = temperature;
    }

    //item spinner (*)
    public String getWeatherName() {
        return weatherName;
    }

    public void setWeatherName(String weatherName) {
        this.weatherName = weatherName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(int weatherType) {
        this.weatherType = weatherType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
