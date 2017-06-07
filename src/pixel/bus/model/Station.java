package pixel.bus.model;

import java.util.*;

/**
 * Created by vanley on 21/05/2017.
 */
public class Station {
    private String name;
    private int nextPassengersIn = 0;
    private int nextPassengersAmount = 0;
    private int stationSize = 0;

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

    public void addPassengerGroup(int amount){
        while (amount > 0){
            addPassenger();
            amount--;
        }
    }

    public static void queuePassengers(){
        for(Station station : stations){
            //todo is still waiting
            if (station.nextPassengersIn == 0){
                station.addPassengerGroup(station.nextPassengersAmount);
                station.scheduleNextPassengers();
            } else {
                station.nextPassengersIn--;
            }
        }
    }

    private void scheduleNextPassengers(){
        int min = 0;
        int max = 100;
        int integerFromRange = getRandomIntegerFromRange(min, max);
        int integerFromRange2 = getRandomIntegerFromRange(min, max);
        nextPassengersIn = (integerFromRange * integerFromRange2)/100 - stationSize;
        nextPassengersAmount = integerFromRange * integerFromRange2/1000 + stationSize;
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

}
