package com.cottondroid.olga.weatherforecast;

import android.support.annotation.NonNull;
import android.util.Log;

import com.cottondroid.olga.weatherforecast.data.WeatherForecastRepository;
import com.cottondroid.olga.weatherforecast.model.Forecast;
import com.cottondroid.olga.weatherforecast.model.ForecastModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class ForecastPresenter {
    private WeatherForecastRepository repository;
    private ForecastView view;


    public ForecastPresenter(WeatherForecastRepository repository, ForecastView view) {
        this.repository = repository;
        this.view = view;
    }

    public void requestData() {
        Observable.combineLatest(
                repository.getWeatherForecast(),
                repository.getCurrentWeather(),
                new BiFunction<ForecastModel, Forecast, List<Forecast>>() {
                    @Override
                    public List<Forecast> apply(@NonNull ForecastModel forecastModel, @NonNull Forecast forecast) throws Exception {
                        List<Forecast> forecasts = new ArrayList<>();
                        forecasts.add(forecast);
                        for (int i = 1; i < forecastModel.getDayForecastList().size(); i++) {
                            forecasts.add(forecastModel.getDayForecastList().get(i).getDayForecast());
                        }
                        return forecasts;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        new Observer<List<Forecast>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                Log.d(ForecastFragment.class.getName(), "onSubscribe");
                            }

                            @Override
                            public void onNext(@NonNull List<Forecast> forecasts) {
                                Log.d(ForecastFragment.class.getName(), "onNext");
                                view.onDataRetrieved(forecasts);
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
