package pixel.bus.model;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by vanley on 21/05/2017.
 */
public class Station extends Tile {
    private String imageLocation = "/res/img/city1.png";

    private String name = "";
    private int nextPassengersIn = 0;
    private int nextPassengersAmount = 0;
    private int stationSize = 0;


    private static List<Station> stations = new ArrayList<>();

    private Queue<Passenger> passengerQueue = new LinkedList<>();

    public Station (int x, int y) {
        super(x, y);
        this.setImage(imageLocation);

        stations.add(this);
        RoadConnection.connect(stations);
    }

    public static List<Station> getStations() {
        return stations;
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

    private void checkPassengers() {
        Iterator<Passenger> iterator = passengerQueue.iterator();
        for (Iterator<Passenger> it = passengerQueue.iterator(); it.hasNext(); ) {
            Passenger aValue = it.next();
            if(!aValue.isWaiting()) {
                it.remove();
            }
        }
    }

    private void scheduleNextPassengers(){
        int min = 0;
        int max = 100;
        int integerFromRange = Utilities.getRandomIntegerFromRange(min, max);
        int integerFromRange2 = Utilities.getRandomIntegerFromRange(min, max);
        nextPassengersIn = (integerFromRange * integerFromRange2)/100 - stationSize;
        nextPassengersAmount = integerFromRange * integerFromRange2/1000 + stationSize;
    }

    public static void queuePassengers(){
        for(Station station : stations){
            station.checkPassengers();
            if (station.nextPassengersIn == 0){
                station.addPassengerGroup(station.nextPassengersAmount);
                station.scheduleNextPassengers();
            } else {
                station.nextPassengersIn--;
            }
        }
    }

    @Override
    public void animate(Graphics g) {
        g.setColor(new Color(200, 0, 0));
        g.drawString(" "+ this.getPassengerQueue().size(), getX(), getY()+getTileSize() * 4/3);
    }

}
