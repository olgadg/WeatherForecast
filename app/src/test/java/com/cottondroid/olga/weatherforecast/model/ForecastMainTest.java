package com.cottondroid.olga.weatherforecast.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ForecastMainTest {

    @Test
    public void forecastMainTest() {
        ForecastMain forecastMain1 = new ForecastMain(
                ForecastMain.KELVIN + 5,
                ForecastMain.KELVIN + 3,
                ForecastMain.KELVIN + 9);

        ForecastMain forecastMain = new ForecastMain(Collections.singletonList(forecastMain1));

        assertThat("Check temperature is average", forecastMain.getTemperature(), is(5));
        assertThat("Check min temperature is the min", forecastMain.getMinTemperature(), is(3));
        assertThat("Check max temperature is the max", forecastMain.getMaxTemperature(), is(9));
    }

    @Test
    public void forecastMainTest_two() {
        ForecastMain forecastMain1 = new ForecastMain(
                ForecastMain.KELVIN + 5,
                ForecastMain.KELVIN + 3,
                ForecastMain.KELVIN + 9);
        ForecastMain forecastMain2 = new ForecastMain(
                ForecastMain.KELVIN + 7,
                ForecastMain.KELVIN + 2,
                ForecastMain.KELVIN + 8);

        ForecastMain forecastMain = new ForecastMain(Arrays.asList(forecastMain1, forecastMain2));

        assertThat("Check temperature is average", forecastMain.getTemperature(), is(6));
        assertThat("Check min temperature is the min", forecastMain.getMinTemperature(), is(2));
        assertThat("Check max temperature is the max", forecastMain.getMaxTemperature(), is(9));
    }
}
