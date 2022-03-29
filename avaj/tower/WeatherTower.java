package avaj.tower;

import avaj.coordinates.Coordinates;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates) {
        return (WeatherProvider.getCurrentWeather(coordinates));
    }
    public void changeWeather() {
        this.conditionsChanged();
    }
}