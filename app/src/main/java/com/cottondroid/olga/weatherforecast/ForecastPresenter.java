package com.cottondroid.olga.weatherforecast;

import android.util.Log;

import com.cottondroid.olga.weatherforecast.data.WeatherForecastRepository;
import com.cottondroid.olga.weatherforecast.model.ForecastModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ForecastPresenter {
    private WeatherForecastRepository repository;
    private ForecastView view;


    public ForecastPresenter(WeatherForecastRepository repository, ForecastView view) {
        this.repository = repository;
        this.view = view;
    }

    public void requestData() {
        repository.getWeatherForecast()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        new Observer<ForecastModel>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                Log.d(ForecastFragment.class.getName(), "onSubscribe");
                            }

                            @Override
                            public void onNext(@NonNull ForecastModel forecastModel) {
                                Log.d(ForecastFragment.class.getName(), "onNext");
                                view.onDataRetrieved(forecastModel.getDayForecastList());
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.d(ForecastFragment.class.getName(), "onError");
                                view.onDataError(e);

                            }

                            @Override
                            public void onComplete() {
                                Log.d(ForecastFragment.class.getName(), "onComplete");
                            }
                        });
    }
}
