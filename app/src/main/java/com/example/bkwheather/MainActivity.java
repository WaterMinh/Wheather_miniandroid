package com.example.bkwheather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.bkwheather.dao.IWeatherDAO;
import com.example.bkwheather.dao.ImplWeatherDAO;
import com.example.bkwheather.entity.Weather;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Weather> mLst;
    private GridView lstView;
    private AdapterWeather mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadWeather();
        registerForContextMenu(lstView);
    }

    private void loadWeather() {
        IWeatherDAO dao = new ImplWeatherDAO(this);

        mLst = dao.selectAll();

    //        mLst = new ArrayList<>();
    //        for (Weather weather : lstWeather) {
    //            String loai_thoitiet = cdao.selectById(weather.getWeatherType()).getWeatherName();
    //            String ten_tp = weather.getCityName();
    //            String mo_ta = weather.getDescribe();
    //            String nhiet_do = weather.getTemperature();
    //
    //            ItemViewWeather item = new ItemViewWeather(loai_thoitiet, ten_tp, mo_ta, nhiet_do);
    //            mLst.add(item);
    //        }

        mAdapter = new AdapterWeather(this, R.layout.item_weather, mLst);

        lstView = findViewById(R.id.formListView);
        lstView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        loadWeather();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idMenu = item.getItemId();
        switch (idMenu) {
            case R.id.menu_trangchu:
                Intent weatherTrangchu = new Intent(this, WelcomeScreenActivity.class);
                startActivity(weatherTrangchu);
                break;
            case R.id.menu_add:
                Intent weatherAdd = new Intent(this, AddActivity.class);
                startActivity(weatherAdd);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        getMenuInflater().inflate(R.menu.context_menu_weather , menu);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Weather weather = mLst.get(info.position);
        menu.setHeaderTitle(weather.getCityName());

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int idMenu = item.getItemId();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Weather w = mLst.get(info.position);
        switch (idMenu) {
            case R.id.menu_edit:
                Intent weatherEdit = new Intent(this, UpdateActivity.class);
                weatherEdit.putExtra("id", w.getId());
                startActivity(weatherEdit);
                break;
            case R.id.menu_delete:
                IWeatherDAO dao = new ImplWeatherDAO(this);
                boolean isOk = dao.delete(w.getId());
                if (isOk){
                    Toast.makeText(this, "Xóa dữ liệu thành công", Toast.LENGTH_SHORT).show();
                    mLst.remove(info.position);
                    mAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(this,"Xóa dữ liệu không thành công", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        return super.onContextItemSelected(item);
    }
}