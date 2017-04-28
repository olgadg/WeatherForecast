package com.cottondroid.olga.weatherforecast;


import com.cottondroid.olga.weatherforecast.model.Forecast;

import java.util.List;

interface ForecastView {
    void onDataRetrieved(List<Forecast> forecasts);

    void onDataError(Throwable e);
}
