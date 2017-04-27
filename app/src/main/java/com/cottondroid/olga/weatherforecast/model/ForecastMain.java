package com.cottondroid.olga.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastMain {
    @SerializedName("temp")
    private float temperature;
    @SerializedName("temp_min")
    private float minTemperature;
    @SerializedName("temp_max")
    private float maxTemperature;
    private float pressure;
    @SerializedName("sea_level")
    private float seaLevel;
    @SerializedName("grnd_level")
    private float groundLevel;
    private int humidity;


    /**
     * Constructor using average values.
     * @param forecastMains list of forecastMains to use for the average
     */
    protected ForecastMain(List<ForecastMain> forecastMains) {
        int count = forecastMains.size();
        for (ForecastMain forecastMain : forecastMains) {
            temperature += forecastMain.temperature;
            minTemperature += forecastMain.minTemperature;
            maxTemperature += forecastMain.maxTemperature;
            pressure += forecastMain.pressure;
            seaLevel += forecastMain.seaLevel;
            groundLevel += forecastMain.groundLevel;
            humidity += forecastMain.humidity;
        }
        temperature /= count;
        minTemperature /= count;
        maxTemperature /= count;
        pressure /= count;
        seaLevel /= count;
        groundLevel /= count;
        humidity /= count;
    }
}
