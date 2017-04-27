package com.cottondroid.olga.weatherforecast.model;

import java.util.List;

public class DayForecast {
    private final List<Forecast> forecastList;

    public Forecast getDayForecast() {
        return dayForecast;
    }

    private final Forecast dayForecast;

    public DayForecast(List<Forecast> forecastList) {
        this.forecastList = forecastList;
        dayForecast = new Forecast(forecastList);
    }

    public List<Forecast> getForecastList() {
        return forecastList;
    }
}
