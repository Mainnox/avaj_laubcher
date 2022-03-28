package avaj.flyable;

import java.util.HashMap;
import java.util.Map;

import avaj.aircraft.Aircraft;
import avaj.coordinates.Coordinates;
import avaj.tower.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }
    public void updateCondition() {
        String currentWeather = weatherTower.getWeather(coordinates);
        Map<String, String> weatherMap = new HashMap<String, String>();

        weatherMap.put("SUN", "Sunny day, Sunny view!");
        weatherMap.put("FOG", "Cheryl would love this weather.");
        weatherMap.put("SNOW", "No, I don't want a snowman!");
        weatherMap.put("RAIN", "It's raining mens.");
        switch (currentWeather) {
            case "SUN":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 10,
                    coordinates.getLatitude(), coordinates.getHeight() + 2);
                break;
            case "FOG":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 1,
                    coordinates.getLatitude(), coordinates.getHeight());
                break;
            case "SNOW":
                this.coordinates = new Coordinates(coordinates.getLongitude(),
                    coordinates.getLatitude(), coordinates.getHeight() - 12);
                break;
            case "RAIN":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 5,
                    coordinates.getLatitude(), coordinates.getHeight());
                break;
            default:
                System.out.println("Unexepted error comes on updating the conditions of the ballon.");
        }
        System.out.println("Helicopter#" + this.name + "(" + this.id + ") : " + weatherMap.get(currentWeather));
        if (this.coordinates.getHeight() <= 0)
        {
            System.out.println("Helicopter#" + this.name + "(" + this.id +
            ") landing.");
            weatherTower.unregister(this);
            System.out.println("Tower says: Helicopter#" + this.name + "(" + this.id + ") unregistered to weather tower.");
        }
    }
        public void registerTower(WeatherTower weatherTower) {
            this.weatherTower = weatherTower;
            weatherTower.register(this);
            System.out.println("Tower says: Helicopter#" + this.name + "(" + this.id + ") registered to weather tower.");
        }
}