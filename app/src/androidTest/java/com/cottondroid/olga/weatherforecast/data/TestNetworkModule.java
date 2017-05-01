package com.cottondroid.olga.weatherforecast.data;

import android.support.test.espresso.core.deps.guava.base.Charsets;
import android.support.test.espresso.core.deps.guava.io.Resources;

import com.cottondroid.olga.weatherforecast.data.WeatherForecastRepository;
import com.cottondroid.olga.weatherforecast.model.Forecast;
import com.cottondroid.olga.weatherforecast.model.ForecastModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;

import static org.mockito.Mockito.when;

@Module
public class TestNetworkModule {

    private WeatherForecastRepository mockWeatherForecastRepository;

    public TestNetworkModule(WeatherForecastRepository mockWeatherForecastRepository) {
        this.mockWeatherForecastRepository = mockWeatherForecastRepository;
    }

    @Provides
    @Singleton
    WeatherForecastRepository provideRepository() {
        return mockWeatherForecastRepository;
    }

}
