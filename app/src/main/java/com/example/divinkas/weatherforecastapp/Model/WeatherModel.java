package com.example.divinkas.weatherforecastapp.Model;

import com.example.divinkas.weatherforecastapp.Data.WeatherResponse;
import com.example.divinkas.weatherforecastapp.Model.Retrofit.RetrofitClient;
import com.example.divinkas.weatherforecastapp.Model.Retrofit.WeatherApi;
import com.example.divinkas.weatherforecastapp.Utils.Constants;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class WeatherModel {
    private WeatherApi weatherApi;

    public WeatherModel() {
        Retrofit retrofit = RetrofitClient.getInstance();
        weatherApi = retrofit.create(WeatherApi.class);
    }

    public Observable<WeatherResponse> getWeatherByCoords(double lon, double lat){
        return weatherApi.getWeatherByCoords(lat, lon,
                Constants.MODE_VALUE, Constants.UNITS_VALUE, Constants.APPID_VALUE);
    }

    public Observable<WeatherResponse> getWeatherByNameCity(String nameCity){
        return weatherApi.getWeatherByCityName(nameCity,
                Constants.MODE_VALUE, Constants.UNITS_VALUE, Constants.APPID_VALUE);
    }
}
