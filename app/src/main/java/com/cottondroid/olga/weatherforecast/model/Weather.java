package com.cottondroid.olga.weatherforecast.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Weather {
    protected static final int THUNDERSTORM_CODE = 200;
    protected static final int DRIZZLE_CODE = 300;
    protected static final int RAIN_CODE = 500;
    protected static final int SNOW_CODE = 600;
    protected static final int ATMOSPHERE_CODE = 700;
    protected static final int CLEAR_CODE = 800;
    protected static final int EXTREME_CODE = 900;

    private int id;
    private String main;

    protected Weather(int id, String main) {
        this.id = id;
        this.main = main;
    }

    protected Weather(List<Weather> weathers) {
        if (weathers.isEmpty()) {
            return;
        }
        Map<Integer, Integer> idFrequencies = new HashMap<>();
        Map<String, Integer> mainFrequencies = new HashMap<>();
        for (Weather weather : weathers) {
            if (!idFrequencies.containsKey(weather.id)) {
                idFrequencies.put(weather.id, 1);
            } else {
                idFrequencies.put(weather.id, idFrequencies.get(weather.id) + 1);
            }
            if (!mainFrequencies.containsKey(weather.main)) {
                mainFrequencies.put(weather.main, 1);
            } else {
                mainFrequencies.put(weather.main, mainFrequencies.get(weather.main) + 1);
            }
        }
        int maxIdFrequency = Integer.MIN_VALUE;
        for (Integer currentId : idFrequencies.keySet()) {
            int currentFrequency = idFrequencies.get(currentId);
            if (currentFrequency > maxIdFrequency) {
                maxIdFrequency = currentFrequency;
                id = currentId;
            }
        }
        int maxMainFrequency = Integer.MIN_VALUE;
        for (String currentMain : mainFrequencies.keySet()) {
            int currentFrequency = mainFrequencies.get(currentMain);
            if (currentFrequency > maxMainFrequency) {
                maxMainFrequency = currentFrequency;
                main = currentMain;
            }
        }
    }

    protected String getMain() {
        return main;
    }

    public boolean willItRain() {
        return id < CLEAR_CODE;
    }

    public boolean willThereBeSun() {
        return id >= CLEAR_CODE && id < EXTREME_CODE;
    }

    public int getId() {
        return id;
    }
}
