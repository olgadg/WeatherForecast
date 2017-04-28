package com.cottondroid.olga.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastMain {
    public static final float KELVIN = 273f;
    protected static final int THUNDERSTORM_CODE = 200;
    protected static final int DRIZZLE_CODE = 300;
    protected static final int RAIN_CODE = 500;
    protected static final int SNOW_CODE = 600;
    protected static final int ATMOSPHERE_CODE = 700;
    protected static final int CLEAR_CODE = 800;
    protected static final int EXTREME_CODE = 900;

    private int id;
    @SerializedName("temp")
    private float temperature;
    @SerializedName("temp_min")
    private float minTemperature;
    @SerializedName("temp_max")
    private float maxTemperature;

    /**
     * Constructor using average values.
     *
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

    public boolean willItRain() {
        return id < CLEAR_CODE;
    }

    public boolean willThereBeSun() {
        return id >= CLEAR_CODE && id < EXTREME_CODE;
    }
}
