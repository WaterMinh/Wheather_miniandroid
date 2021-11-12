package com.example.bkwheather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.bkwheather.entity.Weather;

import java.util.List;

public class AdapterWeather extends ArrayAdapter<Weather> {
    private Context mCtx;
    private int mLayout;
    List<Weather> mLst;

    public AdapterWeather(@NonNull Context context, int resource, @NonNull List<Weather> objects) {
        super(context, resource, objects);
        this.mCtx =context;
        this.mLayout = resource;
        this.mLst = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = LayoutInflater.from(mCtx).inflate(mLayout, null);

        }
        Weather weather = mLst.get(position);
        ((TextView) view.findViewById(R.id.itemKieuThoiTiet)).setText(String.valueOf(weather.getWeatherName()));
        ((TextView) view.findViewById(R.id.itemTenTP)).setText(String.valueOf(weather.getCityName()));
        ((TextView) view.findViewById(R.id.itemMoTa)).setText(String.valueOf(weather.getDescribe()));
        ((TextView) view.findViewById(R.id.itemNhietDo)).setText(String.valueOf(weather.getTemperature() + "\u2103"));

        return view;
    }
}
