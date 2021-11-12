package com.example.bkwheather.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bkwheather.database.DatabaseHelper;
import com.example.bkwheather.entity.Catalog;

import java.util.ArrayList;
import java.util.List;

public class ImplCatalogDAO implements ICatalogDAO {
    private Context mCtx;
    private SQLiteDatabase mDb;

    public ImplCatalogDAO(Context mCtx) {
        this.mCtx = mCtx;
        DatabaseHelper helper = new DatabaseHelper(this.mCtx);
        mDb = helper.getWritableDatabase();
    }

    @Override
    public List<Catalog> selectAll() {
        String sql = "SELECT * FROM Catalog";
        Cursor c = mDb.rawQuery(sql,null);
        List<Catalog> lst = new ArrayList<>();
        while (c.moveToNext()){
            int id = c.getInt(c.getColumnIndex("id"));
            String weatherName = c.getString(c.getColumnIndex("weatherName"));

            Catalog catalog  = new Catalog(id, weatherName);
            lst.add(catalog);
        }

        return lst;
    }

    @Override
    public Catalog selectById(int id) {
        String sql = "SELECT * FROM Catalog WHERE id = ?";
        Cursor c = mDb.rawQuery(sql, new String[]{String.valueOf(id)});
        while (c.moveToNext()){
            String weatherName = c.getString(c.getColumnIndex("weatherName"));

            Catalog catalog = new Catalog(id, weatherName);
            return catalog;

        }

        return null;
    }
}
