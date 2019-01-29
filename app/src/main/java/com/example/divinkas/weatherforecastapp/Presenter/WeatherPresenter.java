package com.example.divinkas.weatherforecastapp.Presenter;

import com.example.divinkas.weatherforecastapp.Data.WeatherResponse;
import com.example.divinkas.weatherforecastapp.Model.WeatherModel;
import com.example.divinkas.weatherforecastapp.Utils.UtilsModel;
import com.example.divinkas.weatherforecastapp.View.Contract.WeatherContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WeatherPresenter {
    private WeatherModel weatherModel;
    private UtilsModel utilsModel;
    private WeatherContract weatherContract;

    private Disposable disposable;

    public WeatherPresenter(WeatherContract contract) {
        weatherModel = new WeatherModel();
        utilsModel = new UtilsModel();
        weatherContract = contract;
    }

    public void load(String city){
        weatherContract.showLoading();
        disposable = weatherModel
                .getWeatherByNameCity(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        weatherResponse ->{
                            weatherContract.loadWeather(utilsModel.getThreeItemsWeather(weatherResponse));
                            weatherContract.hideLoading();
                        },
                        throwable -> weatherContract.showErrorLoading()
                );
    }

    public void load(double lat, double lon){
        weatherContract.showLoading();
        disposable = weatherModel
                .getWeatherByCoords(lon, lat)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        weatherResponse -> {
                            weatherContract.loadWeather(utilsModel.getThreeItemsWeather(weatherResponse));
                            weatherContract.hideLoading();
                        },
                        throwable -> weatherContract.showErrorLoading()
                );
    }

    public void unSubscribe(){
        if (disposable!=null)
            disposable.dispose();
    }
}
