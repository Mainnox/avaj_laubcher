package avaj.aircraft;

import avaj.coordinates.Coordinates;
import avaj.flyable.Baloon;
import avaj.flyable.JetPlane;
import avaj.flyable.Helicopter;
import avaj.flyable.Flyable;

public class AircraftFactory {
    private AircraftFactory() {

    }

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        
        switch (type) {
            case "Baloon":
                return (new Baloon(name, coordinates));
            case "Helicopter":
                return (new Helicopter(name, coordinates));
            case "JetPlane":
                return (new JetPlane(name, coordinates));
            default :
                break;
        }
        return (null);
    }
}