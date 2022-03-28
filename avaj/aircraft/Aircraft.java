package avaj.aircraft;

import avaj.coordinates.Coordinates;

public class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 1;

    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.id = nextid();
    }
    private static long nextid() {
        return (Aircraft.idCounter++);
    }
}
