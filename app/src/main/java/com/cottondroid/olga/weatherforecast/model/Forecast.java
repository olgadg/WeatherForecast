package com.cottondroid.olga.weatherforecast.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Forecast {
    @SerializedName("dt")
    private long date;
    private Calendar calendarDate;
    private ForecastMain main;
    private List<Weather> weather;

    /**
     * Constructor using average values.
     *
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
        return isSameDate(forecast.getCalendarDate());
    }

    private boolean isSameDate(Calendar otherCalendarDate) {
        return otherCalendarDate.get(Calendar.YEAR) == getCalendarDate().get(Calendar.YEAR)
                && otherCalendarDate.get(Calendar.DAY_OF_YEAR) == getCalendarDate().get(Calendar.DAY_OF_YEAR);
    }

    public boolean isItToday() {
        Calendar today = Calendar.getInstance();
        return isSameDate(today);
    }

    public String getDayOfTheWeek() {
        return getCalendarDate().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.UK);
    }

    public boolean willItRain() {
        return weather.get(0).willItRain();
    }

    public boolean willThereBeSun() {
        return weather.get(0).willThereBeSun();
    }
}
