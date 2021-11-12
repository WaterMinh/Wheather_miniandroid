package com.example.bkwheather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bkwheather.dao.ICatalogDAO;
import com.example.bkwheather.dao.IWeatherDAO;
import com.example.bkwheather.dao.ImplCatalogDAO;
import com.example.bkwheather.dao.ImplWeatherDAO;
import com.example.bkwheather.entity.Catalog;
import com.example.bkwheather.entity.Weather;

import java.util.List;

public class AddActivity extends AppCompatActivity {

    List<Catalog> mLst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        subform();

    }


    private void subform() {
        ICatalogDAO dao = new ImplCatalogDAO(this);
        mLst = dao.selectAll();
        ArrayAdapter<Catalog> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,mLst);
        ((Spinner) findViewById(R.id.spnKieuThoiTiet)).setAdapter(adapter);


    }

    public void addWeather(View view) {
        String cityName = ((EditText) findViewById(R.id.txtTinhThanh)).getText().toString();

        Spinner spCatalog = findViewById(R.id.spnKieuThoiTiet);
        Catalog catalog = (Catalog) spCatalog.getSelectedItem();
        int weatherType = catalog.getId();

        String describe = ((EditText) findViewById(R.id.txtMoTa)).getText().toString();
        String temperature = ((EditText) findViewById(R.id.txtNhietDo)).getText().toString();

        Weather weather = new Weather(cityName, weatherType, describe, temperature);

        IWeatherDAO dao = new ImplWeatherDAO(this);
        boolean isOk = dao.insert(weather);
        if (isOk){
            Toast.makeText(this, "Thêm mới thành công", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "Thêm mới thất bại", Toast.LENGTH_SHORT).show();
        }

    }

}