package com.cottondroid.olga.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ForecastModel {

    @SerializedName("list")
    private List<Forecast> forecastList;
    private List<DayForecast> dayForecastList;
    private City city;

    public String getCityName() {
        return city.getName();
    }


    public List<DayForecast> getDayForecastList() {
        if (dayForecastList == null) {
            dayForecastList = buildDayForecastList();
        }
        return dayForecastList;
    }

    public List<DayForecast> buildDayForecastList() {
        List<DayForecast> dayForecasts = new ArrayList<>();
        List<Forecast> forecastForOneDay = new ArrayList<>();
        Forecast currentForecast = null;
        for (Forecast forecast : forecastList) {
            if (currentForecast == null) {
                currentForecast = forecast;
                forecastForOneDay.add(forecast);
            } else if (forecast.isSameDate(currentForecast)) {
                forecastForOneDay.add(forecast);
            } else {
                currentForecast = forecast;
                dayForecasts.add(new DayForecast(forecastForOneDay));
                forecastForOneDay = new ArrayList<>();
            }
        }
        dayForecasts.add(new DayForecast(forecastForOneDay));
        return dayForecasts;
    }
}
