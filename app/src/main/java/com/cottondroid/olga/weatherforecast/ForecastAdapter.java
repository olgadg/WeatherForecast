package com.cottondroid.olga.weatherforecast;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cottondroid.olga.weatherforecast.model.DayForecast;

import java.util.ArrayList;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>{

    private final List<DayForecast> forecasts = new ArrayList<>();

    public void setForecasts(List<DayForecast> forecasts) {
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
        DayForecast forecast = forecasts.get(position);
        holder.bind(forecast);
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

    public static class ForecastViewHolder extends ViewHolder{
        private TextView temperature;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            temperature = (TextView) itemView.findViewById(R.id.temperature_textview);
        }


        public void bind(DayForecast forecast) {
            temperature.setText(String.valueOf(forecast.getDayForecast().getTemperature()));
        }
    }
}
