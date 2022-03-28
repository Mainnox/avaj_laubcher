package avaj.tower;

import java.util.Random;

import avaj.coordinates.Coordinates;

public class WeatherProvider {
    private WeatherProvider weatherProvider = this;
    private static String[] weather = {"SUN", "FOG", "RAIN", "SNOW"};
    private static final Random magic = new Random();

    private WeatherProvider() {

    }

    public WeatherProvider getProvider() {
        return (this.weatherProvider);
    }

    public static String getCurrentWeather(Coordinates coordinates) {
        int random = ((coordinates.getHeight()
            + coordinates.getLatitude()
            + coordinates.getLongitude())
            + magic.nextInt(4)) % 4;
        return (weather[random]);
    }
}