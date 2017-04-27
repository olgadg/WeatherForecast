package com.cottondroid.olga.weatherforecast;


import android.app.Application;

import com.cottondroid.olga.weatherforecast.data.DaggerNetworkComponent;
import com.cottondroid.olga.weatherforecast.data.NetworkComponent;
import com.cottondroid.olga.weatherforecast.data.NetworkModule;

public class ForecastApplication extends Application {
    private NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        networkComponent = DaggerNetworkComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }
}
