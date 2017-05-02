package com.cottondroid.olga.weatherforecast.data;

import com.cottondroid.olga.weatherforecast.model.Forecast;
import com.cottondroid.olga.weatherforecast.model.ForecastModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface WeatherForecastRepository {
    long LONDON_ID = 2643743L;
    String APP_ID = "";

    @GET("forecast?id=" + LONDON_ID + "&mode=json&appid=" + APP_ID)
    Observable<ForecastModel> getWeatherForecast();

    @GET("weather?id=" + LONDON_ID + "&mode=json&appid=" + APP_ID)
    Observable<Forecast> getCurrentWeather();
}
