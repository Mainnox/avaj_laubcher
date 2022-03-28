package avaj.flyable;

import java.util.HashMap;
import java.util.Map;

import avaj.aircraft.Aircraft;
import avaj.coordinates.Coordinates;
import avaj.tower.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }
    public void updateCondition() {
        String currentWeather = weatherTower.getWeather(coordinates);
        Map<String, String> weatherMap = new HashMap<String, String>();

        weatherMap.put("SUN", "Like Romanian said: \"Numa Numa Yei\"");
        weatherMap.put("FOG", "This fog remains me the red pyramid thing...");
        weatherMap.put("SNOW", "It's seems Elsa is on fire today!");
        weatherMap.put("RAIN", "J'aime le bruit blanc de l'eau sur mes rotors!");
        switch (currentWeather) {
            case "SUN":
                this.coordinates = new Coordinates(coordinates.getLongitude(),
                    coordinates.getLatitude() + 10, coordinates.getHeight() + 2);
                break;
            case "FOG":
                this.coordinates = new Coordinates(coordinates.getLongitude(),
                    coordinates.getLatitude() + 1, coordinates.getHeight());
                break;
            case "SNOW":
                this.coordinates = new Coordinates(coordinates.getLongitude(),
                    coordinates.getLatitude(), coordinates.getHeight() - 7);
                break;
            case "RAIN":
                this.coordinates = new Coordinates(coordinates.getLongitude(),
                    coordinates.getLatitude() + 5, coordinates.getHeight());
                break;
            default:
                System.out.println("Unexepted error comes on updating the conditions of the ballon.");
        }
        System.out.println("JetPlane#" + this.name + "(" + this.id + ") : " + weatherMap.get(currentWeather));
        if (this.coordinates.getHeight() <= 0)
        {
            System.out.println("JetPlane#" + this.name + "(" + this.id +
            ") landing.");
            weatherTower.unregister(this);
            System.out.println("Tower says: JetPlane#" + this.name + "(" + this.id + ") unregistered to weather tower.");
        }
    }
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        System.out.println("Tower says: JetPlane#" + this.name + "(" + this.id + ") registered to weather tower.");
    }
}
