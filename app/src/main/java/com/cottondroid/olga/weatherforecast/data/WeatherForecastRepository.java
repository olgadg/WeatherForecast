package com.cottondroid.olga.weatherforecast.data;

import com.cottondroid.olga.weatherforecast.model.ForecastModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface WeatherForecastRepository {
    long LONDON_ID = 2643743L;
    String APP_ID = "9ab980154b2797d68656609717cbb4e6";

    @GET("forecast?id=" + LONDON_ID + "&mode=json&appid=" + APP_ID)
    Observable<ForecastModel> getWeatherForecast();
}
