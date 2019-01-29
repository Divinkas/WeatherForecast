package com.example.divinkas.weatherforecastapp.View.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.divinkas.weatherforecastapp.Data.WeatherResponse;
import com.example.divinkas.weatherforecastapp.R;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
    private Context context;
    private WeatherResponse weather;

    public WeatherAdapter(Context context, WeatherResponse data) {
        this.context = context;
        weather = data;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new WeatherViewHolder(LayoutInflater.from(context).inflate(R.layout.item_weather, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder weatherViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return weather.getList().size();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder{

        WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
