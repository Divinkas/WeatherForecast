package com.example.divinkas.weatherforecastapp.View.Contract;

import com.example.divinkas.weatherforecastapp.Data.WeatherResponse;

public interface WeatherContract {
    void loadWeather(WeatherResponse weatherResponse);
    void showLoading();
    void hideLoading();
    void showErrorLoading();
}
