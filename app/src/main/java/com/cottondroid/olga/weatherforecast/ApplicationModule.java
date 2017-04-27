package com.cottondroid.olga.weatherforecast;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private ForecastApplication application;

    public ApplicationModule(ForecastApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    ForecastApplication provideApplication() {
        return application;
    }
}
