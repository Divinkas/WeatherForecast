package com.example.divinkas.weatherforecastapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.divinkas.weatherforecastapp.Data.WeatherResponse;
import com.example.divinkas.weatherforecastapp.Presenter.WeatherPresenter;
import com.example.divinkas.weatherforecastapp.Utils.Constants;
import com.example.divinkas.weatherforecastapp.View.Adapter.WeatherAdapter;
import com.example.divinkas.weatherforecastapp.View.NetworkActivity;
import com.example.divinkas.weatherforecastapp.View.Contract.WeatherContract;

public class WeatherActivity extends NetworkActivity implements WeatherContract {
    private WeatherPresenter presenter;

    private ProgressBar progressBar;
    private RecyclerView recyclerWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_weather);
        presenter = new WeatherPresenter(this);
        progressBar = findViewById(R.id.progress);
        recyclerWeather = findViewById(R.id.rvWeather);

        String city = getIntent().getStringExtra(Constants.CITY);
        double lon = getIntent().getDoubleExtra(Constants.LON, -999.0f);
        double lat = getIntent().getDoubleExtra(Constants.LAT, -999.0f);

        if(city!=null){
            presenter.load(city);
        } else if(lat != -999.0f && lon != -999.0f){
            presenter.load(lat, lon);
        } else{
            Toast.makeText(this, "No searched data!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loadWeather(WeatherResponse weatherResponse) {
        if(recyclerWeather != null && weatherResponse != null){
            recyclerWeather.setLayoutManager(new LinearLayoutManager(this));
            WeatherAdapter weatherAdapter = new WeatherAdapter(this, weatherResponse);
            recyclerWeather.setAdapter(weatherAdapter);

        }
    }

    @Override
    public void showLoading() {
        if(progressBar!=null) progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        if(progressBar!=null) progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorLoading() {
        Toast.makeText(this, "Error loading weather!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unSubscribe();
    }
}
