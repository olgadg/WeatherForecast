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
import com.cottondroid.olga.weatherforecast.model.DayForecast;
import com.cottondroid.olga.weatherforecast.model.Forecast;
import com.cottondroid.olga.weatherforecast.model.ForecastModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ForecastFragment extends Fragment implements ForecastView {

    @Inject
    WeatherForecastRepository repository;
    private ForecastAdapter adapter;
    private ForecastPresenter presenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(ForecastFragment.class.getName(), "onActivityCreated");
        ((ForecastApplication) getActivity().getApplication()).getNetworkComponent().inject(this);
        presenter = new ForecastPresenter(repository, this);
        presenter.requestData();
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

    @Override
    public void onDataRetrieved(List<Forecast> forecasts) {
        Log.d(ForecastFragment.class.getName(), "onDataRetrieved");
        adapter.setForecasts(forecasts);
    }

    @Override
    public void onDataError(Throwable e) {

    }
}
