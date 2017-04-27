package com.cottondroid.olga.weatherforecast;


import android.app.Application;

import com.cottondroid.olga.weatherforecast.data.DaggerNetworkComponent;
import com.cottondroid.olga.weatherforecast.data.NetworkComponent;
import com.cottondroid.olga.weatherforecast.data.NetworkModule;
import com.cottondroid.olga.weatherforecast.data.WeatherForecastModule;

import javax.inject.Inject;

public class ForecastApplication extends Application {
    @Inject NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        networkComponent = DaggerNetworkComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule())
                .weatherForecastModule(new WeatherForecastModule())
                .build();
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }
}
