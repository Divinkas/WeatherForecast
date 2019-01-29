package com.example.divinkas.weatherforecastapp.Utils;

import android.annotation.SuppressLint;

import com.example.divinkas.weatherforecastapp.Data.WeatherResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UtilsModel {
    public UtilsModel() {
    }

    public WeatherResponse getThreeItemsWeather(WeatherResponse weatherResponse){
        if(weatherResponse != null){
            int current_count = 0;
            String current_day = "";
            List<com.example.divinkas.weatherforecastapp.Data.List> data = new ArrayList<>();
            for(int position = 0; position < weatherResponse.getList().size(); position++){
                String day = rightFormatDay(weatherResponse.getList().get(position).getDtTxt());
                if(!current_day.equals(day)){
                    current_count++;
                    current_day = day;
                    data.add(weatherResponse.getList().get(position));
                }
                if(current_count >= 3){
                    weatherResponse.setList(data);
                    return weatherResponse;
                }
            }
            weatherResponse.setList(data);
            return weatherResponse;
        }
        return null;
    }

    @SuppressLint("SimpleDateFormat")
    private String rightFormatDay(String dtTxt) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try { date = format1.parse(dtTxt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format2.format(date);
    }
}
