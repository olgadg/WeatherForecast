package com.cottondroid.olga.weatherforecast;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cottondroid.olga.weatherforecast.data.WeatherForecastRepository;
import com.cottondroid.olga.weatherforecast.model.ForecastModel;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WeatherForecastFragment extends Fragment {

    @Inject
    WeatherForecastRepository repository;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((ForecastApplication) getActivity().getApplication()).getNetworkComponent().inject(this);

        repository.getWeatherForecast()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        new Observer<ForecastModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(WeatherForecastFragment.class.getName(), "onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull ForecastModel forecastModel) {
                        Log.i(WeatherForecastFragment.class.getName(), "forecastModel: " + forecastModel);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(WeatherForecastFragment.class.getName(), "onError", e);

                    }

                    @Override
                    public void onComplete() {
                        Log.d(WeatherForecastFragment.class.getName(), "onComplete");

                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forecast, container, false);
    }
}
