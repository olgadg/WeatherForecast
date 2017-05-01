package com.cottondroid.olga.weatherforecast.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ForecastTest {

    @Test
    public void forecastTest() {
        Forecast forecast1 = new Forecast(
                Calendar.getInstance().getTimeInMillis() / 1000,
                new ForecastMain(ForecastMain.KELVIN + 5,
                        ForecastMain.KELVIN + 1,
                        ForecastMain.KELVIN + 8),
                Collections.singletonList(new Weather(Weather.CLEAR_CODE, "Clear")));

        Forecast forecast2 = new Forecast(
                Calendar.getInstance().getTimeInMillis() / 1000,
                new ForecastMain(ForecastMain.KELVIN + 6,
                        ForecastMain.KELVIN + 2,
                        ForecastMain.KELVIN + 9),
                Collections.singletonList(new Weather(Weather.CLEAR_CODE, "Clear")));

        Forecast forecast3 = new Forecast(
                Calendar.getInstance().getTimeInMillis() / 1000,
                new ForecastMain(ForecastMain.KELVIN + 7,
                        ForecastMain.KELVIN + 3,
                        ForecastMain.KELVIN + 10),
                Collections.singletonList(new Weather(Weather.RAIN_CODE, "Rain")));

        Forecast forecast = new Forecast(Arrays.asList(forecast1, forecast2, forecast3));

        assertThat("Check willItRain", forecast.willItRain(), is(false));
        assertThat("Check willThereBeSun", forecast.willThereBeSun(), is(true));
        assertThat("Check main", forecast.getMainWeather(), is("Clear"));

        assertThat("Check temperature is average", forecast.getTemperature(), is(6));
        assertThat("Check min temperature is the min", forecast.getMinTemperature(), is(1));
        assertThat("Check max temperature is the max", forecast.getMaxTemperature(), is(10));
    }

    @Test
    public void isSameDate_true() {

        Calendar calendar = Calendar.getInstance();
        Forecast forecast = new Forecast(
                calendar.getTimeInMillis() / 1000,
                new ForecastMain(ForecastMain.KELVIN + 5,
                        ForecastMain.KELVIN + 1,
                        ForecastMain.KELVIN + 8),
                Collections.singletonList(new Weather(Weather.CLEAR_CODE, "Clear")));

        assertThat("Check same date", forecast.isSameDate(calendar), is(true));
    }

    @Test
    public void isSameDate_false() {

        Calendar calendar = Calendar.getInstance();
        Forecast forecast = new Forecast(
                calendar.getTimeInMillis() / 1000,
                new ForecastMain(ForecastMain.KELVIN + 5,
                        ForecastMain.KELVIN + 1,
                        ForecastMain.KELVIN + 8),
                Collections.singletonList(new Weather(Weather.CLEAR_CODE, "Clear")));

        calendar.add(Calendar.DATE, 1);

        assertThat("Check same date", forecast.isSameDate(calendar), is(false));
    }

    @Test
    public void isItToday() {

        Calendar calendar = Calendar.getInstance();
        Forecast forecast = new Forecast(
                calendar.getTimeInMillis() / 1000,
                new ForecastMain(ForecastMain.KELVIN + 5,
                        ForecastMain.KELVIN + 1,
                        ForecastMain.KELVIN + 8),
                Collections.singletonList(new Weather(Weather.CLEAR_CODE, "Clear")));

        assertThat("Check same date", forecast.isItToday(), is(true));
    }
}
