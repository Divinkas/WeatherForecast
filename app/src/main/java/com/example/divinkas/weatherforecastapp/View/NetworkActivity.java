package com.example.divinkas.weatherforecastapp.View;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.divinkas.weatherforecastapp.R;

public abstract class NetworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isNetworkConnection()){ initView(); }
        else{ errorNetworkView(); }
    }

    protected boolean isNetworkConnection(){
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm != null ? cm.getActiveNetworkInfo() : null;
        if (networkInfo != null) {
            if (networkInfo.isConnected()) {
                NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                return (mobile != null && mobile.isConnected()) || (wifi != null && wifi.isConnected());
            } else
                return false;
        }
        return false;
    }

    protected void errorNetworkView(){
        setContentView(R.layout.no_connection_view);
        Button buttonRestart = findViewById(R.id.btnNewConnection);
        buttonRestart.setOnClickListener(view -> {
            if(isNetworkConnection()){
                initView();
            }
        });

    }

    protected abstract void initView();
}
