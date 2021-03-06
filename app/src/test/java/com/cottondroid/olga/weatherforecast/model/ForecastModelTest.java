package com.cottondroid.olga.weatherforecast.model;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ForecastModelTest {

    @Test
    public void testForecastModel() throws Exception {
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();
        final String json = Resources.toString(getClass().getClassLoader().getResource("forecast.json"), Charsets.UTF_8);

        ForecastModel forecastModel = gson.fromJson(json, ForecastModel.class);

        assertThat("City name", forecastModel.getCityName(), is("London"));
        assertThat("Five days", forecastModel.getDayForecastList().size(), is(6)); //today + 5days
        assertThat("Todays blocks", forecastModel.getDayForecastList().get(0).getForecastList().size(), is(3)); //3 blocks for today
        assertThat("Todays main weather", forecastModel.getDayForecastList().get(0).getForecastList().get(0).getMainWeather(), is("Rain"));
        assertThat("Todays temperature", forecastModel.getDayForecastList().get(0).getForecastList().get(0).getTemperature(), is(9));
        assertThat("Todays min temperature", forecastModel.getDayForecastList().get(0).getForecastList().get(0).getMinTemperature(), is(9));
        assertThat("Todays max temperature", forecastModel.getDayForecastList().get(0).getForecastList().get(0).getMaxTemperature(), is(9));
        assertThat("Todays average weather", forecastModel.getDayForecastList().get(0).getDayForecast().getMainWeather(), is("Rain"));
        assertThat("Todays average temperature", forecastModel.getDayForecastList().get(0).getDayForecast().getTemperature(), is(8));
        assertThat("Todays average min temperature", forecastModel.getDayForecastList().get(0).getDayForecast().getMinTemperature(), is(8));
        assertThat("Todays average max temperature", forecastModel.getDayForecastList().get(0).getDayForecast().getMaxTemperature(), is(9));
        for (int i = 1; i < forecastModel.getDayForecastList().size() - 1; i++) {
            assertThat("All blocks", forecastModel.getDayForecastList().get(i).getForecastList().size(), is(24 / 3 - 1)); //1 block for every 3 hours
        }
        assertThat("Last days' blocks", forecastModel.getDayForecastList().get(0).getForecastList().size(), is(3)); //3 blocks for the last day
    }

    @Test
    public void testCurrentForecast() throws Exception {
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();
        final String json = Resources.toString(getClass().getClassLoader().getResource("weather.json"), Charsets.UTF_8);

        Forecast forecast = gson.fromJson(json, Forecast.class);

        assertThat("Todays temperature", forecast.getTemperature(), is(10));
        assertThat("Todays min temperature", forecast.getMinTemperature(), is(9));
        assertThat("Todays max temperature", forecast.getMaxTemperature(), is(13));
        assertThat("Todays main weather", forecast.getMainWeather(), is("Clouds"));
    }
}
