package com.cottondroid.olga.weatherforecast.model;

import com.google.gson.annotations.SerializedName;

public class Coordinates {
    @SerializedName("lat")
    private float latitude;
    @SerializedName("lon")
    private float longitude;
}
