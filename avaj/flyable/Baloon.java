package avaj.flyable;

import java.util.HashMap;
import java.util.Map;

import avaj.aircraft.Aircraft;
import avaj.coordinates.Coordinates;
import avaj.tower.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }
    public void updateCondition() {
        String currentWeather = weatherTower.getWeather(coordinates);
        Map<String, String> weatherMap = new HashMap<String, String>();

        weatherMap.put("SUN", "Dude, the heat is over 9000!");
        weatherMap.put("FOG", "Did I hear a siren?");
        weatherMap.put("SNOW", "Frozen get mad at me!");
        weatherMap.put("RAIN", "The tears the gods gonna make us down!");
        switch (currentWeather) {
            case "SUN":
                this.coordinates = new Coordinates(coordinates.getLongitude() + 2,
                    coordinates.getLatitude(), coordinates.getHeight() + 4);
                break;
            case "FOG":
                this.coordinates = new Coordinates(coordinates.getLongitude(),
                    coordinates.getLatitude(), coordinates.getHeight() - 3);
                break;
            case "SNOW":
                this.coordinates = new Coordinates(coordinates.getLongitude(),
                    coordinates.getLatitude(), coordinates.getHeight() - 15);
                break;
            case "RAIN":
                this.coordinates = new Coordinates(coordinates.getLongitude(),
                    coordinates.getLatitude(), coordinates.getHeight() - 5);
                break;
            default:
                System.out.println("Unexepted error comes on updating the conditions of the ballon.");
        }
        System.out.println("Ballon#" + this.name + "(" + this.id + ") : " + weatherMap.get(currentWeather));
        if (this.coordinates.getHeight() <= 0)
        {
            System.out.println("Ballon#" + this.name + "(" + this.id +
            ") landing.");
            weatherTower.unregister(this);
            System.out.println("Tower says: Baloon#" + this.name + "(" + this.id + ") unregistered to weather tower.");
        }
    }
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        System.out.println("Tower says: Baloon#" + this.name + "(" + this.id + ") registered to weather tower.");
    }
}