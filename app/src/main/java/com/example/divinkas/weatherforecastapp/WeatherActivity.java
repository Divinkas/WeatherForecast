package com.example.divinkas.weatherforecastapp;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.divinkas.weatherforecastapp.Data.WeatherResponse;
import com.example.divinkas.weatherforecastapp.Presenter.WeatherPresenter;
import com.example.divinkas.weatherforecastapp.Utils.Constants;
import com.example.divinkas.weatherforecastapp.View.Adapter.WeatherAdapter;
import com.example.divinkas.weatherforecastapp.View.NetworkActivity;
import com.example.divinkas.weatherforecastapp.View.Contract.WeatherContract;

import java.util.Objects;

public class WeatherActivity extends NetworkActivity implements WeatherContract {
    private WeatherPresenter presenter;

    private ProgressBar progressBar;
    private RecyclerView recyclerWeather;
    private SwipeRefreshLayout swipeRefreshLayout;

    private String city;
    private double lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_weather);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        presenter = new WeatherPresenter(this);
        progressBar = findViewById(R.id.progress);
        recyclerWeather = findViewById(R.id.rvWeather);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        city = getIntent().getStringExtra(Constants.CITY);
        lon = getIntent().getDoubleExtra(Constants.LON, -999.0f);
        lat = getIntent().getDoubleExtra(Constants.LAT, -999.0f);

        loadData();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(false);
            if(isNetworkConnection()) {
                recyclerWeather.setAdapter(new WeatherAdapter(this, null));
                loadData();
            } else{ errorNetworkView(); }
        });
    }

    @Override
    public void loadWeather(WeatherResponse weatherResponse) {
        if(recyclerWeather != null && weatherResponse != null){
            setTitle(weatherResponse.getCity().getName());
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

    private void loadData() {
        if(city!=null){ presenter.load(city); }
        else if(lat != -999.0f && lon != -999.0f){ presenter.load(lat, lon); }
        else{ Toast.makeText(this, "No searched data!", Toast.LENGTH_SHORT).show(); }

    }
}
