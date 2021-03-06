package com.cottondroid.olga.weatherforecast;


import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cottondroid.olga.weatherforecast.model.DayForecast;
import com.cottondroid.olga.weatherforecast.model.Forecast;

import java.util.ArrayList;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private final List<Forecast> forecasts = new ArrayList<>();

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts.clear();
        this.forecasts.addAll(forecasts);
        notifyDataSetChanged();
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_forecast_row, parent, false);

        return new ForecastViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        Forecast forecast = forecasts.get(position);
        holder.bind(forecast);
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

    public static class ForecastViewHolder extends ViewHolder {
        private TextView date;
        private TextView mainWeather;
        private TextView extraWeather;
        private TextView temperature;
        private TextView minTemperature;
        private TextView maxTemperature;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date_textview);
            mainWeather = (TextView) itemView.findViewById(R.id.weather_main_textview);
            extraWeather = (TextView) itemView.findViewById(R.id.weather_extra_textview);
            temperature = (TextView) itemView.findViewById(R.id.temperature_textview);
            minTemperature = (TextView) itemView.findViewById(R.id.min_temperature_textview);
            maxTemperature = (TextView) itemView.findViewById(R.id.max_temperature_textview);
        }

        public void bind(Forecast forecast) {
            if (forecast.isItToday()) {
                date.setText(date.getResources().getString(R.string.today));
                temperature.setText(temperature.getResources().getString(R.string.temperature_degrees, forecast.getTemperature()));
                temperature.setVisibility(View.VISIBLE);
            } else {
                date.setText(forecast.getDayOfTheWeek());
                temperature.setVisibility(View.INVISIBLE);
            }
            mainWeather.setText(forecast.getMainWeather());
            if (forecast.willThereBeSun()) {
                extraWeather.setVisibility(View.VISIBLE);
                extraWeather.setText(extraWeather.getResources().getString(R.string.sunglasses_text));
            } else if (forecast.willItRain()) {
                extraWeather.setVisibility(View.VISIBLE);
                extraWeather.setText(extraWeather.getResources().getString(R.string.umbrella_text));
            } else {
                extraWeather.setVisibility(View.GONE);
            }
            minTemperature.setText(minTemperature.getResources().getString(R.string.temperature_degrees, forecast.getMinTemperature()));
            maxTemperature.setText(maxTemperature.getResources().getString(R.string.temperature_degrees, forecast.getMaxTemperature()));
        }
    }
}
