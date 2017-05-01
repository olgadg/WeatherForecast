package com.cottondroid.olga.weatherforecast.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class WeatherForecastModule {
    @Provides
    @Singleton
    WeatherForecastRepository provideRepository(Retrofit retrofit) {
        return retrofit.create(WeatherForecastRepository.class);
    }
}
