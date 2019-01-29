package com.example.divinkas.weatherforecastapp.Utils;

import com.bumptech.glide.request.RequestOptions;
import com.example.divinkas.weatherforecastapp.R;

public class Constants {
    public static final String LINK_API = "https://api.openweathermap.org/data/2.5/";

    public static final String LAT = "lat";
    public static final String LON = "lon";
    public static final String MODE = "mode";
    public static final String UNITS = "units";
    public static final String APPID = "appid";
    public static final String CITY = "q";

    public static final String APPID_VALUE = "732b5276c8717baaa38cc4d2e4ec1ef2";
    public static final String UNITS_VALUE = "metric";
    public static final String MODE_VALUE = "json";
    public static final String LAT_VALUE_DEFAULT = "65";
    public static final String LON_VALUE_DEFAULT = "98";

    public static final RequestOptions GLIDE_OPTIONS = new RequestOptions().centerCrop().error(R.mipmap.ic_launcher);
    public static final String LINK_WEATHER_IMAGE = "http://openweathermap.org/img/w/";
    public static int COUNT_DAYS = 3;
}
