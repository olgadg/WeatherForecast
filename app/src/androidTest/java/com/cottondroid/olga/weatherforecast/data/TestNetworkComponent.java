package com.cottondroid.olga.weatherforecast.data;

import com.cottondroid.olga.weatherforecast.ApplicationModule;
import com.cottondroid.olga.weatherforecast.ForecastActivityTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, TestNetworkModule.class})
public interface TestNetworkComponent extends NetworkComponent {
}