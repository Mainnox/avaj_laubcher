package avaj.tower;

import avaj.flyable.Flyable;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable fly) {
        try {
            observers.add(fly);
        } catch (Exception e) {
            System.out.println("Failed to register this Aircraft");
        }
    }

    public void unregister(Flyable fly) {
        try {
            observers.remove(fly);
        } catch (Exception e) {
            System.out.println("Failed to unregister this Aircraft");
        }
    }

    public List<Flyable> getobservers() {
        return (this.observers);
    }

    protected void conditionsChanged() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updateCondition();
        }
    }
}
