package com.cottondroid.olga.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

public class City {
    private String name;
    private String country;
    @SerializedName("coord")
    private Coordinates coordinates;

    public String getName() {
        return name;
    }
}
