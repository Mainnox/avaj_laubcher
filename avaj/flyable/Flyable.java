package avaj.flyable;

import avaj.tower.WeatherTower;

public interface Flyable {
    public void updateCondition();
    public void registerTower(WeatherTower weatherTower);
    }