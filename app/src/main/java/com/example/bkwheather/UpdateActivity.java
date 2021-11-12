package com.example.bkwheather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bkwheather.dao.ICatalogDAO;
import com.example.bkwheather.dao.IWeatherDAO;
import com.example.bkwheather.dao.ImplCatalogDAO;
import com.example.bkwheather.dao.ImplWeatherDAO;
import com.example.bkwheather.entity.Catalog;
import com.example.bkwheather.entity.Weather;

import java.util.List;

public class UpdateActivity extends AppCompatActivity {
    List<Catalog> mLst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        subform();
    }

    private void subform() {
        ICatalogDAO dao = new ImplCatalogDAO(this);
        mLst = dao.selectAll();
        ArrayAdapter<Catalog> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,mLst);
        ((Spinner) findViewById(R.id.spnKieuThoiTiet)).setAdapter(adapter);

        //Lấy id item đổ lên form cập nhật
        int id = getIntent().getExtras().getInt("idW");
        IWeatherDAO wdao = new ImplWeatherDAO(this);
        Weather weather = wdao.selectById(id);

        //Lấy thành phần form
        ((EditText) findViewById(R.id.txtTinhThanh)).setText(weather.getCityName());
        //Trỏ tới spinner Catalog
        int loaiThoiTiet = 0;
        for (int i = 0; i < mLst.size(); i++){
            Catalog cata = mLst.get(i);
            if (cata.getId() == weather.getId()){
                loaiThoiTiet = i;
                break;
            }

        }
        ((EditText) findViewById(R.id.txtMoTa)).setText(weather.getDescribe());
        ((EditText) findViewById(R.id.txtNhietDo)).setText(weather.getTemperature());
        ((Button) findViewById(R.id.btnThem)).setEnabled(false);
        ((Button) findViewById(R.id.btnSua)).setEnabled(true);
    }

    public void editWeather(View view) {
        String cityName = ((EditText) findViewById(R.id.txtTinhThanh)).getText().toString();

        Spinner spCatalog = findViewById(R.id.spnKieuThoiTiet);
        Catalog catalog = (Catalog) spCatalog.getSelectedItem();
        int weatherType = catalog.getId();

        String describe = ((EditText) findViewById(R.id.txtMoTa)).getText().toString();
        String temperature = ((EditText) findViewById(R.id.txtNhietDo)).getText().toString();

        Weather weather = new Weather(cityName, weatherType, describe, temperature);

        IWeatherDAO dao = new ImplWeatherDAO(this);
        boolean isOk = dao.update(weather);
        if (isOk){
            Toast.makeText(this, "Suả dữ liệu  thành công", Toast.LENGTH_SHORT).show();
            findViewById(R.id.btnThem).setEnabled(true);
            findViewById(R.id.btnSua).setEnabled(false);
        }else {
            Toast.makeText(this, "Sủa dữ liệu thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}