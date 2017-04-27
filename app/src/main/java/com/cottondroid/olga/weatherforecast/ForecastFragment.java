package com.cottondroid.olga.weatherforecast;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class ForecastFragment extends Fragment {

    @Inject
    WeatherForecastRepository repository;
    private ForecastAdapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(ForecastFragment.class.getName(), "onActivityCreated");
        ((ForecastApplication) getActivity().getApplication()).getNetworkComponent().inject(this);

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
                                Log.i(ForecastFragment.class.getName(), "forecastModel: " + forecastModel);
                                adapter.setForecasts(forecastModel.getDayForecastList());
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.e(ForecastFragment.class.getName(), "onError", e);

                            }

                            @Override
                            public void onComplete() {
                                Log.d(ForecastFragment.class.getName(), "onComplete");

                            }
                        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);
        Log.d(ForecastFragment.class.getName(), "onCreateView");

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        adapter = new ForecastAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
