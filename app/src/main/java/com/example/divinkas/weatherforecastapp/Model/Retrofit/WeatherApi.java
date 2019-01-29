package com.example.divinkas.weatherforecastapp.Model.Retrofit;

import com.example.divinkas.weatherforecastapp.Data.WeatherResponse;
import com.example.divinkas.weatherforecastapp.Utils.Constants;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("forecast")
    Observable<WeatherResponse> getWeatherByCoords(
            @Query(Constants.LAT) double lat,
            @Query(Constants.LON) double lon,
            @Query(Constants.MODE) String mode,
            @Query(Constants.UNITS) String units,
            @Query(Constants.APPID) String appid
    );

    @GET("forecast")
    Observable<WeatherResponse> getWeatherByCityName(
            @Query(Constants.CITY) String city,
            @Query(Constants.MODE) String mode,
            @Query(Constants.UNITS) String units,
            @Query(Constants.APPID) String appid
    );
}
