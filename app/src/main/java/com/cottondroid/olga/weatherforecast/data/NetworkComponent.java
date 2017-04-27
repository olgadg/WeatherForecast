package com.cottondroid.olga.weatherforecast.data;


import com.cottondroid.olga.weatherforecast.ApplicationModule;
import com.cottondroid.olga.weatherforecast.HomeForecastActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface NetworkComponent {
    void inject(HomeForecastActivity activity);

}