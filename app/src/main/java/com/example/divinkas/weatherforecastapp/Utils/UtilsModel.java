package com.example.divinkas.weatherforecastapp.Utils;

import com.example.divinkas.weatherforecastapp.Data.WeatherResponse;

import java.util.ArrayList;
import java.util.List;

public class UtilsModel {
    public UtilsModel() {
    }

    public WeatherResponse getThreeItemsWeather(WeatherResponse weatherResponse){
        if(weatherResponse != null){
            List<com.example.divinkas.weatherforecastapp.Data.List> data = new ArrayList<>();
            int current_count = 0;
            String current_day = "";

            for(int position = 0; position < weatherResponse.getList().size(); position++){
                String day = weatherResponse.getList().get(position).getDtTxt().substring(0,10);
                if(!current_day.equals(day) && current_count < Constants.COUNT_DAYS){
                    com.example.divinkas.weatherforecastapp.Data.List obj = weatherResponse.getList().get(position);
                    obj.setDtTxt(day);
                    data.add(obj);

                    current_count++;
                    current_day = day;
                }
            }

            weatherResponse.setList(data);
            return weatherResponse;
        }
        return null;
    }

}
