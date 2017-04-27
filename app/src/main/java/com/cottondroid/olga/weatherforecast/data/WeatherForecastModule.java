package com.cottondroid.olga.weatherforecast.data;


import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import retrofit2.Retrofit;

@Module
public class WeatherForecastModule {
    @Provides
    @Singleton
    WeatherForecastRepository provideRepository(Retrofit retrofit) {
        return retrofit.create(WeatherForecastRepository.class);
    }
}
