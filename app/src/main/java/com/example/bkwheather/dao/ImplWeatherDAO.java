package com.example.bkwheather.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bkwheather.database.DatabaseHelper;
import com.example.bkwheather.entity.Weather;

import java.util.ArrayList;
import java.util.List;

public class ImplWeatherDAO implements IWeatherDAO {
    private Context mCtx;
    List<Weather> mLst;
    private SQLiteDatabase mDb;

    public ImplWeatherDAO(Context mCtx) {
        this.mCtx = mCtx;
        DatabaseHelper helper = new DatabaseHelper(this.mCtx);
        mDb = helper.getWritableDatabase();

    }

    @Override
    public List<Weather> selectAll() {
//        String sql = "SELECT * FROM Weather";

        //item spinner (*)
        String sql = "SELECT * FROM Weather JOIN Catalog ON Weather.id = Catalog.id";

        Cursor c = mDb.rawQuery(sql,null);
        mLst = new ArrayList<>();
        while (c.moveToNext()){
            int id = c.getInt(c.getColumnIndex("id"));
            String cityName = c.getString(c.getColumnIndex("cityName"));
            int weatherType = c.getInt(c.getColumnIndex("weatherType"));
            String describe = c.getString(c.getColumnIndex("describe"));
            String temperature = c.getString(c.getColumnIndex("temperature"));

            Weather weather = new Weather(id, cityName, weatherType, describe, temperature);

            //item spinner (*)
            weather.setWeatherName(c.getString(c.getColumnIndex("weatherName")));

            mLst.add(weather);

        }
        return mLst;
    }

    @Override
    public boolean insert(Weather weather) {
        ContentValues values = new ContentValues();
        values.put("cityName", weather.getCityName());
        values.put("weatherType", weather.getWeatherType());
        values.put("describe", weather.getDescribe());
        values.put("temperature", weather.getTemperature());

        long newID = mDb.insert("Weather", null, values);
        if (newID>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Weather weather) {
        ContentValues values = new ContentValues();
        values.put("cityName", weather.getCityName());
        values.put("weatherType", weather.getWeatherType());
        values.put("describe", weather.getDescribe());
        values.put("temperature", weather.getTemperature());

        int row = mDb.update("Weather", values,"id = ?", new String[] {String.valueOf(weather.getId())});
        if (row>0){
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        int row = mDb.delete("Weather", "id = ?", new String[] {String.valueOf(id)});
        if (row>0){
            return true;
        }
        return false;
    }

    @Override
    public Weather selectById(int id) {
        String sql = "SELECT * FROM Weather WHERE id = ? ";
        Cursor c = mDb.rawQuery(sql, new String[] {String.valueOf(id)});
        while (c.moveToNext()){
            int idW = c.getInt(c.getColumnIndex("idW"));
            String cityName = c.getString(c.getColumnIndex("cityName"));
            int weatherType = c.getInt(c.getColumnIndex("weatherType"));
            String describe = c.getString(c.getColumnIndex("describe"));
            String temperature = c.getString(c.getColumnIndex("temperature"));

            Weather weather = new Weather(idW,cityName,weatherType,describe,temperature);
            return weather;

        }
        return null;
    }
}
