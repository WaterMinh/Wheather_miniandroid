package com.example.bkwheather.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "BKWeather.sqlite";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Catalog (\n" +
                "    id           INTEGER      PRIMARY KEY AUTOINCREMENT,\n" +
                "    weatherName VARCHAR (50) NOT NULL\n" +
                ");\n";
        db.execSQL(sql);

        sql = "INSERT INTO Catalog(weatherName) VALUES ('Nắng')";
        db.execSQL(sql);
        sql = "INSERT INTO Catalog(weatherName) VALUES ('Mưa')";
        db.execSQL(sql);
        sql = "INSERT INTO Catalog(weatherName) VALUES ('Sương gió')";
        db.execSQL(sql);
        sql = "INSERT INTO Catalog(weatherName) VALUES ('Bão tố')";
        db.execSQL(sql);

        sql = "CREATE TABLE Weather (\n" +
                "    id           INTEGER       PRIMARY KEY AUTOINCREMENT,\n" +
                "    cityName     VARCHAR (100) NOT NULL,\n" +
                "    weatherType INT           NOT NULL\n" +
                "                               REFERENCES Catalog (id),\n" +
                "    describe     VARCHAR (300) NOT NULL,\n" +
                "    temperature  VARCHAR (100)           NOT NULL\n" +
                ");\n";
        db.execSQL(sql);

        sql = "INSERT INTO Weather(cityName, weatherType, describe, temperature) VALUES ('Thái Bình',1,'Ngày nắng, một số nơi có mưa rào rải rác','32')";
        db.execSQL(sql);
        sql = "INSERT INTO Weather(cityName, weatherType, describe, temperature) VALUES ('Hà Nội',2,'Ngày nắng, một số nơi có mưa rào rải rác','27')";
        db.execSQL(sql);
        sql = "INSERT INTO Weather(cityName, weatherType, describe, temperature) VALUES ('Đà Nẵng',3,'Ngày nắng, một số nơi có mưa rào rải rác','30')";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
