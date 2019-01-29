package com.example.divinkas.weatherforecastapp.View.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.divinkas.weatherforecastapp.Data.WeatherResponse;
import com.example.divinkas.weatherforecastapp.R;
import com.example.divinkas.weatherforecastapp.Utils.Constants;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
    private Context context;
    private WeatherResponse weather;

    public WeatherAdapter(Context context, WeatherResponse data) {
        this.context = context;
        weather = data;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new WeatherViewHolder(LayoutInflater.from(context).inflate(R.layout.item_weather, viewGroup, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        holder.tvTemp.setText(weather.getList().get(position).getMain().getTemp() + " 'C");
        holder.tvHumidity.setText("humidity: "+ weather.getList().get(position).getMain().getHumidity() + "%");
        holder.tvData.setText(weather.getList().get(position).getDtTxt());
        holder.tvWind.setText("wind: " + weather.getList().get(position).getWind().getSpeed().toString() + " m/s");
        Glide.with(context)
                .load(Constants.LINK_WEATHER_IMAGE + weather.getList().get(position).getWeather().get(0).getIcon() +".png")
                .apply(Constants.GLIDE_OPTIONS)
                .into(holder.imgWeather);
    }

    @Override
    public int getItemCount() {
        if(weather!=null) return weather.getList().size();
        else return 0;
    }


    class WeatherViewHolder extends RecyclerView.ViewHolder{
        TextView tvTemp, tvData, tvWind, tvHumidity;
        ImageView imgWeather;
        WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            imgWeather = itemView.findViewById(R.id.imgWeather);
            tvTemp = itemView.findViewById(R.id.tvTemp);
            tvData = itemView.findViewById(R.id.tvDate);
            tvHumidity = itemView.findViewById(R.id.tvHumidity);
            tvWind = itemView.findViewById(R.id.tvWind);
        }
    }
}
