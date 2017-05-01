package com.cottondroid.olga.weatherforecast;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.core.deps.guava.base.Charsets;
import android.support.test.espresso.core.deps.guava.io.Resources;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.cottondroid.olga.weatherforecast.data.DaggerTestNetworkComponent;
import com.cottondroid.olga.weatherforecast.data.TestNetworkComponent;
import com.cottondroid.olga.weatherforecast.data.TestNetworkModule;
import com.cottondroid.olga.weatherforecast.data.WeatherForecastRepository;
import com.cottondroid.olga.weatherforecast.model.DayForecast;
import com.cottondroid.olga.weatherforecast.model.Forecast;
import com.cottondroid.olga.weatherforecast.model.ForecastModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.util.Calendar;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
public class ForecastActivityTest {
    @Mock
    private WeatherForecastRepository mockWeatherForecastRepository;
    private boolean idle = false;

    @Rule
    public ActivityTestRule<ForecastActivity> activityTestRule =
            new ActivityTestRule<ForecastActivity>(ForecastActivity.class) {
                @Override
                protected void beforeActivityLaunched() {
                    super.beforeActivityLaunched();

                    initMocks(this);
                    generateDataFromJson();

                    TestNetworkComponent component = DaggerTestNetworkComponent.builder()
                            .testNetworkModule(new TestNetworkModule(mockWeatherForecastRepository)).build();

                    ForecastApplication app = (ForecastApplication) InstrumentationRegistry
                            .getInstrumentation().getTargetContext().getApplicationContext();
                    app.setNetworkComponent(component);
                }
            };

    private void generateDataFromJson() {
        mockWeatherForecastRepository = mock(WeatherForecastRepository.class);
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();
        try {
            final String forecastModelJson = Resources.toString(getClass().getClassLoader().getResource("forecast.json"), Charsets.UTF_8);
            ForecastModel forecastModel = gson.fromJson(forecastModelJson, ForecastModel.class);
            final String forecastJson = Resources.toString(getClass().getClassLoader().getResource("weather.json"), Charsets.UTF_8);
            final Forecast forecast = gson.fromJson(forecastJson, Forecast.class);
            for (int i = 0; i < forecastModel.getDayForecastList().size(); i++) {
                Calendar date = Calendar.getInstance();
                date.add(Calendar.DATE, i);
                forecastModel.getDayForecastList().get(i).getDayForecast().setDate(date);
            }
            forecast.setDate(Calendar.getInstance());

            when(mockWeatherForecastRepository.getWeatherForecast()).thenReturn(Observable.fromArray(forecastModel));
            when(mockWeatherForecastRepository.getCurrentWeather()).thenReturn(Observable.fromArray(forecast));
        } catch (IOException e) {
            //nothing
        }
    }

    @Test
    public void testActivity() {
        activityTestRule.launchActivity(new Intent());

        onView(withText("Today")).check(matches(isDisplayed()));
    }

}
