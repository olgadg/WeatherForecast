package com.cottondroid.olga.weatherforecast.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Forecast {
    @SerializedName("dt")
    private long date;
    private Calendar calendarDate;
    private ForecastMain main;
    private List<Weather> weather;

    /**
     * Constructor using average values.
     * @param forecasts list of forecastMains to use for the average
     */
    protected Forecast(List<Forecast> forecasts) {
        date = forecasts.get(0).date;
        List<ForecastMain> forecastMains = new ArrayList<>();
        List<Weather> weathers = new ArrayList<>();
        for (Forecast forecast : forecasts) {
            forecastMains.add(forecast.main);
            weathers.addAll(forecast.weather);
        }
        main = new ForecastMain(forecastMains);
        weather = weathers;
    }

    public String getMainWeather() {
        if (weather == null || weather.isEmpty()) {
            return null;
        }
        return weather.get(0).getMain();
    }

    public int getTemperature() {
        return main.getTemperature();
    }

    public int getMinTemperature() {
        return main.getMinTemperature();
    }

    public int getMaxTemperature() {
        return main.getMaxTemperature();
    }

    private Calendar getCalendarDate() {
        if (calendarDate == null) {
            calendarDate = Calendar.getInstance();
            calendarDate.setTimeInMillis(date * 1000);
        }
        return calendarDate;
    }

    protected boolean isSameDate(Forecast forecast) {
        return forecast.getCalendarDate().get(Calendar.YEAR) == getCalendarDate().get(Calendar.YEAR)
                && forecast.getCalendarDate().get(Calendar.DAY_OF_YEAR) == getCalendarDate().get(Calendar.DAY_OF_YEAR);
    }
}
