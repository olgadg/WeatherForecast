package com.cottondroid.olga.weatherforecast;


import com.cottondroid.olga.weatherforecast.model.DayForecast;

import java.util.List;

interface ForecastView {
    void onDataRetrieved(List<DayForecast> forecasts);

    void onDataError(Throwable e);
}
