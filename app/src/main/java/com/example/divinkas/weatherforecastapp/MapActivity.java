package com.example.divinkas.weatherforecastapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.widget.Button;

import com.example.divinkas.weatherforecastapp.Utils.Constants;
import com.example.divinkas.weatherforecastapp.View.NetworkActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends NetworkActivity implements OnMapReadyCallback {
    private GoogleMap map;
    private double lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map2);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        Button btnWeatherForecast = findViewById(R.id.btnWeatherForecast);
        btnWeatherForecast.setOnClickListener(view -> {
            if(map!= null){
                showWeatherActivity(lat, lon);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if(map != null) {
            LatLng sydney = new LatLng(-34, 151);
            map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            map.setOnMapClickListener(latLng -> {
                map.clear();
                map.addMarker(new MarkerOptions().position(latLng));
                lat = latLng.latitude; lon = latLng.longitude;
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.menuSearch).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(!s.isEmpty()){
                    showWeatherActivity(s);
                    return true;
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    private void showWeatherActivity(double lat, double lon) {
        Intent intent = new Intent(getApplicationContext(), WeatherActivity.class);
        intent.putExtra(Constants.LAT, lat);
        intent.putExtra(Constants.LON, lon);
        startActivity(intent);
    }

    private void showWeatherActivity(String s) {
        Intent intent = new Intent(getApplicationContext(), WeatherActivity.class);
        intent.putExtra(Constants.CITY, s);
        startActivity(intent);
    }

}
