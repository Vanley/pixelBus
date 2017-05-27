package pixel.bus.model;

/**
 * Created by vanley on 21/05/2017.
 */
public class Vehicle {
    private String name;
    private int capacityCurrent = 0;

    final private VehicleEnum type;
    final private int capacity;
    final private int speed;
    final private int runningCost;
    final private int standingCost;


    protected Vehicle(VehicleEnum type) {
        this.type = type;

        this.capacity = type.getCapacity();
        this.speed = type.getSpeed();
        this.runningCost = type.getRunningCost();
        this.standingCost = type.getStandingCost();

        this.name = type.getName();
    }

}