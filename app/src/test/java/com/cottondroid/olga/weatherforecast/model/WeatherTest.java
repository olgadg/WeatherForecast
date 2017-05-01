package com.cottondroid.olga.weatherforecast.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WeatherTest {

    @Test
    public void weatherTest_one() {
        Weather weather1 = new Weather(Weather.RAIN_CODE, "Rain");

        Weather weather = new Weather(Collections.singletonList(weather1));
        assertThat("Check id", weather.getId(), is(Weather.RAIN_CODE));
        assertThat("Check main", weather.getMain(), is("Rain"));
    }

    @Test
    public void weatherTest_three() {
        Weather weather1 = new Weather(Weather.RAIN_CODE, "Rain");
        Weather weather2 = new Weather(Weather.CLEAR_CODE, "Clear");
        Weather weather3 = new Weather(Weather.CLEAR_CODE, "Clear");

        Weather weather = new Weather(Arrays.asList(weather1, weather2, weather3));
        assertThat("Check id", weather.getId(), is(Weather.CLEAR_CODE));
        assertThat("Check main", weather.getMain(), is("Clear"));

    }

    @Test
    public void willItRain_true() {
        Weather weather = new Weather(Weather.RAIN_CODE, "Rain");
        assertThat("Check will it rain", weather.willItRain(), is(true));
    }

    @Test
    public void willItRain_false() {
        Weather weather = new Weather(Weather.CLEAR_CODE, "Clear");
        assertThat("Check will it rain", weather.willItRain(), is(false));
    }

    @Test
    public void willThereBeSun_true() {
        Weather weather = new Weather(Weather.CLEAR_CODE, "Clear");
        assertThat("Check will it rain", weather.willThereBeSun(), is(true));
    }

    @Test
    public void willThereBeSun_false() {
        Weather weather = new Weather(Weather.RAIN_CODE, "Rain");
        assertThat("Check will it rain", weather.willThereBeSun(), is(false));
    }

}
