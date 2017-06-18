package pixel.bus.model;

import pixel.bus.model.enu.VehicleEnum;

/**
 * Created by vanley on 21/05/2017.
 */
public class Vehicle {
    private String name;
    private int capacityCurrent = 0;
    private int capacityEstimation = 0;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacityCurrent() {
        return capacityCurrent;
    }

    public void setCapacityCurrent(int capacityCurrent) {
        this.capacityCurrent = capacityCurrent;
    }

    public VehicleEnum getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSpeed() {
        return speed;
    }

    public int getRunningCost() {
        return runningCost;
    }

    public int getStandingCost() {
        return standingCost;
    }

    public int getCapacityEstimation() {
        return capacityEstimation;
    }

    public void setCapacityEstimation(int capacityEstimation) {
        this.capacityEstimation = capacityEstimation;
    }
}
