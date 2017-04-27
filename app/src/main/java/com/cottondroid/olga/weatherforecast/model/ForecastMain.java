package com.cottondroid.olga.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastMain {
    public static final float KELVIN = 273f;

    @SerializedName("temp")
    private float temperature;
    @SerializedName("temp_min")
    private float minTemperature;
    @SerializedName("temp_max")
    private float maxTemperature;


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
        }
        temperature /= count;
        minTemperature /= count;
        maxTemperature /= count;
    }

    public int getTemperature() {
        return (int) (temperature - KELVIN);
    }

    public int getMinTemperature() {
        return (int) (minTemperature - KELVIN);
    }

    public int getMaxTemperature() {
        return (int) (maxTemperature - KELVIN);
    }
}
