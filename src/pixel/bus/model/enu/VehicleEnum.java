package pixel.bus.model.enu;

/**
 * Created by vanley on 21/05/2017.
 */
public enum VehicleEnum {
    MOTORBIKE("Motorbike", 60, 1, 20, 1, 0),
    CAR("Car", 50, 3, 100, 2, 1),
    VAN("Van", 40, 5, 300, 3, 1),
    BUS("Bus", 25, 15, 500, 5, 2),
    LONG_BUS("Big Bus", 20, 25, 1000, 7, 2),
    TRAM("Tram", 10, 50, 3000, 4, 1),
    UNDERGROUND("Underground", 20, 100, 10000, 20, 20);

    final private String name;
    final private int speed;
    final private int capacity;
    final private int initialPrice;

    final private int runningCost;
    final private int standingCost;

    VehicleEnum(String name,
                int speed,
                int capacity,
                int initialPrice,
                int runningCost,
                int standingCost) {

        this.name = name;
        this.speed = speed;
        this.capacity = capacity;
        this.initialPrice = initialPrice;

        this.runningCost = runningCost;
        this.standingCost = standingCost;
    }


    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getInitialPrice() {
        return initialPrice;
    }

    public int getRunningCost() {
        return runningCost;
    }

    public int getStandingCost() {
        return standingCost;
    }

}
