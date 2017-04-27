package com.cottondroid.olga.weatherforecast.model;

import java.util.List;

public class Weather {
    private long id;
    private String main;
    private String description;
    private String icon;

    protected String getMain() {
        return main;
    }
}
