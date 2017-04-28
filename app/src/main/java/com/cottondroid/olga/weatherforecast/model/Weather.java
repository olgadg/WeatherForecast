package com.cottondroid.olga.weatherforecast.model;

import java.util.List;

public class Weather {
    protected static final int THUNDERSTORM_CODE = 200;
    protected static final int DRIZZLE_CODE = 300;
    protected static final int RAIN_CODE = 500;
    protected static final int SNOW_CODE = 600;
    protected static final int ATMOSPHERE_CODE = 700;
    protected static final int CLEAR_CODE = 800;
    protected static final int EXTREME_CODE = 900;

    private long id;
    private String main;
    private String description;
    private String icon;

    protected String getMain() {
        return main;
    }


    public boolean willItRain() {
        return id < CLEAR_CODE;
    }

    public boolean willThereBeSun() {
        return id >= CLEAR_CODE && id < EXTREME_CODE;
    }
}
