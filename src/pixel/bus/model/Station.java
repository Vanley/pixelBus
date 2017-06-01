package pixel.bus.model;

import java.util.*;

/**
 * Created by vanley on 21/05/2017.
 */
public class Station {
    private String name;
    private int rangeMin = 0;
    private int rangeMax = 7;

    private static List<Station> stations = new ArrayList<>();

    private Queue<Passenger> passengerQueue = new LinkedList<>();

    public Station () {
        stations.add(this);
    }

    public Queue<Passenger> getPassengerQueue() {
        return passengerQueue;
    }

    public void addPassenger(){
        passengerQueue.add(new Passenger());
    }

    public static void queuePassengers(){
        for(Station station : stations){
            //todo is still waiting
            int count = getRandomIntegerFromRange(station.rangeMin, station.rangeMax);
            while (count > 0){
                station.passengerQueue.add(new Passenger());
                count--;
            }
        }
    }

    private static int getRandomIntegerFromRange(int min, int max){
        Random random = new Random();
        if (min > max) {
            throw new IllegalArgumentException("minimum cannot exceed maximum.");
        }
        long range = (long)max - (long)min + 1;
        long fraction = (long)(range * random.nextDouble());
        return (int)(fraction + min);

    }

    public int getRangeMax() {
        return rangeMax;
    }

    public void setRangeMax(int rangeMax) {
        this.rangeMax = rangeMax;
    }
}
